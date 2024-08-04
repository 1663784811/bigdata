<template>

  <tree-type :setting="dataTree" :editer="true"/>

</template>

<script setup>
import TreeType from '@/component/tree/TreeType.vue'
import {pageConfig} from "@/store/pageConfig.js";
import {ref} from "vue";

const usePageConfig = pageConfig();

const dataTree = ref({});

const initFn = async () => {
  const pageCode = 'department'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  dataTree.value = pageData.dataTree;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}

initFn();


</script>

<style lang="less" scoped>

</style>