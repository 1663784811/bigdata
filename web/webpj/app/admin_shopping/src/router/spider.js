export const spider = {
    path: 'spider',
    children: [
        {
            path: 'Monitor',
            component: () => import('@/views/spider/spiderMonitor.vue'),
            name: 'spiderMonitor'
        },
        {
            path: 'Data',
            component: () => import('@/views/spider/spiderData.vue'),
            name: 'spiderData'
        },
    ]
}
