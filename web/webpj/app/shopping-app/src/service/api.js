import axios from "@/utils/axios";
import {baseUrl} from '@/service/webConfig.js'

// todo 未完成
const appId = 'sss'

//后台app管理员信息
export function getAppAdminInfo(params = {}) {return axios.get(`${baseUrl}/app/si/admin/signIn/userInfo`, {params});}

//通用查询
export function commonQuery(params = {}) {return axios.get(`${baseUrl}/app/${appId}/common/query`, {params});}

//通用保存
export function commonSave(params = {}) {return axios.post(`${baseUrl}/app/${appId}/common/save`, params);}

//登录
export const login = (params = {}) => {return axios.post(`${baseUrl}/app/${appId}/user/login/login`, params);}

//注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/login/shopping/register`, params);}

export const getBanner = (params = {}) => {return axios.get(`${baseUrl}/shopping/${appId}/web/banner/findBanner`,{ params });}

export const searchGoods = (params = {}) => {return axios.get(`${baseUrl}/shopping/${appId}/goods/search/searchGoods`,{ params });}



export function logout() {
    return axios.get('/categories');
}

export function search(params) {
    return axios.get('/search', { params });
}

// ================================================

export const goodsDetails = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/${appId}/goods/search/goodsDetails`,{ params });
}

export const goodsDetailsText = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/${appId}/goods/search/goodsDetailsText`,{ params });
}

export const goodsPhoto = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/${appId}/goods/search/goodsPhoto`,{ params });
}

export const enterpriseType = (params = {}) => {return axios.get(`${baseUrl}/shopping/${appId}/goods/type/enterpriseType`,{ params });}




export function getByCartItemIds(params) {
    return axios.get(`/shop-cart/settle`, {params});
}

// ====================================
/**
 * 查询购物车列表
 */
export function getCart(params) {return axios.get(`${baseUrl}/shopping/${appId}/goods/cart/query`, {params});}

/**
 * 添加购物车
 */
export function addCart(params = {}) {return axios.post(`${baseUrl}/shopping/${appId}/goods/cart/updateCart`, params);}

/**
 * 计算商品价格
 */
export function countGoodsPrice(params = {}) {
    return axios.post(`${baseUrl}/shopping/${appId}/goods/order/countGoodsPrice`, params);
}

/**
 * 删除购物车商品
 */
export function deleteCartItem(params) {
    return axios.post(`${baseUrl}/shopping/${appId}/goods/cart/delCartGoods`, params);
}

export function EditUserInfo(params) {
    return axios.put('/user/info', params);
}

// =============================================================================

export function getUserInfo(params = {}) {
    return axios.get( `${baseUrl}/shopping/${appId}/user/userInfo`, {params});
}

export function cancelOrder(id) {
    return axios.put(`/order/${appId}/cancel`);
}

export function confirmOrder(id) {
    return axios.put(`/order/${appId}/finish`)
}

export function payOrder(params) {
    return axios.get(`/paySuccess`, { params })
}



// =============================================

/**
 * 订单列表
 */
export function getOrderList(params) {
    return axios.get(`${baseUrl}/shopping/${appId}/goods/order/query`, { params });
}

/**
 * 订单详情
 */
export function getOrderDetail(params) {
    return axios.get(`${baseUrl}/shopping/${appId}/goods/order/orderById`, { params });
}

/**
 * 创建订单
 */
export function createOrder(params) {
    return axios.post(`${baseUrl}/shopping/${appId}/goods/order/createOrder`, params);
}


export function addAddress(params) {
    return axios.post(`/address`, params);
}

export function EditAddress(params) {
    return axios.put(`/address`, params);
}

export function DeleteAddress(id) {
    return axios.delete(`/address/${appId}`);
}

// ====================================================


export function getAddressList(params = {}) {
    return axios.get(`${baseUrl}/shopping/${appId}/user/address`, {params})
}

/**
 * 获取默认地址
 */
export function getDefaultAddress(params = {}) {
    return axios.get(`${baseUrl}/shopping/${appId}/user/defaultAddress`, {params});
}
