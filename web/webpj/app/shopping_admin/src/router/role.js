export const role = {
    path: 'role',
    children: [
        {
            path: 'user',
            name: 'user',
            component: () => import('@/views/admin/role/AdminUser.vue'),
        },
        {
          path: '',
          component: () => import('@/views/admin/role/AdminDepartmentLayout.vue'),
          children:[
              {
                  path: 'adminPower',
                  name: 'adminPower',
                  component: () => import('@/views/admin/role/AdminPower.vue'),
              },
              {
                  path: 'adminPost',
                  name: 'adminPost',
                  component: () => import('@/views/admin/role/AdminPost.vue'),
              },
          ]
        },

        {
            path: 'adminDepartment',
            name: 'adminDepartment',
            component: () => import('@/views/admin/role/AdminDepartment.vue'),
        },
        {
            path: 'role',
            component: () => import('@/views/admin/role/AdminRole.vue'),
            name: 'role'
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
