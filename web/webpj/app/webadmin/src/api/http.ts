import {AxiosResponse} from 'axios'
import {App} from '@vue/runtime-core'
import request from './axios.config'

export interface HttpOption {
    url: string
    data?: any
    method?: string
    headers?: any
    beforeRequest?: () => void
    afterRequest?: () => void
}

export interface Response<T = any> {
    totalSize: number | 0
    code: number
    msg: string
    data: T
}

function http<T = any>({url, data, method, headers, beforeRequest, afterRequest}: HttpOption) {
    const successHandler = (res: AxiosResponse<Response<T>>) => {
        // @ts-ignore
        if (res.data && res.data.code === 2000) {
            return res.data
        } else {
            throw new Error(res.data.msg || '请求失败，未知异常')
        }
    }
    const failHandler = (error: Response<Error>) => {
        afterRequest && afterRequest()
        throw new Error(error.msg || '请求失败，未知异常')
    }
    beforeRequest && beforeRequest()
    method = method || 'GET'
    const params = Object.assign(typeof data === 'function' ? data() : data || {}, {})
    if (method === 'GET') {
        return request.get(
            url,
            {
                params,
                headers: headers
            }
        ).then(successHandler, failHandler);
    } else {
        return request.post(url, params, {headers: headers}).then(successHandler, failHandler)
    }
}

export function get<T = any>({
                                 url,
                                 data,
                                 method = 'GET',
                                 beforeRequest,
                                 afterRequest,
                             }: HttpOption): Promise<Response<T>> {
    return http<T>({
        url,
        method,
        data,
        beforeRequest,
        afterRequest,
    })
}

export function post<T = any>({
                                  url,
                                  data,
                                  method = 'POST',
                                  headers,
                                  beforeRequest,
                                  afterRequest,
                              }: HttpOption): Promise<Response<T>> {
    return http<T>({
        url,
        method,
        data,
        headers,
        beforeRequest,
        afterRequest,
    })
}

function install(app: App): void {
    app.config.globalProperties.$http = http

    app.config.globalProperties.$get = get

    app.config.globalProperties.$post = post
}

export default {
    install,
    get,
    post,
}

declare module '@vue/runtime-core' {
    // 为 `this.$` 提供类型声明
    interface ComponentCustomProperties {
        $get: <T>(options: HttpOption) => Promise<Response<T>>
        $post: <T>(options: HttpOption) => Promise<Response<T>>
    }
}
