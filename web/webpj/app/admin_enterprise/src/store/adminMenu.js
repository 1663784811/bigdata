import {defineStore} from 'pinia'
import {reactive, ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

export const useAdminMenuStore = defineStore('adminMenu',
    {
        persist: {
            enabled: true,//开启数据持久化
            strategies: [
                {
                    key: 'enterpriseMenu',
                    storage: localStorage,
                }
            ]
        },
        state: () => {
            const activeName = ref("");
            const openNames = ref([]);
            // 已经选择的模式
            const selectList = {};
            // 模式列表
            const leftMenu = ref([]);
            const topMenu = ref([
                {
                    name: 'Dashboard',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            name: '主控台',
                            icon: 'md-settings',
                            routeName: 'dashboard'
                        },
                        {
                            name: '系统监控',
                            icon: 'md-settings',
                            routeName: 'systemMonitor'
                        },
                        {
                            name: '应用中心',
                            icon: 'md-settings',
                            routeName: 'appCenter'
                        }
                    ]
                },
                {
                    name: '后台设置',
                    icon: 'md-settings',
                    children: [
                        {
                            name: '组积架构',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    name: '部门管理',
                                    icon: 'md-settings',
                                    routeName: 'adminDepartment'
                                },
                                {
                                    name: '角色列表',
                                    icon: 'md-settings',
                                    routeName: 'role'
                                },
                                {
                                    name: '管理员管理',
                                    icon: 'md-settings',
                                    routeName: 'admin'
                                },
                                {
                                    name: '权限管理',
                                    icon: 'md-settings',
                                    routeName: 'adminPower'
                                },
                                {
                                    name: '权限分配到角色',
                                    icon: 'md-settings',
                                    routeName: 'adminRolePower'
                                },
                                {
                                    name: '菜单管理',
                                    icon: 'md-settings',
                                    routeName: 'menu'
                                }
                            ]
                        },
                        {
                            name: '任务管理',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    name: '流程管理',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            name: '模型管理',
                                            icon: 'md-settings',
                                            routeName: 'modelList'
                                        },
                                        {
                                            name: '部署管理',
                                            icon: 'md-settings',
                                            routeName: 'modelArrange'
                                        },
                                    ]
                                },
                                {
                                    name: '待办任务',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            name: '我的待办',
                                            icon: 'md-settings',
                                            routeName: 'myTask'
                                        },
                                        {
                                            name: '全部待办',
                                            icon: 'md-settings',
                                            routeName: 'adminPower'
                                        },
                                    ]
                                },
                                {
                                    name: '功能表',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            name: '请假申请',
                                            icon: 'md-settings',
                                            routeName: ''
                                        },
                                        {
                                            name: '采购申请',
                                            icon: 'md-settings',
                                            routeName: ''
                                        },
                                        {
                                            name: '发起会议',
                                            icon: 'md-settings',
                                            routeName: 'meeting'
                                        }
                                    ]
                                },
                                {
                                    name: '流程监控',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            name: '流程实例',
                                            icon: 'md-settings',
                                            routeName: 'processList'
                                        },
                                        {
                                            name: '运行历史',
                                            icon: 'md-settings',
                                            routeName: 'historyProcess'
                                        }
                                    ]
                                }
                            ]
                        },
                    ]
                },
                {
                    name: '商城',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            name: '商城概览',
                            icon: 'md-settings',
                            routeName: 'shoppingDashboard'
                        },
                        {
                            name: '用户管理',
                            icon: 'md-settings',
                            routeName: 'shoppingUser'
                        },
                        {
                            name: '门店管理',
                            icon: 'md-settings',
                            children: [
                                {
                                    name: '门店列表',
                                    icon: 'md-settings',
                                    routeName: 'storeList',
                                }
                            ]
                        },
                        {
                            name: '订单管理',
                            icon: 'md-settings',
                            routeName: 'dashboard',
                            children: [
                                {
                                    name: '订单列表',
                                    icon: 'md-settings',
                                    routeName: 'shoppingOrderList',
                                },
                                {
                                    name: '异常订单',
                                    icon: 'md-settings',
                                    routeName: 'shoppingExceptionalOrder',
                                },
                            ]
                        },
                        {
                            name: '商城设置',
                            icon: 'md-settings',
                            children: [
                                {
                                    name: '商城设置',
                                    icon: 'md-settings',
                                    routeName: 'shoppingSettings'
                                },
                                {
                                    name: '首页banner图',
                                    icon: 'md-settings',
                                    routeName: 'shoppingBanner'
                                },
                                {
                                    name: '商品分类',
                                    icon: 'md-settings',
                                    routeName: 'shoppingGType'
                                },
                                {
                                    name: '商品品牌',
                                    icon: 'md-settings',
                                    routeName: 'shoppingBrand'
                                }
                            ]
                        },
                        {
                            name: '支付设置',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    name: '微信支付',
                                    icon: 'md-settings',
                                    routeName: 'shoppingWeixinPay',
                                },
                                {
                                    name: '支付宝支付',
                                    icon: 'md-settings',
                                    routeName: 'shoppingAliPay',
                                },
                            ]
                        }
                    ]
                },
                {
                    name: '小黑屋',
                    icon: 'md-settings',
                    routeName: 'appCenter',
                    children: [
                        {
                            name: '设置',
                            icon: 'md-settings',
                            routeName: 'dashboard'
                        },
                        {
                            name: '小黑人列表',
                            icon: 'md-settings',
                            routeName: 'appCenter'
                        }
                    ]
                },
                {
                    name: '记账',
                    icon: 'md-settings',
                    routeName: 'enterprise',
                    children: [
                        {
                            name: '设置',
                            icon: 'md-settings',
                            routeName: 'dashboard'
                        },
                        {
                            name: '小黑人列表',
                            icon: 'md-settings',
                            routeName: 'appCenter'
                        }
                    ]
                },
                {
                    name: '手机管理',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            name: '手机列表',
                            icon: 'md-settings',
                            routeName: 'phoneList'
                        }
                    ]
                }
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
                openNames,
                activeName,
                selectList,
                topMenu,
                nowMenu,
                setNowMenu,
                leftMenu,
                loadMenu
            }
        }
    })
