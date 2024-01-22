export const enterprise = {
    commonTable: {
        requestObj: {
            queryRequest: {
                url: '/admin/enterprise/findPage',
                parameter: {
                    size: 2
                }
            },
            saveRequest: {
                url: '/admin/common/save',
                parameter: {
                    table: 't_power',
                    data:[]
                }
            },
            delRequest: {
                url: '/admin/common/sql/delSql',
            }
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
                key: "note",
                title: "备注",
                length: 255,
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
