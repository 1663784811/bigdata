import {defineStore} from 'pinia'
import {reactive, ref} from "vue";
import {apiAdminMenu} from "@/api/api.js"

export const useAdminMenuStore = defineStore('adminMenu',
    {
        persist: {
            enabled: true,//开启数据持久化
            strategies: [
                {
                    key: 'storeMenu',
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
                    title: 'Dashboard',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            title: '主控台',
                            icon: 'md-settings',
                            routeName: 'dashboard'
                        }
                    ]
                },
                {
                    title: '后台设置',
                    icon: 'md-settings',
                    children: [
                        {
                            title: '组积架构',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    title: '角色列表',
                                    icon: 'md-settings',
                                    routeName: 'role'
                                },
                                {
                                    title: '管理员管理',
                                    icon: 'md-settings',
                                    routeName: 'admin'
                                },
                                {
                                    title: '权限管理',
                                    icon: 'md-settings',
                                    routeName: 'adminPower'
                                },
                                {
                                    title: '权限分配到角色',
                                    icon: 'md-settings',
                                    routeName: 'adminRolePower'
                                },
                                {
                                    title: '菜单管理',
                                    icon: 'md-settings',
                                    routeName: 'menu'
                                }
                            ]
                        },
                        {
                            title: '任务管理',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    title: '待办任务',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            title: '我的待办',
                                            icon: 'md-settings',
                                            routeName: 'user'
                                        },
                                        {
                                            title: '全部待办',
                                            icon: 'md-settings',
                                            routeName: 'adminPower'
                                        }
                                    ]
                                },
                                {
                                    title: '任务申请',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            title: '请假申请',
                                            icon: 'md-settings',
                                            routeName: ''
                                        },
                                        {
                                            title: '采购申请',
                                            icon: 'md-settings',
                                            routeName: ''
                                        },
                                        {
                                            title: '发起会议',
                                            icon: 'md-settings',
                                            routeName: ''
                                        }
                                    ]
                                },
                                {
                                    title: '流程管理',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            title: '模型管理',
                                            icon: 'md-settings',
                                            routeName: ''
                                        },
                                        {
                                            title: '流程列表',
                                            icon: 'md-settings',
                                            routeName: ''
                                        }
                                    ]
                                },

                            ]
                        },
                    ]
                },
                {
                    title: '订单管理',
                    icon: 'md-settings',
                    children: [
                        {
                            title: '订单列表',
                            icon: 'md-settings',
                            routeName: 'orderList',
                        },
                        {
                            title: '异常订单',
                            icon: 'md-settings',
                            routeName: 'abnormalOrder',
                        },
                        {
                            title: '发货',
                            icon: 'md-settings',
                            routeName: 'sendOutGoods',
                        },
                    ]
                },
                {
                    title: '商品管理',
                    icon: 'md-settings',
                    children: [
                        {
                            title: '商品上下架',
                            icon: 'md-settings',
                            routeName: 'saleGoods',
                        },
                        {
                            title: '商品管理',
                            icon: 'md-settings',
                            routeName: 'goodsList',
                        },
                    ]
                },
                {
                    title: '仓库管理',
                    icon: 'md-settings',
                    children: [
                        {
                            title: '仓库概览',
                            icon: 'md-settings',
                            routeName: 'dashboard',
                        },
                        {
                            title: '仓库列表',
                            icon: 'md-settings',
                            routeName: 'depositoryList',
                        },
                        {
                            title: '仓库商品',
                            icon: 'md-settings',
                            routeName: 'depositoryGoods',
                        },
                        {
                            title: '进出库日志',
                            icon: 'md-settings',
                            routeName: 'depositoryLog',
                        }
                    ]
                },
                {
                    title: '门店设置',
                    icon: 'md-settings',
                    children: [
                        {
                            title: '门店banner图',
                            icon: 'md-settings',
                            routeName: 'storeBanner'
                        },
                        {
                            title: '商品分类',
                            icon: 'md-settings',
                            routeName: 'goodsType',
                        },
                        {
                            title: '商品品牌',
                            icon: 'md-settings',
                            routeName: 'goodsBrand',
                        },
                    ]
                },
                {
                    title: '支付设置',
                    icon: 'md-settings',
                    children: [
                        {
                            title: '微信支付',
                            icon: 'md-settings',
                            routeName: 'weixinPay'
                        },
                        {
                            title: '支付宝支付',
                            icon: 'md-settings',
                            routeName: 'aliPay'
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

            const loadMenu = (params, appid) => {
                apiAdminMenu(params, appid).then((res) => {
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
