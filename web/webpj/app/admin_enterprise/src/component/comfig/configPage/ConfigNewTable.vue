<template>
  <div class="aaa">
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="state.databaseLoad = true">加载数据库</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看配置</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
  </div>
  <Tabs v-model="configModule.configPage.tabsName" @onClick="(name)=>{configModule.configPage.tabsName = name}">
    <TabPane label="搜索" name="搜索">
      <obj-search :setting="state.searchObj"/>
    </TabPane>
    <TabPane label="操作对象" name="操作对象">
      <obj-operation :setting="state.operationObj"/>
    </TabPane>
    <TabPane label="表格" name="表格">
      <ObjTable :setting="state.tableObj"/>
    </TabPane>
    <TabPane label="保存" name="保存">
      <ObjSave :setting="state.saveObj"/>
    </TabPane>
    <TabPane label="修改" name="修改">
      <ObjSave :setting="state.updateObj"/>
    </TabPane>
  </Tabs>

  <Modal v-model="state.jsonData.show" :loading="state.jsonData.loading" title="数据" width="80vw" @on-ok="saveComponentsFn">
    <Input v-model="state.jsonData.data" type="textarea" :rows="40"/>
  </Modal>


  <Modal v-model="winCode.show" title="查看代码" width="80vw" @on-ok="showCodeHandleFn">
    <Input v-model="winCode.data" type="textarea" :rows="30"/>
  </Modal>

  <DatabaseLoad v-model="state.databaseLoad" @event="loadDataHandleFn"/>

</template>
<script setup>
import DatabaseLoad from '../DatabaseLoad.vue'
import {onMounted, reactive, watch} from 'vue'
import {Message} from "view-ui-plus";
import {saveComponents} from '@/api/api.js'
import {useConfigModule} from "@/store/configModule.js";
import {useWinModal} from '@/store/winModal.js'
import ObjSave from './com/ObjSave.vue'
import ObjTable from './com/ObjTable.vue'
import ObjSearch from './com/ObjSearch.vue'
import ObjOperation from './com/ObjOperation.vue'

const configModule = useConfigModule();
const {winIcon, winCode} = useWinModal();


const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  // ===============  搜索
  searchObj: {},
  // ===============  表格
  tableObj: {},
  // ===============  保存
  saveObj: {},
  // =============== 修改
  updateObj: {},
  operationObj: {},
  columnsArr: [],
  jsonData: {
    show: false,
    loading: false,
    id: '',
    tid: '',
    data: ''
  },
  databaseLoad: false,
})


onMounted(() => {
  initFn();
})

const loadDataHandleFn = (data) => {
  state.saveObj = data.saveObj;
  state.updateObj = data.updateObj;
  state.searchObj = data.searchObj
  state.tableObj = data.tableObj;
  state.operationObj = data.operationObj
}

const showCodeHandleFn = () => {
  if (winCode.callBack) {
    winCode.callBack(winCode.data);
    winCode.callBack = null;
  }
}


/**
 * 显示代码
 */
const showCodeFn = () => {
  state.jsonData.show = true;
  compileCode();
}

/**
 * 保存组件数据
 */
const saveComponentsFn = () => {
  compileCode();
  saveComponents({
    id: state.jsonData.id,
    data: state.jsonData.data
  }).then(rest => {
    Message.success({
      background: true,
      content: `${rest.msg}`
    });
  }).finally(() => {
    state.jsonData.loading = false;
  })
}

const compileCode = () => {
  state.tableObj.operation = state.operationObj;
  const json = {
    searchObj: state.searchObj,
    tableObj: state.tableObj,
    saveObj: state.saveObj,
    updateObj: state.updateObj,
  }
  state.jsonData.data = JSON.stringify(json, null, "  ");
}

const initFn = () => {
  const {setting} = props;
  const {updateObj, saveObj, tableObj, searchObj, id, tid} = setting
  if (searchObj) {
    state.searchObj = searchObj;
  }
  if (tableObj) {
    state.tableObj = tableObj;
    state.operationObj = tableObj.operation;
  }
  if (saveObj) {
    state.saveObj = saveObj;
  }
  if (updateObj) {
    state.updateObj = updateObj;
  }
  if (id) {
    state.jsonData.id = id;
  }
  if (tid) {
    state.jsonData.tid = tid;
  }
}

watch(() => props.setting, () => {
  initFn()
})

</script>
<style scoped lang="less">
</style>
