import axios from "@/utils/axios";

const baseUrl = import.meta.env.VITE_BASE_URL;

// =====================================================================
// =====================================================================
// =====================================================================
//通用查询
export function commonQuery(params = {}, appId) {
    params.url=window.location.href;
    return axios.get(`${baseUrl}/appAdmin/${appId}/common/query`, {params});
}
//通用保存
export function commonSave(params = {}, appId) {return axios.post(`${baseUrl}/app/${appId}/common/save`, params);}
//登录
export const ApiLogin = (params = {}, appId) => {
    return axios.post(`${baseUrl}/app/${appId}/user/login/login`, params);
}
//注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/app/${appId}/user/login/register`, params);}
//登录
export const adminLogin = (params = {}, appId) => {return axios.post(`${baseUrl}/app/${appId}/admin/login/adminLogin`, params);}
//
export function logout(appId) {return axios.get(`${baseUrl}/app/${appId}/user/login`)}
//
export function getUserInfo(params = {}, appId) {return axios.get( `${baseUrl}/app/${appId}/user/userInfo`, {params});}

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
export const searchGoods = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/shopping/goods/search/searchGoods`,{ params });}
//
export const enterpriseType = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/shopping/type/findType`,{ params });}
//查询购物车列表
export function getCart(params, appId) {return axios.get(`${baseUrl}/app/${appId}/goods/cart/query`, {params});}
//添加购物车
export function addCart(params = {},appId) {return axios.post(`${baseUrl}/app/${appId}/goods/cart/updateCart`, params);}
//计算商品价格
export function countGoodsPrice(params = {}, appId) {return axios.post(`${baseUrl}/app/${appId}/goods/order/countGoodsPrice`, params);}
//删除购物车商品
export function deleteCartItem(params, appId) {return axios.post(`${baseUrl}/app/${appId}/goods/cart/delCartGoods`, params);}
//订单列表
export function getOrderList(params, appId) {return axios.get(`${baseUrl}/app/${appId}/goods/order/query`, { params });}
//订单详情
export function getOrderDetail(params, appId) {return axios.get(`${baseUrl}/app/${appId}/goods/order/orderById`, { params });}
//
export function getAddressList(params = {}, appId) {return axios.get(`${baseUrl}/app/${appId}/user/address`, {params})}
// 获取默认地址
export function getDefaultAddress(params = {}, appId) {return axios.get(`${baseUrl}/app/${appId}/user/defaultAddress`, {params});}

export const goodsDetails = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/goods/search/goodsDetails`,{ params });}

export const goodsDetailsText = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/goods/search/goodsDetailsText`,{ params });}

export const goodsPhoto = (params = {}, appId) => {return axios.get(`${baseUrl}/app/${appId}/goods/search/goodsPhoto`,{ params });}

//创建订单
export function createOrder(params, appId) {return axios.post(`${baseUrl}/app/${appId}/goods/order/createOrder`, params);}


// =====================================================================

export function search(params) {
    return axios.get('/search', { params });
}

export function EditUserInfo(params) {
    return axios.put('/user/info', params);
}

export function payOrder(params) {
    return axios.get(`/paySuccess`, { params })
}
export function addAddress(params, appId) {return axios.post(`/address`, params);}

export function EditAddress(params) {return axios.put(`/address`, params);}

export function DeleteAddress(id) {return axios.delete(`/address/${appId}`);}

