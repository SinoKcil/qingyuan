# encoding=utf-8
__author__ = 'Zephyr369'

from app.API import web
from flask import request, jsonify

from app.functions.data_processor import process_data


# 由于是四向穿梭车，九轴IMU的横滚角和俯仰角需要进行转换，故需要对数据进行预处理，
# 接受刚才保存好的文件的路径
@web.route('/preprocessData', methods=['POST', 'GET'])
def preprocess_data():
    if request.is_json:
        data = request.get_json()
        file_path = data.get('filePath', None)
        file_name = data.get('fileName', None)
        # 当文件为空时 抛出异常
        if file_path is None:
            return jsonify({'code': 400001, 'message': 'file path is not detected!'}), 400
        else:
            #  返回保存好的数据
            processed_data_path = process_data(file_path,file_name)
            result_to_return = {'code': 200000,
                                'message': 'data preprocession complete!',
                                'processedDataPath': processed_data_path}
            return jsonify(result_to_return)

