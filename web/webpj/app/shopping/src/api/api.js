import {AJAXGET} from './webinfo.js'




export const getBanner = (params = {}) => {
    return AJAXGET('http://127.0.0.1:8080/web/banner/findBanner', params);
}

export const getEnterpriseType = (params = {}) => {
    return AJAXGET('http://127.0.0.1:8080/goods/type/enterpriseType', params);
}
