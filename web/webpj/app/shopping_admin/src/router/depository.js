export const depository = [
    {
        path: 'depositoryList',
        name: 'depositoryList',
        component: () => import('@/views/shopping/depository/ShoppingDepository.vue'),
        meta: {title: '仓库列表'}
    },
    {
        path: 'depositoryGoods',
        name: 'depositoryGoods',
        component: () => import('@/views/shopping/depository/ShoppingDepositoryGoods.vue'),
        meta: {title: '仓库商品'}
    },
    {
        path: 'depositoryLog',
        name: 'depositoryLog',
        component: () => import('@/views/shopping/depository/ShoppingDepositoryLog.vue'),
        meta: {title: '进出库记录'}
    }
]
