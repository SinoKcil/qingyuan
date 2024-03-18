import { http } from "@/utils/http";
import {
  systemDetail,
  abnormalities,
  abnormalityDetail,
  getAuth,
  getAbnormalityDetailForForm
} from "./utils";

/**
 *
 * @param data
 * @returns 所有区域的名单regions[]，
 * 用户名单users:[
 * {
 * id,username,phone,password,role,avatar,region
 * }
 * ]
 */
export const getRegions = (data?: object) => {
  // 应该用get
  return http.request<any>("get", systemDetail(""), { data });
};

/**
 *
 * @param areaPost
 * @param data
 * @returns 该区域对应故障
 */
export const getAbnormalities = (region: string, layer: number) => {
  return http.request<any>("get", abnormalities(""), {
    params: { region: region, layer: layer }
  });
};

/**
 *
 * @param Post
 * @param data
 * @returns 故障的详细信息
 */
export const getAbnormalDetails = (
  region: string,
  layer: number,
  row: number,
  col: number,
  label: number
) => {
  return http.request<any>("get", abnormalityDetail(""), {
    params: { region: region, layer: layer, row: row, col: col, label: label }
  });
};

export const getAuthByToken = (token: string) => {
  return http.request<any>(
    "get",
    getAuth(""),
    {}, //没有参数
    {
      //传一个headers
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );
};

export const fetchAbnormalityForForm = id => {
  return http.request<any>("get", getAbnormalityDetailForForm(""), {
    params: { id }
  });
};
