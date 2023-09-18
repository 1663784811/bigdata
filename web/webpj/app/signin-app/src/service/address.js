import axios from '../utils/axios'
import {baseUrl, enterpriseId} from '@/service/webConfig.js'


export function addAddress(params) {
    return axios.post(`/address`, params);
}

export function EditAddress(params) {
    return axios.put(`/address`, params);
}

export function DeleteAddress(id) {
    return axios.delete(`/address/${id}`);
}

// ====================================================


export function getAddressList(params = {}) {
    return axios.get(`${baseUrl}/shopping/user/address`, {params})
}

/**
 * 获取默认地址
 */
export function getDefaultAddress(params = {}) {
    return axios.get(`${baseUrl}/shopping/user/defaultAddress`, {params});
}
