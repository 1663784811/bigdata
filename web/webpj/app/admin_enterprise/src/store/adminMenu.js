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
                    title: 'Dashboard',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            title: '主控台',
                            icon: 'md-settings',
                            routeName: 'dashboard'
                        },
                        {
                            title: '系统监控',
                            icon: 'md-settings',
                            routeName: 'systemMonitor'
                        },
                        {
                            title: '应用中心',
                            icon: 'md-settings',
                            routeName: 'appCenter'
                        },
                        {
                            title: '面试试题',
                            icon: 'md-settings',
                            routeName: 'questions'
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
                                    title: '部门管理',
                                    icon: 'md-settings',
                                    routeName: 'adminDepartment'
                                },
                                {
                                    title: '管理员管理',
                                    icon: 'md-settings',
                                    routeName: 'admin'
                                },
                                {
                                    title: '角色列表',
                                    icon: 'md-settings',
                                    routeName: 'role'
                                },
                                {
                                    title: '权限管理',
                                    icon: 'md-settings',
                                    routeName: 'adminPower'
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
                                    title: '流程管理',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            title: '模型管理',
                                            icon: 'md-settings',
                                            routeName: 'modelList'
                                        },
                                        {
                                            title: '部署管理',
                                            icon: 'md-settings',
                                            routeName: 'modelArrange'
                                        },
                                    ]
                                },
                                {
                                    title: '待办任务',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            title: '我的待办',
                                            icon: 'md-settings',
                                            routeName: 'myTask'
                                        },
                                        {
                                            title: '全部待办',
                                            icon: 'md-settings',
                                            routeName: 'adminPower'
                                        },
                                    ]
                                },
                                {
                                    title: '功能表',
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
                                            routeName: 'meeting'
                                        }
                                    ]
                                },
                                {
                                    title: '流程监控',
                                    icon: 'md-settings',
                                    routeName: '',
                                    children: [
                                        {
                                            title: '流程实例',
                                            icon: 'md-settings',
                                            routeName: 'processList'
                                        },
                                        {
                                            title: '运行历史',
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
                    title: '商城',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            title: '商城概览',
                            icon: 'md-settings',
                            routeName: 'shoppingDashboard',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '用户管理',
                            icon: 'md-settings',
                            routeName: 'shoppingUser',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '门店管理',
                            icon: 'md-settings',
                            children: [
                                {
                                    title: '门店列表',
                                    icon: 'md-settings',
                                    routeName: 'shoppingStore',
                                    params: {
                                        appId: 'sss'
                                    }
                                }
                            ]
                        },
                        {
                            title: '订单管理',
                            icon: 'md-settings',
                            routeName: 'dashboard',
                            children: [
                                {
                                    title: '订单列表',
                                    icon: 'md-settings',
                                    routeName: 'shoppingOrderList',
                                    params: {
                                        appId: 'sss'
                                    }
                                },
                                {
                                    title: '异常订单',
                                    icon: 'md-settings',
                                    routeName: 'shoppingExceptionalOrder',
                                    params: {
                                        appId: 'sss'
                                    }
                                },
                            ]
                        },
                        {
                            title: '商城设置',
                            icon: 'md-settings',
                            children: [
                                {
                                    title: '商城设置',
                                    icon: 'md-settings',
                                    routeName: 'shoppingSettings',
                                    params: {
                                        appId: 'sss'
                                    }
                                },
                                {
                                    title: '首页banner图',
                                    icon: 'md-settings',
                                    routeName: 'shoppingBanner',
                                    params: {
                                        appId: 'sss'
                                    }
                                },
                                {
                                    title: '商品分类',
                                    icon: 'md-settings',
                                    routeName: 'shoppingGType',
                                    params: {
                                        appId: 'sss'
                                    }
                                },
                                {
                                    title: '商品品牌',
                                    icon: 'md-settings',
                                    routeName: 'shoppingBrand',
                                    params: {
                                        appId: 'sss'
                                    }
                                }
                            ]
                        },
                        {
                            title: '支付设置',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    title: '微信支付',
                                    icon: 'md-settings',
                                    routeName: 'shoppingWeixinPay',
                                    params: {
                                        appId: 'sss'
                                    }
                                },
                                {
                                    title: '支付宝支付',
                                    icon: 'md-settings',
                                    routeName: 'shoppingAliPay',
                                    params: {
                                        appId: 'sss'
                                    }
                                },
                            ]
                        }
                    ]
                },
                {
                    title: '点餐系统',
                    icon: 'md-settings',
                    routeName: 'appCenter',
                    children: [
                        {
                            title: '系统概览',
                            icon: 'md-settings',
                            routeName: 'dashboard',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '门店管理',
                            icon: 'md-settings',
                            routeName: 'appCenter',
                            params: {
                                appId: 'sss'
                            }
                        }
                    ]
                },
                {
                    title: '小黑屋',
                    icon: 'md-settings',
                    routeName: 'appCenter',
                    children: [
                        {
                            title: '设置',
                            icon: 'md-settings',
                            routeName: 'dashboard',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '小黑人列表',
                            icon: 'md-settings',
                            routeName: 'appCenter',
                            params: {
                                appId: 'sss'
                            }
                        }
                    ]
                },
                {
                    title: '记账',
                    icon: 'md-settings',
                    routeName: 'enterprise',
                    children: [
                        {
                            title: '设置',
                            icon: 'md-settings',
                            routeName: 'dashboard',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '小黑人列表',
                            icon: 'md-settings',
                            routeName: 'appCenter',
                            params: {
                                appId: 'sss'
                            }
                        }
                    ]
                },
                {
                    title: '手机管理',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            title: '手机列表',
                            icon: 'md-settings',
                            routeName: 'phoneList',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '公司',
                            icon: 'md-settings',
                            routeName: 'companyList',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '黑洞',
                            icon: 'md-settings',
                            routeName: 'spiderData',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '单词解释',
                            icon: 'md-settings',
                            routeName: 'bookWord',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '招聘列表',
                            icon: 'md-settings',
                            routeName: 'recruitList',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '商业历史',
                            icon: 'md-settings',
                            routeName: 'commerceHistory',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '人物录',
                            icon: 'md-settings',
                            routeName: 'people',
                            params: {
                                appId: 'sss'
                            }
                        }
                    ]
                },
                {
                    title: '交友APP',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            title: '用户列表',
                            icon: 'md-settings',
                            routeName: 'friendsUser',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '好友列表',
                            icon: 'md-settings',
                            routeName: 'friendsList',
                            params: {
                                appId: 'sss'
                            }
                        },
                        {
                            title: '内容管理',
                            icon: 'md-settings',
                            routeName: 'friendsContent',
                            params: {
                                appId: 'sss'
                            }
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
                    console.log('sss', res)
                    //topMenu.value.push(...res.data);
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
