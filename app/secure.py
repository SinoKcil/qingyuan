# encoding=utf-8
__author__ = 'Zephyr369'

SQLALCHEMY_DATABASE_URI = 'mysql+pymysql://root:hhhhh@127.0.0.1/software1'
SECRET_KEY = 'ssssseeeeeffffff/sdfsadf^&*KKKKKL*(*(*))'
# 传输给API的文件临时存储位置
UPLOAD_FOLDER = 'data\\temp'
# 允许传输的文件类型
ALLOWED_EXTENSIONS = {'csv'}
# 数据存储目录
CHUNK_PATH = '.\\chunk\\'
# LSTM的检测模型
# LSTM_MODEL_PATH = '.\\app\\MLmodels\\lstm_shock_label_model.h5'