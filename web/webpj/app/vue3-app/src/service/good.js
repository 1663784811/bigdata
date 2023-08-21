import axios from '../utils/axios'
import {baseUrl, enterpriseId} from '@/service/webConfig.js'


export function getDetail(id) {
  return axios.get(`/goods/detail/${id}`);
}

export function getCategory() {
  return axios.get('/categories');
}

export function search(params) {
  return axios.get('/search', { params });
}

// ================================================

export const goodsDetails = (params = {}) => {
  return axios.get(`${baseUrl}/shopping/goods/search/goodsDetails`,{ params });
}

export const goodsDetailsText = (params = {}) => {
  return axios.get(`${baseUrl}/shopping/goods/search/goodsDetailsText`,{ params });
}

export const goodsPhoto = (params = {}) => {
  return axios.get(`${baseUrl}/shopping/goods/search/goodsPhoto`,{ params });
}

export const enterpriseType = (params = {}) => {
  return axios.get(`${baseUrl}/shopping/goods/type/enterpriseType`,{ params });
}
