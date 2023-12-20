export const shopping = {
    path: 'shopping',
    children: [
        {
            path: 'shoppingList',
            component: () => import('@/views/app/shopping/ShoppingList.vue'),
            name: 'shopping'
        },
        {
            path: 'shoppingBanner',
            component: () => import('@/views/app/shopping/ShoppingBanner.vue'),
            name: 'shoppingBanner'
        },
        {
            path: 'shoppingBrand',
            component: () => import('@/views/app/shopping/ShoppingBrand.vue'),
            name: 'shoppingBrand'
        },
        {
            path: 'shoppingGoodsSku',
            component: () => import('@/views/app/shopping/goods/ShoppingGoodsSku.vue'),
            name: 'shoppingGoodsSku'
        },
        {
            path: 'shoppingGType',
            component: () => import('@/views/app/shopping/ShoppingGType.vue'),
            name: 'shoppingGType'
        },
        {
            path: 'shoppingStore',
            component: () => import('@/views/app/shopping/ShoppingStore.vue'),
            name: 'shoppingStore'
        },
        {
            path: 'goodsList',
            component: () => import('@/views/app/shopping/goods/ShoppingGoodsList.vue'),
            name: 'goodsList'
        },
        {
            path: 'shoppingDepository',
            component: () => import('@/views/app/shopping/depository/ShoppingDepository.vue'),
            name: 'shoppingDepository'
        },
        {
            path: 'depository',
            component: () => import('@/views/app/shopping/depository/ShoppingDepositoryLayout.vue'),
            children: [
                {
                    path: 'shoppingDepositoryGoods',
                    component: () => import('@/views/app/shopping/depository/ShoppingDepositoryGoods.vue'),
                    name: 'shoppingDepositoryGoods'
                },
                {
                    path: 'shoppingDepositoryLog',
                    component: () => import('@/views/app/shopping/depository/ShoppingDepositoryLog.vue'),
                    name: 'shoppingDepositoryLog'
                }
            ]
        },
        {
            path: 'orderList',
            component: () => import('@/views/app/shopping/order/ShoppingOrderList.vue'),
            name: 'orderList'
        },
        {
            path: 'shoppingGoodsEditor',
            component: () => import('@/views/app/shopping/goods/ShoppingGoodsEditor.vue'),
            name: 'shoppingGoodsEditor'
        },
    ]
}
