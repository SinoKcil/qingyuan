# encoding=utf-8
__author__ = 'Zephyr369'

from flask import request, jsonify

from app.API import web
from app.Models.Base import db
from app.Models.Region import Region
from app.utils.Chunk import logger


# 添加区域 视图函数
@web.route('/addChunks', methods=['POST'])
def add_chunks():
    if request.is_json:
        data = request.get_json()
        chunk_name = data.get('chunkName')
        chunks = db.session.query(Region).filter_by(region_name=chunk_name).first()
        if chunks:
            return jsonify({"code": 400000, "message": f"Error! Chunk {chunk_name} already exists"}), 400
        else:
            new_region = Region(region_name=chunk_name)
            db.session.add(new_region)
            db.session.commit()
            logger.critical(f"Successfully added chunk {chunk_name}")
            return jsonify({"code": 200000, "message": f"Successfully added chunk {chunk_name}"}), 200
    else:
        return jsonify({"code": 4000001, "message": "request must be json!"}),400
