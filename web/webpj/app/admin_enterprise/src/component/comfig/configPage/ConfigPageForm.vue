<template>
  <div class="aaa">
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="state.databaseLoad = true">加载数据库</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看配置</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
  </div>
  <Tabs v-model="configModule.configPage.tabsName" @onClick="(name)=>{configModule.configPage.tabsName = name}">
    <TabPane label="查询" name="查询">
      <div>
        <ObjRequest :setting="state.outObj.queryObj" title="查询地址"/>
      </div>
    </TabPane>
    <TabPane label="添加" name="添加">
      <div class="configBox">
        <ObjSave :setting="state.outObj.addObj"/>
      </div>
    </TabPane>
    <TabPane label="更新" name="更新">
      <div class="configBox">
        <ObjSave :setting="state.outObj.updateObj"/>
      </div>
    </TabPane>
    <TabPane label="删除" name="删除">
      <div>
        <ObjRequest :setting="state.outObj.delObj" title="删除地址"/>
      </div>
    </TabPane>
  </Tabs>

  <Modal v-model="state.jsonData.show" :loading="state.jsonData.loading" title="数据" width="80vw" @on-ok="saveComponentsFn">
    <Input v-model="state.jsonData.data" type="textarea" :rows="40"/>
  </Modal>
  <Modal v-model="state.showCode.show" title="查看代码" width="80vw" @on-ok="showCodeHandleFn">
    <Input v-model="state.showCode.data" type="textarea" :rows="30"/>
  </Modal>
  <DatabaseLoad v-model="state.databaseLoad" @event="loadDataHandleFn"/>

</template>
<script setup>
import DatabaseLoad from '../DatabaseLoad.vue'
import ObjSave from './com/ObjSave.vue'
import ObjRequest from './com/ObjRequest.vue'


import {onMounted, reactive, watch} from 'vue'
import {Message} from "view-ui-plus";
import {saveComponents} from '@/api/api.js'
import {useConfigModule} from "@/store/configModule.js";
import {useWinModal} from '@/store/winModal.js'


const configModule = useConfigModule();
const winIcon = useWinModal().winIcon;


const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  outObj: {
    // ===============  查询
    queryObj: {},
    // ===============  添加
    addObj: {},
    // ===============  更新
    updateObj: {},
    // ===============  删除
    delObj: {},
  },
  databaseLoad: false,
  showCode: {
    show: false,
    data: '',
    modal: ''
  },
  jsonData: {
    show: false,
    loading: false,
    id: '',
    tid: '',
    data: ''
  },
})


onMounted(() => {
  initFn();
})


const loadDefaultFn = (str) => {
  if (str === 'operation') {
    state.operationObj = state.defaultConfig.operation;
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
    searchObj: state.state,
    tableObj: state.tableObj,
    saveObj: state.saveObj,
  }
  state.jsonData.data = JSON.stringify(json, null, "  ");
}

const loadDataHandleFn = (data) => {
  state.queryObj = data.queryObj;
  state.addObj = data.addObj
  state.updateObj = data.updateObj;
  state.delObj = data.delObj
}

const showCodeHandleFn = () => {
  if (state.showCode.modal === 'table') {
    state.tableObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'search') {
    state.searchObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'save') {
    state.saveObj = JSON.parse(state.showCode.data);
  }
}

const initFn = () => {
  const {setting} = props;
  const {saveObj, tableObj, searchObj, id, tid} = setting
  // if (searchObj) {
  //   state.searchObj = searchObj;
  // }
  // if (tableObj) {
  //   state.tableObj = tableObj;
  //   state.operationObj = tableObj.operation;
  // }
  // if (saveObj) {
  //   state.saveObj = saveObj;
  // }
  // if (id) {
  //   state.jsonData.id = id;
  // }
  // if (tid) {
  //   state.jsonData.tid = tid;
  // }
}


watch(() => props.setting, () => {
  initFn()
})

</script>
<style scoped lang="less">

</style>
