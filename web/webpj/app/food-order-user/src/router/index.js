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
            path: '/:appid/store/:storeId',
            component: () => import('@/views/AppMain.vue'),
            children: [
                {
                    path: 'home/:code',
                    name: 'home',
                    component: () => import('@/views/Home.vue'),
                    meta: {index: 1, title: '点菜'}
                },
                {
                    path: 'selectNumber/:code',
                    name: 'selectNumber',
                    component: () => import('@/views/SelectNumber.vue'),
                    meta: {notLogin: true, title: '选择用餐人数'}
                },
                {
                    path: 'order/:code',
                    name: 'order',
                    component: () => import('@/views/Order.vue'),
                    meta: {index: 1, title: '订单'}
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
        if (params.appid) {
            next({name: 'selectNumber', params})
        } else {
            next({name: 'welcomePage'})
        }
    } else {
        next()
    }
});


export default router
