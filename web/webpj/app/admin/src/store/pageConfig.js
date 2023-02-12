import {defineStore} from 'pinia'
import {ref} from "vue";

export const pageConfig = defineStore('pageConfig', () => {
    const pageConfigList = ref({
        role: {
            commonTable: {
                search: {
                    searchUrl: 'sss',
                    saveUrl: 'ddd',
                    delUrl: 'sss',
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
