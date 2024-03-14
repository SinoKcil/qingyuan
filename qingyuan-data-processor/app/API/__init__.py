# encoding=utf-8
__author__ = 'Zephyr369'

from flask import Blueprint, jsonify

web = Blueprint("web", __name__)

# @web.app_errorhandler(404)
# def not_fount(e):
#     return jsonify({"result":"page not found"})

from app.API.import_data import load_data_by_csv
from .preprocess_data import preprocess_data
from app.API.analyze_data import analyze_data
from app.API.add_chunks import add_chunks