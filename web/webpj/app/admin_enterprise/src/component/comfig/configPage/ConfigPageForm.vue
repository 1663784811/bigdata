<template>
  <div class="aaa">
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="state.databaseLoad = true">加载数据库</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeTableFn('')">查看代码</Button>
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
        <ObjSave :setting="state.outObj.addObj" @showCode="showCodeTableFn('addObj')"/>
      </div>
    </TabPane>
    <TabPane label="更新" name="更新">
      <div class="configBox">
        <ObjSave :setting="state.outObj.updateObj" @showCode="showCodeTableFn('updateObj')"/>
      </div>
    </TabPane>
    <TabPane label="删除" name="删除">
      <div>
        <ObjRequest :setting="state.outObj.delObj" title="删除地址"/>
      </div>
    </TabPane>
  </Tabs>
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

const showCodeTableFn = (modalKey) => {
  state.showCode.show = true;
  state.showCode.modal = modalKey
  if (modalKey) {
    state.showCode.data = JSON.stringify(state.outObj[modalKey], null, "  ");
  } else {
    state.showCode.data = JSON.stringify(state.outObj, null, "  ");
  }
}


const showCodeHandleFn = () => {
  const {modal} = state.showCode;
  if (modal) {
    state.outObj[modal] = JSON.parse(state.showCode.data);
  } else {
    state.outObj = JSON.parse(state.showCode.data);
  }
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
  state.jsonData.data = JSON.stringify(state.outObj, null, "  ");
}

const loadDataHandleFn = (data) => {
  state.outObj = {
    ...data
  };
}


const initFn = () => {
  const {setting} = props;
  console.log(setting)
  const {queryObj, addObj, updateObj, delObj, id} = setting
  if (queryObj) {
    state.outObj.queryObj = queryObj;
  }
  if (addObj) {
    state.outObj.addObj = addObj;
  }
  if (updateObj) {
    state.outObj.updateObj = updateObj;
  }
  if (delObj) {
    state.outObj.delObj = delObj;
  }
  if (id) {
    state.jsonData.id = id;
  }
}


watch(() => props.setting, () => {
  initFn()
})

</script>
<style scoped lang="less">

</style>
