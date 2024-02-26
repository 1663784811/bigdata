export const phone = {
    path: '/:code/phone',
    children: [
        {
            path: 'phoneList',
            name: 'phoneList',
            meta: {
                title: '手机列表',
            },
            component: () => import('@/views/app/phone/PhoneList.vue')
        },
        {
            path: 'companyList',
            name: 'companyList',
            meta: {
                title: '公司列表',
            },
            component: () => import('@/views/app/phone/CompanyList.vue')
        },
        {
            path: 'spiderData',
            name: 'spiderData',
            meta: {
                title: '黑洞',
            },
            component: () => import('@/views/app/phone/SpiderData.vue')
        },
        {
            path: 'bookWord',
            name: 'bookWord',
            meta: {
                title: '单词解释',
            },
            component: () => import('@/views/app/phone/BookWord.vue')
        }
    ]
}
