export const role = {
    path: 'role',
    children: [
        {
            path: 'adminDepartment',
            name: 'adminDepartment',
            component: () => import('@/views/admin/role/AdminDepartment.vue'),
            meta: {title: '部门管理'}
        },
        {
            path: '',
            component: () => import('@/views/admin/role/AdminDepartmentLayout.vue'),
            children: [
                {
                    path: 'admin',
                    name: 'admin',
                    component: () => import('@/views/admin/role/Admin.vue'),
                    meta: {title: '管理员管理'}
                },
                {
                    path: 'role',
                    name: 'role',
                    component: () => import('@/views/admin/role/AdminRole.vue'),
                    meta: {title: '角色管理'}
                }
            ]
        },
        {
            path: 'adminPower',
            name: 'adminPower',
            component: () => import('@/views/admin/role/AdminPower.vue'),
            meta: {title: '权限管理'}
        },

        {
            path: '',
            component: () => import('@/views/admin/role/AdminRoleLayout.vue'),
            children: [
                {
                    path: 'adminRolePower',
                    name: 'adminRolePower',
                    component: () => import('@/views/admin/role/AdminRolePower.vue'),
                }
            ]
        },
        {
            path: 'user',
            component: () => import('@/views/admin/role/AdminUser.vue'),
            name: 'user'
        },
        {
            path: 'power',
            component: () => import('@/views/admin/role/Power.vue'),
            name: 'power'
        },
        {
            path: 'menu',
            component: () => import('@/views/admin/role/AdminMenu.vue'),
            name: 'menu'
        }
    ]
}
