# encoding=utf-8
__author__ = 'Zephyr369'
import joblib
import numpy as np
import os

import pandas as pd
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import Dataset, DataLoader
import torch.nn.functional as F
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler


class FeatureDataset(Dataset):
    def __init__(self, features, labels):
        self.features = features
        self.labels = labels

    def __len__(self):
        return len(self.features)

    def __getitem__(self, idx):
        return self.features[idx], self.labels[idx]


class CNNModel(nn.Module):
    def __init__(self, num_features):
        super(CNNModel, self).__init__()
        self.conv1 = nn.Conv1d(1, 16, kernel_size=3, stride=1, padding=1)
        self.conv2 = nn.Conv1d(16, 32, kernel_size=3, stride=1, padding=1)
        self.fc1 = nn.Linear(32 * (num_features // 4), 120)
        self.fc2 = nn.Linear(120, 2)

    def forward(self, x):
        x = x.unsqueeze(1)  # Add channel dimension
        x = F.max_pool1d(F.relu(self.conv1(x)), 2)
        x = F.max_pool1d(F.relu(self.conv2(x)), 2)
        x = torch.flatten(x, 1)
        x = F.relu(self.fc1(x))
        x = self.fc2(x)
        return x


def prepare_datasets(csv_file, test_size=0.2):
    df = pd.read_csv(csv_file)
    print("参与训练的DataFrame前五行:\n", df.iloc[1000:1010])

    features = df.iloc[:, 1:-1].values
    labels = df.iloc[:, -1].values

    # 特征标准化
    scaler = StandardScaler()
    features = scaler.fit_transform(features)
    # 假设scaler是您已经拟合好的StandardScaler实例
    scaler = StandardScaler().fit(features)

    # 保存scaler到文件
    joblib.dump(scaler, 'scaler.pkl')

    # 分割数据集为训练集和测试集
    X_train, X_test, y_train, y_test = train_test_split(features, labels, test_size=test_size, random_state=42)

    train_dataset = FeatureDataset(torch.tensor(X_train, dtype=torch.float), torch.tensor(y_train, dtype=torch.long))
    test_dataset = FeatureDataset(torch.tensor(X_test, dtype=torch.float), torch.tensor(y_test, dtype=torch.long))

    return train_dataset, test_dataset


def train_cnn(csv_file, num_epochs=25, device=torch.device("cuda" if torch.cuda.is_available() else "cpu")):
    train_dataset, test_dataset = prepare_datasets(csv_file, test_size=0.2)
    train_loader = DataLoader(train_dataset, batch_size=64, shuffle=True)
    test_loader = DataLoader(test_dataset, batch_size=64, shuffle=False)

    model = CNNModel(num_features=train_dataset.features.shape[1]).to(device)
    criterion = nn.CrossEntropyLoss()
    optimizer = optim.Adam(model.parameters(), lr=0.001)

    for epoch in range(num_epochs):
        # 训练阶段
        model.train()
        train_loss = 0.0
        for features, labels in train_loader:
            features, labels = features.to(device), labels.to(device)
            optimizer.zero_grad()
            outputs = model(features)
            loss = criterion(outputs, labels)
            loss.backward()
            optimizer.step()
            train_loss += loss.item()

        # 评估阶段
        model.eval()
        test_loss = 0.0
        with torch.no_grad():
            for features, labels in test_loader:
                features, labels = features.to(device), labels.to(device)
                outputs = model(features)
                loss = criterion(outputs, labels)
                test_loss += loss.item()

        print(
            f'Epoch {epoch + 1}/{num_epochs}, Train Loss: {train_loss / len(train_loader)}, Test Loss: {test_loss / len(test_loader)}')

        # 保存检查点
        if epoch % 5 == 0:
            # 创建一个文件夹来保存检查点
            if not os.path.exists('models/checkpoint'):
                os.makedirs('models/checkpoint')

            # 循环保存当前和前五个 epoch 的模型
            for i in range(epoch, max(0, epoch - 5), -1):
                torch.save(model.state_dict(), f'models/checkpoint/cnn_epoch_{i}.pth')

    print('Training completed.')
