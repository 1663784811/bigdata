import axios from "./axiosRequest";
const baseUrl = import.meta.env.VITE_BASE_URL;

// =====================================================================
// =====================================================================
// =====================================================================
//通用查询
export function commonQuery(params = {}) {return axios.get(`${baseUrl}/appAdmin/\${appId}/common/query`, {params});}
//通用保存
export function commonSave(params = {}) {return axios.post(`${baseUrl}/app/\${appId}/common/save`, params);}
//登录
export const login = (params = {}) => {return axios.post(`${baseUrl}/app/\${appId}/user/login/login`, params);}
//注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/app/\${appId}/user/login/register`, params);}
//登录
export const adminLogin = (params = {}) => {return axios.post(`${baseUrl}/app/\${appId}/admin/login/adminLogin`, params);}
//
export function logout(appId) {return axios.get(`${baseUrl}/app/\${appId}/user/login`)}
//
export function getUserInfo(params = {}) {return axios.get( `${baseUrl}/app/\${appId}/user/userInfo`, {params});}

// =====================================================================
// =====================================================================
// =====================================================================

export function countGoodsPrice(params = {}) {return axios.post( `${baseUrl}/app/\${appId}/order/countGoodsPrice`, params);}
export function createOrder(params = {}) {return axios.post( `${baseUrl}/app/\${appId}/store/${params.storeId}/food/board/createOrder/${params.boardId}`, params);}

export function crateBoard(params = {}) {return axios.post( `${baseUrl}/app/\${appId}/store/${params.storeId}/food/board/crateBoard`, params);}

// 订单详情
export function getBoardOrder(params = {}) {return axios.get( `${baseUrl}/app/\${appId}/store/${params.storeId}/food/board/boardOrder/${params.boardId}`);}
// 更新购物车
export function updateCart(params = {}) {return axios.post( `${baseUrl}/app/\${appId}/store/${params.storeId}/food/board/updateCart/${params.boardId}`, params);}
// 查找购物车
export function findMyCart(params = {}) {return axios.get( `${baseUrl}/app/\${appId}/store/${params.storeId}/food/board/findMyCart/${params.boardId}`);}
