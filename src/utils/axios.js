// 引入axios
import axios from 'axios';
import {ElMessage} from "element-plus";

// 创建axios实例
const httpService = axios.create({
  // url前缀-'http:xxx.xxx'
  // baseURL: process.env.BASE_API, // 需自定义
  baseURL:'http://localhost:8888/',
  // 请求超时时间
  timeout: 3000,
  headers:{
    
  }
});

//添加请求和响应拦截器
// 添加请求拦截器
httpService.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  config.headers.token=window.sessionStorage.getItem('token');
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});

// 添加响应拦截器
httpService.interceptors.response.use( response=> {
  // 对响应数据做点什么

      return response;
},
    function (error) {
  // 对响应错误做点什么
  return Promise.reject(error);
});

/*网络请求部分*/

/*
 *  get请求
 *  url:请求地址
 *  params:参数
 * */
export function get(url, params = {}) {
  return new Promise((resolve, reject) => {
    httpService({
      url: url,
      method: 'get',
      params: params
    }).then(response => {
      resolve(response);
    }).catch(error => {
      reject(error);
    });
  });
}

/*
 *  post请求
 *  url:请求地址
 *  params:参数
 * */
export function post(url, params = {}) {
  return new Promise((resolve, reject) => {
    httpService({
      url: url,
      method: 'post',
      data: params
    }).then(response => {
      console.log(response)
      resolve(response);
    }).catch(error => {
      console.log(error)
      reject(error);
    });
  });
}
/*
*   put请求 
*/
export function put(url, params = {}) {
  return new Promise((resolve, reject) => {
    httpService({
      url: url,
      method: 'put',
      data: params
    }).then(response => {
      console.log(response)
      resolve(response);
    }).catch(error => {
      console.log(error)
      reject(error);
    });
  });
}
/*
 *  delete请求
 *  url:请求地址
 *  params:参数
 * */
export function Delete(url, params = {}) {
  return new Promise((resolve, reject) => {
    httpService({
      url: url,
      method: 'delete',
      data: params
    }).then(response => {
      console.log(response)
      resolve(response);
    }).catch(error => {
      console.log(error)
      reject(error);
    });
  });
}
/*
 *  文件上传
 *  url:请求地址
 *  params:参数
 * */
export function fileUpload(url, params = {}) {
  return new Promise((resolve, reject) => {
    httpService({
      url: url,
      method: 'post',
      data: params,
      headers: { 'Content-Type': 'multipart/form-data' }
    }).then(response => {
      resolve(response);
    }).catch(error => {
      reject(error);
    });
  });
}

export default {
  get,
  post,
  put,
  Delete,
  fileUpload
}
