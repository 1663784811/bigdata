export const welcome = [
    {
        path: '/',
        component: () => import('@/views/Index.vue'),
        name: 'index',
        meta: {notLogin: true, title: '首页'}
    },
    {
        path: '/cyyaw/welcomePage',
        name: 'welcomePage',
        component: () => import('@/views/admin/welcomePage.vue'),
        meta: {notLogin: true, title: '欢迎页面'}
    }
]
