import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import MainLayOut from '@/views/MainLayOut.vue'
import About from "@/views/About.vue";
import Project from "@/views/Project.vue";
import Wechat from "@/views/Wechat.vue";
import AppDevelopment from "@/views/AppDevelopment.vue";

const routes = [
    {
        path: '/',
        name: 'root',
        component: MainLayOut,
        children:[
            {
                path: '',
                name:'home',
                component: Home,
            },
            {
                path: 'appDevelopment',
                component: AppDevelopment,
            },
            {
                path: 'project',
                component: Project,
            },
            {
                path: 'wechat',
                component: Wechat,
            },
            {
                path: 'about',
                component: About,
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
