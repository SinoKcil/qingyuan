# encoding=utf-8
__author__ = 'Zephyr369'

import logging
from datetime import datetime

from app.Models.Base import db
from app.Models.Region import Abnormal
from Logger import Logger
logger = Logger(name="MyAppLogger", level=logging.DEBUG, log_file="logs/my_app.log").get_logger()
# 将结果添加到数据库中
def insert_dataframe_to_sql(df):
    try:
        for index, row in df.iterrows():
            # time = datetime.strptime(row['time'], "%Y-%m-%d %H:%M:%S")
            time = row['time'].to_pydatetime()
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
        db.session.commit()
        return "success"
    except Exception as e:
        logger.error(e)
        return "error"



