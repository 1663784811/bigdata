export const enterprise = {
    path: 'enterprise',
    children: [
        {
            path: 'peopleCenter',
            component: () => import('@/views/admin/account/PeopleCenter.vue'),
            name: 'peopleCenter',
        },
        {
            path: 'dashboard',
            component: () => import('@/views/admin/enterprise/Dashboard.vue'),
            name: 'dashboard',
        },
        {
            path: 'appCenter',
            component: () => import('@/views/admin/enterprise/AppCenter.vue'),
            name: 'appCenter',
        },
        {
            path: 'List',
            component: () => import('@/views/admin/enterprise/List.vue'),
            name: 'enterprise',
        },
        {
            path: 'storeList',
            name: 'storeList',
            component: () => import('@/views/admin/enterprise/StoreList.vue'),
            meta: {title: '门店列表'}
        },
        {
            path: 'storehouse',
            component: () => import('@/views/admin/enterprise/Storehouse.vue'),
            name: 'storehouse',
        },
        {
            path: 'productCenter',
            component: () => import('@/views/admin/enterprise/productCenter.vue'),
            name: 'productCenter',
        },
        {
            path: 'MyProductCenter',
            component: () => import('@/views/admin/enterprise/MyProductCenter.vue'),
            name: 'MyProductCenter',
        }
    ]

}
