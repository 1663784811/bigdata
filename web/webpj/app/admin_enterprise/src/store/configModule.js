import {defineStore} from 'pinia'
import {reactive, ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

/**
 * 配置模块
 */
export const useConfigModule = defineStore('configModule',
    {
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
            const configPage = ref({
                show: false,
                showOperation: false,
                select: '',
                data: {},
                pageId: '',
                pageCode: '',
                tabsName: '',
            });
            const sqlConfig = ref({
                show: false
            })
            return {
                sqlConfig,
                configPage
            }
        }
    })
