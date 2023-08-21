import axios from '../utils/axios'
import {baseUrl, enterpriseId} from '@/service/webConfig.js'


export function EditUserInfo(params) {
    return axios.put('/user/info', params);
}

export function login(params) {
    return axios.post('/user/login', params);
}

export function logout() {
    return axios.post('/user/logout')
}

export function register(params) {
    return axios.post('/user/register', params);
}

// =============================================================================

export function getUserInfo(params = {}) {
    return axios.get( `${baseUrl}/shopping/user/userInfo`, {params});
}
