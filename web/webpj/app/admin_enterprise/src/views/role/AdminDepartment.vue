<template>

  <tree-type :treeSetting="treeSetting" />

</template>

<script setup>
import TreeType from '@/component/tree/TreeType.vue'

import {pageConfig} from "@/store/pageConfig";
import {ref} from "vue";

const usePageConfig = pageConfig();

const treeSetting = ref({});

const initFn = async () => {
  const role = await usePageConfig.getPageConfig("department");
  treeSetting.value = role.commonTable;
}

initFn();


</script>

<style lang="less" scoped>

</style>