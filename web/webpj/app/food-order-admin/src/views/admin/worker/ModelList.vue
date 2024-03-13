<template>
  <data-table :setting="state.newTable" @event="eventFn"/>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import {useRoute, useRouter} from "vue-router";

const baseUrl = import.meta.env.VITE_BASE_URL;

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
    window.open(baseUrl + "/activiti/index.html?modelId=" + event.data.id)
  } else {

  }
}

</script>

<style scoped lang="less">
</style>
