<template>
  <div>
    <CommonTable
        :search-columns="tableData.searchColumns"
        :table-columns="tableData.columns"
        :table-data="tableData.data"
        :operation="tableData.operation"
        :save-columns="tableData.saveColumns"
        :request-obj="tableData.requestObj"
    />


  </div>
</template>

<script setup>

import {ref} from "vue";
import {useRoute} from 'vue-router'

import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const $router = useRoute();
const usePageConfig = pageConfig();
const role = usePageConfig.getPageConfig("power");
const query = $router.query;
console.log('sssssseeeeeeeeeeeeeeeeeeeeeeee')

const commonTable = role.commonTable;

const tableData = ref({
  searchColumns: commonTable.search.columns,
  columns: commonTable.columns,
  operation: commonTable.operation,
  saveColumns: commonTable.save.columns,
  requestObj: commonTable.requestObj,
  data: []
})

</script>

<style scoped>

</style>
