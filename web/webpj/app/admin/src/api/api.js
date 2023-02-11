import {AJAXGET, AJAXPOST} from "@/api/webinfo";

const baseUrl = "http://127.0.0.1:8080";


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


export const apiAdminMenu = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/user/adminMenu`, parameter, 1)
}
