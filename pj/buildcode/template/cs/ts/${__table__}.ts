import service from '@/utils/service'
import { ${__Table__} } from './types'

export const default${__Table__}Data: ${__Table__} = {
  ${typeScriptType.defaultData}
}
// 增加 / 修改
export const ${__table__}Save = (params: any) => service({url: '${__table__}Admin/save', method: 'post', params})
// 删除
export const ${__table__}Delete = (params: any) => service({url: '${__table__}Admin/delete', method: 'get', params})
// 根ID 查询
export const ${__table__}ListByQuery = (params: any) => service({url: '${__table__}Admin/getListByQuery', method: 'get', params})
// 查询列表
export const ${__table__}ById = (params: any) => service({url: '${__table__}Admin/getById', method: 'get', params})
// 条件通用查询
export const ${__table__}ByCondition = (params: any) => service({url: '/${__table__}Admin/selectListByCondition', method: 'get', params})