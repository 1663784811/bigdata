export const role = {
    path: 'role',
    children: [
        {
            path: 'user',
            component: import('@/views/role/User.vue'),
            name: 'user'
        },
        {
            path: 'department',
            component: import('@/views/role/AdminPower.vue'),
            name: 'department'
        },
        {
            path: 'role',
            component: import('@/views/role/Role.vue'),
            name: 'role'
        },
        {
            path: 'power',
            component: import('@/views/role/Power.vue'),
            name: 'power'
        }
    ]
}
