import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import MainLayOut from '@/views/MainLayOut.vue'
import Login from '@/views/Login.vue'


const routes = [
    {
        path: '/',
        name: 'home',
        component: MainLayOut,
        children:[
            {
                path: 'home',
                component: Home,
            }
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: Login
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
