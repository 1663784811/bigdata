<template>
  <div>
    <CommonTable
        :table-setting="commonTable"
        @event="eventFn"
    />

    <div class="tableConfig">
      <div class="tableColumnsBtn">
        <Button class="dataBtn" type="primary" icon="md-archive" @click="databaseLoadFn">数据库加载</Button>
        <Button class="dataBtn" type="primary" icon="md-archive" @click="saveJsonData">历史</Button>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveJsonData">保存</Button>
        <Button class="dataBtn" type="primary" icon="md-list" @click="showJsonData">查看代码</Button>
      </div>
      <div class="cardBox">
        <Card :bordered="true">
          <template #title>
            <Icon type="ios-film-outline"></Icon>
            请求对象
            <Button class="showCodeBtn" type="primary" icon="md-list" @click="showCodeBtnFn(requestObjData)"/>
          </template>
          <div class="dataBox">
            <div class="dataRow">
              <div class="rowLabel">查询URl:</div>
              <Checkbox border v-model="requestObjData.queryRequest.show"></Checkbox>
              <Input v-model="requestObjData.queryRequest.url" placeholder="查询URl"/>
              <Button class="dataBtn" type="primary" icon="md-list"
                      @click="urlSettingParameter(requestObjData.queryRequest, 'query')">设置参数
              </Button>
            </div>
            <div class="dataRow">
              <div class="rowLabel">保存URl:</div>
              <Checkbox border v-model="requestObjData.saveRequest.show"></Checkbox>
              <Input v-model="requestObjData.saveRequest.url" placeholder="large size"/>
              <Button class="dataBtn" type="primary" icon="md-list"
                      @click="urlSettingParameter(requestObjData.saveRequest, 'save')">设置参数
              </Button>
            </div>
            <div class="dataRow">
              <div class="rowLabel">删除URl:</div>
              <Checkbox border v-model="requestObjData.delRequest.show"></Checkbox>
              <Input v-model="requestObjData.delRequest.url" placeholder="large size"/>
              <Button class="dataBtn" type="primary" icon="md-list"
                      @click="urlSettingParameter(requestObjData.delRequest, 'del')">设置参数
              </Button>
            </div>
          </div>
        </Card>
      </div>
      <div class="cardBox">
        <Card :bordered="true">
          <template #title>
            <Icon type="ios-film-outline"></Icon>
            操作对象
            <Button class="dataBtn" type="primary" icon="ios-add-circle-outline">添加</Button>
            <Button class="showCodeBtn" type="primary" icon="md-list" @click="showCodeBtnFn(operationObj)"/>
          </template>
          <div class="operationObjBox">
            <div>
              操作标题:
              <Checkbox border v-model="operationObj.show"></Checkbox>
              <Input v-model="operationObj.title" placeholder="标题" clearable style="width: 100px"/>
            </div>
            <div>
              宽:
              <Input v-model="operationObj.width" placeholder="宽" clearable type="number" style="width: 80px"/>
            </div>
          </div>
          <div class="operationBox">
            <div class="operationRow" v-for="(item, index) in operationObj.operationArr" :key="index">
              <div>
                名称:
                <Checkbox border v-model="item.show"></Checkbox>
                <Input v-model="item.label" placeholder="名称" clearable style="width: 100px"/>
              </div>
              <div>
                事件:
                <Input v-model="item.even" placeholder="事件" clearable style="width: 100px"/>
              </div>
            </div>
          </div>
        </Card>
      </div>

      <div class="cardBox">
        <Card :bordered="true">
          <template #title>
            <Icon type="ios-film-outline"></Icon>
            字段列表
            <Button class="showCodeBtn" type="primary" icon="md-list" @click="showCodeBtnFn(columnsArr)"/>
          </template>
          <div class="columnBox" v-for="(item, index) in columnsArr" :key="index">
            <div class="upBox">
              <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up" @click="upIndexDataFn(index)"/>
              <Button size="small" type="error" icon="ios-trash-outline" @click="delIndexDataFn(index)"/>
            </div>

            <Divider orientation="left">表格</Divider>
            <div class="inputGroup">
              <div>
                标题:
                <Input v-model="item.title" placeholder="标题" clearable style="width: 100px"/>
              </div>
              <div>
                key:
                <Input v-model="item.key" placeholder="key" clearable style="width: 100px"/>
              </div>
              <div>
                宽:
                <Input v-model="item.width" placeholder="宽" clearable type="number" style="width: 80px"/>
              </div>
              <div>
                表格类型:
                <Select v-model="item.type" size="small" clearable style="width:110px">
                  <Option value="text">文本</Option>
                  <Option value="selection">选择框</Option>
                  <Option value="img">图片</Option>
                  <Option value="filters">过滤</Option>
                </Select>
              </div>
              <div>
                <Checkbox v-model="item.isShowColumn">显示字段</Checkbox>
              </div>
              <div>
                <Checkbox v-model="item.tooltip">越长不换行</Checkbox>
              </div>
              <div>
                <Checkbox v-model="item.sortable">排序</Checkbox>
              </div>
            </div>

            <Divider orientation="left">添加</Divider>
            <div class="inputGroup">
              <div>
                长度:
                <Input v-model="item.length" placeholder="长度" clearable type="number" style="width: 80px"/>
              </div>
              <div>
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
              <div>
                <Checkbox v-model="item.isShowSave">显示字段</Checkbox>
              </div>
            </div>

            <Divider orientation="left">搜索</Divider>
            <div class="inputGroup">
              <div>
                搜索条件:
                <Select v-model="item.javaWhere" clearable size="small" style="width:100px">
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
              <div>
                <Checkbox v-model="item.isShowSearch">显示字段</Checkbox>
              </div>
            </div>
          </div>

          <div class="columnBottomBox">
            <Button class="dataBtn" type="primary" icon="ios-add-circle-outline" @click="addJsonData">添加</Button>
          </div>

        </Card>
      </div>

    </div>

    <!-- =============    弹出层   =============   -->
    <Modal
        v-model="jsonData.show"
        :loading="jsonData.loading"
        title="数据"
        width="80vw"
        @on-ok="saveComponentsFn"
    >
      <Input v-model="jsonData.data" type="textarea" :rows="40"/>
    </Modal>
    <Modal v-model="showCode.show" title="显示数据" width="80vw">
      <Input v-model="showCode.data" type="textarea" :rows="30"/>
    </Modal>
    <Modal
        v-model="databaseLoad.show"
        title="加载数库"
        width="80vw"
        @on-ok="databaseLoadOkFn"
    >
      <div>
        <Table :columns="databaseLoad.columns"
               :data="databaseLoad.data"
               :loading="databaseLoad.loading"
               :height="300"
               @on-row-click="selectTable"
               highlight-row
        ></Table>
      </div>
      <Input v-model="databaseLoad.jsData" type="textarea" :rows="30"/>
    </Modal>
    <Modal
        v-model="settingParameter.show"
        :loading="settingParameter.loading"
        title="设置参数"
        width="80vw"
        @on-ok="settingParameterOk"
    >
      <Input v-model="settingParameter.data" type="textarea" :rows="40"/>
    </Modal>
    <!-- =============    弹出层   =============   -->
  </div>
</template>

<script setup>

import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'
import {ref} from "vue";
import {findSetting, saveComponents, loadTable} from "@/api/api";


const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("PageComponents");
  commonTable.value = role.commonTable;
}
initFn();

const settingParameter = ref({
  show: false,
  data: '',
  model: ''
})

const selectData = ref({});
const requestObjData = ref({
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
});

const columnsArr = ref([]);

const operationObj = ref({
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
    }
);


/**
 * 事件
 */
const eventFn = (eventData) => {
  selectData.value = eventData;
  findSetting({pageId: eventData.pageId}).then((res) => {
    const {columns, operation, requestObj} = res.data.commonTable
    operationObj.value = operation;
    requestObjData.value = requestObj;
    columnsArr.value = columns;
  })
}

const jsonData = ref({
  show: false,
  data: '',
  loading: false
})
const showCode = ref({
  show: false,
  data: ''
})

const databaseLoad = ref({
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
  commonTable: {},
  jsData: ''
})


const showJsonData = () => {
  jsonData.value.show = !jsonData.value.show

  const json = {
    requestObj: requestObjData.value,
    operation: operationObj.value,
    columns: columnsArr.value
  }
  jsonData.value.data = JSON.stringify(json, null, "  ");
}

const upIndexDataFn = (index) => {
  if (index > 0) {
    const objA = columnsArr.value[index - 1]
    const objB = columnsArr.value[index]
    columnsArr.value[index - 1] = objB
    columnsArr.value[index] = objA
  }
}

const delIndexDataFn = (index) => {
  columnsArr.value.splice(index, 1)
}

const saveJsonData = () => {
  console.log("saveJsonData")
}

const showCodeBtnFn = (showData) => {
  showCode.value.data = JSON.stringify(showData, null, "  ");
  showCode.value.show = !showCode.value.show;
}

/**
 * 保存组件数据
 */
const saveComponentsFn = () => {
  jsonData.value.loading = true;
  saveComponents({
    id: selectData.value.id,
    data: jsonData.value.data
  }, true).finally(() => {
    jsonData.value.loading = false;
  })

}

/**
 * 添加字段数据
 */
const addJsonData = () => {
  const js = {
    key: "",
    title: "标题",
    controlType: "text",
    isShowColumn: true,
    isWhere: true,
    javaWhere: "like",
    javaType: "string",
  };
  columnsArr.value.push(js);
}

const databaseLoadFn = () => {
  databaseLoad.value.show = !databaseLoad.value.show;
  databaseLoad.value.loading = true;
  loadTable({}).then((res) => {
    const {data} = res;
    databaseLoad.value.data = data;
  }).finally(() => {
    databaseLoad.value.loading = false
  })
}

/**
 * 选择数据
 */
const selectTable = (item) => {
  databaseLoad.value.commonTable = item.pageConfig.commonTable;
  databaseLoad.value.jsData = JSON.stringify(databaseLoad.value.commonTable, null, "  ");
}

const databaseLoadOkFn = () => {
  const {columns, operation, requestObj} = databaseLoad.value.commonTable
  operationObj.value = operation;
  requestObjData.value = requestObj;
  columnsArr.value = columns;
}

const urlSettingParameter = (data, model) => {
  settingParameter.value.show = true
  settingParameter.value.model = model
  if (data.parameter) {
    settingParameter.value.data = JSON.stringify(data.parameter, null, "  ");
  } else {
    settingParameter.value.data = ""
  }
}
const settingParameterOk = () => {
  let setData = {};
  if (settingParameter.value.data && settingParameter.value.data.length > 3) {
    setData = JSON.parse(settingParameter.value.data)
  }
  if (settingParameter.value.model === 'query') {
    requestObjData.value.queryRequest.parameter = setData
  } else if (settingParameter.value.model === 'save') {
    requestObjData.value.saveRequest.parameter = setData
  } else if (settingParameter.value.model === 'del') {
    requestObjData.value.delRequest.parameter = setData
  }
}

</script>

<style scoped lang="less">
.tableConfig {
  position: relative;
  padding: 10px 20px 50px 20px;
  background: #d6dfe7;

  .tableColumnsBtn {
    position: absolute;
    top: -30px;
    right: 20px;

    .dataBtn {
      margin: 0 6px;
    }
  }

  .cardBox {
    margin-bottom: 20px;

    .operationObjBox {
      display: flex;
    }

    .operationBox {
      margin: 10px 0;

      .operationRow {
        margin: 10px 0;
        padding: 10px;
        display: flex;
        border-bottom: 1px solid #ededed;
      }
    }

    .showCodeBtn {
      position: absolute;
      top: 4px;
      right: 10px;
    }

    .dataBox {
      .dataRow {
        display: flex;
        margin: 10px 0;

        .rowLabel {
          width: 120px;
          text-align: right;
          margin-right: 10px;
        }
      }
    }

    .columnBox {
      background: #f7f7f7;
      margin: 10px 0;
      padding: 20px;
      position: relative;

      .upBox {
        position: absolute;
        top: 0;
        left: -10px;
      }

      .inputGroup {
        display: flex;
        margin: 10px 0;
        align-items: center;
      }
    }
  }

}


</style>
