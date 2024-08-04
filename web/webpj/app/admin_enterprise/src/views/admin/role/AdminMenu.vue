<template>
  <tree-type :setting="state.treeSetting" :editer="true"/>
</template>

<script setup>
import TreeType from '@/component/tree/TreeType.vue'
import {pageConfig} from "@/store/pageConfig.js";
import {reactive, ref} from "vue";

const usePageConfig = pageConfig();


const state = reactive({
  treeSetting: {}
});

const initFn = async () => {
  const pageCode = 'adminMenu'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.treeSetting = pageData.dataTree;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}
initFn();
</script>

<style lang="less" scoped>
</style>