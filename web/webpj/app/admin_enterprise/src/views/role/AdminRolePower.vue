<template>
  <CommonTable :table-setting="tableInfo"/>
</template>

<script setup>

import {ref} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const tableInfo = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("adminMenu");
  tableInfo.value = role.commonTable;
}
initFn();
</script>

<style scoped lang="less">
</style>
