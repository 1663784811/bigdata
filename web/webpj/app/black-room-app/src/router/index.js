import {createRouter, createWebHashHistory} from 'vue-router'
import {useUserStore} from "@/stores/user.js";

import Home from '@/views/home/Home.vue'


const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            redirect: '/hello/home'
        },
        {
            path: '/:appid',
            children: [
                {
                    path: 'login',
                    name: 'login',
                    component: () => import('@/views/Login.vue'),
                    meta: {
                        notLogin: true
                    }
                },
                {
                    path: 'home',
                    name: 'home',
                    component: Home,
                    meta: {
                        index: 1
                    }
                },
                {
                    path: 'user',
                    name: 'user',
                    component: () => import('@/views/User.vue'),
                    meta: {
                        index: 1
                    }
                },
                {
                    path: 'category',
                    name: 'category',
                    component: () => import('@/views/Category.vue'),
                    meta: {
                        index: 1
                    }
                },
                {
                    path: 'about',
                    name: 'about',
                    component: () => import('@/views/About.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'payOrder',
                    name: 'payOrder',
                    component: () => import('@/views/PayOrder.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'SignInDetails',
                    name: 'SignInDetails',
                    component: () => import('@/views/signin/SignInDetails.vue'),
                },
                {
                    path: 'contacts',
                    name: 'contacts',
                    component: () => import('@/views/contacts/Contacts.vue'),
                },
                {
                    path: 'SignInPage',
                    name: 'SignInPage',
                    component: () => import('@/views/signin/SignInPage.vue'),
                    meta: {
                        notLogin: true
                    }
                },
                {
                    path: 'saveSignIn',
                    name: 'saveSignIn',
                    component: () => import('@/views/signin/SaveSignIn.vue'),
                },
            ]
        }
    ]
})

router.beforeEach(({meta = {}, name, params}, from, next) => {
    const {title, notLogin} = meta;
    if (title) document.title = title;
    const useUser = useUserStore();
    let token = useUser.token;
    if (!token && !notLogin) {
        // 未登录
        next({
            name: 'login',
            params:{
                appid: params.appid
            }
        })
    } else {
        next()
    }
});


export default router
