import { defineStore } from 'pinia'

import company from './pagesetting/company'
import people from './pagesetting/people'
import SpiderNickName from './pagesetting/SpiderNickName'
import { getPageConfig } from '@/api/api'

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
    async getPageConfig(data: {}) {
      const res = await getPageConfig(data)
      this.$state.pageData = res.data
      console.log('=======sssss===========', this.$state.pageData)
    },
  },
})

export default pageConfig
