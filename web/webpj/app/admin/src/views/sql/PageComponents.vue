<template>
  <div>
    <CommonTable
        :table-setting="commonTable"
        @event="eventFn"
    />

    <div class="tableConfig">
      <div class="tableColumnsBtn">
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="saveJsonData"/>
        <Button class="dataBtn" type="primary" icon="md-list" @click="showJsonData"/>
        <Modal
            v-model="jsonData.show"
            :loading="jsonData.loading"
            title="数据"
            width="80vw"
            @on-ok="saveComponentsFn"
        >
          <Input v-model="jsonData.data" type="textarea" :rows="40"/>
        </Modal>
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
              <Input v-model="requestObjData.queryRequest.url" placeholder="查询URl"/>
            </div>
            <div class="dataRow">
              <div class="rowLabel">保存URl:</div>
              <Input v-model="requestObjData.saveRequest.url" placeholder="large size"/>
            </div>
            <div class="dataRow">
              <div class="rowLabel">删除URl:</div>
              <Input v-model="requestObjData.delRequest.url" placeholder="large size"/>
            </div>
          </div>
        </Card>
      </div>

      <div class="cardBox">
        <Card :bordered="true">
          <template #title>
            <Icon type="ios-film-outline"></Icon>
            操作对象
            <Button class="showCodeBtn" type="primary" icon="md-list" @click="showCodeBtnFn(operationObj)"/>
          </template>
          <Checkbox
              border
              v-for="(item, index) in operationObj"
              :key="index"
              v-model="item.val"
          >
            {{ item.label }}
          </Checkbox>
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
            <div class="upBox" v-if="index>0">
              <Button size="small" type="primary" icon="md-arrow-up" @click="upIndexDataFn(index)"/>
            </div>
            <div>
              <Input v-model="item.title" placeholder="标题" clearable style="width: 100px"/>
            </div>
            <div>
              <Input v-model="item.key" placeholder="key" clearable style="width: 100px"/>
            </div>
            <div>
              <Input v-model="item.length" placeholder="长度" clearable type="number" style="width: 100px"/>
            </div>
            <div>
              <Input v-model="item.controlType" placeholder="组件类型" clearable style="width: 100px"/>
            </div>
            <div>
              <Input v-model="item.type" placeholder="表格类型" clearable style="width: 100px"/>
            </div>
            <div>
              <Input v-model="item.controlType" placeholder="输入类型" clearable style="width: 100px"/>
            </div>
            <div>
              <Checkbox v-model="item.isShowColumn">显示字段</Checkbox>
            </div>
            <div>
              <Checkbox v-model="item.tooltip">越长不换行</Checkbox>
            </div>
          </div>
        </Card>
      </div>

    </div>

    <Modal v-model="showCode.show" title="显示数据" width="80vw">
      <Input v-model="showCode.data" type="textarea" :rows="30"/>
    </Modal>
  </div>
</template>

<script setup>

import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'
import {ref} from "vue";
import {findSetting, saveComponents} from "@/api/api";


const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("PageComponents");
  commonTable.value = role.commonTable;
}
initFn();

const selectData = ref({});
const requestObjData = ref({
  queryRequest: {},
  saveRequest: {},
  delRequest: {}
});

const columnsArr = ref([]);

const operationObj = ref({
      show: {
        label: "查看",
        val: false
      },
      update: {
        label: "修改",
        val: false
      },
      del: {
        label: "删除",
        val: false
      }
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

</script>

<style scoped lang="less">
.tableConfig {
  position: relative;
  padding: 10px 20px 50px 20px;

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
      display: flex;

      .upBox {
        position: absolute;
        top: 0;
        left: -10px;
      }
    }
  }

}


</style>
