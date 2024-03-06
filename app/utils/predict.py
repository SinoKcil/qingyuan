# encoding=utf-8
__author__ = 'Zephyr369'

import joblib
from tensorflow.python.keras.models import load_model
import numpy as np
from run import app


# 对传入的dataframe进行预测，从而进行故障检测

def predict(df):
    new_data = df[['CurAccZ', 'CurAccX', 'CurAccY', 'Pitch', 'Roll']]
    model_path = app.config['LSTM_MODEL_PATH']
    # 使用相同的标准化器
    scaler_path = app.config['SCALER_PATH']
    model = load_model(model_path)
    scaler = joblib.load(scaler_path)
    user_defined_threshold = app.config['THRESHOLD']

    new_features = scaler.transform(new_data.values)
    new_features = new_features.reshape((new_features.shape[0], 1, new_features.shape[1]))

    predictions = model.predict(new_features)

    # 注意这里，我们将初始化列添加到原始df中，而不是new_data
    df['predict_abnormal_labels'] = 0  # 初始化为0 表示正常

    for i, prediction in enumerate(predictions):
        max_prob = np.max(prediction)  # 获取最大概率
        max_prob_index = np.argmax(prediction)  # 获取最大概率的索引
        # 计算最大概率与其他概率的差值
        diff = max_prob - np.sort(prediction)[-2]  # 与第二大的概率差值

        # 如果差值大于用户定义的阈值，则认为这是一个确定的分类
        if diff > user_defined_threshold:
            df.at[i, 'predict_abnormal_labels'] = max_prob_index

    return df







