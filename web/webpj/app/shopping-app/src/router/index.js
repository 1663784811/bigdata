import {createRouter, createWebHashHistory} from 'vue-router'
import {useUserStore} from "@/stores/user.js";

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            redirect: '/cyyaw/welcomePage'
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
                    component: () => import('@/views/Login.vue'),
                    meta: {
                        notLogin: true,
                        title:'登录'
                    }
                },
                {
                    path: 'home',
                    name: 'home',
                    component: () => import('@/views/home/Home.vue'),
                    meta: {
                        index: 1,
                        notLogin: true,
                        title: '首页'
                    }
                },
                {
                    path: 'user',
                    name: 'user',
                    component: () => import('@/views/User.vue'),
                    meta: {
                        index: 1,
                        title: '用户'
                    }
                },
                {
                    path: 'product-list',
                    name: 'product-list',
                    component: () => import('@/views/ProductList.vue'),
                    meta: {
                        index: 2,
                        notLogin: true,
                        title: '商品列表'
                    }
                },
                {
                    path: 'category',
                    name: 'category',
                    component: () => import('@/views/Category.vue'),
                    meta: {
                        index: 1,
                        notLogin: true,
                        title: '分类'
                    }
                },
                {
                    path: 'product/:id',
                    name: 'product',
                    component: () => import('@/views/ProductDetail.vue'),
                    meta: {
                        index: 3,
                        notLogin: true,
                    }
                },
                {
                    path: 'cart',
                    name: 'cart',
                    component: () => import('@/views/Cart.vue'),
                    meta: {
                        index: 1,
                        notLogin: true,
                        title: '购物车'
                    }
                },
                {
                    path: 'create-order',
                    name: 'create-order',
                    component: () => import('@/views/CreateOrder.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'address',
                    name: 'address',
                    component: () => import('@/views/Address.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'address-edit',
                    name: 'address-edit',
                    component: () => import('@/views/AddressEdit.vue'),
                    meta: {
                        index: 3
                    }
                },
                {
                    path: 'order',
                    name: 'order',
                    component: () => import('@/views/Order.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'order-detail',
                    name: 'order-detail',
                    component: () => import('@/views/OrderDetail.vue'),
                    meta: {
                        index: 3
                    }
                },
                {
                    path: 'setting',
                    name: 'setting',
                    component: () => import('@/views/Setting.vue'),
                    meta: {
                        index: 2
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
        console.log("ssssss", params.appid)
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
