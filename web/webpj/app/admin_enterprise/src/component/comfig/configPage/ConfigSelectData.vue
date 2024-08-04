<template>
  <div class="aaa">
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="loadTableColumns('')">加载数据库</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeTableFn('all')">查看代码</Button>
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
            <ObjRequest :setting="state.selectObj.queryRequest" title="查询地址" />
          </div>

          <div v-if="state.selectObj && state.selectObj.delRequest">
            <ObjRequest :setting="state.selectObj.delRequest" title="删除地址" />
          </div>


        </div>
      </div>
    </TabPane>
    <TabPane label="保存" name="保存">
      <div class="configBox">


        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeTableFn('save')">
              查看代码
            </Button>
            <Button class="dataBtn" type="primary" icon="md-list" @click="loadDefaultFn('save')">加载默认</Button>
          </div>
        </div>


        <div class="dataContent">
          <div v-if="state.saveObj && state.saveObj.saveRequest">
            <ObjRequest :setting="state.saveObj.saveRequest" title="保存地址" />
          </div>
          <div>
            <div>映射</div>
            <div>
              <div>sssss</div>
              <div>sssss</div>
              <div>sssss</div>
              <div>sssss{{state.saveObj}}</div>
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
            <ObjRequest :setting="state.tableObj.queryRequest" title="查询地址" />
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
import DatabaseLoad from '../DatabaseLoad.vue'
import ObjRequest from './com/ObjRequest.vue'
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
  } else if (state.showCode.modal === 'select') {
    state.showCode.data = JSON.stringify(state.selectObj, null, "  ");
  } else if (state.showCode.modal === 'save') {
    state.showCode.data = JSON.stringify(state.saveObj, null, "  ");
  } else if (state.showCode.modal === 'all') {
    state.tableObj.operation = state.operationObj;
    const json = {
      selectObj: state.selectObj,
      tableObj: state.tableObj,
      saveObj: state.saveObj,
    }
    state.showCode.data = JSON.stringify(json, null, "  ");
  } else {
    state.showCode.data = "";
  }

}

const showCodeHandleFn = () => {
  if (state.showCode.modal === 'table') {
    state.tableObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'select') {
    state.selectObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'save') {
    state.saveObj = JSON.parse(state.showCode.data);
  } else if (state.showCode.modal === 'all') {
    const {selectObj, tableObj, saveObj} = JSON.parse(state.showCode.data);
    state.selectObj = selectObj;
    state.tableObj = tableObj;
    state.saveObj = saveObj;
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
 * 保存组件数据
 */
const saveComponentsFn = () => {
  compileCode();
  saveComponents({
    id: state.jsonData.id,
    data: state.jsonData.data
  }, true).finally(() => {
    state.jsonData.loading = false;
    initFn();
  })
}

const compileCode = () => {
  state.tableObj.operation = state.operationObj;
  const json = {
    selectObj: state.selectObj,
    saveObj: state.saveObj,
    tableObj: state.tableObj,
  }
  state.jsonData.data = JSON.stringify(json, null, "  ");
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