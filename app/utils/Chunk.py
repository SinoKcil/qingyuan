# encoding=utf-8
__author__ = 'Zephyr369'

import glob
import logging

import numpy as np
import pandas as pd
import os

from Logger import Logger
from app.APIconfig import API_config
from app.utils.generate_pitch_and_pool import generate_pitch_and_rool
from app.utils.predict import predict
from app.utils.rasterize import rasterize
from run import app

# 封装Chunk类，对不同仓库进行管理
#  eg : chunk_name = Shanghai
# 导入日志类
logger = Logger(name="MyAppLogger", level=logging.DEBUG, log_file="logs/my_app.log").get_logger()


class Chunk():
    def __init__(self, chunk_name='Default', chunk_path=app.config['CHUNK_PATH']):
        self.chunk_name = chunk_name
        self.chunk_path = chunk_path
        self.file_number = 0
        self.file_list = []

    # 对数据进行预处理 生成Roll&Pitch
    def check_and_create_file_path(self):
        chunk_reso = self.chunk_path + self.chunk_name + '\\reso\\'  # 预处理好的文件
        if not os.path.exists(chunk_reso):
            os.makedirs(chunk_reso)
            logger.info(f"Chunk:{self.chunk_name}的reso路径已经创建在: {chunk_reso}下！")
        else:
            print(f'{chunk_reso}目录已经存在')
        return chunk_reso

    def process_data(self, csv_path):
        logger.info(f'Received data from filePath: {csv_path}, start to preprocess')
        df = pd.read_csv(csv_path)
        file_default_time = '2024-04-11 12:58:59'
        # 替换空格和冒号为_
        file_time = str(df.loc[0, 'time']).replace(' ', '_').replace(':', '_') if not pd.isnull(
            df.loc[0, 'time']) else file_default_time
        file_name = f"{self.chunk_name}_{file_time}.csv"
        generated_df = generate_pitch_and_rool(df)
        csv_save_path = self.check_and_create_file_path() + file_name
        if not generated_df.empty:
            generated_df.to_csv(csv_save_path, index=False)
            self.file_list.append(csv_save_path)
            logger.info(f'File saved: {csv_save_path}')
            print(self.file_list)
        else:
            logger.error("Generated DataFrame is empty. No file saved.")
        return csv_save_path

    def analyze_data(self, file_path):
        # csv_save_path = self.check_and_create_file_path()
        # csv_files = glob.glob(f'{csv_save_path}*.csv')
        # for file in csv_files:
        #     df = pd.read_csv(file)
        original_df = pd.read_csv(file_path)
        rasterize_df = rasterize(original_df)
        logger.info(f'数据栅格化完成:{str(rasterize_df.head(10))}')
        predicted_df = predict(rasterize_df)

        save_path = self.chunk_path + 'reso\\test.csv'
        predicted_df.to_csv()

        return 'success'