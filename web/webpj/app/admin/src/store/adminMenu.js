import {defineStore} from 'pinia'
import {ref} from "vue";
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
            const modeList = ref([
                {
                    name: '超级管理员',
                    menuList: [
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
                                    name: '产品中心',
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
                    ]
                },
                {
                    name: '企业模式',
                    menuList: [
                        {
                            name: '企业管理',
                            icon: 'md-settings',
                            routeName: 'enterprise',
                            children: [
                                {
                                    name: '产品中心',
                                    icon: 'md-settings',
                                    routeName: 'storeList'
                                }
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
                    ]
                },
                {
                    name: '商城模式',
                    menuList: [
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
                                }
                            ]
                        },
                        {
                            name: '商城管理',
                            icon: 'md-settings',
                            routeName: 'shopping',
                            children: [
                                {
                                    name: '首页banner图',
                                    icon: 'md-settings',
                                    routeName: 'shoppingBanner'
                                },
                                {
                                    name: '品牌',
                                    icon: 'md-settings',
                                    routeName: 'shoppingBrand'
                                },
                                {
                                    name: '品类',
                                    icon: 'md-settings',
                                    routeName: 'shoppingGType'
                                },
                                {
                                    name: '门店管理',
                                    icon: 'md-settings',
                                    routeName: 'shoppingStore'
                                }
                            ]
                        },
                    ]
                },
                {
                    name: '门店',
                    menuList: [
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
                                }
                            ]
                        },
                        {
                            name: '门店管理',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    name: '商品管理',
                                    icon: 'md-settings',
                                    routeName: 'goodsList'
                                },
                                {
                                    name: '货品SKU',
                                    icon: 'md-settings',
                                    routeName: 'shoppingGoodsSku'
                                },
                                {
                                    name: '编辑商品',
                                    icon: 'md-settings',
                                    routeName: 'shoppingGoodsEditor'
                                }
                            ]
                        },
                        {
                            name: '仓库管理',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    name: '仓库设置',
                                    icon: 'md-settings',
                                    routeName: 'shoppingDepository'
                                },
                                {
                                    name: '仓库货品',
                                    icon: 'md-settings',
                                    routeName: 'shoppingDepositoryGoods'
                                },
                                {
                                    name: '出入仓库记录',
                                    icon: 'md-settings',
                                    routeName: 'shoppingDepositoryLog'
                                },
                            ]
                        },
                        {
                            name: '订单管理',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    name: '订单列表',
                                    icon: 'md-settings',
                                    routeName: 'orderList'
                                }
                            ]
                        },
                    ]
                }
            ])

            const leftMenu = ref([]);
            const topMenu = ref([])

            const nowMenu = ref({});

            const setNowMenu = (menu) => {
                nowMenu.value = menu;
                if (menu && menu.children && menu.children.length > 0) {
                    leftMenu.value = menu.children;
                } else {
                    leftMenu.value = [];
                }
            }

            const loadMenu = () => {
                apiAdminMenu({}).then((res) => {
                    topMenu.value.push(...res.data);
                })
            }


            return {
                selectList,
                modeList,
                topMenu,
                nowMenu,
                setNowMenu,
                leftMenu,
                loadMenu
            }
        }
    })
