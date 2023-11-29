import axios from "@/utils/axios";
import {baseUrl, enterpriseId} from '@/service/webConfig.js'

//后台app管理员信息
export function getAppAdminInfo(params = {}) {return axios.get(`${baseUrl}/app/si/admin/signIn/userInfo`, {params});}

//通用查询
export function commonQuery(params = {}) {return axios.get(`${baseUrl}/app/common/query`, {params});}

//通用保存
export function commonSave(params = {}) {return axios.post(`${baseUrl}/app/common/save`, params);}

//登录
export const login = (params = {}) => {return axios.post(`${baseUrl}/login/shopping/login`, {enterpriseId, ...params});}

//注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/login/shopping/register`, {enterpriseId, ...params});}




