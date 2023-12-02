export const role = {
    path: 'role',
    children: [
        {
            path: 'user',
            name: 'user',
            component: () => import('@/views/role/AdminUser.vue'),
        },
        {
          path: '',
          component: () => import('@/views/role/AdminDepartmentLayout.vue'),
          children:[
              {
                  path: 'adminPower',
                  name: 'adminPower',
                  component: () => import('@/views/role/AdminPower.vue'),
              },
              {
                  path: 'adminPost',
                  name: 'adminPost',
                  component: () => import('@/views/role/AdminPost.vue'),
              },
          ]
        },

        {
            path: 'adminDepartment',
            name: 'adminDepartment',
            component: () => import('@/views/role/AdminDepartment.vue'),
        },
        {
            path: 'role',
            component: () => import('@/views/role/AdminRole.vue'),
            name: 'role'
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
