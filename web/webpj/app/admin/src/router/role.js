export const role = {
    path: 'role',
    children: [
        {
            path: 'user',
            component: import('@/views/role/User.vue'),
            name: 'user'
        },
        {
            path: 'adminPower',
            component: import('@/views/role/AdminPower.vue'),
            name: 'adminPower'
        },
        {
            path: 'department',
            component: import('@/views/role/Department.vue'),
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
