# encoding=utf-8
__author__ = 'Zephyr369'

from flask import request, jsonify

from app.API import web
from app.functions.Chunk import Chunk


@web.route('/analyzeData', methods=['POST'])
def analyze_data():
    if request.is_json:
        data = request.get_json()
        file_path = data.get('processedDataPath')
        chunk_name = data.get('chunkName')
        if file_path is None:
            return jsonify({'code': 400001, 'message': 'file path is not detected!'}), 400
        else:
            # 对数据进行分析
            chunk = Chunk(chunk_name)
            chunk.analyze_data(file_path)
