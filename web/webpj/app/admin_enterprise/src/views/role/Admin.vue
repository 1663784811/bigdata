<template>
  <CommonTable :table-setting="tableSetting"/>
</template>

<script setup>

import {ref} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const tableSetting = ref(null);
const initFn = async () => {
  const pageCode = 'admin'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  tableSetting.value = pageData.commonTable;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}
initFn();
</script>

<style scoped lang="less">
</style>
