import axios from "./axiosRequest";
import {upLoadFile} from "./axiosRequest";
const baseUrl = import.meta.env.VITE_BASE_URL;

// =====================================================================
// =====================================================================
// =====================================================================
// 获取页面设置
export const pageSetting = (params = {}) => {return axios.get( `${baseUrl}/tx/config/page/pageSetting`, {params});}
//获取表格数据
export const getSqlList = (params) => {return axios.get( `${baseUrl}/admin/common/sql/sqlList`, {params});}
//保存SQL
export const saveSql = (params) => {return axios.post( `${baseUrl}/admin/common/sql/saveSql`, params);}
// 删除
export const delSql = (params) => {return axios.post( `${baseUrl}/admin/common/sql/delSql`, params);}
export const loadColumn = (params) => {return axios.get( `${baseUrl}/admin/common/sql/loadColumn`, {params});}
// 获取页面设置
export const findSetting = (params) => {return axios.get( `${baseUrl}/tx/config/page/findSetting`, params);}
// 保存组件
export const saveComponents = (params) => {return axios.post( `${baseUrl}/tx/config/page/saveComponents`, params);}
// 复制页面
export const copyCPageRequest = (params) => {return axios.post( `${baseUrl}/admin/config/page/copyCPage`, params);}
// 上传文件
export const uploadFile = (formData) =>{return upLoadFile.post(`${baseUrl}/admin/\${eCode}/file/upload`,formData);};
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
export function commonQuery(params = {}) {return axios.get(`${baseUrl}/appAdmin/\${appId}/common/query`, {params});}
//通用保存
export function commonSave(params = {}) {return axios.post(`${baseUrl}/appAdmin/\${appId}/common/save`, params);}
//登录
export const logInFn = (params = {}) => {return axios.post(`${baseUrl}/appAdmin/\${appId}/store/\${storeId}/login`, params);}
//注册
export const register = (params = {}) => {return axios.post(`${baseUrl}/appAdmin/\${appId}/login/register`, params);}
//
export const logout = (appId) => {return axios.get(`${baseUrl}/appAdmin/\${appId}/user/login`)}
//
export const userInfo = (params = {}) => {return axios.get( `${baseUrl}/appAdmin/\${appId}/user/userInfo`, {params});}

// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
// ===============================================================================================================================================================================================================
//获取企业信息
export const enterpriseFindPage = (params, appId) => {return axios.get( `${baseUrl}/appAdmin/\${appId}/enterprise/enterpriseInfo`, params);}
// 获取菜单
export const apiAdminMenu = (params, appId) => {return axios.get( `${baseUrl}/appAdmin/\${appId}/user/menu`, params);}
//
export const findPageWebImage = (params, appId) => {return axios.get( `${baseUrl}/appAdmin/\${appId}/user/messsssssssssnu`, params);}
export const findIdCPageComponents = (params) => {return axios.get( `${baseUrl}/appAdmin/config/cpagecomponents/findIdCPageComponents`, params);}
export const findCPageComponents = (params) => {return axios.get( `${baseUrl}/appAdmin/config/cpagecomponents/findPage`, params);}
export const loadTable = (params) => {return axios.get( `${baseUrl}/appAdmin/buildCode/loadTable`, params);}
export const findIdGGoods = (params) => {return axios.get( `${baseUrl}/appAdmin/gGoods/findIdGGoods`, params);}
export const goodsPhoto = (params) => {return axios.get( `${baseUrl}/shopping/goods/search/goodsPhoto`, params);}
export const findGoodsSku = (params) => {return axios.get( `${baseUrl}/shopping/appAdmin/findGoodsSku`, params);}
export const saveGGoods = (params) => {return axios.get( `${baseUrl}/appAdmin/gGoods/saveGGoods`, params);}
export const saveGStoreGoodsSku = (params) => {return axios.get( `${baseUrl}/appAdmin/gStoreGoodsSku/saveGStoreGoodsSku`, params);}
export const enterpriseRegister = (params) => {return axios.get( `${baseUrl}/login/appAdmin/enterpriseRegister`, params);}
export const getPhoneList = (params) => {return axios.get( `${baseUrl}/appAdmin/phone/phone/phoneList`, params);}







