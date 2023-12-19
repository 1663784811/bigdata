<template>
  <DataTable :setting="state.newTable" @event="eventFn"/>


</template>
<script setup>
import {onMounted, ref, provide, reactive, inject} from "vue";
import SelectDataDrawer from '@/component/modal/SelectDataDrawer.vue'
import DataTable from '@/component/modal/DataTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

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
  const pageCode = 'activity_module'
  state.pageData = await usePageConfig.getPageConfig(pageCode);
  state.newTable = state.pageData.newTable;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}

const eventFn = (eventObj) => {
  console.log(eventObj)
}

</script>
<style scoped lang="less">

</style>