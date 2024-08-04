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
    <div>
      <Select v-model="dataLoad.selectKey" style="width:200px">
        <Option v-for="item in dataLoad.selectType" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
    </div>
    <div>
      <Input v-model="dataLoad.jsData" type="textarea" :rows="30"/>
    </div>
  </Modal>
</template>
<script setup>

import {ref, watch} from "vue";
import {loadTable} from "@/api/api.js";

const emits = defineEmits(['event', "update:modelValue"]);


const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false
  }
});

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
  selectData: {},
  selectType: [
    {
      value: 'newTable',
      label: '新表格'
    },
    {
      value: 'selectData',
      label: '数据选择'
    },
    {
      value: 'column',
      label: '字段数据'
    },
    {
      value: "dataTree",
      label: "数据树"
    },
    {
      value: 'pageForm',
      label: '页面表单'
    },
  ],
  jsData: '',
  selectKey: 'newTable'
});

const initFn = () => {
  dataLoad.value.loading = true;
  loadTable({}).then((res) => {
    const {data} = res;
    dataLoad.value.data = data;
  }).finally(() => {
    dataLoad.value.loading = false;
  })
}


const databaseLoadOkFn = () => {
  emits('event', JSON.parse(dataLoad.value.jsData))
}


const selectTable = (item) => {
  dataLoad.value.selectData = item.pageConfig;
  if (dataLoad.value.selectKey) {
    if (dataLoad.value.selectKey === "column") {
      dataLoad.value.jsData = JSON.stringify(dataLoad.value.selectData.newTable.tableObj.columns, null, "  ");
    } else {
      dataLoad.value.jsData = JSON.stringify(dataLoad.value.selectData[dataLoad.value.selectKey], null, "  ");
    }

  }
}


// ===============================================
watch(() => props.modelValue, () => {
  dataLoad.value.show = props.modelValue;
  if (dataLoad.value.data.length === 0) {
    initFn();
  }
}, {deep: false, immediate: false})

watch(() => dataLoad.value.show, () => {
  emits("update:modelValue", dataLoad.value.show)
})

</script>

<style scoped lang="less">

</style>
