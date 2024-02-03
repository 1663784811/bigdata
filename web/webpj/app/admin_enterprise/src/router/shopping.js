export const shopping = {
    path: 'shopping',
    children: [
        {
            path: 'shoppingDashboard',
            name: 'shoppingDashboard',
            component: () => import('@/views/app/shopping/ShoppingDashboard.vue'),
            meta: {title: '商城-概览'}
        },
        {
            path: 'shoppingUser',
            name: 'shoppingUser',
            component: () => import('@/views/app/shopping/ShoppingUser.vue'),
            meta: {title: '商城-用户管理'}
        },
        {
            path: 'shoppingList',
            name: 'shopping',
            component: () => import('@/views/app/shopping/ShoppingList.vue'),
            meta: {title: '商城-'}
        },
        {
            path: 'shoppingBanner',
            name: 'shoppingBanner',
            component: () => import('@/views/app/shopping/setting/ShoppingBanner.vue'),
            meta: {title: '商城-首页Banner'}
        },
        {
            path: 'shoppingBrand',
            name: 'shoppingBrand',
            meta: {title: '商城-首页品牌'},
            component: () => import('@/views/app/shopping/setting/ShoppingBrand.vue'),
        },
        {
            path: 'shoppingGType',
            name: 'shoppingGType',
            meta: {title: '商城-分类'},
            component: () => import('@/views/app/shopping/setting/ShoppingGType.vue'),
        },
        {
            path: 'shoppingStore',
            component: () => import('@/views/app/shopping/ShoppingStore.vue'),
            name: 'shoppingStore'
        },
        {
            path: 'orderList',
            name: 'orderList',
            component: () => import('@/views/app/shopping/order/ShoppingOrderList.vue'),
            meta: {title: '商城-订单列表'}
        },
        {
            path: 'weixinPay',
            name: 'weixinPay',
            component: () => import('@/views/app/shopping/pay/WeixinPay.vue'),
            meta: {title: '商城-微信支付'}
        },
        {
            path: 'aliPay',
            name: 'aliPay',
            component: () => import('@/views/app/shopping/pay/AliPay.vue'),
            meta: {title: '商城-支付宝支付'}
        },

    ]
}
