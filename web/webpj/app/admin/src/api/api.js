import {AJAXGET} from "@/api/webinfo";

const baseUrl = "http://127.0.0.1:8080";


/**
 * 获取表格数据
 */
export const getSqlList = (parameter) => {
    return AJAXGET(`${baseUrl}/admin/common/sqlList`, parameter)
}