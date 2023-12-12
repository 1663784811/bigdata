export const phone = {
    path: '/:code/phone',
    children: [
        {
            path: 'phoneList',
            name: 'phoneList',
            meta: {
                title: '手机列表',
            },
            component: () => import('@/views/phone/PhoneList.vue')
        }
    ]
}
