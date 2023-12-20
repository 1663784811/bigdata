export const sql = {
    path: 'sql',
    children: [
        {
            path: 'config',
            component: () => import('@/views/app/sql/Config.vue'),
            name: 'sqlConfig'
        },
        {
            path: 'pageSetting',
            component: () => import('@/views/app/sql/PageSetting.vue'),
            name: 'pageSetting'
        },
        {
            path: 'pageComponents',
            component: () => import('@/views/app/sql/PageComponents.vue'),
            name: 'pageComponents'
        },
        {
            path: 'createCode',
            component: () => import('@/views/app/sql/CreateCode.vue'),
            name: 'createCode'
        }
    ]
}
