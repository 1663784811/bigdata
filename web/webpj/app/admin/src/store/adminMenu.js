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
                    name: '门店管理',
                    icon: 'md-settings',
                    routeName: 'shoppingStore'
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
                {
                    name: '仓库管理',
                    icon: 'md-settings',
                    routeName: 'gDepository'
                },
                {
                    name: '货品出入仓库',
                    icon: 'md-settings',
                    routeName: 'gDepository'
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
                },
                {
                    name: '代码生成',
                    icon: 'md-settings',
                    routeName: 'createCode'
                },
                {
                    name: '部门管理(删除)',
                    icon: 'md-settings',
                    routeName: 'department'
                },
                {
                    name: '角色管理(删除)',
                    icon: 'md-settings',
                    routeName: 'role'
                },
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

    const loadMenu= ()=>{
        apiAdminMenu({}).then((res) => {
            topMenu.value.push(...res.data);
        })
    }



    return {
        topMenu,
        nowMenu,
        setNowMenu,
        leftMenu,
        loadMenu
    }
})
