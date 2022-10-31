import { defineStore } from 'pinia'

import company from './pagesetting/company'
import people from './pagesetting/people'
import SpiderNickName from './pagesetting/SpiderNickName'
import { getPageConfig } from '@/api/api'
import { generatorRoutes } from '@/store/help'

const pageConfig = defineStore('pageConfig', {
  state: () => {
    return {
      pageData: {
        company,
        people,
        SpiderNickName,
      } as any,
    }
  },
  getters: {},
  actions: {
    async getRoutes(data: {}) {
      try {
        const res = await getPageConfig(data)
        console.log('ssddss', res.data)
        return generatorRoutes(res.data)
      } catch (error) {
        console.log(
          '路由加载失败了，请清空一下Cookie和localStorage，重新登录；如果已经采用真实接口的，请确保菜单接口地址真实可用并且返回的数据格式和mock中的一样'
        )
        return []
      }
    },
  },
})

export default pageConfig
