export const role = {
    path: 'role',
    children: [
        {
            path: 'adminDepartment',
            name: 'adminDepartment',
            component: () => import('@/views/role/AdminDepartment.vue'),
            meta: {title: '部门管理'}
        },
        {
            path: '',
            component: () => import('@/views/role/AdminDepartmentLayout.vue'),
            children: [
                {
                    path: 'admin',
                    name: 'admin',
                    component: () => import('@/views/role/Admin.vue'),
                    meta: {title: '管理员管理'}
                },
                {
                    path: 'role',
                    name: 'role',
                    component: () => import('@/views/role/AdminRole.vue'),
                    meta: {title: '角色管理'}
                }
            ]
        },
        {
            path: 'adminPower',
            name: 'adminPower',
            component: () => import('@/views/role/AdminPower.vue'),
            meta: {title: '权限管理'}
        },

        {
            path: '',
            component: () => import('@/views/role/AdminRoleLayout.vue'),
            children: [
                {
                    path: 'adminRolePower',
                    name: 'adminRolePower',
                    component: () => import('@/views/role/AdminRolePower.vue'),
                }
            ]
        },
        {
            path: 'user',
            component: () => import('@/views/role/AdminUser.vue'),
            name: 'user'
        },
        {
            path: 'power',
            component: () => import('@/views/role/Power.vue'),
            name: 'power'
        },
        {
            path: 'menu',
            component: () => import('@/views/role/AdminMenu.vue'),
            name: 'menu'
        }
    ]
}
