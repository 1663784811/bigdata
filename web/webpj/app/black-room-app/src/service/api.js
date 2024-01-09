import axios from "@/utils/axios";
import {baseUrl, appId} from '@/service/webConfig.js'

// =====================================================================
// =====================================================================
// =====================================================================
//通用查询
export function commonQuery(params = {}, appId) {return axios.get(`${baseUrl}/app/${appId}/common/query`, {params});}
//通用保存
export function commonSave(params = {}, appId) {return axios.post(`${baseUrl}/app/${appId}/common/save`, params);}
//登录
export const login = (params = {}, appId) => {return axios.post(`${baseUrl}/app/${appId}/user/login/login`, params);}
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

