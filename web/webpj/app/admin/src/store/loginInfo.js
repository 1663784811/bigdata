import {defineStore} from 'pinia'
import {ref} from "vue";

export const loginInfo = defineStore('loginInfo', {
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
        const eCode = ref("aaa")
        return {
            userInfo,
            token,
            eCode
        }
    }
})
