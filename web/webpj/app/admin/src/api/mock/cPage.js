export const cPage = {
    commonTable: {
        requestObj: {
            queryRequest: {
                url: '/admin/config/page/findPage',
                parameter: {

                }
            },
            saveRequest: {
                url: '/admin/config/page/saveCPage',

            },
            delRequest: {
                url: '/admin/config/page/delCPage',
            }
        },
        columns: [
            {
                "width":60,
                "key":"id",
                "title":"id",
                "type":"selection",
                "length":10,
                "controltype":"hidden",
                "max":"",
                "min":"",
                "isShowColumn":true,
                "javawhere":"equals",
                "javatype":"integer"
            }
            ,{
                "key":"createTime",
                "title":"创建时间",
                "length":19,
                "controltype":"datetime",
                "isShowColumn":true,
                "iswhere":true,
                "javawhere":"equals",
                "javatype":"date"
            }
            ,{
                "key":"del",
                "title":"是否删除",
                "length":10,
                "controltype":"integer",
                "max":"",
                "min":"",
                "isShowColumn":true,
                "iswhere":true,
                "filters": [
                    {
                        "value":0,
                        "label":"否"
                    }
                    ,{
                        "value":1,
                        "label":"是"
                    }
                ],
                "javawhere":"equals",
                "javatype":"integer"
            }
            ,{
                "key":"name",
                "title":"名称",
                "length":32,
                "controltype":"input",
                "isShowColumn":true,
                "iswhere":true,
                "javawhere":"like",
                "javatype":"string"
            }
            ,{
                "key":"note",
                "title":"备注",
                "length":255,
                "controltype":"input",
                "isShowColumn":true,
                "iswhere":true,
                "javawhere":"like",
                "javatype":"string"
            }
            ,{
                "key":"pageCode",
                "title":"pageCode",
                "length":32,
                "controltype":"input",
                "isShowColumn":true,
                "iswhere":true,
                "javawhere":"like",
                "javatype":"string"
            }
            ,{
                "key":"pageIcon",
                "title":"图标",
                "length":65535,
                "controltype":"textarea",
                "isShowColumn":true,
                "iswhere":true,
                "javawhere":"like",
                "javatype":"string"
            }
            ,{
                "key":"tid",
                "title":"tid",
                "length":32,
                "controltype":"input",
                "isShowColumn":true,
                "iswhere":true,
                "javawhere":"like",
                "javatype":"string"
            }
        ],
        operation: {
            show: true,
            update: true,
            del: true
        }
    }

}
