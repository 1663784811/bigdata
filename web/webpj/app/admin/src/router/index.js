import {createRouter, createWebHashHistory} from 'vue-router'


export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
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
