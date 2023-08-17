export const shopping = {
    path: 'shopping',
    children: [
        {
            path: 'shoppingList',
            component: import('@/views/shopping/ShoppingList.vue'),
            name: 'shopping'
        },
        {
            path: 'shoppingBanner',
            component: import('@/views/shopping/ShoppingBanner.vue'),
            name: 'shoppingBanner'
        },
        {
            path: 'shoppingBrand',
            component: import('@/views/shopping/ShoppingBrand.vue'),
            name: 'shoppingBrand'
        },
        {
            path: 'shoppingGoodsSku',
            component: import('@/views/shopping/ShoppingGoodsSku.vue'),
            name: 'shoppingGoodsSku'
        },
        {
            path: 'shoppingGType',
            component: import('@/views/shopping/ShoppingGType.vue'),
            name: 'shoppingGType'
        },
        {
            path: 'shoppingStore',
            component: import('@/views/shopping/ShoppingStore.vue'),
            name: 'shoppingStore'
        },
        {
            path: 'goodsList',
            component: import('@/views/shopping/GoodsList.vue'),
            name: 'goodsList'
        },
        {
            path: 'gDepository',
            component: import('@/views/shopping/GDepository.vue'),
            name: 'gDepository'
        }
    ]
}
