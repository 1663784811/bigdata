import axios from 'axios'
import {showToast, showFailToast} from 'vant'
import {setLocal} from '@/common/js/utils'
import router from '../router'
import {useUserStore} from "@/stores/user.js";


console.log('import.meta.env', import.meta.env)

axios.defaults.baseURL = import.meta.env.MODE == 'development' ? '//backend-api-01.newbee.ltd/api/v1' : '//backend-api-01.newbee.ltd/api/v1'
axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers.post['Content-Type'] = 'application/json'

/**
 * 请求拦截器
 */
axios.interceptors.request.use(config => {
    const userStore = useUserStore();
    config.headers['token'] = userStore.token;
    return config
}, error => {
    return Promise.reject(error)
})


/**
 * 响应拦截器
 */
axios.interceptors.response.use(res => {
    if (typeof res.data !== 'object') {
        showFailToast('服务端异常！')
        return Promise.reject(res)
    }
    // if (res.data.code != 2000) {
    //     if (res.data.message) showFailToast(res.data.message)
    //     // if (res.data.resultCode == 416) {
    //     //   router.push({ path: '/login' })
    //     // }
    //     if (res.data.data && window.location.hash == '#/login') {
    //         setLocal('token', res.data.data)
    //         axios.defaults.headers['token'] = res.data.data
    //     }
    //     console.log("=============================")
    //     return Promise.reject(res.data)
    // }
    return res.data
}, error => {
    return Promise.reject(error)
})

export default axios
 