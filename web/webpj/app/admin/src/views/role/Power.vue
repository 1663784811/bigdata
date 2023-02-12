<template>
  <div>
    <CommonTable />


  </div>
</template>

<script setup>

import {ref} from "vue";
import {Modal} from "view-ui-plus";
import CommonTable from '@/component/CommonTable.vue'


const pageData = ref({
  page: 1,
  total: 0,
  size: 30
});


const tableData = ref({
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
    },
    {
      title: '操作',
      key: 'operation',
      slot: 'operation',
      width: 200,
    }
  ],
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
