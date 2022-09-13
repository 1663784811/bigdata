import { baseUrl } from '@/settings'
import { AJAXPOST, AJAXGET } from '@/utils/requestInterface'
import { ${__Table__} } from './types'

export const default${__Table__}Data: ${__Table__} = {
${typeScriptType.defaultData}
}
// 增加 / 修改
export const save${__Table__} = (params: any) => AJAXPOST(`${'$'}{baseUrl}/admin/${__table__all__}/save${__Table__}`, params, 1)
// 删除
export const del${__Table__} = (params: any) => AJAXPOST(`${'$'}{baseUrl}/admin/${__table__all__}/del${__Table__}`, params, 1)
// 查询列表
export const findId${__Table__} = (params: any) => AJAXGET(`${'$'}{baseUrl}/admin/${__table__all__}/findId${__Table__}`, params)
// 条件分页查询
export const findPage${__Table__} = (params: any) => AJAXGET(`${'$'}{baseUrl}/admin/${__table__all__}/findPage${__Table__}`, params)
