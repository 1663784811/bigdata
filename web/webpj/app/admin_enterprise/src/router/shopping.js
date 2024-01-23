export const shopping = {
    path: 'shopping',
    children: [
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
            component: () => import('@/views/app/shopping/setting/ShoppingBrand.vue'),
            meta: {title: '商城-首页品牌'}
        },
        {
            path: 'shoppingGType',
            component: () => import('@/views/app/shopping/setting/ShoppingGType.vue'),
            name: 'shoppingGType'
        },
        {
            path: 'shoppingStore',
            component: () => import('@/views/app/shopping/ShoppingStore.vue'),
            name: 'shoppingStore'
        },
        {
            path: 'orderList',
            component: () => import('@/views/app/shopping/order/ShoppingOrderList.vue'),
            name: 'orderList'
        },
    ]
}
