<template>
  <CommonTable :table-setting="state.tableSetting" @event="eventFn"/>
  <SelectDataDrawer :setting="state.drawerSetting"/>
</template>

<script setup>

import {onMounted, ref, provide, reactive, inject} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import SelectDataDrawer from '@/component/modal/SelectDataDrawer.vue'

import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();

const showDrawer = ref(false)
provide('showDrawer', showDrawer);
const state = reactive({
  tableSetting: {},
  drawerSetting: {},
})


onMounted(() => {
  initFn();
})


const initFn = async () => {
  const pageCode = 'role'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.tableSetting = pageData.commonTable;
  state.drawerSetting = pageData.commonTable;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}

const eventFn = (eventObj) => {
  if (eventObj.even === 'department') {
    showDrawer.value = true
    console.log('ssssssssssssssssssss')
  }
  console.log(eventObj)
}


</script>

<style scoped>

</style>
