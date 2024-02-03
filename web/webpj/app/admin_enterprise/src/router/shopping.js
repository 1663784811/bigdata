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
            path: 'shoppingOrderList',
            name: 'shoppingOrderList',
            component: () => import('@/views/app/shopping/order/ShoppingOrderList.vue'),
            meta: {title: '商城-订单列表'}
        },
        {
            path: 'shoppingExceptionalOrder',
            name: 'shoppingExceptionalOrder',
            component: () => import('@/views/app/shopping/order/ShoppingExceptionalOrder.vue'),
            meta: {title: '商城-订单列表'}
        },
        {
            path: 'shoppingWeixinPay',
            name: 'shoppingWeixinPay',
            component: () => import('@/views/app/shopping/pay/ShoppingWeixinPay.vue'),
            meta: {title: '商城-微信支付'}
        },
        {
            path: 'shoppingAliPay',
            name: 'shoppingAliPay',
            component: () => import('@/views/app/shopping/pay/ShoppingAliPay.vue'),
            meta: {title: '商城-支付宝支付'}
        },

    ]
}
