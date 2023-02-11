import {defineStore} from 'pinia'
import {ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

export const adminMenu = defineStore('adminMenu', () => {
    const menuList = ref([
        {
            name: 'Sql配置',
            icon: 'md-settings',
            routeName: 'sqlConfig'
        },
        {
            name: '爬虫监控',
            icon: 'md-settings',
            routeName: 'spiderMonitor'
        },
        {
            name: '数据处理',
            icon: 'md-settings',
            routeName: 'spiderData'
        },
        {
            name: '企业管理',
            icon: 'md-settings',
            routeName: 'enterprise'
        },
        {
            name: '商城管理',
            icon: 'md-settings',
            routeName: 'shopping'
        }
    ])
    apiAdminMenu({}).then((res) => {
        menuList.value.push(... res.data);
    })
    return {menuList}
})
