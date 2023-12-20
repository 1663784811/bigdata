export const login = {
    path: '/:code/account',
    children: [
        {
            path: 'login',
            name: 'login',
            meta: {
                title: 'Login - 登录',
                hideInMenu: true,
                notLogin: true,
            },
            component: () => import('@/views/admin/account/Login.vue')
        }
    ]
}
