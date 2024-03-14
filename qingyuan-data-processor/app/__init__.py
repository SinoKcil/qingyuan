# encoding=utf-8
__author__ = 'Zephyr369'

from flask import Flask

from app.Models.Base import db
from app.Models.Region import Region  # 确保导入了Region模型


def register_blueprint(app):
    from app.API import web
    app.register_blueprint(web)


def insert_default_regions():
    # 检查Region表是否为空
    if not Region.query.first():
        default_regions = ['Region1', 'Region2', 'Region3']
        for region_name in default_regions:
            db.session.add(Region(region_name=region_name))
        db.session.commit()


def create_app():
    # app = Flask(__name__)
    #
    # app.config.from_object('app.setting')
    # app.config.from_object('app.secure')
    #
    # register_blueprint(app)
    #
    # db.init_app(app)
    # db.create_all()

    app = Flask(__name__)

    app.config.from_object('app.setting')
    app.config.from_object('app.secure')
    register_blueprint(app)
    db.init_app(app)
    # db.app = app
    db.create_all(app=app)

    return app
