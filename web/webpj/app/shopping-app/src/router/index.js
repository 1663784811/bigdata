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
                    component: () => import('@/views/login/Login.vue'),
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
                    component: () => import('@/views/my/User.vue'),
                    meta: {
                        index: 1,
                        title: '用户'
                    }
                },
                {
                    path: 'product-list',
                    name: 'product-list',
                    component: () => import('@/views/goods/ProductList.vue'),
                    meta: {
                        index: 2,
                        notLogin: true,
                        title: '商品列表'
                    }
                },
                {
                    path: 'category',
                    name: 'category',
                    component: () => import('@/views/goods/Category.vue'),
                    meta: {
                        index: 1,
                        notLogin: true,
                        title: '分类'
                    }
                },
                {
                    path: 'product/:id',
                    name: 'product',
                    component: () => import('@/views/goods/ProductDetail.vue'),
                    meta: {
                        index: 3,
                        notLogin: true,
                    }
                },
                {
                    path: 'cart',
                    name: 'cart',
                    component: () => import('@/views/goods/Cart.vue'),
                    meta: {
                        index: 1,
                        notLogin: true,
                        title: '购物车'
                    }
                },
                {
                    path: 'createOrder',
                    name: 'createOrder',
                    component: () => import('@/views/order/CreateOrder.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'address',
                    name: 'address',
                    component: () => import('@/views/my/Address.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'address-edit',
                    name: 'address-edit',
                    component: () => import('@/views/my/AddressEdit.vue'),
                    meta: {
                        index: 3
                    }
                },
                {
                    path: 'order',
                    name: 'order',
                    component: () => import('@/views/order/Order.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'orderDetail',
                    name: 'orderDetail',
                    component: () => import('@/views/order/OrderDetail.vue'),
                    meta: {
                        index: 3
                    }
                },
                {
                    path: 'setting',
                    name: 'setting',
                    component: () => import('@/views/my/Setting.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'about',
                    name: 'about',
                    component: () => import('@/views/my/About.vue'),
                    meta: {
                        index: 2
                    }
                },
                {
                    path: 'payOrder',
                    name: 'payOrder',
                    component: () => import('@/views/order/PayOrder.vue'),
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
