<template>

  <div class="aaa">
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="state.databaseLoad = true">加载数据库</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看配置</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
  </div>

  <Tabs v-model="configModule.configPage.tabsName" @onClick="(name)=>{configModule.configPage.tabsName = name}">
    <TabPane label="搜索" name="搜索">
      <obj-search :setting="state.searchObj" />
    </TabPane>
    <TabPane label="操作对象" name="操作对象">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-list" @click="loadDefaultFn('operation')">加载默认</Button>
          </div>
        </div>
        <div class="dataContent" v-if="state.operationObj">
          <div class="row">
            <div class="labelLeft">操作标题:</div>
            <Checkbox border v-model="state.operationObj.show"></Checkbox>
            <div class="rightInput">
              <Input v-model="state.operationObj.title" placeholder="标题"/>
            </div>
            <div>宽度:</div>
            <div class="rightInput">
              <Input v-model="state.operationObj.width" placeholder="宽" clearable type="number"/>
            </div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="addOperationFn">添加</Button>
          </div>
          <div class="row" v-for="(item, index) in state.operationObj.operationArr" :key="index">
            <div class="sortBtn">
              <Button size="small" type="error" icon="ios-trash-outline" @click="delOperationFn(index)"/>
              <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up"/>
            </div>
            <div>名称:</div>
            <Checkbox border v-model="item.show"></Checkbox>
            <div class="rightInput">
              <Input v-model="item.label" placeholder="名称"/>
            </div>
            <div>事件:</div>
            <div class="rightInput">
              <Input v-model="item.even" placeholder="事件" clearable/>
            </div>
          </div>
        </div>
      </div>
    </TabPane>
    <TabPane label="表格" name="表格">
      <ObjTable :setting="state.tableObj" @showCode="showCodeTableFn('table')"/>
    </TabPane>
    <TabPane label="保存" name="保存">
      <ObjSave :setting="state.saveObj" @showCode="showCodeTableFn('save')"/>
    </TabPane>
    <TabPane label="修改" name="修改">
      <ObjSave :setting="state.updateObj" @showCode="showCodeTableFn('update')"/>
    </TabPane>
  </Tabs>
  <Modal v-model="state.jsonData.show" :loading="state.jsonData.loading" title="数据" width="80vw"
         @on-ok="saveComponentsFn">
    <Input v-model="state.jsonData.data" type="textarea" :rows="40"/>
  </Modal>

  <Modal v-model="state.showCode.show" title="查看代码" width="80vw" @on-ok="showCodeHandleFn">
    <Input v-model="state.showCode.data" type="textarea" :rows="30"/>
  </Modal>
  <DatabaseLoad v-model="state.databaseLoad" @event="loadDataHandleFn"/>

</template>
<script setup>
import DatabaseLoad from '../DatabaseLoad.vue'
import {reactive, onMounted, watch} from 'vue'
import {Input, Message} from "view-ui-plus";
import {saveComponents, loadTable} from '@/api/api.js'
import {useConfigModule} from "@/store/configModule.js";
import draggable from "vuedraggable";
import {useWinModal} from '@/store/winModal.js'
import ObjSave from './com/ObjSave.vue'
import ObjTable from './com/ObjTable.vue'
import ObjSearch from './com/ObjSearch.vue'

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
  // ===============  搜索
  searchObj: {},
  // ===============  表格
  tableObj: {},
  // ===============  保存
  saveObj: {},
  // =============== 修改
  updateObj: {},
  showCode: {
    show: false,
    data: '',
    modal: ''
  },
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
  // ===========================
  defaultConfig: {
    operation: {
      show: true,
      title: '操作',
      key: 'operation',
      width: 200,
      operationArr: [
        {
          label: "查看",
          even: '',
          show: true,
        },
        {
          label: "修改",
          even: '',
          show: true,
        },
        {
          label: "删除",
          even: '',
          show: true,
        }
      ]
    },
  }

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


const showCodeTableFn = (modal) => {
  state.showCode.show = true;
  state.showCode.modal = modal
  if (state.showCode.modal === 'table') {
    state.showCode.data = JSON.stringify(state.tableObj, null, "  ");
  } else if (state.showCode.modal === 'search') {
    state.showCode.data = JSON.stringify(state.searchObj, null, "  ");
  } else if (state.showCode.modal === 'save') {
    state.showCode.data = JSON.stringify(state.saveObj, null, "  ");
  } else if (state.showCode.modal === 'update') {
    state.showCode.data = JSON.stringify(state.updateObj, null, "  ");
  } else {
    state.showCode.data = "";
  }

}

const showCodeHandleFn = () => {
  if (state.showCode.modal === 'table') {
    state.tableObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'search') {
    state.searchObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'save') {
    state.saveObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'update') {
    state.updateObj = JSON.parse(state.showCode.data);
  }
}

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
    searchObj: state.searchObj,
    tableObj: state.tableObj,
    saveObj: state.saveObj,
    updateObj: state.updateObj,
  }
  state.jsonData.data = JSON.stringify(json, null, "  ");
}

const delOperationFn = (index) => {
  state.operationObj.operationArr.splice(index, 1);
}
const addOperationFn = () => {
  state.operationObj.operationArr.push({
    label: "",
    even: '',
    show: true
  })
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

const arrUp = (list, index) => {
  if (index > 0) {
    let nowObj = list[index];
    let prObj = list[index - 1];
    list[index] = prObj;
    list[index - 1] = nowObj;
  }
}



watch(() => props.setting, () => {
  initFn()
})

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
