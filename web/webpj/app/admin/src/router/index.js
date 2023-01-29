import {createRouter, createWebHashHistory} from 'vue-router'


export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/login',
            name: 'login',
            meta: {
                title: 'Login - 登录',
                hideInMenu: true
            },
            component: () => import('@/views/login/index.vue')
        },
        {
            path: '/',
            component: import('@/views/home/Home.vue')
        },
        {
            path: '/login',
            component: import('@/views/login/index.vue')
        },
    ]
})
