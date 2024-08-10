import {defineStore} from 'pinia'
import {ref} from "vue";
import {pageSetting} from "@/api/api.js";


export const pageConfig = defineStore('pageConfig', {
    persist: {
        enabled: false,//开启数据持久化
        strategies: [
            {
                key: 'pageConfig',
                storage: localStorage,
            }
        ]
    },
    state: () => {
        const pageConfigList = ref({});
        const componentConfig = ref({
            show: false,
            pageCodeList: {}
        });
        const getPageConfig = async (pageCode) => {
            componentConfig.value.pageCodeList[pageCode] = pageCode
            let codeData = pageConfigList.value[pageCode]
            if (!codeData) {
                const loadData = await pageSetting({pageCode: pageCode, url: window.location.href});
                if (loadData.code === 2000) {
                    console.log(pageCode, "获取远程配置:", loadData.data)
                    codeData = loadData.data;
                    // codeData = pageConfigList.value[pageCode] = loadData.data;
                } else {
                    console.log(pageCode, "获取本地配置:", codeData)
                }
            } else {
                console.log(pageCode, "获取本地配置:", codeData)
            }
            return codeData;
        }


        return {
            getPageConfig,
            componentConfig
        }
    }
})
