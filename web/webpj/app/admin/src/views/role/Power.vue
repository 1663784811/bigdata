<template>
  <div>
    <CommonTable
        :search-columns="tableData.search.columns"
        :table-columns="tableData.columns"
        :table-data="tableData.data"
        :operation="tableData.operation"
        :save-columns="tableData.saveColumns"
        :search-url="tableData.searchUrl"
        :save-url="tableData.saveUrl"
        :del-url="tableData.delUrl"
    />


  </div>
</template>

<script setup>

import {ref} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const role = usePageConfig.getPageConfig("role");

const commonTable = role.commonTable;

const tableData = ref({
  search: commonTable.search,
  columns: commonTable.columns,
  operation: commonTable.operation,
  saveColumns: commonTable.save.columns,
  searchUrl: commonTable.search.searchUrl,
  saveUrl: commonTable.search.saveUrl,
  delUrl: commonTable.search.delUrl,
  data: [
    {
      name: 'John Brown',
      age: 18,
      address: 'New York No. 1 Lake Park',
      date: '2016-10-03'
    },
    {
      name: 'Jim Green',
      age: 24,
      address: 'London No. 1 Lake Park',
      date: '2016-10-01'
    },
    {
      name: 'Joe Black',
      age: 30,
      address: 'Sydney No. 1 Lake Park',
      date: '2016-10-02'
    },
    {
      name: 'Jon Snow',
      age: 26,
      address: 'Ottawa No. 2 Lake Park',
      date: '2016-10-04'
    }

  ]
})

</script>

<style scoped>

</style>
