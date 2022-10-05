import { post } from '@/api/http'

export const loginAdmin = (parameter) => post('/admin/login', parameter)
