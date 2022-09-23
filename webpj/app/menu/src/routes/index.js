import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import LayOut from '@/views/LayOut.vue'

const routes = [
    {
        path: '/',
        name: 'home',
        component: LayOut,
        children:[
            {
                path: 'profile',
                component: Home,
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
