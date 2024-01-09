import {ref} from 'vue'
import {defineStore} from 'pinia'

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
        const variable = ref({
            appid: ''
        });
        return {
            userInfo,
            token,
            variable
        }
    }
})
