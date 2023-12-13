<template>
  <Tabs value="name1">
    <TabPane label="请求对象" name="name1">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload">查看配置</Button>
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
    <TabPane label="表格字段" name="name3">
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
    <TabPane label="保存字段" name="保存字段">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
          </div>
        </div>
        <div class="dataContent">
          <div class="row" v-for="(item,index) in state.columnsArr" :key="index">
            <div class="rowItem saveTitle">{{ item.title }}</div>
            <div class="rowItem">
              <Input v-model="item.length" placeholder="长度" clearable type="number" style="width: 80px"/>
            </div>
            <div class="rowItem">
              输入类型:
              <Select v-model="item.controlType" clearable size="small" style="width:100px">
                <Option value="hidden">隐藏框</Option>
                <Option value="text">文本</Option>
                <Option value="textarea">长文本</Option>
                <Option value="date">日期</Option>
                <Option value="time">时间</Option>
                <Option value="datetime">日期时间</Option>
                <Option value="img">图片</Option>
              </Select>
            </div>
            <div class="rowItem">
              <Checkbox v-model="item.isShowSave">显示字段</Checkbox>
            </div>
          </div>
        </div>
      </div>
    </TabPane>
    <TabPane label="修改字段" name="修改字段">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
          </div>
        </div>
        <div class="dataContent">
          <div class="row" v-for="(item,index) in state.columnsArr" :key="index">
            <div class="rowItem saveTitle">{{ item.title }}</div>
            <div class="rowItem">
              <Input v-model="item.length" placeholder="长度" clearable type="number" style="width: 80px"/>
            </div>
            <div class="rowItem">
              输入类型:
              <Select v-model="item.controlType" clearable size="small" style="width:100px">
                <Option value="hidden">隐藏框</Option>
                <Option value="text">文本</Option>
                <Option value="textarea">长文本</Option>
                <Option value="date">日期</Option>
                <Option value="time">时间</Option>
                <Option value="datetime">日期时间</Option>
                <Option value="img">图片</Option>
              </Select>
            </div>
            <div class="rowItem">
              <Checkbox v-model="item.isShowSave">显示字段</Checkbox>
            </div>
          </div>
        </div>
      </div>
    </TabPane>


    <TabPane label="搜索字段" name="name5">
      <div class="configBox">
        <div class="headerBox">
          <div></div>
          <div>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveComponentsFn">保存</Button>
          </div>
        </div>
        <div class="dataContent">

          <div class="row">
            <div class="labelLeft">显示按钮:</div>
            <Checkbox border v-model="state.operationObj.show"></Checkbox>
            <Button class="dataBtn" type="primary" icon="md-cloud-upload">添加按钮</Button>
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
            <Button class="dataBtn" type="primary" icon="md-list">设置参数</Button>
          </div>


          <div class="row" v-for="(item,index) in state.columnsArr" :key="index">
            <div class="rowItem saveTitle">{{ item.title }}</div>
            <div class="rowItem">
              <Checkbox v-model="item.isShowSearch">显示字段</Checkbox>
            </div>
            <div class="rowItem">
              搜索条件:
              <Select v-model="item.javaWhere" clearable size="small" style="width:160px">
                <Option value="lk">%模糊查询%</Option>
                <Option value="lkR">模糊查询%</Option>
                <Option value="lkL">%模糊查询</Option>
                <Option value="eq">等于</Option>
                <Option value="neq">不等于</Option>
                <Option value="geq">大于等于</Option>
                <Option value="gt">大于</Option>
                <Option value="leq">小于等于</Option>
                <Option value="lt">小于</Option>
              </Select>
            </div>
          </div>
        </div>
      </div>
    </TabPane>

  </Tabs>
</template>
<script setup>

import {reactive, onMounted, watch} from 'vue'
import {Input} from "view-ui-plus";
import {pageConfig} from '@/store/pageConfig.js'
import {pageSetting, saveComponents} from '@/api/api.js'
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
  const {columns, operation, requestObj, id, tid} = setting
  if (requestObj) {
    state.requestObjData = requestObj;
  }
  if (operation) {
    state.operationObj = operation;
  }
  if (columns) {
    state.columnsArr = columns;
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