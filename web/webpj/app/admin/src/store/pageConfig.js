import {defineStore} from 'pinia'
import {ref} from "vue";
import {sql} from "@/api/mock/sql.js";
import {role} from "@/api/mock/role.js";
import {power} from "@/api/mock/power.js";
import {user} from "@/api/mock/user.js";


export const pageConfig = defineStore('pageConfig', () => {
    const pageConfigList = ref({
        sql,
        role,
        power,
        user
    })
    const getPageConfig = (pageCode) => {
        return pageConfigList.value[pageCode]
    }
    return {pageConfigList, getPageConfig}
})
