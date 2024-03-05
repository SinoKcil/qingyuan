# encoding=utf-8
__author__ = 'Zephyr369'

import numpy as np
import pandas as pd
import os

from app.APIconfig import API_config
from app.functions.processor import  generate_pitch_and_rool
from run import app


# 封装Chunk类，对不同仓库进行管理
#  eg : chunk_name = Shanghai


class Chunk():
    def __init__(self, chunk_name='Default', chunk_path=app.config['CHUNK_PATH']):
        self.chunk_name = chunk_name
        self.chunk_path = chunk_path
    # 对数据进行预处理 生成Roll&Pitch
    def check_and_create_file_path(self):
        chunk_reso = self.chunk_path + self.chunk_name + '\\reso\\'  # 预处理好的文件
        if not os.path.exists(chunk_reso):
            os.makedirs(chunk_reso)
            print(f"Chunk:{self.chunk_name}的reso路径已经创建在: {chunk_reso}下！")
        else:
            print(f'{chunk_reso}目录已经存在')
        return chunk_reso

    def process_data(self, csv_path, file_name):
        print(f'Received data from filePath: {csv_path}, start to preprocess')
        # 保存最初刚刚加载的数据
        # csv_save_path = self.chunk_path + self.chunk_name + '\\temp'
        df = pd.read_csv(csv_path)
        # 调整好俯仰角和横滚角，生成完备的数据
        generated_df = generate_pitch_and_rool(df)
        # # 添加文件名到路径中
        # csv_save_directory = os.path.dirname(csv_save_path)  # 获取目录路径
        #
        # # 检查目录是否存在，如果不存在，则创建它
        # if not os.path.exists(csv_save_directory):
        #     os.makedirs(csv_save_directory, exist_ok=True)
        csv_save_path = self.check_and_create_file_path()
        csv_save_path += file_name
        generated_df.to_csv(csv_save_path, index=False)
        return csv_save_path
