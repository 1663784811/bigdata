import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useAppStore = defineStore('app', {
    persist: {
        enabled: true,//开启数据持久化
        strategies: [
            {
                key: 'appInfo',
                storage: localStorage,
            }
        ]
    },
    state: () => {
        const appInfo = ref({});
        return {
            appInfo
        }
    }
})
