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
            component: () => import('@/views/enterprise/StoreList.vue'),
            name: 'storeList',
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