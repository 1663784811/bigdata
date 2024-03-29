import axios from 'axios'
import {loginInfo} from "@/store/loginInfo.js"

const instance = axios.create();

instance.defaults.withCredentials = true
instance.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
instance.defaults.headers.post['Content-Type'] = 'application/json'

/**
 * 请求拦截器
 */
instance.interceptors.request.use(config => {
    const userStore = loginInfo();
    config.headers['token'] = userStore.token;
    return config
}, error => {
    return Promise.reject(error)
})


/**
 * 响应拦截器
 */
instance.interceptors.response.use(res => {
    if (typeof res.data !== 'object') {
        console.error('服务端异常！')
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
    console.error('服务端异常！')
    return Promise.reject(error)
})

export default instance
 