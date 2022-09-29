import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import MainLayOut from '@/views/MainLayOut.vue'
import User from '@/views/User.vue'
import Order from '@/views/Order.vue'

const routes = [
    {
        path: '/',
        name: 'home',
        component: MainLayOut,
        children:[
            {
                path: 'home',
                component: Home,
            },
            {
                path: 'user',
                component: User,
            },
            {
                path: 'order',
                component: Order,
            }
        ]
    },
    {
        path: '/spider',
        name: 'spider',
        component: Home
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(),
    scrollBehavior() {
        return { top: 0 }
    }
})

export default router
