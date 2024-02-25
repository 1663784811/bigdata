import {defineStore} from 'pinia'
import {reactive, ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

export const useUploadFileStore = defineStore('uploadFile', {
    persist: {
        enabled: false
    }, state: () => {
        const uploadFile = ref({
            show: false,
            callBack: (data) => {

            },
        })

        const eventFn = (data) => {
            console.log(data)
            if (uploadFile.value.callBack) {
                uploadFile.value.callBack(data);
            }
        }
        return {
            eventFn, uploadFile
        }
    }
})
