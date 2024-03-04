# encoding=utf-8
__author__ = 'Zephyr369'

import os
from datetime import time, datetime
from random import random

from flask import jsonify, request

from app.API import web
from app.functions.data_processor import process_data
from run import app
import pandas as pd
# 从csv中读取原始数据
# 首先做文件类型检验
def allowed_file(filename):

    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in app.config['ALLOWED_EXTENSIONS']

@web.route('/LoadDataByCsv', methods=['POST'])
def load_data_by_csv():
    if request.method == 'POST':
        # 检查是否有文件在请求中
        if 'file' not in request.files:
            # 返回传输文件为空的错误代码
            return jsonify({'code': 400001, 'message': 'No file part'}), 400

        file = request.files['file']
        # 如果用户没有选择文件，浏览器也会提交一个空的文件名
        if file.filename == '':
            # 返回传输文件为空的错误代码
            return jsonify({'code': 400001, 'message': 'No selected file'}), 400

        if file and allowed_file(file.filename):
            try:
                os.makedirs(app.config['UPLOAD_FOLDER'], exist_ok=True)
                # 使用微秒级时间戳和随机数生成唯一文件名
                timestamp = datetime.now().strftime('%Y%m%d%H%M%S%f')
                # random_number = random.randint(100, 999)
                file_extension = file.filename.rsplit('.', 1)[1].lower()  # 获取文件扩展名
                filename = f"{timestamp}_csvfile.{file_extension}"
                filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
                file.save(filepath)
                process_data(filepath)
                return jsonify(
                    {'code': 200000, 'message': 'File successfully uploaded and processed', 'filename': filename}), 200
            except Exception as e:
                return jsonify({'code': 500001, 'message': 'Server failed to read the file'}), 500
        else:
            return jsonify({'code': 400002, 'message': 'File format not allowed'}), 400

    # 对于非 POST 请求，返回不支持的方法错误
    return jsonify({'code': 405, 'message': 'Method Not Allowed'}), 405