import {createRouter, createWebHashHistory} from 'vue-router'


export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            component: import('@/views/Home.vue')
        },
        {
            path: '/about',
            component: import('@/views/Home.vue')
        },
    ]
})
