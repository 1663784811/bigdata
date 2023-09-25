import axios from "@/utils/axios";
import {baseUrl, appId} from '@/service/webConfig.js'


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

/**
 * 登录
 */
export const login = (params = {}) => {
    return axios.post(`${baseUrl}/login/app/adminLogin`, {
        appId,
        ...params
    });
}

/**
 * 注册
 */
export const register = (params = {}) => {
    return axios.post(`${baseUrl}/login/shopping/register`, {
        appId,
        ...params
    });
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



export function getDetail(id) {
    return axios.get(`/goods/detail/${id}`);
}

export function getCategory() {
    return axios.get('/categories');
}

export function search(params) {
    return axios.get('/search', { params });
}

// ================================================

export const goodsDetails = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/goods/search/goodsDetails`,{ params });
}

export const goodsDetailsText = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/goods/search/goodsDetailsText`,{ params });
}

export const goodsPhoto = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/goods/search/goodsPhoto`,{ params });
}

export const enterpriseType = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/goods/type/enterpriseType`,{ params });
}

export const getBanner = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/web/banner/findBanner`,{ params });
}

export const searchGoods = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/goods/search/searchGoods`,{ params });
}



export function cancelOrder(id) {
    return axios.put(`/order/${id}/cancel`);
}

export function confirmOrder(id) {
    return axios.put(`/order/${id}/finish`)
}

export function payOrder(params) {
    return axios.get(`/paySuccess`, { params })
}



// =============================================

/**
 * 订单列表
 */
export function getOrderList(params) {
    return axios.get(`${baseUrl}/shopping/goods/order/query`, { params });
}

/**
 * 订单详情
 */
export function getOrderDetail(params) {
    return axios.get(`${baseUrl}/shopping/goods/order/orderById`, { params });
}

/**
 * 创建订单
 */
export function createOrder(params) {
    return axios.post(`${baseUrl}/shopping/goods/order/createOrder`, params);
}


export function EditUserInfo(params) {
    return axios.put('/user/info', params);
}

export function logout() {
    return axios.post('/user/logout')
}


// =============================================================================

export function getUserInfo(params = {}) {
    return axios.get( `${baseUrl}/shopping/user/userInfo`, {params});
}


