export const role = {
    commonTable: {
        requestObj: {
            queryRequest: {
                url: '/admin/common/query',
                parameter: {
                    code: 'select_t_role'
                }
            },
            saveRequest: {
                url: '/admin/common/save',
                parameter: {
                    table: 't_role',
                    data:[]
                }
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
                },
                {
                    key: "code",
                    title: "授权码",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                },
                {
                    key: "create_time",
                    title: "创建时间",
                    length: 19,
                    controlType: "datetime",
                    isShowColumn: true,
                    isWhere: true,
                    sortable: "custom",
                    javaWhere: "equals",
                    javaType: "date"
                },
                {
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
                },
                {
                    key: "name",
                    title: "角色名称",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                },
                {
                    key: "note",
                    title: "备注",
                    length: 255,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                },
                {
                    key: "pid",
                    title: "父级ID",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "equals",
                    javaType: "integer"
                },
                {
                    key: "tid",
                    title: "tid",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                },
                {
                    key: "treecode",
                    title: "树码(一级三位)",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                },
                {
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
            },
            {
                key: "code",
                title: "授权码",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            },
            {
                key: "create_time",
                title: "创建时间",
                length: 19,
                controlType: "datetime",
                isShowColumn: true,
                isWhere: true,
                sortable: "custom",
                javaWhere: "equals",
                javaType: "date"
            },
            {
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
            },
            {
                key: "name",
                title: "角色名称",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            },
            {
                key: "note",
                title: "备注",
                length: 255,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            },
            {
                key: "pid",
                title: "父级ID",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "equals",
                javaType: "integer"
            },
            {
                key: "tid",
                title: "tid",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            },
            {
                key: "treecode",
                title: "树码(一级三位)",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            },
            {
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
