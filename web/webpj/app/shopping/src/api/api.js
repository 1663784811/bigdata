import {AJAXGET} from './webinfo.js'




export const getBanner = (params = {}) => {
    return AJAXGET('http://127.0.0.1:8080/shopping/web/banner/findBanner', params);
}

export const getEnterpriseType = (params = {}) => {
    return AJAXGET('http://127.0.0.1:8080/shopping/goods/type/enterpriseType', params);
}

export const searchGoods = (params = {}) => {
    return AJAXGET('http://127.0.0.1:8080/shopping/goods/search/searchGoods', params);
}


