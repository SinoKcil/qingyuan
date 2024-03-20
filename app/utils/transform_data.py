# encoding=utf-8
__author__ = 'Zephyr369'

from datetime import datetime

import pandas as pd
from flask import current_app

# from app.utils.Chunk import logger


def transform_data(df, region_name, threshold=0.5):
    results = []

    # 按照Layers分组
    for layer, group_df in df.groupby('Layers'):
        # 按照CurX和CurY分组
        for (CurX, CurY), subgroup_df in group_df.groupby(['CurX', 'CurY']):
            # 计算非0行占总行数的比
            total_rows = len(subgroup_df)
            non_zero_count = (subgroup_df['predict_abnormal_labels'] != 0).sum()
            ratio = non_zero_count / total_rows

            if ratio >= threshold:
                # 当非0行占比超过阈值时，选择出现频率最高的非0标签
                non_zero_labels = subgroup_df['predict_abnormal_labels'].value_counts().drop(0, errors='ignore')
                most_common_label = non_zero_labels.idxmax()
                status = 1  # 存在异常，状态设为1
            else:
                # 当所有行或非0行占比不足阈值时，标签设为0，并且状态也设为0（表示正常）
                most_common_label = 0
                status = 0  # 表示正常

            first_row = subgroup_df.iloc[0]
            results.append({
                'region_name': region_name,
                'x': CurX,
                'y': CurY,
                'label': most_common_label,
                'time': datetime.strptime(first_row['time'], '%Y-%m-%d %H:%M'),
                'Layers': layer,
                'status': status
            })

    results_df = pd.DataFrame(results)
    return results_df

# 分布式
# from pyspark.sql import SparkSession
# from pyspark.sql.functions import col, udf, when, count, lit
# from pyspark.sql.types import IntegerType, TimestampType
# from datetime import datetime

# 初始化SparkSession
# spark = SparkSession.builder.appName("DataTransformation").getOrCreate()
#
#
# # 定义UDF来处理逻辑
# def calculate_status_and_label(predict_abnormal_labels, threshold=0.5):
#     total_rows = len(predict_abnormal_labels)
#     non_zero_count = len([label for label in predict_abnormal_labels if label != 0])
#     ratio = non_zero_count / total_rows
#     if ratio >= threshold:
#         # 过滤出非0的标签并计算出现次数
#         non_zero_labels = {}
#         for label in predict_abnormal_labels:
#             if label != 0:
#                 non_zero_labels[label] = non_zero_labels.get(label, 0) + 1
#         most_common_label = max(non_zero_labels, key=non_zero_labels.get)
#         status = 1
#     else:
#         most_common_label = 0
#         status = 0
#     return (most_common_label, status)
#
#
# # 注册UDF
# status_label_udf = udf(calculate_status_and_label, returnType=IntegerType())
#
#
# # 转换数据
# def transform_data_spark(df, region_name, threshold=0.5):
#     # 使用withColumn添加原始时间列的Timestamp类型版本
#     df = df.withColumn("timestamp", (col("time").cast(TimestampType())))
#
#     # 按照Layers, CurX, CurY进行分组并聚合
#     grouped_df = df.groupBy("Layers", "CurX", "CurY").agg(
#         collect_list("predict_abnormal_labels").alias("labels"),
#         first("timestamp").alias("time")
#     )
#
#     # 应用UDF进行状态和标签的计算
#     result_df = grouped_df.withColumn("result", status_label_udf(col("labels")))
#     result_df = result_df.withColumn("most_common_label", col("result").getItem(0))
#     result_df = result_df.withColumn("status", col("result").getItem(1))
#
#     # 选择最终结果的列
#     final_df = result_df.select("Layers", "CurX", "CurY", "most_common_label", "time", "status")
#
#     return final_df