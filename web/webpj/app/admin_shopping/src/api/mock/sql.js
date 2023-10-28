export const sql = {
    commonTable: {
        requestObj: {
            queryRequest: {
                url: '/admin/common/query',
                parameter: {
                    code: 'save_table'
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
                    key: 'name',
                    name: '名称',
                    type: 'input',
                    note: '名称备注'
                },
                {
                    key: 'name1',
                    name: '名称1',
                    type: 'input',
                    note: '名称备注'
                },
                {
                    key: 'name2',
                    name: '名称2',
                    type: 'input',
                    note: '名称备注'
                }
            ]
        },
        columns: [
            {
                title: 'ID',
                key: 'tid',
                width: 250
            },
            {
                title: '账号',
                key: 'account'
            },
            {
                title: '分类',
                key: 'type'
            },
            {
                title: '备注',
                key: 'tags'
            }
        ],
        operation: {
            show: true,
            update: true,
            del: true
        }
    }

}
