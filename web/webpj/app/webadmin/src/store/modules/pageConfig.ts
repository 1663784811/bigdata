import { RouteRecordRaw } from 'vue-router'
import { defineStore } from 'pinia'
import useUserStore from './user'
import router from '@/router'
import { baseAddress, getMenuListByRoleId } from '@/api/url'
import { post } from '@/api/http'
import { findRootPathRoute, generatorRoutes, mapTwoLevelRouter } from '../help'
import { constantRoutes } from '@/router/routes/constants'

const pageConfig = defineStore('pageConfig', {
  state: () => {
    return {
      pageDate: {} as any,
    }
  },
  getters: {
    // getPageConfig(state) {
    //   console.log('seeeeeeeeeeeeeeeeeeeee', state)
    //
    //   return null
    // },
  },
  actions: {
    async getPageConfig(data?: { userId: number; roleId: number }) {
      console.log(data)
      return {
        aa: 'dd',
      }
    },
  },
})

export default pageConfig
