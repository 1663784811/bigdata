<template>
  <div>
    <CommonTable
        :table-columns="tableData.columns"
        :table-data="tableData.data"
        :operation="tableData.operation"
    />


  </div>
</template>

<script setup>

import {ref} from "vue";
import {Modal} from "view-ui-plus";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'


const usePageConfig = pageConfig();
const role = usePageConfig.getPageConfig("role");

const commonTable = role.commonTable;


const pageData = ref({
  page: 1,
  total: 0,
  size: 30
});


const tableData = ref({
  columns: commonTable.columns,
  operation: commonTable.operation,
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


// ===============================
const selectTableData = (row, index, editor = false) => {
  console.log(row);
}
/**
 * 删除数据
 */
const delTableData = (row, index) => {
  Modal.confirm({
    title: '是否删除?',
    content: `${row.name}`,
    okText: '删除',
    loading: true,
    onOk: () => {
      console.log("onOk", this);
      Modal.remove();
    },
  });
}


const changePage = (page) => {
  pageData.value.page = page;
  // loadTableData();
}


</script>

<style scoped>

</style>
