<template>
  <data-table :setting="state.newTable"/>
</template>

<script setup>

import {onMounted, provide, reactive, ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import {useRoute, useRouter} from "vue-router";

const router = useRouter();
const route = useRoute();
const usePageConfig = pageConfig();

const commonTableSearchData = ref({})
provide("commonTableSearchData", commonTableSearchData);


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
  commonTableSearchData.value = {"appId": ''}
}

</script>

<style scoped lang="less">
</style>
