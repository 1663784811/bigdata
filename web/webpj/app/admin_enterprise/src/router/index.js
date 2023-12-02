import {createRouter, createWebHashHistory} from 'vue-router'
import {enterprise} from './enterprise.js'
import {common} from './common.js'
import {sql} from './sql.js'
import {role} from './role.js'
import {spider} from './spider.js'
import {shopping} from './shopping.js'
import {login} from './login.js'
import {worker} from './worker.js'
import {loginInfo} from '../store/loginInfo.js'


const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            redirect: '/cyyaw/welcomePage'
        },
        {
            path: '/cyyaw/welcomePage',
            name: 'welcomePage',
            component: () => import('@/views/welcomePage.vue'),
            meta: {notLogin: true, title: '欢迎页面'}
        },
        {
            path: '/cyyaw/register',
            name: 'register',
            meta: {
                title: '注册',
                notLogin: true,
            },
            component: () => import('@/views/account/Register.vue')
        },
        {
            path: '/:code',
            component: () => import('@/views/AppMain.vue'),
            children: [
                login,
                {
                    path: '',
                    name: 'home',
                    component: () => import('@/views/layout/Main.vue'),
                    children: [
                        enterprise,
                        common,
                        sql,
                        role,
                        spider,
                        shopping,
                        worker
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
    let token = useUser.token;
    if (!token && !notLogin) {
        // 未登录
        console.log(params)
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
