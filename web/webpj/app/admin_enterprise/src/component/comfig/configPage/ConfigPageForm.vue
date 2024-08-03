<template>

  <div class="aaa">
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="state.databaseLoad = true">加载数据库</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看配置</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
  </div>
  <Tabs v-model="configModule.configPage.tabsName" @onClick="(name)=>{configModule.configPage.tabsName = name}">
    <TabPane label="查询" name="查询">
      <div>
        sss
      </div>
    </TabPane>
    <TabPane label="添加" name="添加">
      <div class="configBox">
        <SaveObj :setting="state.addObj" />
      </div>
    </TabPane>
    <TabPane label="更新" name="更新">
      <div class="configBox">
        <SaveObj :setting="state.updateObj" />
      </div>
    </TabPane>
    <TabPane label="删除" name="删除">
      <div class="configBox">
        木ssdddddd
      </div>
    </TabPane>
  </Tabs>
  <DatabaseLoad v-model="state.databaseLoad" @event="loadDataHandleFn"/>

</template>
<script setup>
import DatabaseLoad from '../DatabaseLoad.vue'
import SaveObj from './com/SaveObj.vue'
import {reactive, onMounted, watch, ref, provide} from 'vue'
import {Input, Message} from "view-ui-plus";
import {saveComponents, loadTable} from '@/api/api.js'
import {useConfigModule} from "@/store/configModule.js";
import draggable from "vuedraggable";
import {useWinModal} from '@/store/winModal.js'


const configModule = useConfigModule();
const winIcon = useWinModal().winIcon;

const commonTableSearchData = ref({})
provide("commonTableSearchData", commonTableSearchData);




const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  // ===============  查询
  queryObj: {
    url: "/appAdmin/{appid}/common/del",
    parameter: {
      code: ""
    }
  },
  // ===============  添加
  addObj: {},
  // ===============  更新
  updateObj: {},
  // ===============  删除
  delObj: {},
  // ===============
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
  // state.jsonData.show = true;
  compileCode();
}

/**
 * 保存组件数据
 */
const saveComponentsFn = () => {
  compileCode();
  // saveComponents({
  //   id: state.jsonData.id,
  //   data: state.jsonData.data
  // }).then(rest => {
  //   Message.success({
  //     background: true,
  //     content: `${rest.msg}`
  //   });
  // }).finally(() => {
  //   state.jsonData.loading = false;
  // })
}

const compileCode = () => {
  // state.tableObj.operation = state.operationObj;
  // const json = {
  //   searchObj: state.searchObj,
  //   tableObj: state.tableObj,
  //   saveObj: state.saveObj,
  // }
  // state.jsonData.data = JSON.stringify(json, null, "  ");
}


const initFn = () => {
  // const {setting} = props;
  // const {saveObj, tableObj, searchObj, id, tid} = setting
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

const loadDataHandleFn = (data) => {
  state.queryObj = data.queryObj;
  state.addObj = data.addObj
  state.updateObj = data.updateObj;
  state.delObj = data.delObj
}


watch(() => props.setting, () => {
  initFn()
}, {deep: false, immediate: false})

</script>
<style scoped lang="less">
.configBox {
  .headerBox {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
  }

  .dataContent {
    min-height: 800px;

    .row {
      display: flex;
      align-items: center;
      padding: 12px 0;
      border-top: 1px solid #f1f1f1;

      &:hover {
        background: #f1f1f1;
      }

      .labelLeft {
        width: 120px;
        text-align: right;
        margin-right: 10px;
      }

      .rightInput {
        flex: 1;
        margin-right: 10px;
      }

      .rowItem {
        margin: 0 4px;
      }

      .sortBtn {
      }

      .saveTitle {
        width: 120px;
        text-align: right;
      }
    }
  }
}

</style>
