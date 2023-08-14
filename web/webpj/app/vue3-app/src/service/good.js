import axios from '../utils/axios'

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
  return axios.get('http://127.0.0.1:8080/shopping/goods/search/goodsDetails',{ params });
}

export const goodsDetailsText = (params = {}) => {
  return axios.get('http://127.0.0.1:8080/shopping/goods/search/goodsDetailsText',{ params });
}

export const goodsPhoto = (params = {}) => {
  return axios.get('http://127.0.0.1:8080/shopping/goods/search/goodsPhoto',{ params });
}
