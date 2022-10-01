import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import MainLayOut from '@/views/MainLayOut.vue'
import User from '@/views/User.vue'
import Order from '@/views/Order.vue'
import StoreHome from "@/views/StoreHome.vue";

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
        path: '/StoreHome',
        name: 'StoreHome',
        component: StoreHome
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
