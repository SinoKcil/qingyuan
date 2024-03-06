# encoding=utf-8
__author__ = 'Zephyr369'

# 将坐标栅格化为整数
def rasterize(df):
    df['CurX'] = df['CurX'].astype(int)
    df['CurY'] = df['CurY'].astype(int)
    return df
