import axios from "./axiosRequest";
const baseUrl = import.meta.env.VITE_BASE_URL;

import {AJAXGET, AJAXPOST, asyncREQUEST} from "@/api/webinfo";
// =====================================================================
// =====================================================================
// =====================================================================
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

// =====================================================================
// =====================================================================
// =====================================================================

//获取企业信息
export const enterpriseFindPage = (parameter, eCode) => {return axios.get( `${baseUrl}/admin/${eCode}/enterprise/enterpriseInfo`, parameter);}
// 获取菜单
export const apiAdminMenu = (parameter, eCode) => {return axios.get( `${baseUrl}/admin/${eCode}/user/menu`, parameter);}















export const commonRequest = (url, parameter, type = 'get') => {
    if (type === 'post') {
        return AJAXPOST(`${baseUrl}${url}`, parameter, 1)
    } else {
        return AJAXGET(`${baseUrl}${url}`, parameter)
    }
}


/**
 * 获取表格数据
 */
export const getSqlList = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/common/sql/sqlList`, parameter)
}

/**
 * 保存SQL
 */
export const saveSql = (parameter) => {
    return AJAXPOST(`${baseUrl}/admin/common/sql/saveSql`, parameter, 1)
}


/**
 * 查询系统菜单
 */
export const queryMenu = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/power/queryMenu`, parameter, 1)
}

/**
 * 保存系统菜单
 */
export const saveMenu = (parameter) => {
    return AJAXPOST(`${baseUrl}/admin/power/saveMenu`, parameter, 1)
}

/**
 * 删除系统菜单
 */
export const delMenu = (parameter) => {
    return AJAXPOST(`${baseUrl}/admin/power/delMenu`, parameter, 1)
}

/**
 * 获取页面设置
 */
export const pageSetting = (parameter) => {
    return asyncREQUEST(`${baseUrl}/tx/config/page/pageSetting`, parameter, 0)
}

/**
 * 获取页面设置
 */
export const findSetting = (parameter) => {
    return asyncREQUEST(`${baseUrl}/tx/config/page/findSetting`, parameter, 0)
}
/**
 * 获取页面设置
 */
export const saveComponents = (parameter, showMsg = false) => {
    return AJAXPOST(`${baseUrl}/tx/config/page/saveComponents`, parameter, 1, showMsg)
}





export const findIdCPageComponents = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/config/cpagecomponents/findIdCPageComponents`, parameter, 0)
}

export const findCPageComponents = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/config/cpagecomponents/findPage`, parameter, 0)
}


export const loadTable = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/buildCode/loadTable`, parameter, 0)
}


/**
 * 查询企业部门
 */
export const dss = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/buildCode/loadTable`, parameter, 0)
}


export const findIdGGoods = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/gGoods/findIdGGoods`, parameter, 0)
}


export const goodsPhoto = (parameter) => {
    return AJAXGET(`${baseUrl}/shopping/goods/search/goodsPhoto`, parameter, 0)
}


export const findGoodsSku = (parameter) => {
    return AJAXGET(`${baseUrl}/shopping/admin/findGoodsSku`, parameter, 0)
}

export const saveGGoods = (parameter, showMsg = false) => {
    return AJAXPOST(`${baseUrl}/admin/gGoods/saveGGoods`, parameter, 1, showMsg)
}

export const saveGStoreGoodsSku = (parameter, showMsg = false) => {
    return AJAXPOST(`${baseUrl}/admin/gStoreGoodsSku/saveGStoreGoodsSku`, parameter, 1, showMsg)
}


export const findPageWebImage = (parameter, showMsg = false) => {
    return AJAXGET(`${baseUrl}/admin/image/findPageWebImage`, parameter, 0);
}


export const enterpriseRegister = (parameter, showMsg = false) => {
    return AJAXPOST(`${baseUrl}/login/admin/enterpriseRegister`, parameter, 1, showMsg)
}


export const getPhoneList = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/phone/phone/phoneList`, parameter)
}