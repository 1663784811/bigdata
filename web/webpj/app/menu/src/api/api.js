import {AJAXGET, AJAXPOST, asyncREQUEST} from "@/api/webinfo";

const baseUrl = "http://127.0.0.1:8080";

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
 * 获取管理员菜单
 */
export const apiAdminMenu = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/user/adminMenu`, parameter, 1)
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

/**
 * 登录
 */
export const logInFn = (parameter) => {
    return asyncREQUEST(`${baseUrl}/login/admin/login`, parameter, 1, "post")
}

export const userInfo = (parameter) => {
    return asyncREQUEST(`${baseUrl}/admin/user/info`, parameter, 0, "get")
}

/**
 * 获取企业信息
 */
export const enterpriseFindPage = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/enterprise/findPage`, parameter, 0)
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



// =================================================
/**
 * 查商品品类
 */
export const getGType = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/gType/findPage`, parameter, 0)
}


































