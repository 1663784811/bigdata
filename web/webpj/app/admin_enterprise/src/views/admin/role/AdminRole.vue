<template>
  <DataTable :setting="state.newTable" @event="eventFn"/>
  <SelectDataDrawer v-model="state.drawerSetting.show" :setting="state.drawerSetting"/>
</template>

<script setup>

import {onMounted, ref, provide, reactive, inject} from "vue";
import SelectDataDrawer from '@/component/modal/SelectDataDrawer.vue'
import DataTable from '@/component/modal/DataTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();


const state = reactive({
  pageData: {
    drawerSetting:{}
  },
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

<style scoped>

</style>
