# encoding=utf-8
__author__ = 'Zephyr369'

from datetime import datetime

import pandas as pd
from flask import current_app

# from app.utils.Chunk import logger


def transform_data(df, region_name):
    results = []
    threshold = current_app.config['THRESHOLD']
    # 按照Layers分组
    for layer, group_df in df.groupby('Layers'):
        # 按照CurX和CurY分组
        for (CurX, CurY), subgroup_df in group_df.groupby(['CurX', 'CurY']):
            # 计算非0行占总行数的比
            total_rows = len(subgroup_df)
            non_zero_count = (subgroup_df['predict_abnormal_labels'] != 0).sum()
            ratio = non_zero_count / total_rows

            if ratio > threshold:
                non_zero_labels = (subgroup_df['predict_abnormal_labels']
                                   .value_counts()
                                   .drop(0, errors='ignore'))
                most_common_label = non_zero_labels.idxmax()
                first_row = subgroup_df.iloc[0]
                results.append({
                    'region_name': region_name,
                    'x': CurX,
                    'y': CurY,
                    'label': most_common_label,
                    'time': datetime.strptime(first_row['time'], '%Y-%m-%d %H:%M'),
                    'Layers': layer,
                    'status': 1
                })

    results_df = pd.DataFrame(results)
    return results_df
