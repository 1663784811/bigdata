<template>
  <div>
    <CommonTable
        :table-setting="commonTable"
        @event="eventFn"
    />
    <div class="pageConfig">
      <div class="cList">
        <div>通用表格</div>
      </div>
      <div class="dataBox">
        <div>
          <div>查找数据</div>
          <div>
            地址:<Input size="large" placeholder="large size"/>
          </div>
          <div>
            参数:<Input size="large" placeholder="large size"/>
          </div>
        </div>
        <div>
          字段:<Input size="large" placeholder="large size"/>
        </div>
        <div class="componentBox">
          <CommonTableBox/>
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
import {findIdCPageComponents, findCPageComponents} from '@/api/api.js'


const usePageConfig = pageConfig();
const role = usePageConfig.getPageConfig("cPage");


const commonTable = role.commonTable;

/**
 * 事件
 */
const eventFn = (eventData) => {
  console.log("sssssssssssssssssssssssss", eventData)
  findCPageComponentsFn(eventData.tid);
}

/**
 * 加载组件数据
 */
const findCPageComponentsFn = (id) => {
  findCPageComponents({page_id: id}).then((rest) => {
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
