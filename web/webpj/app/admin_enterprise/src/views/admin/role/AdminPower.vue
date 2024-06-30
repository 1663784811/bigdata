<template>
  <data-table :setting="state.newTable"/>
</template>

<script setup>

import {onMounted, reactive, ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();

const state = reactive({
  pageData: {},
  drawerSetting: {},
  newTable: {
    show: true
  },
})

onMounted(()=>{
  initFn();
})

const initFn = async () => {
  const pageCode = 'adminPower'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.newTable = pageData.newTable;
}

</script>

<style scoped lang="less">
</style>
