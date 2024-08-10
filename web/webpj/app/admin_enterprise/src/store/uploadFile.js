import {defineStore} from 'pinia'
import {ref} from "vue";

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
