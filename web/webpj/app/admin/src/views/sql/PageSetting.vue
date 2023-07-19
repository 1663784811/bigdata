<template>
  <div>
    <CommonTable
        :search-columns="tableData.searchColumns"
        :table-columns="tableData.columns"
        :table-data="tableData.data"
        :operation="tableData.operation"
        :save-columns="tableData.saveColumns"
        :request-obj="tableData.requestObj"
        @event="eventFn"
    />


    <div class="pageConfig">
      <div class="cList">
        <div>通用表格</div>
      </div>
      <div class="dataBox">
        <div>
          类型:
          <Select style="width:200px">
            <Option value="通用表格" key="commonTable"/>
          </Select>
        </div>
        <div>
          数据:<Input size="large" placeholder="large size"/>
        </div>
        <div class="componentBox">
          <CommonTableBox />
        </div>

      </div>
    </div>

  </div>
</template>

<script setup>

import {ref} from "vue";


import CommonTable from '@/component/CommonTable.vue'
import CommonTableBox from './CommonTableBox.vue'
import {pageConfig} from '@/store/pageConfig.js'
import {findIdCPageComponents} from '@/api/api.js'


const usePageConfig = pageConfig();
const role = usePageConfig.getPageConfig("cPage");


const commonTable = role.commonTable;

const tableData = ref({
  searchColumns: commonTable.search.columns,
  columns: commonTable.columns,
  operation: commonTable.operation,
  saveColumns: commonTable.save.columns,
  requestObj: commonTable.requestObj,
  data: []
})

/**
 * 事件
 */
const eventFn = (eventData) => {
  console.log("sssssssssssssssssssssssss", eventData)
  findIdCPageComponentsFn(eventData.tid);
}

/**
 * 加载数据
 */

const findIdCPageComponentsFn = (id) => {
  findIdCPageComponents({id}).then((rest) => {
    console.log(rest)
  })
}


</script>

<style scoped lang="less">
.pageConfig {
  display: flex;

  .cList {
    width: 200px;
  }

  .dataBox {
      width: 100%;
  }
}
</style>
