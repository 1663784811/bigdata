import axios from '../utils/axios'
import {baseUrl, enterpriseId} from '@/service/webConfig.js'


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

/**
 * 计算商品价格
 */
export function countGoodsPrice(params = {}) {
    return axios.post(`${baseUrl}/shopping/goods/order/countGoodsPrice`, params);
}

/**
 * 删除购物车商品
 */
export function deleteCartItem(params) {
    return axios.post(`${baseUrl}/shopping/goods/cart/delCartGoods`, params);
}
