import { http } from "@/utils/http";
import { systemDetail,abnormalities } from "./utils";

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
    return http.request<any>("post", systemDetail(""), { data });
  };
  
/**
 * 
 * @param areaPost 
 * @param data 
 * @returns 该区域对应故障
 */  
export const getAbnormalities = (data?: object) => {
    return http.request<any>("post",abnormalities(""), { data });
  };