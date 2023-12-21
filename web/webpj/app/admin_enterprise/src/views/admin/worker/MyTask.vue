<template>
  <data-table :setting="state.newTable"/>
</template>

<script setup>

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

</script>

<style scoped lang="less">
</style>
