import axios from "@/utils/axios";

const baseUrl = "http://127.0.0.1:8080";

/**
 * 登录
 */
export const login = (params) => {
    return axios.post(`${baseUrl}/login/shopping/login`, params);
}

/**
 * 注册
 */
export const register = (params) => {
    return axios.post(`${baseUrl}/login/shopping/register`, params);
}




