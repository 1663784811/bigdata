import axios from '../utils/axios'
import {baseUrl, enterpriseId} from '@/service/webConfig.js'




export function cancelOrder(id) {
  return axios.put(`/order/${id}/cancel`);
}

export function confirmOrder(id) {
  return axios.put(`/order/${id}/finish`)
}

export function payOrder(params) {
  return axios.get(`/paySuccess`, { params })
}



// =============================================

/**
 * 订单列表
 */
export function getOrderList(params) {
  return axios.get(`${baseUrl}/shopping/goods/order/query`, { params });
}

/**
 * 订单详情
 */
export function getOrderDetail(params) {
  return axios.get(`${baseUrl}/shopping/goods/order/orderById`, { params });
}

/**
 * 创建订单
 */
export function createOrder(params) {
  return axios.post(`${baseUrl}/shopping/goods/order/createOrder`, params);
}



