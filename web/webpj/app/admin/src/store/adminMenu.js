import {defineStore} from 'pinia'
import {ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

export const useAdminMenuStore = defineStore('adminMenu', () => {
    const leftMenu = ref([]);
    const topMenu = ref([
        {
            name: '配置',
            icon: 'md-settings',
            routeName: 'sqlConfig',
            children: [
                {
                    name: 'SQL',
                    icon: 'md-settings',
                    routeName: 'spiderMonitor'
                },
                {
                    name: '页面',
                    icon: 'md-settings',
                    routeName: 'spiderMonitor'
                }
            ]
        },
        {
            name: '爬虫监控',
            icon: 'md-settings',
            routeName: 'spiderMonitor',
            children: [
                {
                    name: 'SQL',
                    icon: 'md-settings',
                    routeName: 'spiderMonitor'
                },
                {
                    name: '页面',
                    icon: 'md-settings',
                    routeName: 'spiderMonitor'
                }
            ]
        },
        {
            name: '数据处理',
            icon: 'md-settings',
            routeName: 'spiderData',
            children: [
                {
                    name: 'SQL',
                    icon: 'md-settings',
                    routeName: 'spiderMonitor'
                },
                {
                    name: '页面',
                    icon: 'md-settings',
                    routeName: 'spiderMonitor'
                }
            ]
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
        },
        {
            name: '权限管理',
            icon: 'md-settings',
            routeName: 'power'
        }
    ])

    const nowMenu = ref({});

    const setNowMenu = (menu) => {
        nowMenu.value = menu;
        if (menu && menu.children && menu.children.length > 0) {
            leftMenu.value = menu.children;
        } else {
            leftMenu.value = [];
        }
    }

    apiAdminMenu({}).then((res) => {
        topMenu.value.push(...res.data);
    })


    return {
        topMenu,
        nowMenu,
        setNowMenu,
        leftMenu
    }
})
