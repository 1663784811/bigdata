<template>
  <div>
    <CommonTable
        :search-columns="tableData.search.columns"
        :table-columns="tableData.columns"
        :table-data="tableData.data"
        :operation="tableData.operation"
        :save-columns="tableData.saveColumns"
        :request-obj="tableData.requestObj"
    />


  </div>
</template>

<script setup>

import {ref, onMounted} from "vue";
import {useRoute} from 'vue-router'

import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const $router = useRoute();
const usePageConfig = pageConfig();
const role = usePageConfig.getPageConfig("role");


onMounted(function () {
  console.log('sssssssssssssssssssss', $router.query.id);

  // route.push({
  //   name: 'search',
  //   query: {/**/},
  // })
});

const commonTable = role.commonTable;

const tableData = ref({
  search: commonTable.search,
  columns: commonTable.columns,
  operation: commonTable.operation,
  saveColumns: commonTable.save.columns,
  requestObj: commonTable.requestObj,
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
