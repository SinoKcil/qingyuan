# encoding=utf-8
__author__ = 'Zephyr369'

import os

import pandas as pd

# 针对四向穿梭车 生成Roll和Pitch
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

# 使用spark分布式
# from pyspark.sql import functions as F
#
# def generate_pitch_and_rool(spark_df):
#     # 计算 Roll 和 Pitch 的值
#     spark_df = spark_df.withColumn('Pitch', F.when(spark_df['CurY'] == F.lag('CurY', 1).over(Window.partitionBy().orderBy('time')), spark_df['CurPitch']))
#     spark_df = spark_df.withColumn('Roll', F.when(spark_df['CurX'] == F.lag('CurX', 1).over(Window.partitionBy().orderBy('time')), spark_df['CurRoll']))
#     # 删除包含null的行
#     spark_df = spark_df.na.drop()
#     return spark_df