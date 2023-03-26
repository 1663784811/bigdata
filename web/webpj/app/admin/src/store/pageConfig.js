import {defineStore} from 'pinia'
import {ref} from "vue";
import {sql} from "@/api/mock/sql.js";
import {role} from "@/api/mock/role.js";
import {power} from "@/api/mock/power.js";
import {user} from "@/api/mock/user.js";
import {enterprise} from "@/api/mock/enterprise.js";
import {store} from "@/api/mock/store.js";
import {pageSetting} from "@/api/api.js";


export const pageConfig = defineStore('pageConfig', () => {
    const pageConfigList = ref({
        sql,
        role,
        power,
        user,
        enterprise,
        store
    })
    const getPageConfig = (pageCode) => {
        let codeData = pageConfigList.value[pageCode]
        const loadData = pageSetting({code: pageCode});
        console.log("sssssssssssssssssssssssssss",loadData)
        if (!codeData) {
            const loadData = pageSetting({code: pageCode});
            if (loadData.code === 2000) {
                codeData = pageConfigList.value[pageCode] = loadData.data;
            }
        }
        return codeData;
    }
    return {pageConfigList, getPageConfig}
})
