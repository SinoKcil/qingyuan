// 作者代理已经有/了 不需要/
export const systemDetail = (url: string) => `/api/${url}admin/users`;
export const abnormalities = (url: string) => `/api/${url}region`;
export const baseApiUrl = (url: string) => `/api/${url}`;
export const abnormalityDetail = (url: string) => `/api/${url}detail`;
export const getAuth = (url: string) => `/api/${url}auth/getauth`;
export const getAbnormalityDetailForForm = (url: string) => `/api/${url}id`;
export const formUploader = (url: string) => `/api/${url}form/upload`;
