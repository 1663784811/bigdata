<template>
  <DataTable :setting="state.newTable" @event="eventFn"/>


</template>

<script setup>
import DataTable from '@/component/modal/DataTable.vue'
import {pageConfig} from "@/store/pageConfig.js";
import {onMounted, reactive, ref} from "vue";


const usePageConfig = pageConfig();


const state = reactive({
  pageData: {},
  drawerSetting: {},
  newTable: {
    show: true
  },
})


onMounted(() => {
  initFn();
})


const initFn = async () => {
  const pageCode = 'role'
  state.pageData = await usePageConfig.getPageConfig(pageCode);
  state.newTable = state.pageData.newTable;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}

const eventFn = (eventObj) => {
  if (eventObj.even === 'department') {
    state.pageData.drawerSetting.show = true;
    state.pageData.drawerSetting.data = eventObj.data;
    state.drawerSetting = state.pageData.drawerSetting;
    console.log('ssssssssssssssssssss', eventObj)
  }
  console.log(eventObj)
}

</script>

<style scoped lang="less">

</style>
