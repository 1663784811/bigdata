<template>
  <Tabs value="name1">
    <TabPane label="搜索" name="搜索">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="loadDataBaseFn">加载数据库</Button>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload">查看配置</Button>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
          </div>
        </div>
        <div class="dataContent">
          <div class="row">
            <div class="labelLeft">显示搜索模块:</div>
            <Checkbox border v-model="state.searchObj.show"></Checkbox>
            <Button class="dataBtn" type="primary" icon="md-list">添加</Button>
          </div>
          <template v-for="(item, index) in state.searchObj.columns" :key="index">
            <div class="row">
              <div class="sortBtn">
                <Button size="small" type="error" icon="ios-trash-outline"/>
                <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up"/>
              </div>
              <div>名称:</div>
              <Checkbox border v-model="item.show"></Checkbox>
              <div class="rightInput">
                <Input v-model="item.name" placeholder="名称"/>
              </div>
              <div class="rightInput">
                <Input v-model="item.icon" placeholder="icon"/>
              </div>
              <div class="rightInput">
                <Input v-model="item.type" placeholder="icon"/>
              </div>
              <div>事件:</div>
              <div class="rightInput">
                <Input v-model="item.even" placeholder="事件" clearable/>
              </div>
            </div>


            <!-- ================== -->
            <template v-if="item.even === 'search' && item.parameter.length>0">
              <div class="row" style="margin-left: 50px;" v-for="(item,index) in item.parameter" :key="index">
                <div class="rowItem sortBtn">
                  <Button size="small" type="error" icon="ios-trash-outline" @click="delIndexDataFn(index)"/>
                  <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up" @click="upIndexDataFn(index)"/>

                </div>
                <div class="rowItem">
                  <Checkbox v-model="item.isShowColumn" />
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

                </div>
                <div class="rowItem">
                  <Checkbox v-model="item.tooltip">越长不换行</Checkbox>
                </div>
                <div class="rowItem">
                  <Checkbox v-model="item.sortable">排序</Checkbox>
                </div>
              </div>
            </template>
            <!-- ================== -->

          </template>


        </div>
      </div>
    </TabPane>
    <TabPane label="表格" name="表格">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
          </div>
        </div>
        <div class="dataContent">
          <div>
            操作对象
          </div>
          <div>
            显示
            查询地址:
            删除地址:
          </div>
          <div class="row" v-for="(item,index) in state.tableObj.columns" :key="index">
            <div class="rowItem sortBtn">
              <Button size="small" type="error" icon="ios-trash-outline" @click="delIndexDataFn(index)"/>
              <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up" @click="upIndexDataFn(index)"/>
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
              <Checkbox v-model="item.isShowColumn">显示字段</Checkbox>
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
    <TabPane label="添加" name="添加">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
          </div>
        </div>
        <div class="dataContent">
          <div class="row" v-for="(item,index) in state.columnsArr" :key="index">
            <div class="rowItem sortBtn">
              <Button size="small" type="error" icon="ios-trash-outline" @click="delIndexDataFn(index)"/>
              <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up" @click="upIndexDataFn(index)"/>
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
              <Checkbox v-model="item.isShowColumn">显示字段</Checkbox>
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
    <TabPane label="更新" name="更新">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
          </div>
        </div>
        <div class="dataContent">
          <div class="row" v-for="(item,index) in state.columnsArr" :key="index">
            <div class="rowItem sortBtn">
              <Button size="small" type="error" icon="ios-trash-outline" @click="delIndexDataFn(index)"/>
              <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up" @click="upIndexDataFn(index)"/>
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
              <Checkbox v-model="item.isShowColumn">显示字段</Checkbox>
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
      v-model="state.databaseLoad.show"
      title="加载数库"
      width="80vw"
      @on-ok="databaseLoadOkFn"
  >
    <div>
      <Table :columns="state.databaseLoad.columns"
             :data="state.databaseLoad.data"
             :loading="state.databaseLoad.loading"
             :height="300"
             @on-row-click="selectTable"
             highlight-row
      ></Table>
    </div>
    <Input v-model="state.databaseLoad.jsData" type="textarea" :rows="30"/>
  </Modal>


</template>
<script setup>

import {reactive, onMounted, watch} from 'vue'
import {Input} from "view-ui-plus";
import {pageConfig} from '@/store/pageConfig.js'
import {pageSetting, saveComponents, loadTable} from '@/api/api.js'
import {useRoute, useRouter} from "vue-router";

const router = useRouter();
const route = useRoute();

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

  operationObj: {
    title: '操作',
    key: 'operation',
    width: 200,
    operationArr: [
      {
        label: "查看",
        even: ''
      },
      {
        label: "修改",
        even: ''
      },
      {
        label: "删除",
        even: ''
      }
    ]
  },
  columnsArr: [],
  jsonData: {
    loading: false,
    id: '',
    tid: '',
    data: ''
  },
  databaseLoad: {
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
    newTable: {},
    jsData: ''
  }
})


onMounted(() => {
  initFn();
})

const upIndexDataFn = (index) => {
  if (index > 0) {
    const objA = state.columnsArr[index - 1]
    const objB = state.columnsArr[index]
    state.columnsArr[index - 1] = objB
    state.columnsArr[index] = objA
  }
}

const delIndexDataFn = (index) => {
  state.columnsArr.splice(index, 1)
}

/**
 * 保存组件数据
 */
const saveComponentsFn = () => {
  state.jsonData.loading = true;
  const json = {
    searchObj: state.searchObj,
    tableObj: state.tableObj,
    saveObj: state.saveObj,
  }
  state.jsonData.data = JSON.stringify(json, null, "  ");
  saveComponents({
    id: state.jsonData.id,
    data: state.jsonData.data
  }, true).finally(() => {
    state.jsonData.loading = false;
  })

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


const loadDataBaseFn = () => {
  state.databaseLoad.show = true;
  state.databaseLoad.loading = true;
  loadTable({}).then((res) => {
    const {data} = res;
    state.databaseLoad.data = data;
  }).finally(() => {
    state.databaseLoad.loading = false
  })
}


const databaseLoadOkFn = () => {
  const {searchObj, tableObj, saveObj} = state.databaseLoad.newTable
  state.searchObj = searchObj;
  state.tableObj = tableObj;
  state.saveObj = saveObj;
}

const selectTable = (item) => {
  state.databaseLoad.newTable = item.pageConfig.newTable;
  state.databaseLoad.jsData = JSON.stringify(state.databaseLoad.newTable, null, "  ");
}

const initFn = () => {
  const {setting} = props;
  const {saveObj, tableObj, searchObj, id, tid} = setting
  if (searchObj) {
    state.searchObj = searchObj;
  }
  if (tableObj) {
    state.tableObj = tableObj;
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