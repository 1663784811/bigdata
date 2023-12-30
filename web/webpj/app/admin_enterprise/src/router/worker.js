export const worker = {
    path: 'worker', children: [{
        path: 'myTask',
        name: 'myTask',
        component: () => import('@/views/admin/worker/MyTask.vue'),
        meta: {title: '我的任务'}
    }, {
        path: 'modelList',
        name: 'modelList',
        component: () => import('@/views/admin/worker/ModelList.vue'),
        meta: {title: '模型管理'}
    }, {
        path: 'processList',
        name: 'processList',
        component: () => import('@/views/admin/worker/ProcessList.vue'),
        meta: {title: '实例流程'}
    }, {
        path: 'historyProcess',
        name: 'historyProcess',
        component: () => import('@/views/admin/worker/HistoryProcess.vue'),
        meta: {title: '运行历史'}
    }, {
        path: 'meeting',
        name: 'meeting',
        component: () => import('@/views/admin/worker/Meeting.vue'),
        meta: {title: '会议申请'}
    }, {
        path: 'modelArrange',
        name: 'modelArrange',
        component: () => import('@/views/admin/worker/ModelArrange.vue'),
        meta: {title: '模型部署'}
    }
    ]
}
