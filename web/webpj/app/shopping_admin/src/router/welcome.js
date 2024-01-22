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
        component: () => import('@/views/welcomePage.vue'),
        meta: {notLogin: true, title: '欢迎页面'}
    }
]
