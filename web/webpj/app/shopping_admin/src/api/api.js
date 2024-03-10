import axios from "./axiosRequest";
const baseUrl = import.meta.env.VITE_BASE_URL;

// =====================================================================
// =====================================================================
// =====================================================================
// 获取页面设置
export const pageSetting = (params = {}, appid) => {return axios.get( `${baseUrl}/tx/config/page/pageSetting`, {params});}
//获取表格数据
export const getSqlList = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/common/sql/sqlList`, params);}
//保存SQL
export const saveSql = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/common/sql/saveSql`, params);}
// 删除
export const delSql = (params, appid) => {return axios.post( `${baseUrl}/admin/common/sql/delSql`, params);}
export const loadColumn = (params, appid) => {return axios.get( `${baseUrl}/admin/common/sql/loadColumn`, {params});}
// 获取页面设置
export const findSetting = (params, appid) => {return axios.get( `${baseUrl}/tx/config/page/findSetting`, params);}
//
export const saveComponents = (params, appid) => {return axios.post( `${baseUrl}/tx/config/page/saveComponents`, params);}

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
export function commonQuery(params = {}, appid) {return axios.get(`${baseUrl}/appAdmin/${appid}/common/query`, {params});}
//通用保存
export function commonSave(params = {}, appid) {return axios.post(`${baseUrl}/appAdmin/${appid}/common/save`, params);}
//登录
export const logInFn = (params = {}, appid) => {return axios.post(`${baseUrl}/appAdmin/${appid}/login/login`, params);}
//注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/appAdmin/${appid}/login/register`, params);}
//
export const logout = (appid) => {return axios.get(`${baseUrl}/appAdmin/${appid}/user/login`)}
//
export const userInfo = (params = {}, appid) => {return axios.get( `${baseUrl}/appAdmin/${appid}/user/userInfo`, {params});}

// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
//获取企业信息
export const enterpriseFindPage = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/${appid}/enterprise/enterpriseInfo`, params);}
// 获取菜单
export const apiAdminMenu = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/${appid}/user/menu`, params);}
//
export const findPageWebImage = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/${appid}/user/messsssssssssnu`, params);}
export const findIdCPageComponents = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/config/cpagecomponents/findIdCPageComponents`, params);}
export const findCPageComponents = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/config/cpagecomponents/findPage`, params);}
export const loadTable = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/buildCode/loadTable`, params);}
export const findIdGGoods = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/gGoods/findIdGGoods`, params);}
export const goodsPhoto = (params, appid) => {return axios.get( `${baseUrl}/shopping/goods/search/goodsPhoto`, params);}
export const findGoodsSku = (params, appid) => {return axios.get( `${baseUrl}/shopping/appAdmin/findGoodsSku`, params);}
export const saveGGoods = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/gGoods/saveGGoods`, params);}
export const saveGStoreGoodsSku = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/gStoreGoodsSku/saveGStoreGoodsSku`, params);}
export const enterpriseRegister = (params, appid) => {return axios.get( `${baseUrl}/login/appAdmin/enterpriseRegister`, params);}
export const getPhoneList = (params, appid) => {return axios.get( `${baseUrl}/appAdmin/phone/phone/phoneList`, params);}







