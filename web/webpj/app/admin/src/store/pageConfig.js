import {defineStore} from 'pinia'
import {ref} from "vue";

export const pageConfig = defineStore('pageConfig', () => {
    const pageConfigList = ref({
        role: {
            commonTable: {
                search: {

                },
                columns: [
                    {
                        type: 'selection',
                        rowKey: 'id',
                        width: 60
                    },
                    {
                        title: 'ID',
                        key: 'tid',
                        width: 250
                    },
                    {
                        title: '名称',
                        key: 'name'
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
    })
    const getPageConfig = (pageCode) => {


        return pageConfigList.value[pageCode]
    }
    return {pageConfigList, getPageConfig}
})
