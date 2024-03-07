# encoding=utf-8
__author__ = 'Zephyr369'

from datetime import datetime

from app.Models.Base import db
from app.Models.Region import Abnormal

# 将结果添加到数据库中
def insert_dataframe_to_sql(df):
    for index, row in df.iterrows():
        time = datetime.strptime(row['time'], "%Y-%m-%d %H:%M")

        abnormal = Abnormal(
            region_name=row['region_name'],
            x=int(row['x']),
            y=int(row['y']),
            label=int(row['label']),
            time=time,
            status=int(row['status']),
            Layers=int(row['Layers'])
        )
        db.session.add(abnormal)
    df.session.commit()

