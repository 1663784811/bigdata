import { defineStore } from 'pinia'

import company from './pagesetting/company'

const pageConfig = defineStore('pageConfig', {
  state: () => {
    return {
      pageData: {
        company,
      } as any,
    }
  },
  getters: {},
  actions: {},
})

export default pageConfig
