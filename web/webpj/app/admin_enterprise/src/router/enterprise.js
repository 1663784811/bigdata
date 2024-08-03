export const enterprise = [
    {
        path: 'peopleCenter',
        name: 'peopleCenter',
        meta: {title: '个人中心'},
        component: () => import('@/views/admin/account/PeopleCenter.vue'),
    },
    {
        path: 'dashboard',
        name: 'dashboard',
        meta: {title: '大屏'},
        component: () => import('@/views/admin/enterprise/Dashboard.vue'),
    },
    {
        path: 'appCenter',
        name: 'appCenter',
        meta: {title: 'App中心'},
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
    },
    {
        path: 'messageList',
        name: 'messageList',
        meta: {title: '消息列表'},
        component: () => import('@/views/admin/account/MessageList.vue'),
    },
    {
        path: 'messageDetails',
        name: 'messageDetails',
        meta: {title: '消息详情'},
        component: () => import('@/views/admin/account/MessageDetails.vue'),
    },
    {
        path: 'openApp',
        name: 'openApp',
        component: () => import('@/views/admin/enterprise/AppOpen.vue'),
    }
]

