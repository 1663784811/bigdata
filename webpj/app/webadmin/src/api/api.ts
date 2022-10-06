import { post } from '@/api/http'

export const loginAdmin = (parameter) => post('/admin/login', parameter)

export const getCompany = (parameter) => {
  return post({
    url: 'http://cyyaw.com/admin/login',
    data: parameter,
  })
}
