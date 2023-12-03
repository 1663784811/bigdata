<template>
  <Drawer
      placement="left"
      title="配置面板"
      v-model="usePageConfig.componentConfig.show"
      width="720"
      :mask-closable="false"
  >
    <Tabs value="name1">
      <TabPane label="请求对象" name="name1">
        <div class="configBox">
          <div class="headerBox">
            <div></div>
            <div>
              <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
            </div>
          </div>
          <div class="dataContent">
            <div class="row">
              <div class="labelLeft">查询URl:</div>
              <Checkbox border v-model="state.requestObjData.queryRequest.show"></Checkbox>
              <div class="rightInput">
                <Input v-model="state.requestObjData.queryRequest.url" placeholder="查询URl"/>
              </div>
              <Button class="dataBtn" type="primary" icon="md-list">设置参数</Button>
            </div>
            <div class="row">
              <div class="labelLeft">保存URl:</div>
              <Checkbox border v-model="state.requestObjData.saveRequest.show"></Checkbox>
              <div class="rightInput">
                <Input v-model="state.requestObjData.saveRequest.url" placeholder="保存URl"/>
              </div>
              <Button class="dataBtn" type="primary" icon="md-list">设置参数</Button>
            </div>
            <div class="row">
              <div class="labelLeft">删除URl:</div>
              <Checkbox border v-model="state.requestObjData.delRequest.show"></Checkbox>
              <div class="rightInput">
                <Input v-model="state.requestObjData.delRequest.url" placeholder="删除URl"/>
              </div>
              <Button class="dataBtn" type="primary" icon="md-list">设置参数</Button>
            </div>
          </div>
        </div>
      </TabPane>
      <TabPane label="操作对象" name="name2">
        <div class="configBox">
          <div class="headerBox">
            <div></div>
            <div>
              <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
            </div>
          </div>
          <div class="dataContent">

            <div class="row">
              <div class="labelLeft">操作标题:</div>
              <Checkbox border></Checkbox>
              <div class="rightInput">
                <Input/>
              </div>
              <div>事件:</div>
              <div class="rightInput">
                <Input/>
              </div>
            </div>

            <div class="row" v-for="(item, index) in state.operationObj.operationArr" :key="index">
              <div class="labelLeft">名称:</div>
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
      <TabPane label="字段排序" name="name3">
        <div class="configBox">
          <div class="headerBox">
            <div></div>
            <div>
              <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
            </div>
          </div>
          <div class="dataContent">
            <div class="row"></div>
          </div>
        </div>
      </TabPane>
    </Tabs>
  </Drawer>

  <div class="configOperation">
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
import {reactive, onMounted, watch} from 'vue'
import {Input} from "view-ui-plus";
import {pageConfig} from '@/store/pageConfig.js'
import {pageSetting, saveComponents} from '@/api/api.js'

const usePageConfig = pageConfig();

const state = reactive({
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
  requestObjData: {
    queryRequest: {
      url: '',
      show: true
    },
    saveRequest: {
      url: '',
      show: true
    },
    delRequest: {
      url: '',
      show: true
    }
  },
  columnsArr: [],
  jsonData: {
    loading: false,
    id: '',
    tid: '',
    data: ''
  }
})


const loadData = (pageCode) => {
  pageSetting({
    pageCode
  }).then((rest) => {
    console.log('ssssssssssssssssssss', rest)
    usePageConfig.componentConfig.show = true;
    const {columns, operation, requestObj, id, tid} = rest.data.commonTable
    state.requestObjData = requestObj;
    state.operationObj = operation;
    state.columnsArr = columns;
    state.jsonData.id = id;
    state.jsonData.tid = tid;
  })
}


/**
 * 保存组件数据
 */
const saveComponentsFn = () => {
  state.jsonData.loading = true;
  const json = {
    requestObj: state.requestObjData,
    operation: state.operationObj,
    columns: state.columnsArr
  }
  state.jsonData.data = JSON.stringify(json, null, "  ");
  saveComponents({
    id: state.jsonData.id,
    data: state.jsonData.data
  }, true).finally(() => {
    state.jsonData.loading = false;
  })

}

</script>
<style scoped lang="less">
.configBox {
  .headerBox {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
  }

  .dataContent {
    .row {
      display: flex;
      align-items: center;
      margin: 10px 0;

      .labelLeft {
        width: 120px;
        text-align: right;
        margin-right: 10px;
      }

      .rightInput {
        flex: 1;
        margin-right: 10px;
      }
    }
  }
}

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