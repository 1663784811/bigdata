import { defineStore } from 'pinia'

import company from './pagesetting/company'
import people from './pagesetting/people'
import SpiderNickName from './pagesetting/SpiderNickName'

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
  actions: {},
})

export default pageConfig
