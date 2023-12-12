<template>
  <Drawer
      placement="left"
      title="配置面板"
      v-model="usePageConfig.componentConfig.show"
      width="1200"
      :mask-closable="false"
  >
    <div>
      选择组件:
      <Select clearable style="width:200px">
        <Option value="commonTable">工共表格</Option>
        <Option value="newTable">新表格</Option>
        <Option value="selectDataDrawer">选择数据</Option>
      </Select>
    </div>
    <div class="commonTableBox">
      <ConfigCommonTable/>
    </div>
    <div class="newTable">
      <ConfigNewTable/>
    </div>
    <div class="selectDataDrawer">
      <ConfigSelectData/>
    </div>
  </Drawer>

  <div class="configOperation" v-show="state.pageStatus.showOperation">
    <div class="pageCodeItem"
         v-for="(item, index) in usePageConfig.componentConfig.pageCodeList"
         :key="index"
         @click="loadData(item)"
    >
      {{ item }}
    </div>
  </div>

</template>
<script setup>
import ConfigCommonTable from './ConfigCommonTable.vue'
import ConfigNewTable from './ConfigNewTable.vue'
import ConfigSelectData from './ConfigSelectData.vue'
import {onMounted, reactive} from "vue";
import {pageConfig} from "@/store/pageConfig.js";
import {pageSetting} from "@/api/api.js";
import {useRoute, useRouter} from "vue-router";

const usePageConfig = pageConfig();
const router = useRouter();
const route = useRoute();


const state = reactive({
  pageStatus: {
    showOperation: false
  },
})


onMounted(() => {

  setTimeout(() => {
    console.log("sssssssssssssssssssssssssdddddddddddddddd")
    if (route.query.debugger) {
      state.pageStatus.showOperation = true;
    }
  }, 1000)
})

const loadData = (pageCode) => {
  pageSetting({
    pageCode
  }).then((rest) => {
    usePageConfig.componentConfig.show = true;
    // const {columns, operation, requestObj, id, tid} = rest.data.commonTable
    // state.requestObjData = requestObj;
    // state.operationObj = operation;
    // state.columnsArr = columns;
    // state.jsonData.id = id;
    // state.jsonData.tid = tid;
  })
}

</script>


<style lang="less">
.configOperation {
  position: fixed;
  right: 10px;
  top: 300px;
  z-index: 99;
  background: #fff;
  min-width: 50px;
  border: 1px solid #ccc;
  padding: 6px;
  border-radius: 4px;
  min-height: 100px;

  .pageCodeItem {
    display: flex;
    justify-content: center;
    align-items: center;
    background: #ccc;
    margin: 2px 0;
    cursor: pointer;
  }
}
</style>