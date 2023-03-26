export const enterprise = {
    path: '/enterprise',
    children: [
        {
            path: 'List',
            component: import('@/views/enterprise/List.vue'),
            name: 'enterprise',
        },
        {
            path: 'storeList',
            component: import('@/views/enterprise/StoreList.vue'),
            name: 'storeList',
        },
        {
            path: 'storehouse',
            component: import('@/views/enterprise/Storehouse.vue'),
            name: 'storehouse',
        }
    ]

}
