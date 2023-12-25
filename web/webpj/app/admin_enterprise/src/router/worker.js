export const worker = {
    path: 'worker',
    children: [
        {
            path: 'myTask',
            name: 'myTask',
            component: () => import('@/views/admin/worker/MyTask.vue'),
            meta: {title: '我的任务'}
        },
        {
            path: 'modelList',
            name: 'modelList',
            component: () => import('@/views/admin/worker/ModelList.vue'),
            meta: {title: '模型管理'}
        }
    ]
}
