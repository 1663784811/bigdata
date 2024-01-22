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
        const storeInfo = ref({
            tid: 'dd',
            name: '听心一号店'
        })
        return {
            userInfo,
            token,
            storeInfo
        }
    }
})
