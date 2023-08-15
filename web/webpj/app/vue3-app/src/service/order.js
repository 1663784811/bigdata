
import axios from '../utils/axios'

export function createOrder(params) {
  return axios.post('/saveOrder', params);
}


export function cancelOrder(id) {
  return axios.put(`/order/${id}/cancel`);
}

export function confirmOrder(id) {
  return axios.put(`/order/${id}/finish`)
}

export function payOrder(params) {
  return axios.get('/paySuccess', { params })
}



// =============================================

/**
 * 订单列表
 */
export function getOrderList(params) {
  return axios.get('http://127.0.0.1:8080/shopping/goods/order/query', { params });
}

/**
 * 订单详情
 */
export function getOrderDetail(params) {
  return axios.get('http://127.0.0.1:8080/shopping/goods/order/orderById', { params });
}