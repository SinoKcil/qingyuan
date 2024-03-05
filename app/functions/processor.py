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