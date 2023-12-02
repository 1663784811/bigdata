export const goods = {
    path: 'goods',
    children: [
        {
            path: 'saleGoods',
            name: 'saleGoods',
            component: () => import('@/views/shopping/goods/ShoppingGoodsList.vue'),
            meta: {title: '上下架商品'}
        },
        {
            path: 'goodsList',
            name: 'goodsList',
            component: () => import('@/views/shopping/goods/ShoppingGoodsList.vue'),
            meta: {title: '商品列表'}
        },
        {
            path: 'goodsType',
            name: 'goodsType',
            component: () => import('@/views/shopping/goods/ShoppingGType.vue'),
            meta: {title: '商品分类'}
        },
        {
            path: 'goodsBrand',
            name: 'goodsBrand',
            component: () => import('@/views/shopping/goods/ShoppingBrand.vue'),
            meta: {title: '商品列表'}
        }
    ]
}
