export const store = {
    commonTable: {
        requestObj: {
            queryRequest: {
                url: '/admin/store/findPage',
                parameter: {
                }
            },
            saveRequest: {
                url: '/admin/common/sql/saveSql',

            },
            delRequest: {
                url: '/admin/common/sql/delSql',
            }
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
