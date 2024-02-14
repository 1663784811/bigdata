import axios from "@/utils/axios";

const baseUrl = import.meta.env.VITE_BASE_URL;

// =====================================================================
// =====================================================================
// =====================================================================
//通用查询
export function commonQuery(params = {}, appId) {
    params.url= encodeURIComponent(window.location.href);
    return axios.get(`${baseUrl}/appAdmin/${appId}/common/query`, {params});
}
//通用保存
export function commonSave(params = {}, appId) {return axios.post(`${baseUrl}/app/${appId}/common/save`, params);}
//登录
export const ApiLogin = (params = {}, appId) => {
    return axios.post(`${baseUrl}/app/${appId}/login/login`, params);
}
//注册
export const register = (params = {}, appId) => {return axios.post(`${baseUrl}/app/${appId}/login/register`, params);}
//登录
export const adminLogin = (params = {}, appId) => {return axios.post(`${baseUrl}/app/${appId}/admin/login/adminLogin`, params);}
//
export function logout(appId) {return axios.get(`${baseUrl}/app/${appId}/login/logout`)}
// 获取用户信息
export function getUserInfo(params = {}, appId) {return axios.get( `${baseUrl}/app/${appId}/user/userInfo`, {params});}
// 修改用户信息
export function updateUserInfo(params = {}, appId) {return axios.post( `${baseUrl}/app/${appId}/user/updateUserInfo`, params);}

// =====================================================================
// =====================================================================
// =====================================================================


//
export const getBanner = (params = {}, appId) => {
    params.code="select_app_banner";
    params.appId=appId;
    return commonQuery(params,appId)
}
//
export const searchGoods = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/shopping/goods/searchGoods`,{ params });}
//
export const enterpriseType = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/shopping/type/findType`,{ params });}
//计算商品价格
export function countGoodsPrice(params = {}, appId) {return axios.post(`${baseUrl}/app/${appId}/order/countGoodsPrice`, params);}
//查询购物车列表
export function getCart(params, appId) {return axios.get(`${baseUrl}/app/${appId}/shopping/cart/query`, {params});}
//添加购物车
export function addCart(params = {},appId) {return axios.post(`${baseUrl}/app/${appId}/shopping/cart/updateCart`, params);}
//删除购物车商品
export function deleteCartItem(params, appId) {return axios.post(`${baseUrl}/app/${appId}/shopping/cart/delCartGoods`, params);}
//订单列表
export function getOrderList(params, appId) {return axios.get(`${baseUrl}/app/${appId}/order/query`, { params });}
//订单详情
export function getOrderDetail(params, appId) {return axios.get(`${baseUrl}/app/${appId}/order/orderById`, { params });}

// 获取默认地址
export function getDefaultAddress(params = {}, appId) {return axios.get(`${baseUrl}/app/${appId}/address/defaultAddress`, params);}
// 保存地址
export function saveAddress(params = {}, appId) {return axios.post(`${baseUrl}/app/${appId}/address/saveAddress`, params);}

export const goodsDetails = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/shopping/goods/goodsDetails`,{ params });}

export const goodsDetailsText = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/shopping/goods/goodsDetailsText`,{ params });}

export const goodsPhoto = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/shopping/goods/goodsPhoto`,{ params });}

//创建订单
export function createOrder(params, appId) {return axios.post(`${baseUrl}/app/${appId}/order/createOrder`, params);}


// =====================================================================

export function search(params) {
    return axios.get('/search', { params });
}


export function payOrder(params) {
    return axios.get(`/paySuccess`, { params })
}
