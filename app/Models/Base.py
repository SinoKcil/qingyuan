# encoding=utf-8
__author__ = 'Zephyr369'

from flask_sqlalchemy import SQLAlchemy as _SQLAlchemy, BaseQuery
from sqlalchemy import Column, Integer, SmallInteger
from contextlib import contextmanager
from datetime import datetime


class SQLAlchemy(_SQLAlchemy):
    @contextmanager
    def auto_commit(self):
        try:
            yield
            self.session.commit()
        except Exception as e:
            self.session.rollback()
            raise e

db = SQLAlchemy()

# 基类 用作继承
class Base(db.model):
    __abstract__ = True  # 不创建数据表
