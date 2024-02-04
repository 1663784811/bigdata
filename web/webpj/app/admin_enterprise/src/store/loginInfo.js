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
        const variable = ref({
            code: ''
        })
        const userInfo = ref({});
        const token = ref("");
        // 企业信息
        const enterpriseInfo = ref({})
        const reLoadUrl = (url) => {
            for (const key in variable.value) {
                url = url.replaceAll('${' + key + '}', variable.value[key]);
            }
            return url;
        }
        return {
            userInfo,
            token,
            enterpriseInfo,
            reLoadUrl,
            variable
        }
    }
})
