import {createRouter, createWebHashHistory} from 'vue-router'
import {welcome} from './welcome.js'
import {enterprise} from './enterprise.js'
import {common} from './common.js'
import {sql} from './sql.js'
import {role} from './role.js'
import {spider} from './spider.js'
import {shopping} from './shopping.js'
import {login} from './login.js'
import {worker} from './worker.js'
import {phone} from './phone.js'
import {friends} from './friends.js'
import {loginInfo} from '../store/loginInfo.js'


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
                        enterprise,
                        common,
                        sql,
                        role,
                        spider,
                        shopping,
                        worker,
                        phone,
                        friends
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
