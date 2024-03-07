import axios from "./axiosRequest";
const baseUrl = import.meta.env.VITE_BASE_URL;

// =====================================================================
// =====================================================================
// =====================================================================
// 获取页面设置
export const pageSetting = (params = {}, code) => {return axios.get( `${baseUrl}/tx/config/page/pageSetting`, {params});}
//获取表格数据
export const getSqlList = (params, code) => {return axios.get( `${baseUrl}/appAdmin/common/sql/sqlList`, params);}
//保存SQL
export const saveSql = (params, code) => {return axios.get( `${baseUrl}/appAdmin/common/sql/saveSql`, params);}
// 删除
export const delSql = (params, code) => {return axios.post( `${baseUrl}/admin/common/sql/delSql`, params);}
export const loadColumn = (params, code) => {return axios.get( `${baseUrl}/admin/common/sql/loadColumn`, {params});}
// 获取页面设置
export const findSetting = (params, code) => {return axios.get( `${baseUrl}/tx/config/page/findSetting`, params);}
//
export const saveComponents = (params, code) => {return axios.post( `${baseUrl}/tx/config/page/saveComponents`, params);}

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
export function commonQuery(params = {}, code) {return axios.get(`${baseUrl}/appAdmin/${code}/common/query`, {params});}
//通用保存
export function commonSave(params = {}, code) {return axios.post(`${baseUrl}/appAdmin/${code}/common/save`, params);}
//登录
export const logInFn = (params = {}, code) => {return axios.post(`${baseUrl}/appAdmin/${code}/login/login`, params);}
//注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/appAdmin/${code}/login/register`, params);}
//
export const logout = (code) => {return axios.get(`${baseUrl}/appAdmin/${code}/user/login`)}
//
export const userInfo = (params = {}, code) => {return axios.get( `${baseUrl}/appAdmin/${code}/user/userInfo`, {params});}

// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
//获取企业信息
export const enterpriseFindPage = (params, code) => {return axios.get( `${baseUrl}/appAdmin/${code}/enterprise/enterpriseInfo`, params);}
// 获取菜单
export const apiAdminMenu = (params, code) => {return axios.get( `${baseUrl}/appAdmin/${code}/user/menu`, params);}
//
export const findPageWebImage = (params, code) => {return axios.get( `${baseUrl}/appAdmin/${code}/user/messsssssssssnu`, params);}
export const findIdCPageComponents = (params, code) => {return axios.get( `${baseUrl}/appAdmin/config/cpagecomponents/findIdCPageComponents`, params);}
export const findCPageComponents = (params, code) => {return axios.get( `${baseUrl}/appAdmin/config/cpagecomponents/findPage`, params);}
export const loadTable = (params, code) => {return axios.get( `${baseUrl}/appAdmin/buildCode/loadTable`, params);}
export const findIdGGoods = (params, code) => {return axios.get( `${baseUrl}/appAdmin/gGoods/findIdGGoods`, params);}
export const goodsPhoto = (params, code) => {return axios.get( `${baseUrl}/shopping/goods/search/goodsPhoto`, params);}
export const findGoodsSku = (params, code) => {return axios.get( `${baseUrl}/shopping/appAdmin/findGoodsSku`, params);}
export const saveGGoods = (params, code) => {return axios.get( `${baseUrl}/appAdmin/gGoods/saveGGoods`, params);}
export const saveGStoreGoodsSku = (params, code) => {return axios.get( `${baseUrl}/appAdmin/gStoreGoodsSku/saveGStoreGoodsSku`, params);}
export const enterpriseRegister = (params, code) => {return axios.get( `${baseUrl}/login/appAdmin/enterpriseRegister`, params);}
export const getPhoneList = (params, code) => {return axios.get( `${baseUrl}/appAdmin/phone/phone/phoneList`, params);}







