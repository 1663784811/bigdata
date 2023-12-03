import {defineStore} from 'pinia'
import {ref} from "vue";
import {sql} from "@/api/mock/sql.js";
import {role} from "@/api/mock/role.js";
import {power} from "@/api/mock/power.js";
import {user} from "@/api/mock/user.js";
import {enterprise} from "@/api/mock/enterprise.js";
import {store} from "@/api/mock/store.js";
import {cPage} from "@/api/mock/cPage.js";
import {PageComponents} from "@/api/mock/PageComponents.js";
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
            console.log("=========== 获取配置 =============  ", pageCode)
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
            sql,
            // role,
            power,
            // user,
            // enterprise,
            store,
            // cPage,
            // PageComponents,
            getPageConfig,
            componentConfig
        }
    }
})
