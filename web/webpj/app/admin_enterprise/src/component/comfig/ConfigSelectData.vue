<template>
  <div class="aaa">
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="loadTableColumns('')">加载数据库</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看配置</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
  </div>
  <Tabs v-model="configModule.configPage.tabsName" @onClick="(name)=>{configModule.configPage.tabsName = name}">
    <TabPane label="选择" name="选择">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeTableFn('select')">
              查看代码
            </Button>
            <Button class="dataBtn" type="primary" icon="md-list" @click="loadDefaultFn('select')">加载默认</Button>
          </div>
        </div>
        <div class="dataContent">
          <div v-if="state.selectObj && state.selectObj.queryRequest">
            查询地址: <Input v-model="state.selectObj.queryRequest.url" placeholder="查询地址" clearable/>
            <div>
              参数:
              <div v-for="(parameter, pa) in state.selectObj.queryRequest.parameter" :key="pa">
                {{ pa }} <Input
                  v-model="state.selectObj.queryRequest.parameter[pa]"
                  placeholder="查询地址" clearable
                  type="textarea"/>
              </div>
            </div>
          </div>
          <div v-if="state.selectObj && state.selectObj.delRequest">
            删除地址: <Input v-model="state.selectObj.delRequest.url" placeholder="删除地址" clearable/>
            <div>
              参数:
              <div v-for="(parameter, pa) in state.selectObj.delRequest.parameter" :key="pa">
                {{ pa }} <Input
                  v-model="state.selectObj.delRequest.parameter[pa]"
                  placeholder="查询地址" clearable
                  type="textarea"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </TabPane>
    <TabPane label="保存" name="保存">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-list" @click="loadDefaultFn('save')">加载默认</Button>
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
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeTableFn('table')">
              查看代码
            </Button>
            <Button class="dataBtn" type="primary" icon="md-list" @click="loadDefaultFn('table')">加载默认</Button>
          </div>
        </div>
        <div class="dataContent">
          <div v-if="state.tableObj && state.tableObj.queryRequest">
            查询地址: <Input v-model="state.tableObj.queryRequest.url" placeholder="查询地址" clearable/>
            <div>
              参数:
              <div v-for="(parameter, pa) in state.tableObj.queryRequest.parameter" :key="pa">
                {{ pa }} <Input
                  v-model="state.tableObj.queryRequest.parameter[pa]"
                  placeholder="查询地址" clearable
                  type="textarea"/>
              </div>
            </div>
          </div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-list" @click="loadTableColumns('tableColumns')">
              加载数据字表格字段
            </Button>
          </div>
          <div class="row" v-for="(item,index) in state.tableObj.columns" :key="index">
            <div class="rowItem sortBtn">
              <Button size="small" type="error" icon="ios-trash-outline"
                      @click="state.tableObj.columns.splice(index, 1)"/>
              <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up" @click="upIndexDataFn(index)"/>
            </div>
            <div class="rowItem">
              <Checkbox v-model="item.isShowColumn"/>
            </div>
            <div class="rowItem">
              <Input v-model="item.title" placeholder="标题" clearable style="width: 130px"/>
            </div>
            <div class="rowItem">
              <Input v-model="item.key" placeholder="key" clearable style="width: 100px"/>
            </div>
            <div class="rowItem">
              <Input v-model="item.width" placeholder="宽" clearable type="number" style="width: 80px"/>
            </div>
            <div class="rowItem">
              <Select v-model="item.type" size="small" clearable style="width:110px">
                <Option value="text">文本</Option>
                <Option value="selection">选择框</Option>
                <Option value="img">图片</Option>
                <Option value="filters">过滤</Option>
              </Select>
            </div>
            <div class="rowItem">
              <Checkbox v-model="item.tooltip">越长不换行</Checkbox>
            </div>
            <div class="rowItem">
              <Checkbox v-model="item.sortable">排序</Checkbox>
            </div>
          </div>
        </div>
      </div>
    </TabPane>
  </Tabs>

  <Modal
      v-model="state.jsonData.show"
      :loading="state.jsonData.loading"
      title="数据"
      width="80vw"
      @on-ok="saveComponentsFn"
  >
    <Input v-model="state.jsonData.data" type="textarea" :rows="40"/>
  </Modal>

  <Modal v-model="state.showCode.show"
         title="查看代码" width="80vw"
         @on-ok="showCodeHandleFn"
  >
    <Input v-model="state.showCode.data" type="textarea" :rows="30"/>
  </Modal>

  <DatabaseLoad v-model="state.databaseLoad" @event="loadDataHandleFn"/>

</template>
<script setup>
import DatabaseLoad from './DatabaseLoad.vue'

import {reactive, onMounted, watch} from 'vue'
import {Input} from "view-ui-plus";
import {saveComponents, loadTable} from '@/api/api.js'
import {useConfigModule} from "@/store/configModule.js";


const configModule = useConfigModule();


const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  selectObj: {},
  // ===============  表格
  tableObj: {},
  // ===============  保存
  saveObj: {},
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
  loadKey: "",
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
    selectObj: {
      queryRequest: {
        url: '/admin/${eCode}/common/query',
        parameter: {
          code: ''
        }
      },
      delRequest: {
        url: '/admin/${eCode}/common/query',
        parameter: {
          code: ''
        }
      },
    },
    saveObj: {
      saveRequest: {
        url: '/admin/${eCode}/common/query',
        parameter: {
          code: ''
        }
      },
    },
    tableObj: {
      queryRequest: {
        url: '',
        parameter: {
          code: ''
        }
      },
      columns: []
    }
  }

})


onMounted(() => {
  initFn();
})

const loadDataHandleFn = (data) => {
  if (state.loadKey === "tableColumns") {
    state.tableObj.columns = data;
  }

}
const showCodeTableFn = (modal) => {
  state.showCode.show = true;
  state.showCode.modal = modal
  if (state.showCode.modal === 'table') {
    state.showCode.data = JSON.stringify(state.tableObj, null, "  ");
  } else if (state.showCode.modal === 'search') {
    state.showCode.data = JSON.stringify(state.selectObj, null, "  ");
  } else if (state.showCode.modal === 'save') {
    state.showCode.data = JSON.stringify(state.saveObj, null, "  ");
  } else {
    state.showCode.data = "";
  }

}

const showCodeHandleFn = () => {
  if (state.showCode.modal === 'table') {
    state.tableObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'search') {
    state.selectObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'save') {
    state.saveObj = JSON.parse(state.showCode.data);
  }
}

const upIndexDataFn = (index) => {
  if (index > 0) {
    const objA = state.tableObj.columns[index - 1]
    const objB = state.tableObj.columns[index]
    state.tableObj.columns[index - 1] = objB
    state.tableObj.columns[index] = objA
  }
}

const upIndexSaveFn = (index) => {
  if (index > 0) {
    const objA = state.saveObj.columns[index - 1]
    const objB = state.saveObj.columns[index]
    state.saveObj.columns[index - 1] = objB
    state.saveObj.columns[index] = objA
  }
}

const loadDefaultFn = (str) => {
  if (str === 'operation') {
    state.operationObj = state.defaultConfig.operation;
  } else if (str === 'select') {
    state.selectObj = state.defaultConfig.selectObj;
  } else if (str === 'save') {
    state.saveObj = state.defaultConfig.saveObj;
  } else if (str === 'table') {
    state.tableObj = state.defaultConfig.tableObj;
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
  }, true).finally(() => {
    state.jsonData.loading = false;
  })
}

const compileCode = () => {
  state.tableObj.operation = state.operationObj;
  const json = {
    selectObj: state.selectObj,
    tableObj: state.tableObj,
    saveObj: state.saveObj,
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
  const {saveObj, tableObj, selectObj, id, tid} = setting
  if (selectObj) {
    state.selectObj = selectObj;
  }
  if (tableObj) {
    state.tableObj = tableObj;
    state.operationObj = tableObj.operation;
  }
  if (saveObj) {
    state.saveObj = saveObj;
  }
  if (id) {
    state.jsonData.id = id;
  }
  if (tid) {
    state.jsonData.tid = tid;
  }
}


const loadTableColumns = (name) => {
  state.loadKey = name;
  state.databaseLoad = true;
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
        width: 60px;
      }

      .saveTitle {
        width: 120px;
        text-align: right;
      }
    }
  }
}

</style>