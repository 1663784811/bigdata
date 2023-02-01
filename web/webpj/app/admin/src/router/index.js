import {createRouter, createWebHashHistory} from 'vue-router'


export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/login',
            name: 'login',
            meta: {
                title: 'Login - 登录',
                hideInMenu: true
            },
            component: () => import('@/views/login/index.vue')
        },
        {
            path: '/',
            component: import('@/views/layout/Main.vue'),
            children:[
                {
                    path:'/sql/config',
                    component: import('@/views/sql/Config.vue'),
                    name: 'sqlConfig'
                },
                {
                    path:'/spider/Monitor',
                    component: import('@/views/spider/spiderMonitor.vue'),
                    name: 'spiderMonitor'
                },
                {
                    path:'/spider/Data',
                    component: import('@/views/spider/spiderData.vue'),
                    name: 'spiderData'
                },
                {
                    path:'/enterprise/List',
                    component: import('@/views/enterprise/List.vue'),
                    name: 'enterprise'
                },
                {
                    path:'/shopping/shoppingList',
                    component: import('@/views/shopping/ShoppingList.vue'),
                    name: 'shopping'
                }
            ]
        }
    ]
})
