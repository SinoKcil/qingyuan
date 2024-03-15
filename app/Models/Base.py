# encoding=utf-8
__author__ = 'Zephyr369'

from flask_sqlalchemy import SQLAlchemy as _SQLAlchemy
from sqlalchemy import Column, Integer, SmallInteger
from contextlib import contextmanager
from datetime import datetime


class SQLAlchemy(_SQLAlchemy):

    # 利用上下文 实现提交失败的时候回滚 减少代码冗余
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
class Base(db.Model):
    __abstract__ = True  # 不创建数据表
