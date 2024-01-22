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
    ]
}
