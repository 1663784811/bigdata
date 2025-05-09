import {createRouter, createWebHashHistory} from 'vue-router'
import {welcome} from './welcome.js'
import {enterprise} from './enterprise.js'
import {role} from './role.js'
import {spider} from './spider.js'
import {shopping} from './shopping.js'
import {login} from './login.js'
import {worker} from './worker.js'
import {phone} from './phone.js'
import {friends} from './friends.js'
import {loginInfo} from '@/store/loginInfo.js'


const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        ...welcome,
        {
            path: '/:code',
            component: () => import('@/views/admin/AppMain.vue'),
            children: [
                login,
                {
                    path: '',
                    name: 'home',
                    component: () => import('@/views/admin/layout/Main.vue'),
                    children: [
                        {
                            meta: {title: '企业'},
                            path: 'enterprise',
                            children: enterprise
                        },
                        role,
                        spider,
                        worker,
                        {
                            meta: {title: '商城'},
                            path: 'shopping/:appId',
                            children: shopping
                        },
                        {
                            path: 'phone/:appId',
                            children: phone
                        },
                        {
                            path: 'friends/:appId',
                            children: friends
                        },
                        {
                            path: 'questions',
                            name: 'questions',
                            component:  () => import('@/views/admin/Questions.vue'),
                        },
                        {
                            path: 'equipment',
                            name: 'equipment',
                            component:  () => import('@/views/admin/Equipment.vue'),
                        }
                    ]
                }
            ]
        }
    ]
})


router.beforeEach(({meta = {}, name, params}, from, next) => {
    const {title, notLogin} = meta;
    if (title) document.title = title;
    const useUser = loginInfo();
    useUser.variable.eCode = params.code;
    let token = useUser.token;
    if (!token && !notLogin) {
        // 未登录
        if (params.code) {
            next({
                name: 'login',
                params: {
                    code: params.code
                }
            })
        } else {
            next({
                name: 'welcomePage'
            })
        }
    } else {
        next()
    }
});


export default router
