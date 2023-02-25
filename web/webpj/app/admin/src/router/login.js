export const login = {
    path: '/account',
    children: [
        {
            path: 'login/:adfdffdfd',
            name: 'login',
            meta: {
                title: 'Login - 登录',
                hideInMenu: true
            },
            component: () => import('@/views/account/Login.vue')
        },
        {
            path: 'register',
            name: 'register',
            meta: {
                title: '注册',
                hideInMenu: true
            },
            component: () => import('@/views/account/Register.vue')
        }
    ]
}
