export const friends = {
    path: '/:code/friends',
    children: [
        {
            path: 'userList',
            name: 'friendsUser',
            meta: {
                title: '用户',
            },
            component: () => import('@/views/app/friends/UserList.vue')
        },
        {
            path: 'content',
            name: 'friendsContent',
            meta: {
                title: '内容管理',
            },
            component: () => import('@/views/app/friends/ContentList.vue')
        }
    ]
}
