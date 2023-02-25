export const sql = {
    path: 'sql',
    children: [
        {
            path: 'config',
            component: import('@/views/sql/Config.vue'),
            name: 'sqlConfig'
        },
    ]
}
