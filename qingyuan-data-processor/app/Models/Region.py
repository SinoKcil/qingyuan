# encoding=utf-8
__author__ = 'Zephyr369'

from sqlalchemy import Column, String, ForeignKey, Integer, DateTime
from sqlalchemy.orm import relationship

from app.Models.Base import db, Base


# Chunk 该表用于存储目前都有哪些区域

class Region(Base):
    __tablename__ = 'regions'
    region_name = Column(String(64), primary_key=True, nullable=False)
    abnormalities = relationship('Abnormal', back_populates='region')


# 外键是Region 一个Region会发生多次故障
class Abnormal(Base):
    __tablename__ = 'abnormalities'
    # 发生故障的id 用来标识并且计数
    id = Column(Integer, primary_key=True, autoincrement=True)
    region_name = Column(String(64), ForeignKey('regions.region_name'), nullable=False)
    # 发生的坐标(x, y)
    x = Column(Integer, nullable=False)
    y = Column(Integer, nullable=False)
    # 发生的故障标签
    # 0 正常
    # 1 左右轨道高度不一
    # 2 轨道出现较大落差
    # 3 轨道不平顺
    # 4 穿梭车因为打滑 出现骑轨
    label = Column(Integer, nullable=False)
    # 时间1
    # year = Column(Integer, nullable=False)
    # month = Column(Integer, nullable=False)
    # day = Column(Integer, nullable=False)
    # hour = Column(Integer, nullable=False)
    # minute = Column(Integer, nullable=False)
    time = Column(DateTime, nullable=False)
    # 故障的状态 由springboot进行维护 默认是0
    # 0 表示正常
    # 1 表示出现故障 但是没有被检测员上报
    # 2 表示出现故障，已经上报维护申请，但是未被审批
    # 3 表示出现故障，已经上报维护申请，且已经被审批
    # 4 表示出现故障，已经上报维护申请，且已经排除故障，且模型故障报告正确
    # 5 表示出现故障，已经上报维护申请，且已经排除故障，但模型故障报告错误
    status = Column(Integer, default=0)
    region = relationship("Region", back_populates="abnormalities")
    Layers = Column(Integer, nullable=False)
