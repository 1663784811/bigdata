import axios from "./axiosRequest";
const baseUrl = import.meta.env.VITE_BASE_URL;

// =====================================================================
// =====================================================================
// =====================================================================
// 获取页面设置
export const pageSetting = (params = {}, eCode) => {return axios.get( `${baseUrl}/tx/config/page/pageSetting`, {params});}
//获取表格数据
export const getSqlList = (params, eCode) => {return axios.get( `${baseUrl}/admin/common/sql/sqlList`, {params});}
//保存SQL
export const saveSql = (params, eCode) => {return axios.post( `${baseUrl}/admin/common/sql/saveSql`, params);}
// 删除
export const delSql = (params, eCode) => {return axios.post( `${baseUrl}/admin/common/sql/delSql`, params);}
// 获取页面设置
export const findSetting = (params, eCode) => {return axios.get( `${baseUrl}/tx/config/page/findSetting`, params);}
//
export const saveComponents = (params, eCode) => {return axios.post( `${baseUrl}/tx/config/page/saveComponents`, params);}

// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
export const commonRequest = (url, params, type = 'get') => {
    if (type === 'post') {
        return axios.post(`${baseUrl}${url}`, params);
    } else {
        return axios.get( `${baseUrl}${url}`, {params})
    }
}
//通用查询
export function commonQuery(params = {}, eCode) {return axios.get(`${baseUrl}/admin/${eCode}/common/query`, {params});}
//通用保存
export function commonSave(params = {}, eCode) {return axios.post(`${baseUrl}/admin/${eCode}/common/save`, params);}
//登录
export const logInFn = (params = {}, eCode) => {return axios.post(`${baseUrl}/admin/${eCode}/login/login`, params);}
//注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/admin/${eCode}/login/register`, params);}
//
export const logout = (eCode) => {return axios.get(`${baseUrl}/admin/${eCode}/user/login`)}
//
export const userInfo = (params = {}, eCode) => {return axios.get( `${baseUrl}/admin/${eCode}/user/userInfo`, {params});}

// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
//获取企业信息
export const enterpriseFindPage = (params, eCode) => {return axios.get( `${baseUrl}/admin/${eCode}/enterprise/enterpriseInfo`, params);}
// 获取菜单
export const apiAdminMenu = (params, eCode) => {return axios.get( `${baseUrl}/admin/${eCode}/user/menu`, params);}
//
export const findPageWebImage = (params, eCode) => {return axios.get( `${baseUrl}/admin/${eCode}/user/menu`, params);}
export const findIdCPageComponents = (params, eCode) => {return axios.get( `${baseUrl}/admin/config/cpagecomponents/findIdCPageComponents`, params);}
export const findCPageComponents = (params, eCode) => {return axios.get( `${baseUrl}/admin/config/cpagecomponents/findPage`, params);}
export const loadTable = (params, eCode) => {return axios.get( `${baseUrl}/admin/buildCode/loadTable`, params);}
export const findIdGGoods = (params, eCode) => {return axios.get( `${baseUrl}/admin/gGoods/findIdGGoods`, params);}
export const goodsPhoto = (params, eCode) => {return axios.get( `${baseUrl}/shopping/goods/search/goodsPhoto`, params);}
export const findGoodsSku = (params, eCode) => {return axios.get( `${baseUrl}/shopping/admin/findGoodsSku`, params);}
export const saveGGoods = (params, eCode) => {return axios.get( `${baseUrl}/admin/gGoods/saveGGoods`, params);}
export const saveGStoreGoodsSku = (params, eCode) => {return axios.get( `${baseUrl}/admin/gStoreGoodsSku/saveGStoreGoodsSku`, params);}
export const enterpriseRegister = (params, eCode) => {return axios.get( `${baseUrl}/login/admin/enterpriseRegister`, params);}
export const getPhoneList = (params, eCode) => {return axios.get( `${baseUrl}/admin/phone/phone/phoneList`, params);}







