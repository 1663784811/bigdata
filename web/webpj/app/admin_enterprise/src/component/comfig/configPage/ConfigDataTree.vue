<template>
  <div class="aaa">
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="state.databaseLoad = true">加载数据库</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看配置</Button>
    <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
  </div>
  <Tabs v-model="configModule.configPage.tabsName" @onClick="(name)=>{configModule.configPage.tabsName = name}">
    <TabPane label="查询" name="查询">
      <div class="configBox">
        <div class="dataContent">
          <div v-if="state.saveObj">
            查询地址: <Input v-model="state.queryRequest.url" placeholder="查询地址" clearable/>
          </div>
        </div>
      </div>
    </TabPane>
    <!--      -->
    <TabPane label="保存" name="保存">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="state.saveObj.columns.push({})">添加
            </Button>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload">添加从表格选择</Button>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeTableFn('save')">
              查看代码
            </Button>
          </div>
        </div>
        <div class="dataContent">
          <div v-if="state.saveObj">
            保存地址: <Input v-model="state.saveObj.url" placeholder="保存地址" clearable/>
          </div>
          <draggable
              ghost-class="ghost"
              chosen-class="chosenClass"
              animation="300"
              :list="state.saveObj.columns"
              item-key="id"
          >
            <template #item="{ element, index }">
              <div class="row">
                <div class="rowItem sortBtn">
                  <Button size="small" type="error" icon="ios-trash-outline"
                          @click="state.saveObj.columns.splice(index, 1)"/>
                </div>
                <div class="rowItem">
                  <Checkbox v-model="element.isShowSave"/>
                </div>
                <div class="rowItem">
                  <Input v-model="element.title" placeholder="标题" clearable style="width: 130px"/>
                </div>
                <div class="rowItem">
                  <Input v-model="element.key" placeholder="key" clearable style="width: 100px"/>
                </div>
                <div class="rowItem">
                  输入类型:
                  <Select v-model="element.controlType" clearable size="small" style="width:100px">
                    <Option value="hidden">隐藏框</Option>
                    <Option value="text">文本</Option>
                    <Option value="textarea">长文本</Option>
                    <Option value="date">日期</Option>
                    <Option value="time">时间</Option>
                    <Option value="datetime">日期时间</Option>
                    <Option value="img">图片</Option>
                    <Option value="select">单选</Option>
                    <Option value="number">数字</Option>
                  </Select>
                </div>
              </div>
            </template>
          </draggable>
        </div>
      </div>
    </TabPane>
    <TabPane label="删除" name="删除">
      <div class="configBox">
        <div class="dataContent">
          <div v-if="state.delRequest">
            删除地址: <Input v-model="state.delRequest.url" placeholder="查询地址" clearable/>
          </div>
        </div>
      </div>
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
import {saveComponents, loadTable} from '@/api/api.js'
import {useConfigModule} from "@/store/configModule.js";
import {Message} from "view-ui-plus";

const configModule = useConfigModule();


const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  // ===============  搜索
  queryRequest: {
    url: '',
    parameter: {
      code: ""
    }
  },
  delRequest: {
    url: "",
    parameter: {
      code: ""
    }
  },
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
})


onMounted(() => {
  initFn();
})

const loadDataHandleFn = (data) => {
  state.saveObj = data.saveObj;
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
    Message.success({
      background: true,
      content: '操作成功'
    });
  })
}

const compileCode = () => {
  const json = {
    queryRequest: state.queryRequest,
    delRequest: state.delRequest,
    saveObj: state.saveObj,
  }
  state.jsonData.data = JSON.stringify(json, null, "  ");
}


const initFn = () => {
  const {setting} = props;
  const {queryRequest, delRequest, saveObj} = setting
  if (queryRequest) {
    state.queryRequest = queryRequest;
  }
  if (delRequest) {
    state.delRequest = delRequest;
  }
  if (saveObj) {
    state.saveObj = saveObj;
  }
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
