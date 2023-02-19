export const power = {
    commonTable: {
        requestObj: {
            queryRequest: {
                url: '/admin/common/query',
                parameter: {
                    code: 'select_power'
                }
            },
            saveRequest: {
                url: '/admin/common/sql/saveSql',

            },
            delRequest: {
                url: '/admin/common/sql/delSql',
            }
        },
        search: {
            columns: [
                {
                    key: 'name',
                    name: '名称',
                    type: 'input',
                    note: '名称备注'
                }
            ]
        },
        save: {
            columns: [
                {
                    width: 60,
                    key: "id",
                    title: "id",
                    type: "selection",
                    length: 10,
                    controlType: "hidden",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "code",
                    title: "受权码",
                    length: 255,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "create_time",
                    title: "创建时间",
                    length: 19,
                    controlType: "datetime",
                    isShowColumn: true,
                    isWhere: true,
                    sortable: "custom",
                    javaWhere: "equals",
                    javaType: "date"
                }
                , {
                    key: "del",
                    title: "是否删除",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    filters: [
                        {
                            value: 0,
                            label: "否"
                        }
                        , {
                            value: 1,
                            label: "是"
                        }
                    ],
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "icon",
                    title: "图标",
                    length: 255,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "ispower",
                    title: "是否受权限控制",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    filters: [
                        {
                            value: 1,
                            label: "是"
                        }
                        , {
                            value: 0,
                            label: "否"
                        }
                    ],
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "name",
                    title: "名称",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "note",
                    title: "备注",
                    length: 255,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "pid",
                    title: "父级ID",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "powertype",
                    title: "权限类型",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    filters: [
                        {
                            value: 1,
                            label: "菜单"
                        }
                        , {
                            value: 2,
                            label: "按钮"
                        }
                    ],
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "sort",
                    title: "排序",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "status",
                    title: "状态",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    filters: [
                        {
                            value: 1,
                            label: "显示"
                        }
                        , {
                            value: 0,
                            label: "隐藏"
                        }
                    ],
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "tid",
                    title: "tid",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "treecode",
                    title: "树码(一级三位)",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "url",
                    title: "url",
                    length: 255,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "is_power",
                    title: "是否受权限控制",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    filters: [
                        {
                            value: 1,
                            label: "是"
                        }
                        , {
                            value: 0,
                            label: "否"
                        }
                    ],
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "power_type",
                    title: "权限类型",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    filters: [
                        {
                            value: 1,
                            label: "菜单"
                        }
                        , {
                            value: 2,
                            label: "按钮"
                        }
                    ],
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "tree_code",
                    title: "树码(一级三位)",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
            ]
        },
        columns: [

            {
                width: 60,
                key: "id",
                title: "id",
                type: "selection",
                length: 10,
                controlType: "hidden",
                max: "",
                min: "",
                isShowColumn: true,
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "code",
                title: "受权码",
                length: 255,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "create_time",
                title: "创建时间",
                length: 19,
                controlType: "datetime",
                isShowColumn: true,
                isWhere: true,
                sortable: "custom",
                javaWhere: "equals",
                javaType: "date"
            }
            , {
                key: "del",
                title: "是否删除",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                filters: [
                    {
                        value: 0,
                        label: "否"
                    }
                    , {
                        value: 1,
                        label: "是"
                    }
                ],
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "icon",
                title: "图标",
                length: 255,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "ispower",
                title: "是否受权限控制",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                filters: [
                    {
                        value: 1,
                        label: "是"
                    }
                    , {
                        value: 0,
                        label: "否"
                    }
                ],
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "name",
                title: "名称",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "note",
                title: "备注",
                length: 255,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "pid",
                title: "父级ID",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "powertype",
                title: "权限类型",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                filters: [
                    {
                        value: 1,
                        label: "菜单"
                    }
                    , {
                        value: 2,
                        label: "按钮"
                    }
                ],
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "sort",
                title: "排序",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "status",
                title: "状态",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                filters: [
                    {
                        value: 1,
                        label: "显示"
                    }
                    , {
                        value: 0,
                        label: "隐藏"
                    }
                ],
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "tid",
                title: "tid",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "treecode",
                title: "树码(一级三位)",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "url",
                title: "url",
                length: 255,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "is_power",
                title: "是否受权限控制",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                filters: [
                    {
                        value: 1,
                        label: "是"
                    }
                    , {
                        value: 0,
                        label: "否"
                    }
                ],
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "power_type",
                title: "权限类型",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                filters: [
                    {
                        value: 1,
                        label: "菜单"
                    }
                    , {
                        value: 2,
                        label: "按钮"
                    }
                ],
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "tree_code",
                title: "树码(一级三位)",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
        ],
        operation: {
            show: true,
            update: true,
            del: true
        }
    }

}
