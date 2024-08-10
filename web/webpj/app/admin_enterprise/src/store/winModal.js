import {defineStore} from 'pinia'
import {ref} from "vue";


export const useWinModal = defineStore('useWinModal', {
    persist: {
        enabled: false
    },
    state: () => {
        /**
         * 数据窗口
         */
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
        /**
         * mqtt 窗口
         */
        const winMqtt = ref({
            code: '',   // 设备编号
            name: '',  // 设备名
            show: false, // 显示连接框
            callBack: null // 回调（ 没想好用在什么地方 ）
        });
        /**
         * icon 窗口
         */
        const winIcon = ref({
            show: false,
            callBack: null
        });
        /**
         * 查看代码
         */
        const winCode = ref({
            show: false,
            data: '',
            callBack: null
        });
        return {
            winData,
            winMqtt,
            winIcon,
            winCode
        }
    }
})
