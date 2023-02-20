export const user = {
    commonTable: {
        requestObj: {
            queryRequest: {
                url: '/admin/common/query',
                parameter: {
                    code: 'select_power'
                }
            },
            saveRequest: {
                url: '/admin/common/save',
                parameter: {
                    table: 't_power',
                    data: []
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
                }
                , {
                    key: "account",
                    title: "账号",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "adminid",
                    title: "客服t_admin表id",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "balance",
                    title: "余额",
                    length: 18,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "",
                    javaType: "bigdecimal"
                }
                , {
                    key: "canlogintime",
                    title: "可登录时间",
                    length: 19,
                    controlType: "datetime",
                    isShowColumn: true,
                    isWhere: true,
                    sortable: "custom",
                    javaWhere: "equals",
                    javaType: "date"
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
                    key: "email",
                    title: "邮箱",
                    length: 255,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "face",
                    title: "用户头像",
                    length: 255,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "integral",
                    title: "积分",
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
                    key: "ip",
                    title: "最后登录IP",
                    length: 60,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "lastlogintime",
                    title: "最后登录时间",
                    length: 19,
                    controlType: "datetime",
                    isShowColumn: true,
                    isWhere: true,
                    sortable: "custom",
                    javaWhere: "equals",
                    javaType: "date"
                }
                , {
                    key: "nickname",
                    title: "昵称",
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
                    key: "openid",
                    title: "微信openid",
                    length: 64,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "password",
                    title: "密码",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "phone",
                    title: "手机号",
                    length: 15,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "salt",
                    title: "加密盐",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "sex",
                    title: "性别",
                    length: 5,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
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
                            value: 0,
                            label: "正常"
                        }
                        , {
                            value: 1,
                            label: "暂时锁定"
                        }
                        , {
                            value: 2,
                            label: "永久锁定"
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
                    key: "truename",
                    title: "真实姓名",
                    length: 32,
                    controlType: "input",
                    isShowColumn: true,
                    isWhere: true,
                    javaWhere: "like",
                    javaType: "string"
                }
                , {
                    key: "type",
                    title: "会员类型",
                    length: 10,
                    controlType: "integer",
                    max: "",
                    min: "",
                    isShowColumn: true,
                    isWhere: true,
                    filters: [
                        {
                            value: 0,
                            label: "普通会员"
                        }
                        , {
                            value: 1,
                            label: "客服"
                        }
                    ],
                    javaWhere: "equals",
                    javaType: "integer"
                }
                , {
                    key: "unionid",
                    title: "微信unionid",
                    length: 64,
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
                key: "account",
                title: "账号",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "adminid",
                title: "客服t_admin表id",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "balance",
                title: "余额",
                length: 18,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "",
                javaType: "bigdecimal"
            }
            , {
                key: "canlogintime",
                title: "可登录时间",
                length: 19,
                controlType: "datetime",
                isShowColumn: true,
                isWhere: true,
                sortable: "custom",
                javaWhere: "equals",
                javaType: "date"
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
                key: "email",
                title: "邮箱",
                length: 255,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "face",
                title: "用户头像",
                length: 255,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "integral",
                title: "积分",
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
                key: "ip",
                title: "最后登录IP",
                length: 60,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "lastlogintime",
                title: "最后登录时间",
                length: 19,
                controlType: "datetime",
                isShowColumn: true,
                isWhere: true,
                sortable: "custom",
                javaWhere: "equals",
                javaType: "date"
            }
            , {
                key: "nickname",
                title: "昵称",
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
                key: "openid",
                title: "微信openid",
                length: 64,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "password",
                title: "密码",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "phone",
                title: "手机号",
                length: 15,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "salt",
                title: "加密盐",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "sex",
                title: "性别",
                length: 5,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
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
                        value: 0,
                        label: "正常"
                    }
                    , {
                        value: 1,
                        label: "暂时锁定"
                    }
                    , {
                        value: 2,
                        label: "永久锁定"
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
                key: "truename",
                title: "真实姓名",
                length: 32,
                controlType: "input",
                isShowColumn: true,
                isWhere: true,
                javaWhere: "like",
                javaType: "string"
            }
            , {
                key: "type",
                title: "会员类型",
                length: 10,
                controlType: "integer",
                max: "",
                min: "",
                isShowColumn: true,
                isWhere: true,
                filters: [
                    {
                        value: 0,
                        label: "普通会员"
                    }
                    , {
                        value: 1,
                        label: "客服"
                    }
                ],
                javaWhere: "equals",
                javaType: "integer"
            }
            , {
                key: "unionid",
                title: "微信unionid",
                length: 64,
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
