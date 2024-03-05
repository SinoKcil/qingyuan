# encoding=utf-8
__author__ = 'Zephyr369'

import os

import numpy as np
import pandas as pd
from sqlalchemy import engine

from app.APIconfig import API_config


# 处理数据
def process_data(csv_path, file_name):
    print(f'Received data from filePath: {csv_path}, start to preprocess')
    csv_save_path = API_config.preprocessed_data__save_file
    df = pd.read_csv(csv_path)
    # 调整好俯仰角和横滚角，生成完备的数据
    generated_df = generate_pitch_and_rool(df)
    # 添加文件名到路径中
    csv_save_directory = os.path.dirname(csv_save_path)  # 获取目录路径

    # 检查目录是否存在，如果不存在，则创建它
    if not os.path.exists(csv_save_directory):
        os.makedirs(csv_save_directory, exist_ok=True)

    csv_save_path += file_name
    generated_df.to_csv(csv_save_path, index = False)
    return csv_save_path



def generate_pitch_and_rool(original_df):
    original_df['Pitch'] = None
    original_df['Roll'] = None

    # 遍历DataFrame中的每一行
    for i in range(len(original_df) - 1):  # 排除最后一行，因为没有下一行与之比较
        # 检查CurY的连续相等性
        if original_df.loc[i, 'CurY'] == original_df.loc[i + 1, 'CurY']:
            original_df.loc[i, 'Roll'] = original_df.loc[i, 'CurRoll']
            original_df.loc[i, 'Pitch'] = original_df.loc[i, 'CurPitch']
        # 检查CurX的连续相等性
        # else:
        elif original_df.loc[i, 'CurX'] == original_df.loc[i + 1, 'CurX']:
            original_df.loc[i, 'Roll'] = original_df.loc[i, 'CurPitch']
            original_df.loc[i, 'Pitch'] = original_df.loc[i, 'CurRoll']

    original_df = original_df.dropna()
    return original_df
