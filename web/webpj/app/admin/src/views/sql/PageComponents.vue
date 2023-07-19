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
import {ref} from "vue";


const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("PageComponents");
  console.log('xxxxxxxxxxxxxxxxxxxxxxx', role)
  commonTable.value = role.commonTable;
}
initFn();


/**
 * 事件
 */
const eventFn = (eventData) => {
  console.log("sssssssssssssssssssssssss", eventData)
}


</script>

<style scoped lang="less">

</style>
