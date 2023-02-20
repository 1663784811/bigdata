import {defineStore} from 'pinia'
import {ref} from "vue";
import {sql} from "@/api/mock/sql.js";
import {role} from "@/api/mock/role.js";
import {power} from "@/api/mock/power.js";


export const pageConfig = defineStore('pageConfig', () => {
    const pageConfigList = ref({
        sql,
        role,
        power
    })
    const getPageConfig = (pageCode) => {
        return pageConfigList.value[pageCode]
    }
    return {pageConfigList, getPageConfig}
})
