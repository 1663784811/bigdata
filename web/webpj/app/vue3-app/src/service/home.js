import axios from '../utils/axios'
import {AJAXGET} from './webinfo.js'

export function getHome() {
    return axios.get('/index-infos');
}

export const getBanner = (params = {}) => {
    return AJAXGET('http://127.0.0.1:8080/web/banner/findBanner', params);
}