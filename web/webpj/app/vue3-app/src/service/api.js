import {AJAXGET, AJAXPOST} from "./webinfo";

const baseUrl = "http://127.0.0.1:8080";

/**
 * 登录
 */
export const login = (parameter) => {
    return AJAXPOST(`${baseUrl}/login/shopping/login`, parameter, 1)
}

export const register = (parameter) => {
    return AJAXPOST(`${baseUrl}/login/shopping/register`, parameter, 1)
}




