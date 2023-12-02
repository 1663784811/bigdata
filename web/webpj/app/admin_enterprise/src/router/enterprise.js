export const enterprise = {
    path: 'enterprise',
    children: [
        {
            path: 'dashboard',
            component: () => import('@/views/enterprise/Dashboard.vue'),
            name: 'dashboard',
        },
        {
            path: 'appCenter',
            component: () => import('@/views/enterprise/AppCenter.vue'),
            name: 'appCenter',
        },
        {
            path: 'List',
            component: () => import('@/views/enterprise/List.vue'),
            name: 'enterprise',
        },
        {
            path: 'storeList',
            name: 'storeList',
            component: () => import('@/views/enterprise/StoreList.vue'),
            meta: {title: '门店列表'}
        },
        {
            path: 'storehouse',
            component: () => import('@/views/enterprise/Storehouse.vue'),
            name: 'storehouse',
        },
        {
            path: 'productCenter',
            component: () => import('@/views/enterprise/productCenter.vue'),
            name: 'productCenter',
        },
        {
            path: 'MyProductCenter',
            component: () => import('@/views/enterprise/MyProductCenter.vue'),
            name: 'MyProductCenter',
        }
    ]

}
