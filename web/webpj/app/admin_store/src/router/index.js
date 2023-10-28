import {createRouter, createWebHashHistory} from 'vue-router'
import {enterprise} from './enterprise.js'
import {common} from './common.js'
import {sql} from './sql.js'
import {role} from './role.js'
import {spider} from './spider.js'
import {shopping} from './shopping.js'
import {login} from './login.js'


export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        login,
        {
            path: '/',
            component: () => import('@/views/layout/Main.vue'),
            children: [
                enterprise,
                common,
                sql,
                role,
                spider,
                shopping
            ]
        }
    ]
})
