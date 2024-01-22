export const order = {
    path: 'order',
    children: [
        {
            path: 'orderList',
            name: 'orderList',
            component: () => import('@/views/shopping/order/ShoppingOrderList.vue'),
            meta: {title: '订单列表'}
        },
        {
            path: 'abnormalOrder',
            name: 'abnormalOrder',
            component: () => import('@/views/shopping/order/AbnormalOrder.vue'),
            meta: {title: '异常列表'}
        },
        {
            path: 'sendOutGoods',
            name: 'sendOutGoods',
            component: () => import('@/views/shopping/order/SendOutGoods.vue'),
            meta: {title: '发货'}
        }
    ]
}
