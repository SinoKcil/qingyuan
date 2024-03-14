# encoding=utf-8
__author__ = 'Zephyr369'

from flask import request, jsonify

from app.API import web
from app.utils.Chunk import Chunk


@web.route('/analyzeData', methods=['POST'])
def analyze_data():
    res = {
                "code": 400001,
                "result": "request is not json",
            }
    if request.is_json:
        data = request.get_json()
        file_path = data.get('processedDataPath')
        chunk_name = data.get('chunkName')

        if file_path is None:
            return jsonify({'code': 400001, 'message': 'file path is not detected!'}), 400
        else:
            # 对数据进行分析
            chunk = Chunk(chunk_name)
            result = chunk.analyze_data(file_path)
            res = {
                "code": 200000,
                "result": result,
            }
    return jsonify(res), 200
