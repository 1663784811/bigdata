import {defineStore} from 'pinia'
import {reactive, ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

export const useAdminMenuStore = defineStore('adminMenu',
    {
        persist: {
            enabled: true,//开启数据持久化
            strategies: [
                {
                    key: 'adminMenu',
                    storage: localStorage,
                }
            ]
        },
        state: () => {
            // 已经选择的模式
            const selectList = {};
            // 模式列表

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
                            routeName: 'pageSetting',
                            query: {
                                pageId: "pageConfig",
                            },
                        },
                        {
                            name: '组件',
                            icon: 'md-settings',
                            routeName: 'pageComponents',
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
                        },
                        {
                            name: '代码生成',
                            icon: 'md-settings',
                            routeName: 'createCode'
                        },
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
                    name: '后台管理',
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
                            routeName: 'power'
                        },
                        {
                            name: '菜单',
                            icon: 'md-settings',
                            routeName: 'menu'
                        }
                    ]
                },
            ])

            const nowMenu = ref({});
            const setNowMenu = (menu) => {
                const rest = []
                if (menu && menu.children && menu.children.length > 0) {
                    rest.push(...menu.children);
                }
                return rest;
            }

            const loadMenu = () => {
                apiAdminMenu({}).then((res) => {
                    topMenu.value.push(...res.data);
                })
            }


            return {
                selectList,
                topMenu,
                nowMenu,
                setNowMenu,
                leftMenu,
                loadMenu
            }
        }
    })
