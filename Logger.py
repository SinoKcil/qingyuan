# encoding=utf-8
__author__ = 'Zephyr369'

# 日志类 便于对程序运行情况进行追踪
import logging
from logging.handlers import RotatingFileHandler
import os


# 使用示例
# ------------------------------------------------------------------------------------------------
# # 创建Logger实例
# logger = Logger(name="MyAppLogger", level=logging.DEBUG, log_file="logs/my_app.log").get_logger()
#
# # 记录不同级别的日志
# logger.debug("This is a debug message")
# logger.info("This is an info message")
# logger.warning("This is a warning message")
# logger.error("This is an error message")
# logger.critical("This is a critical message")
# ------------------------------------------------------------------------------------------------
class Logger:
    def __init__(self, name=__name__, level=logging.INFO, log_file="app.log"):
        self.logger = logging.getLogger(name)
        self.logger.setLevel(level)

        # 创建日志目录
        log_dir = os.path.dirname(log_file)
        if not os.path.exists(log_dir):
            os.makedirs(log_dir)

        # 创建一个handler，用于写入日志文件
        file_handler = RotatingFileHandler(log_file, maxBytes=1024 * 1024 * 5, backupCount=5)
        file_formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        file_handler.setFormatter(file_formatter)
        self.logger.addHandler(file_handler)

        # 创建一个handler，用于将日志输出到控制台
        console_handler = logging.StreamHandler()
        console_formatter = logging.Formatter('%(name)s - %(levelname)s - %(message)s')
        console_handler.setFormatter(console_formatter)
        self.logger.addHandler(console_handler)

    def get_logger(self):
        return self.logger
