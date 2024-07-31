<template>
  <data-table :setting="state.newTable" @event="eventFn"/>


</template>

<script setup>

import {onMounted, reactive} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import {useRoute} from "vue-router";
import {useWinModal} from "@/store/winModal.js";

const winMqtt = useWinModal().winMqtt;

const route = useRoute();

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
  const pageCode = 'eq_equipment'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.newTable = pageData.newTable;
}


const eventFn = (dataObj) => {
  const {even, data} = dataObj;
  if (even === "sendData") {
    winMqtt.code = data.code;
    winMqtt.name = data.name;
    winMqtt.show = true;
  }
}

</script>

<style scoped lang="less">
</style>
