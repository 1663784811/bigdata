import {defineStore} from 'pinia'
import {reactive, ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

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
        const sqlModal = ref({
            show: true,
            data: {}
        });
        return {
            configPage,
            sqlConfig,
            sqlModal
        }
    }
})
