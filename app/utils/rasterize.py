# encoding=utf-8
__author__ = 'Zephyr369'

# from pyspark.sql import SparkSession
# from pyspark.sql.functions import col

# 将坐标栅格化为整数
def rasterize(df):
    # 保存原来未栅格的坐标
    df['X'] = df['CurX']
    df['Y'] = df['CurY']
    df['CurX'] = df['CurX'].astype(int)
    df['CurY'] = df['CurY'].astype(int)
    return df


# def rasterize_pyspark(df):
#     # 更新或添加新列以保存原来未栅格化的坐标
#     df = df.withColumn("X", col("CurX"))
#     df = df.withColumn("Y", col("CurY"))
#     # 将CurX和CurY列的数据类型转换为整数
#     df = df.withColumn("CurX", col("CurX").cast("int"))
#     df = df.withColumn("CurY", col("CurY").cast("int"))
#     return df

# 分布式

spark = SparkSession.builder.appName("RasterizeExample").getOrCreate()
# 使用rasterize_pyspark函数处理DataFrame
# processed_df = rasterize_pyspark(original_df)