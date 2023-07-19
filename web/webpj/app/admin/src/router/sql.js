export const sql = {
    path: 'sql',
    children: [
        {
            path: 'config',
            component: import('@/views/sql/Config.vue'),
            name: 'sqlConfig'
        },
        {
            path: 'pageSetting',
            component: import('@/views/sql/PageSetting.vue'),
            name: 'pageSetting'
        },
        {
            path: 'pageComponents',
            component: import('@/views/sql/PageComponents.vue'),
            name: 'pageComponents'
        }
    ]
}
