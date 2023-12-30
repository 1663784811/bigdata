<template>
  <data-table :setting="state.newTable" @event="eventFn"/>
</template>

<script setup>
import ModalIframe from '@/component/modal/ModalIframe.vue'

import {onMounted, reactive, ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import {useRoute, useRouter} from "vue-router";

const router = useRouter();
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
  initFn(route.name);
})

const initFn = async (pageCode) => {
  console.log("页面ID:", pageCode)
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.newTable = pageData.newTable;
}
const eventFn = (event) => {
  console.log(event)
  if (event.even === 'design') {
    window.open("http://127.0.0.1:8080/activiti/index.html?modelId=96e41e21-a4a7-11ee-bcd6-00e01e5602fe")
  } else {

  }
}

</script>

<style scoped lang="less">
</style>
