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
    persist: {
        enabled: false
    },
    state: () => {
        const winData = ref({
            url: '',
            columns: [],
            data: {},
            show: false,
            pageCode: '',
            loading: true,
            changeDataFn: null,
            saveAfterFn: null,
        });
        const winMqtt = ref({
            code: '',   // 设备编号
            name: '',  // 设备名
            show: true, // 显示连接框
            callBack: null // 回调（ 没想好用在什么地方 ）
        });

        return {
            winData,
            winMqtt
        }
    }
})
