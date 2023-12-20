export const spider = {
    path: 'spider',
    children: [
        {
            path: 'Monitor',
            component: () => import('@/views/app/spider/spiderMonitor.vue'),
            name: 'spiderMonitor'
        },
        {
            path: 'Data',
            component: () => import('@/views/app/spider/spiderData.vue'),
            name: 'spiderData'
        },
    ]
}
