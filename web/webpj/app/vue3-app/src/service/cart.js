import axios from '../utils/axios'
import {baseUrl, enterpriseId} from '@/service/webConfig.js'


export function modifyCart(params) {
    return axios.put(`/shop-cart`, params);
}

export function deleteCartItem(id) {
    return axios.delete(`/shop-cart/${id}`);
}

export function getByCartItemIds(params) {
    return axios.get(`/shop-cart/settle`, {params});
}

// ====================================
/**
 * 查询购物车列表
 */
export function getCart(params) {
    return axios.get(`${baseUrl}/shopping/goods/cart/query`, {params});
}

/**
 * 添加购物车
 */
export function addCart(params = {}) {
    return axios.post(`${baseUrl}/shopping/goods/cart/updateCart`, params);
}
