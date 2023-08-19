import axios from "@/utils/axios";

const baseUrl = "http://127.0.0.1:8080";

const enterpriseId = '5467bf30b2bb48509b289bc706bea1ba';

/**
 * 登录
 */
export const login = (params = {}) => {
    return axios.post(`${baseUrl}/login/shopping/login`, {
        enterpriseId,
        ...params
    });
}

/**
 * 注册
 */
export const register = (params = {}) => {
    return axios.post(`${baseUrl}/login/shopping/register`, {
        enterpriseId,
        ...params
    });
}




