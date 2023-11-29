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
                            name: '应用中心',
                            icon: 'md-settings',
                            routeName: 'appCenter'
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
                        }
                    ]
                },
                {
                    name: '后台管理',
                    icon: 'md-settings',
                    routeName: 'power',
                    children: [
                        {
                            name: '部门管理',
                            icon: 'md-settings',
                            routeName: 'adminDepartment'
                        },
                        {
                            name: '管理员管理',
                            icon: 'md-settings',
                            routeName: 'adminPower'
                        },
                        {
                            name: '岗位管理',
                            icon: 'md-settings',
                            routeName: 'adminPost'
                        },
                        {
                            name: '角色列表',
                            icon: 'md-settings',
                            routeName: 'role'
                        },
                        {
                            name: '权限分配',
                            icon: 'md-settings',
                            routeName: 'power'
                        },
                        {
                            name: '菜单管理',
                            icon: 'md-settings',
                            routeName: 'menu'
                        },
                        {
                            name: '用户管理',
                            icon: 'md-settings',
                            routeName: 'user'
                        },
                    ]
                },
                {
                    name: '任务管理',
                    icon: 'md-settings',
                    routeName: '',
                    children: [
                        {
                            name: '待办任务',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    name: '我的待办',
                                    icon: 'md-settings',
                                    routeName: 'user'
                                },
                                {
                                    name: '全部待办',
                                    icon: 'md-settings',
                                    routeName: 'adminPower'
                                }
                            ]
                        },
                        {
                            name: '任务申请',
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
                                    routeName: ''
                                }
                            ]
                        },
                        {
                            name: '流程管理',
                            icon: 'md-settings',
                            routeName: '',
                            children: [
                                {
                                    name: '模型管理',
                                    icon: 'md-settings',
                                    routeName: ''
                                },
                                {
                                    name: '流程列表',
                                    icon: 'md-settings',
                                    routeName: ''
                                }
                            ]
                        },

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
