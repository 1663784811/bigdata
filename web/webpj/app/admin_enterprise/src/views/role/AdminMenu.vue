<template>
  <tree-type :treeSetting="tableSetting" :editer="true"/>
</template>

<script setup>
import {ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import TreeType from "@/component/tree/TreeType.vue";

const usePageConfig = pageConfig();
const tableSetting = ref({});

const initFn = async () => {
  const pageCode = 'adminMenu'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  tableSetting.value = pageData.commonTable;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}
initFn();

</script>

<style lang="less" scoped>
.menuBox {
  padding: 20px;
  width: 400px;
  background: #fff;
}
</style>