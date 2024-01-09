import {createRouter, createWebHashHistory} from 'vue-router'
import {useUserStore} from "@/stores/user.js";

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: 'index',
            component: () => import('@/views/Index.vue'),
            meta: {notLogin: true, title: '首页'}
        },
        {
            path: '/cyyaw/welcomePage',
            name: 'welcomePage',
            component: () => import('@/views/welcomePage.vue'),
            meta: {notLogin: true, title: '欢迎页面'}
        },
        {
            path: '/:appid',
            component: () => import('@/views/AppMain.vue'),
            children: [
                {
                    path: 'login',
                    name: 'login',
                    component: () => import('@/views/my/Login.vue'),
                    meta: {notLogin: true, title: '登录'}
                },
                {
                    path: 'home',
                    name: 'home',
                    component: () => import('@/views/home/Home.vue'),
                    meta: {index: 1, notLogin: true, title: '首页'}
                },
                {
                    path: 'user',
                    name: 'user',
                    component: () => import('@/views/my/User.vue'),
                    meta: {index: 1, notLogin: false, title: '我的'}
                },
                {
                    path: 'about',
                    name: 'about',
                    component: () => import('@/views/my/About.vue'),
                    meta: {index: 2, notLogin: true, title: '关于我们'}
                },
                {
                    path: 'saveBlack',
                    name: 'saveBlack',
                    component: () => import('@/views/black/SaveBlack.vue'),
                    meta: {notLogin: true, title: '添加小黑人'}
                },
                {
                    path: 'BlackPeopleDetails',
                    name: 'BlackPeopleDetails',
                    component: () => import('@/views/black/BlackPeopleDetails.vue'),
                },
                {
                    path: 'contacts',
                    name: 'contacts',
                    component: () => import('@/views/contacts/Contacts.vue'),
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
        console.log('ssssssssssssssssss', params)
        if (params.code) {
            next({
                name: 'login',
                params: {
                    appid: params.appid
                }
            })
        } else {
            next({
                name: 'welcomePage'
            })
        }
    } else {
        next()
    }
});


export default router
