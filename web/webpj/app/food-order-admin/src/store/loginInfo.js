import {defineStore} from 'pinia'
import {ref} from "vue";

export const loginInfo = defineStore('loginInfo', {
    persist: {
        enabled: true,//开启数据持久化
        strategies: [
            {
                key: 'shopping_admin_loginInfo',
                storage: localStorage,
            }
        ]
    },
    state: () => {
        const variable = ref({
            eCode: '',
            appId: '',
            storeId: '',
        })
        const userInfo = ref({});
        const token = ref("");
        // 企业信息
        const enterpriseInfo = ref({})
        // 门店信息
        const storeInfo = ref({
            tid: 'dd',
            name: '听心一号店'
        });

        /**
         * 解释地址
         */
        const reLoadUrl = (url) => {
            for (const key in variable.value) {
                const val = variable.value[key];
                url = url.replaceAll('${' + key + '}', val ? val : '');
            }
            return url;
        }
        /**
         * 解释参数
         */
        const reLoadParameter = (obj) => {
            if (obj) {
                for (const objKey in obj) {
                    for (const key in variable.value) {
                        const val = variable.value[key];
                        obj[objKey] = obj[objKey].replaceAll('${' + key + '}', val ? val : '');
                    }
                }
            }
            return obj;
        }
        return {
            userInfo,
            token,
            enterpriseInfo,
            storeInfo,
            reLoadUrl,
            reLoadParameter,
            variable
        }
    }
})
