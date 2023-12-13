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


export const useWinModal = defineStore('useWinModal', {
    state: () => {
        const winData = ref({
            url: '',
            columns: [],
            data: {},
            show: false,
            pageCode: '',
            loading: true
        });
        return {
            winData
        }
    }
})
