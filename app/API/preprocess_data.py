# encoding=utf-8
__author__ = 'Zephyr369'

import os

from app.API import web
from flask import request, jsonify

from app.Models.Base import db
from app.Models.Region import Region
from app.utils.Chunk import Chunk, logger


# 由于是四向穿梭车，九轴IMU的横滚角和俯仰角需要进行转换，故需要对数据进行预处理，
# 接受刚才保存好的文件的路径
@web.route('/preprocessData', methods=['POST', 'GET'])
def preprocess_data():
    if request.is_json:
        data = request.get_json()
        file_path = data.get('filePath', None)
        file_name = data.get('fileName', None)
        chunk_name = data.get('chunkName', None)

        # 向数据库中添加chunk
        existing_chunk = db.session.query(Region).filter_by(region_name=chunk_name).first()
        logger.info(f"query res: {existing_chunk}")
        if not existing_chunk:
            new_region = Region(region_name=chunk_name)
            db.session.add(new_region)
            # db.session.add(chunk_name)
            db.session.commit()
            logger.info(f"chunk{chunk_name} was appended successfully")
        else:
            logger.info(f"chunk{chunk_name} already exists!")

        # 当文件为空时 抛出异常
        if file_path is None:
            return jsonify({'code': 400001, 'message': 'file path is not detected!'}), 400
        else:
            #  返回保存好的数据
            chunk = Chunk(chunk_name)
            processed_data_path = chunk.process_data(file_path)
            try:
                # 判断文件是否存在
                if os.path.exists(file_path):
                    # 删除文件
                    os.remove(file_path)
                    print("remove completed!")
                else:
                    print("file not exists")
            except Exception as e:
                print("删除文件时发生错误:", str(e))
            result_to_return = {'code': 200000,
                                'message': 'data preprocession complete!',
                                'processedDataPath': processed_data_path,
                                'fileName': file_name,
                                'chunkName': chunk.chunk_name}
            return jsonify(result_to_return)


