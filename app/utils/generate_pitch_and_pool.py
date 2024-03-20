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
# from pyspark.sql import SparkSession
# from pyspark.sql.window import Window
# from pyspark.sql.functions import col, lead, when

# 初始化SparkSession
# spark = SparkSession.builder.appName("GeneratePitchAndRoll").getOrCreate()
#
# # 定义窗口规格，按照原始顺序遍历每一行
# windowSpec = Window.orderBy("some_order_column")
#
# # 使用窗口函数计算相邻行的值
# original_df = original_df.withColumn("next_CurX", lead("CurX", 1).over(windowSpec))
# original_df = original_df.withColumn("next_CurY", lead("CurY", 1).over(windowSpec))
#
# # 根据CurX和CurY的相等性计算Pitch和Roll
# original_df = original_df.withColumn("Pitch",
#                                      when(col("CurY") == col("next_CurY"), col("CurPitch"))
#                                      .otherwise(when(col("CurX") == col("next_CurX"), col("CurRoll")))
#                                     )
# original_df = original_df.withColumn("Roll",
#                                      when(col("CurY") == col("next_CurY"), col("CurRoll"))
#                                      .otherwise(when(col("CurX") == col("next_CurX"), col("CurPitch")))
#                                     )
#
# # 移除空值
# original_df = original_df.filter(col("Pitch").isNotNull() & col("Roll").isNotNull())
# original_df.show()