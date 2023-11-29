import axios from "@/utils/axios";
import {baseUrl, appId} from '@/service/webConfig.js'
// 登录
export const login = (params = {}) => {return axios.post(`${baseUrl}/login/app/adminLogin`, {appId, ...params});}
// 注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/login/shopping/register`, {appId, ...params});}
//退出登录
export function logout() {return axios.post(`${baseUrl}/login/app/logout`)}
//获取登录用户信息
export function getUserInfo(params = {}) {return axios.get(`${baseUrl}/shopping/user/userInfo`, {params});}
//后台app管理员信息
export function getAppAdminInfo(params = {}) {return axios.get(`${baseUrl}/app/si/admin/signIn/userInfo`, {params});}

//通用查询
export function commonQuery(params = {}) {return axios.get(`${baseUrl}/app/common/query`, {params});}

//通用保存
export function commonSave(params = {}) {return axios.post(`${baseUrl}/app/common/save`, params);}




