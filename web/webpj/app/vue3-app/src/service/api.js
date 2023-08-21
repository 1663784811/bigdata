import axios from "@/utils/axios";
import {baseUrl, enterpriseId} from '@/service/webConfig.js'

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




