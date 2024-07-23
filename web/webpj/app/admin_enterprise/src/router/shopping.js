export const shopping = [
    {
        path: 'shoppingDashboard',
        name: 'shoppingDashboard',
        meta: {title: '商城-概览'},
        component: () => import('@/views/app/shopping/ShoppingDashboard.vue'),
    },
    {
        path: 'shoppingUser',
        name: 'shoppingUser',
        meta: {title: '商城-用户管理'},
        component: () => import('@/views/app/shopping/ShoppingUser.vue'),
    },
    {
        path: 'shoppingStore',
        name: 'shoppingStore',
        meta: {title: '商城-门店'},
        component: () => import('@/views/app/shopping/ShoppingStore.vue'),
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
        path: 'shoppingBanner',
        name: 'shoppingBanner',
        meta: {title: '商城-首页Banner'},
        component: () => import('@/views/app/shopping/setting/ShoppingBanner.vue'),
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
        path: 'shoppingSettings',
        name: 'shoppingSettings',
        meta: {title: '商城-设置'},
        component: () => import('@/views/app/shopping/setting/ShoppingSettings.vue'),
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
