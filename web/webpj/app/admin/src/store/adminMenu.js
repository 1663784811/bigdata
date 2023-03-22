import {defineStore} from 'pinia'
import {ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

export const useAdminMenuStore = defineStore('adminMenu', () => {
    const leftMenu = ref([]);
    const topMenu = ref([
        {
            name: '企业管理',
            icon: 'md-settings',
            routeName: 'enterprise',
            children: [
                {
                    name: '企业管理',
                    icon: 'md-settings',
                    routeName: 'enterprise'
                },
                {
                    name: '门店管理',
                    icon: 'md-settings',
                    routeName: 'storeList'
                },
                {
                    name: '仓库管理',
                    icon: 'md-settings',
                    routeName: 'storeList'
                }
            ]
        },
        {
            name: '配置',
            icon: 'md-settings',
            routeName: 'sqlConfig',
            children: [
                {
                    name: 'SQL配置',
                    icon: 'md-settings',
                    routeName: 'sqlConfig'
                },
                {
                    name: '页面配置',
                    icon: 'md-settings',
                    routeName: 'CommonTablePage',
                    query: {
                        pageId: "pageConfig",
                    },
                },
                {
                    name: '通用表格',
                    icon: 'md-settings',
                    routeName: 'CommonTablePage',
                    query: {
                        pageId: "CommonTablePage",
                    },
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
            name: '商城管理',
            icon: 'md-settings',
            routeName: 'shopping',
            children: [
                {
                    name: '商城列表',
                    icon: 'md-settings',
                    routeName: 'shoppingList'
                },
                {
                    name: '商品管理',
                    icon: 'md-settings',
                    routeName: 'goodsList'
                },
                {
                    name: '货品管理',
                    icon: 'md-settings',
                    routeName: 'goodsList'
                },
            ]
        },
        {
            name: '位置服务',
            icon: 'md-settings',
            routeName: 'shopping',
            children: [
                {
                    name: '车辆管理',
                    icon: 'md-settings',
                    routeName: 'shoppingList'
                },
                {
                    name: '商品管理',
                    icon: 'md-settings',
                    routeName: 'goodsList'
                },
                {
                    name: '货品管理',
                    icon: 'md-settings',
                    routeName: 'goodsList'
                },
            ]
        },
        {
            name: '用户管理',
            icon: 'md-settings',
            routeName: 'power',
            children: [
                {
                    name: '用户管理',
                    icon: 'md-settings',
                    routeName: 'user'
                },
                {
                    name: '管理员管理',
                    icon: 'md-settings',
                    routeName: 'adminPower'
                },
                {
                    name: '权限分配',
                    icon: 'md-settings',
                    routeName: 'adminPower'
                },
                {
                    name: '角色管理',
                    icon: 'md-settings',
                    routeName: 'role'
                },
                {
                    name: '权限菜单',
                    icon: 'md-settings',
                    routeName: 'power'
                }
            ]
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
