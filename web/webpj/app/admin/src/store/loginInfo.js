import {defineStore} from 'pinia'
import {ref} from "vue";

export const loginInfo = defineStore('loginInfo', () => {
    const userInfo = ref({});
    const token = ref("");


    return {
        userInfo,
        token
    }
})
