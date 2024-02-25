export const common = {
    path: 'common',
    children:[
        {
            path: 'dashboard',
            name: 'dashboard',
            component: () => import('@/views/admin/home/Dashboard.vue'),
        },
        {
            path: 'peopleCenter',
            name: 'peopleCenter',
            component: () => import('@/views/admin/account/PeopleCenter.vue'),
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
        }
    ]
}
