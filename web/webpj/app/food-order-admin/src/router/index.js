import {createRouter, createWebHashHistory} from 'vue-router'
import {welcome} from './welcome.js'
import {common} from './common.js'
import {role} from './role.js'
import {login} from './login.js'
import {pay} from './pay.js'
import {web} from './web.js'
import {order} from './order.js'
import {goods} from './goods.js'
import {loginInfo} from '../store/loginInfo.js'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        ...welcome,
        {
            path: '/:appId/store/:storeId',
            component: () => import('@/views/admin/AppMain.vue'),
            children: [
                login,
                {
                    path: '',
                    name: 'home',
                    component: () => import('@/views/admin/layout/Main.vue'),
                    children: [
                        common,
                        web,
                        role,
                        pay,
                        order,
                        goods
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
        if (params.appId && params.storeId) {
            next({
                name: 'login',
                params
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
