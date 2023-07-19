<template>
  <div>
    <CommonTable
        :table-setting="commonTable"
        @event="eventFn"
    />
  </div>
</template>

<script setup>

import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'


const usePageConfig = pageConfig();
const role = usePageConfig.getPageConfig("PageComponents");


const commonTable = role.commonTable;

/**
 * 事件
 */
const eventFn = (eventData) => {
  console.log("sssssssssssssssssssssssss", eventData)
}


</script>

<style scoped lang="less">

</style>
