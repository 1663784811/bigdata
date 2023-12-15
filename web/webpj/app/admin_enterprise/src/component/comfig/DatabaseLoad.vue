<template>
  <Modal
      v-model="dataLoad.show"
      title="加载数库"
      width="80vw"
      @on-ok="databaseLoadOkFn"
  >
    <div>
      <Table :columns="dataLoad.columns"
             :data="dataLoad.data"
             :loading="dataLoad.loading"
             :height="300"
             @on-row-click="selectTable"
             highlight-row
      ></Table>
    </div>
    <Input v-model="dataLoad.jsData" type="textarea" :rows="30"/>
  </Modal>
</template>
<script setup>

import {Input} from "view-ui-plus";
import {defineEmits, ref} from "vue";

const emits = defineEmits(['event']);

const dataLoad = ref({
  show: false,
  loading: false,
  columns: [
    {
      title: '数据表',
      key: 'table',
      width: 160
    },
    {
      title: '名称',
      key: 'note'
    },
    {
      title: '操作',
      key: 'operation'
    }
  ],
  data: [],
  newTable: {},
  jsData: ''
});



const databaseLoadOkFn = () => {
  // const {selectObj, tableObj, saveObj} = state.databaseLoad.data
  // state.selectObj = selectObj;
  // state.tableObj = tableObj;
  // state.saveObj = saveObj;

  emits('')

}


const selectTable = (item) => {
  state.databaseLoad.newTable = item.pageConfig.newTable;
  state.databaseLoad.jsData = JSON.stringify(state.databaseLoad.newTable, null, "  ");
}

</script>

<style scoped lang="less">

</style>