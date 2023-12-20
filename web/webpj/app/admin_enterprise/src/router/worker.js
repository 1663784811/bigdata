export const worker = {
    path: 'worker',
    children: [
        {
            path: 'myTask',
            name: 'myTask',
            component: () => import('@/views/admin/worker/MyTask.vue'),
            meta: {title: '我的任务'}
        }
    ]
}
