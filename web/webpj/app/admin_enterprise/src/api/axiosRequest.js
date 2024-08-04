import axios from 'axios'
import {loginInfo} from "@/store/loginInfo.js"

const instance = axios.create();

instance.defaults.withCredentials = true
instance.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
instance.defaults.headers.post['Content-Type'] = 'application/json'

const requestConfig = function (config) {
    const userStore = loginInfo();
    config.headers['token'] = userStore.token;
    if (userStore.variable.eCode) {
        config.url = config.url.replace("${eCode}", userStore.variable.eCode)
        console.log(config.url)
    }
    return config
}

const responseConfig = function (rest) {
    if (typeof rest.data !== 'object') {
        console.error('网络错误', rest)
        return Promise.reject(rest)
    }
    if (rest.data && rest.data.code !== 2000) {
        return Promise.reject(rest.data)
    }
    return rest.data
}

/**
 *
 */
instance.interceptors.request.use(requestConfig, error => Promise.reject(error));
/**
 * 响应拦截器
 */
instance.interceptors.response.use(responseConfig, error => Promise.reject(error));
export default instance


// ====================================================================

const upLoad = axios.create();
//请求拦截器
upLoad.interceptors.request.use(requestConfig, error => Promise.reject(error))
// 响应拦截器
upLoad.interceptors.response.use(responseConfig, error => Promise.reject(error))
export const upLoadFile = upLoad;
 