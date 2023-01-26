import {get, post} from '@/api/http'

const baseUrl = "http://127.0.0.1:8080";


export const loginAdmin = (parameter: any) => {
    return post({
        url: '/admin/login',
        data: parameter,
    })
}

export const getCompany = (parameter: any) => {
    return post({
        url: `${baseUrl}/admin/common/query`,
        data: parameter,
    })
}

export const getSqlList = (parameter: any) => {
    return get({
        url: `${baseUrl}/admin/common/sqlList`,
        data: parameter,
    })
}

export const saveSql = (parameter: any) => {
    return post({
        url: `${baseUrl}/admin/common/saveSql`,
        data: parameter,
    })
}

export const getPageConfig = (parameter: any) => {
    return get({
        url: `${baseUrl}/admin/common/getPageConfig`,
        data: parameter,
    })
}
