<template>
  <CommonTable :table-setting="commonTable"/>
</template>

<script setup>

import {ref} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("admin");
  commonTable.value = role.commonTable;
}
initFn();
</script>

<style scoped lang="less">
</style>
