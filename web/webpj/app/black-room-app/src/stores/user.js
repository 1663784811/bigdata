import {ref} from 'vue'
import {defineStore} from 'pinia'
import {getCart} from '@/service/api'

export const useUserStore = defineStore('user', {
    persist: {
        enabled: true,//开启数据持久化
        strategies: [
            {
                key: 'loginInfo',
                storage: localStorage,
            }
        ]
    },
    state: () => {
        const userInfo = ref({});
        const token = ref("");
        return {
            userInfo,
            token
        }
    }
})
