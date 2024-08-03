import {defineStore} from 'pinia'
import {ref} from "vue";

/**
 * 配置模块
 */
export const useConfigModule = defineStore('configModule', {
    persist: {
        enabled: true,
        strategies: [
            {
                key: 'configModule',
                storage: localStorage,
            }
        ]
    },
    state: () => {
        // 配置页面
        const configPage = ref({
            show: false,
            showOperation: false,
            select: '',
            data: {},
            pageId: '',
            pageCode: '',
            tabsName: '',
        });
        // sql 页面
        const sqlConfig = ref({
            show: false
        });
        // sql 编辑
        const sqlModal = ref({
            editor: true,
            show: true,
            data: {},
            callBack: null
        });
        return {
            configPage,
            sqlConfig,
            sqlModal
        }
    }
})
