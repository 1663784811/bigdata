<template>
  <div>
    <CommonTable :table-setting="commonTable"/>
  </div>
</template>

<script setup>
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from "@/store/pageConfig.js";
import {ref} from "vue";

const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("orderList");
  commonTable.value = role.commonTable;
}
initFn();


</script>

<style scoped lang="less">

</style>
