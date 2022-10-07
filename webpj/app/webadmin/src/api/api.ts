import { post } from '@/api/http'

export const loginAdmin = (parameter: any) => {
  return post({
    url: '/admin/login',
    data: parameter,
  })
}

export const getCompany = (parameter: any) => {
  return post({
    url: 'http://cyyaw.com/admin/login',
    data: parameter,
  })
}

export const getSqlList = (parameter: any) => {
  return post({
    url: 'http://127.0.0.1:8080/home/common/sqlList',
    data: parameter,
  })
}
