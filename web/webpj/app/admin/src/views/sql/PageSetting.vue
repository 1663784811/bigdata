<template>
  <div>
    <CommonTable :table-setting="commonTable" @event="eventFn"/>
  </div>
</template>

<script setup>

import {ref} from "vue";


import CommonTable from '@/component/CommonTable.vue'
import CommonTableBox from './CommonTableBox.vue'
import {pageConfig} from '@/store/pageConfig.js'
import {findIdCPageComponents, findCPageComponents} from '@/api/api.js'


const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("cPage");
  commonTable.value = role.commonTable;
}
initFn();


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
