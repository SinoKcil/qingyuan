# encoding=utf-8
__author__ = 'Zephyr369'
import pandas as pd
import numpy as np
from sqlalchemy import create_engine
from config import Config
import numpy as np
import pandas as pd
import torch
import torch.nn as nn
import torch.optim as optim
from sqlalchemy import create_engine
from torch.utils.data import Dataset, DataLoader
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler

from config import LSTMConfig
class DataProcessor:
    def __init__(self, config: Config):
        self.config = config
        self.engine = self.create_engine()
        self.features_df = pd.DataFrame()

    def create_engine(self):
        cfg = self.config.database_config
        user = cfg['user']
        password = cfg['password']
        host = cfg['host']
        database = cfg['database']
        return create_engine(f'mysql+pymysql://{user}:{password}@{host}/{database}')

    def load_data(self):
        table_name = self.config.database_config['table_name']
        df = pd.read_sql_table(table_name, columns=['CurAccZ', 'shock_label'], con=self.engine)
        df = df.dropna()
        return df

    def process_data(self):
        df = self.load_data()
        g = self.config.group_size  # 使用配置中的分组大小
        groups = []
        abnormal_labels = []

        # 对shock_label为1和为0的数据进行分组
        for shock_value in [1, 0]:
            df_shock = df[df['shock_label'] == shock_value]
            group_size = 30 if shock_value == 1 else g
            for start in range(0, len(df_shock), group_size):
                groups.append(df_shock.iloc[start:start+group_size])
                abnormal_labels.append(shock_value)

        # 初始化新的DataFrame来保存统计特征
        columns = ['group_id', 'max', 'min', 'peak_to_peak', 'mean', 'abs_mean', 'rms', 'variance', 'std_dev',
                   'skewness', 'kurtosis', 'peak_factor', 'skewness_indicator', 'kurtosis_indicator', 'impulse_indicator', 'waveform_indicator', 'abnormal']
        features_df = pd.DataFrame(columns=columns)

        for i, group in enumerate(groups):
            cur_acc_z = group['CurAccZ']
            # 最大值
            max_val = cur_acc_z.max()
            # 最小值
            min_val = cur_acc_z.min()
            # 峰峰值
            peak_to_peak = max_val - min_val
            # 均值
            mean_val = cur_acc_z.mean()
            # 绝对平均值
            abs_mean = cur_acc_z.abs().mean()
            # 均方根值
            rms = np.sqrt(np.mean(cur_acc_z ** 2))
            # 方差
            variance = cur_acc_z.var()
            # 标准差
            std_dev = cur_acc_z.std()
            # 翘度
            skewness = cur_acc_z.skew()
            # 偏度
            kurtosis = cur_acc_z.kurt()
            # 峰值因子
            peak_factor = max_val / rms
            # 偏斜度指标
            skewness_indicator = skewness / std_dev
            # 翘度指标
            kurtosis_indicator = kurtosis / (std_dev ** 2)
            # 脉冲指标
            impulse_indicator = max_val / abs_mean
            # 波形指标
            waveform_indicator = rms / abs_mean
            new_row = {
                'group_id': i + 1, 'max': max_val, 'min': min_val, 'peak_to_peak': peak_to_peak, 'mean': mean_val,
                'abs_mean': abs_mean, 'rms': rms, 'variance': variance, 'std_dev': std_dev,
                'skewness': skewness, 'kurtosis': kurtosis, 'peak_factor': peak_factor,
                'skewness_indicator': skewness_indicator,
                'kurtosis_indicator': kurtosis_indicator, 'impulse_indicator': impulse_indicator,
                'waveform_indicator': waveform_indicator,
                'abnormal': abnormal_labels[i]
            }
            # 添加新行到DataFrame
            # features_df = features_df.append({
            #     'group_id': i + 1, 'max': max_val, 'min': min_val, 'peak_to_peak': peak_to_peak, 'mean': mean_val,
            #     'abs_mean': abs_mean, 'rms': rms, 'variance': variance, 'std_dev': std_dev,
            #     'skewness': skewness, 'kurtosis': kurtosis, 'peak_factor': peak_factor, 'skewness_indicator': skewness_indicator,
            #     'kurtosis_indicator': kurtosis_indicator, 'impulse_indicator': impulse_indicator, 'waveform_indicator': waveform_indicator,
            #     'abnormal': abnormal_labels[i]
            # }, ignore_index=True)
            features_df = pd.concat([features_df, pd.DataFrame([new_row])], ignore_index=True)

        self.features_df = features_df

    def save_features(self):
        save_path = f"{self.config.data_directory}/features.csv"
        self.features_df.to_csv(save_path, index=False)





# 从config中获取配置参数 将数据集加载到dataframe中
class DataProcessor:
    def __init__(self, config: LSTMConfig):
        self.config = config
        self.engine = self.create_engine()
        self.features_df = pd.DataFrame()

    def create_engine(self):
        cfg = self.config.database_config
        user = cfg['user']
        password = cfg['password']
        host = cfg['host']
        database = cfg['database']
        return create_engine(f'mysql+pymysql://{user}:{password}@{host}/{database}')

    def load_data(self):
        table_name = self.config.database_config['table_name']
        df = pd.read_sql_table(table_name, columns=self.config.columns, con=self.engine)
        df = df.dropna()
        self.features_df = df
        return df

    def prepare_dataset(self):
        df = self.features_df
        features = df[self.config.columns].values
        labels = df[self.config.label_column].values
        scaler = StandardScaler()
        features = scaler.fit_transform(features)
        X_train, X_test, y_train, y_test = train_test_split(features, labels, test_size=self.config.test_size,
                                                            random_state=42)
        return X_train, X_test, y_train, y_test


# 滑动时间窗口
class TimeSeriesDataset(Dataset):
    def __init__(self, features, labels, config):
        self.features = features.astype(np.float32)  # 确保特征数据为float32类型
        self.labels = labels
        self.time_steps = config.time_steps

    def __len__(self):
        return len(self.features) - self.time_steps

    def __getitem__(self, idx):
        # 将特征和标签转换为torch的FloatTensor
        return torch.tensor(self.features[idx:idx + self.time_steps], dtype=torch.float), torch.tensor(self.labels[idx + self.time_steps], dtype=torch.long)



class LSTMClassifier(nn.Module):
    def __init__(self, config: LSTMConfig):
        super(LSTMClassifier, self).__init__()
        self.config = config
        input_size = len(config.columns)  # 从配置中获取输入大小
        hidden_size = config.hidden_size  # 从配置中获取隐藏层大小
        num_layers = config.num_layers  # 从配置中获取LSTM层数
        num_classes = config.num_classes  # 从配置中获取类别数

        # 定义LSTM层
        self.lstm = nn.LSTM(input_size, hidden_size, num_layers, batch_first=True)
        # 定义全连接层
        self.fc = nn.Linear(hidden_size, num_classes)

    def forward(self, x):
        # LSTM层前向传播
        lstm_out, (h_n, c_n) = self.lstm(x)
        # 选择最后一个时间步的隐藏状态作为全连接层的输入
        out = self.fc(h_n[-1])
        return out


def train_model(train_loader, val_loader, config, class_weights):
    # device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    # # 创建一个模型
    # model = LSTMClassifier(config)
    # model.to(device)
    # criterion = nn.CrossEntropyLoss()
    # optimizer = optim.Adam(model.parameters(), lr=0.001)
    # num_epochs = config.epochs
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model = LSTMClassifier(config)
    model.to(device)

    # 应用类别权重
    criterion = nn.CrossEntropyLoss(weight=class_weights.to(device))

    optimizer = optim.Adam(model.parameters(), lr=config.learning_rate)
    num_epochs = config.epochs


    for epoch in range(num_epochs):
        model.train()
        running_loss = 0.0
        correct = 0
        total = 0
        for data in train_loader:
            inputs, labels = data[0].to(device), data[1].to(device)
            optimizer.zero_grad()
            outputs = model(inputs)
            loss = criterion(outputs, labels)
            loss.backward()
            optimizer.step()
            running_loss += loss.item() * inputs.size(0)
            _, predicted = torch.max(outputs.data, 1)
            total += labels.size(0)
            correct += (predicted == labels).sum().item()
        epoch_loss = running_loss / total
        epoch_acc = correct / total
        print(f'Epoch {epoch + 1}/{num_epochs}, Train Loss: {epoch_loss:.4f}, Train Accuracy: {epoch_acc:.4f}')
        val_loss, val_acc = evaluate_model(model, val_loader, device, criterion)
        print(f'Epoch {epoch + 1}/{num_epochs}, Validation Loss: {val_loss:.4f}, Validation Accuracy: {val_acc:.4f}')

    # 保存模型
    torch.save(model.state_dict(), 'qigui_lstm.pth')


# 评估模型
def evaluate_model(model, data_loader, device, criterion):
    model.eval()
    running_loss = 0.0
    correct = 0
    total = 0
    with torch.no_grad():
        for data in data_loader:
            inputs, labels = data[0].to(device), data[1].to(device)
            outputs = model(inputs)
            loss = criterion(outputs, labels)
            running_loss += loss.item() * inputs.size(0)
            _, predicted = torch.max(outputs.data, 1)
            total += labels.size(0)
            correct += (predicted == labels).sum().item()
    loss = running_loss / total
    accuracy = correct / total
    return loss, accuracy

def train_lstm_model():
    config = LSTMConfig()
    processor = DataProcessor(config)
    processor.load_data()
    X_train, X_test, y_train, y_test = processor.prepare_dataset()
    # 计算类别权重
    class_counts = [np.sum(y_train == i) for i in range(config.num_classes)]
    class_weights = torch.tensor([1.0 / count for count in class_counts], dtype=torch.float)

    train_dataset = TimeSeriesDataset(X_train, y_train, config)
    train_loader = DataLoader(train_dataset, batch_size=config.batch_size, shuffle=True)

    val_dataset = TimeSeriesDataset(X_test, y_test, config)
    val_loader = DataLoader(val_dataset, batch_size=config.batch_size)

    train_model(train_loader, val_loader, config, class_weights)
