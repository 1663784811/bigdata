export const enterprise = {
    path: 'enterprise',
    children: [
        {
            path: 'peopleCenter',
            name: 'peopleCenter',
            component: () => import('@/views/admin/account/PeopleCenter.vue'),
        },
        {
            path: 'dashboard',
            name: 'dashboard',
            component: () => import('@/views/admin/enterprise/Dashboard.vue'),
        },
        {
            path: 'appCenter',
            name: 'appCenter',
            component: () => import('@/views/admin/enterprise/AppCenter.vue'),
        },
        {
            path: 'systemMonitor',
            name: 'systemMonitor',
            component: () => import('@/views/admin/enterprise/SystemMonitor.vue'),
        },
        {
            path: 'List',
            name: 'enterprise',
            component: () => import('@/views/admin/enterprise/List.vue'),
        },
        {
            path: 'storeList',
            name: 'storeList',
            component: () => import('@/views/admin/enterprise/StoreList.vue'),
            meta: {title: '门店列表'}
        },
        {
            path: 'storehouse',
            name: 'storehouse',
            component: () => import('@/views/admin/enterprise/Storehouse.vue'),
        },
        {
            path: 'productCenter',
            name: 'productCenter',
            component: () => import('@/views/admin/enterprise/productCenter.vue'),
        },
        {
            path: 'MyProductCenter',
            name: 'MyProductCenter',
            component: () => import('@/views/admin/enterprise/MyProductCenter.vue'),
        }
    ]

}
