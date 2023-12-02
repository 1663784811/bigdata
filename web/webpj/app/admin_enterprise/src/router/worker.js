export const worker = {
    path: 'worker',
    children: [
        {
            path: 'myTask',
            name: 'myTask',
            component: () => import('@/views/worker/MyTask.vue'),
            meta: {title: '我的任务'}
        }
    ]
}
