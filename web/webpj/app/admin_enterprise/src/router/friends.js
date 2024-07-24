export const friends = [
    {
        path: 'userList',
        name: 'friendsUser',
        meta: {
            title: '用户',
        },
        component: () => import('@/views/app/friends/UserList.vue')
    },
    {
        path: 'friendsList',
        name: 'friendsList',
        meta: {
            title: '好友列表',
        },
        component: () => import('@/views/app/friends/FriendsList.vue')
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
