export const PageComponents = {
    commonTable: {
        requestObj: {
            queryRequest: {
                url: '/admin/config/cpagecomponents/findPage',
                parameter: {}
            },
            saveRequest: {
                url: '/admin/config/cpagecomponents/saveCPageComponents',

            },
            delRequest: {
                url: '/admin/config/cpagecomponents/delCPageComponents',
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
                key: "components_code",
                title: "类型",
                length: 45,
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
                key: "data",
                title: "数据",
                length: 65535,
                controlType: "textarea",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
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
                title: "icon图标",
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
                length: 45,
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
                key: "page_id",
                title: "页面ID",
                length: 45,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
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
                key: "tid",
                title: "tid",
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
