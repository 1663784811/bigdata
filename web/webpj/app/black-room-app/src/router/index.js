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
                    meta: {notLogin: true, title: '登录'}
                },
                {
                    path: 'home',
                    name: 'home',
                    component: Home,
                    meta: {index: 1, notLogin: true, title: '首页'}
                },
                {
                    path: 'user',
                    name: 'user',
                    component: () => import('@/views/my/User.vue'),
                    meta: {index: 1, notLogin: true, title: '我的'}
                },
                {
                    path: 'about',
                    name: 'about',
                    component: () => import('@/views/my/About.vue'),
                    meta: {index: 2, notLogin: true, title: '关于我们'}
                },
                {
                    path: 'saveSignIn',
                    name: 'saveSignIn',
                    component: () => import('@/views/black/SaveBlack.vue'),
                    meta: {notLogin: true, title: '添加小黑人'}
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
                }
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
            params: {
                appid: params.appid
            }
        })
    } else {
        next()
    }
});


export default router
