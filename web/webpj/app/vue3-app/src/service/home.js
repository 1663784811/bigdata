import axios from '../utils/axios'
import {baseUrl, enterpriseId} from '@/service/webConfig.js'


export const getBanner = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/web/banner/findBanner`,{ params });
}

export const searchGoods = (params = {}) => {
    return axios.get(`${baseUrl}/shopping/goods/search/searchGoods`,{ params });
}

