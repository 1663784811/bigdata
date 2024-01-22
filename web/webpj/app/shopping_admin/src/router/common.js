export const common = {
    path: 'common',
    children:[
        {
            path: 'CommonTablePage',
            name: 'CommonTablePage',
            component: () => import('@/views/common/CommonTablePage.vue'),
        },
        {
            path: 'dashboard',
            name: 'dashboard',
            component: () => import('@/views/home/Dashboard.vue'),
        }
    ]
}
