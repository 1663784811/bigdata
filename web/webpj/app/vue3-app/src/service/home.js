import axios from '../utils/axios'

export function getHome() {
    return axios.get('/index-infos');
}

export const getBanner = (params = {}) => {
    return axios.get('http://127.0.0.1:8080/shopping/web/banner/findBanner',{ params });
}

export const searchGoods = (params = {}) => {
    return axios.get('http://127.0.0.1:8080/shopping/goods/search/searchGoods',{ params });
}

