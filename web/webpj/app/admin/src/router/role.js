export const role = {
    path: 'role',
    children: [
        {
            path: 'user',
            component: import('@/views/role/AdminUser.vue'),
            name: 'user'
        },
        {
          path: '',
          component: import('@/views/role/AdminDepartmentLayout.vue'),
          children:[
              {
                  path: 'adminPower',
                  component: import('@/views/role/AdminPower.vue'),
                  name: 'adminPower'
              },
              {
                  path: 'adminPost',
                  component: import('@/views/role/AdminPost.vue'),
                  name: 'adminPost'
              },
          ]
        },

        {
            path: 'adminDepartment',
            component: import('@/views/role/AdminDepartment.vue'),
            name: 'adminDepartment'
        },
        {
            path: 'role',
            component: import('@/views/role/AdminRole.vue'),
            name: 'role'
        },
        {
            path: 'power',
            component: import('@/views/role/Power.vue'),
            name: 'power'
        },
        {
            path: 'menu',
            component: import('@/views/role/AdminMenu.vue'),
            name: 'menu'
        }
    ]
}
