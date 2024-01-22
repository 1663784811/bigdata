<template>

  <tree-type :treeSetting="treeSetting"/>

</template>

<script setup>
import TreeType from '@/component/tree/TreeType.vue'

import {getAddColumns} from "@/api/webUtil.js";
import {pageConfig} from "@/store/pageConfig.js";
import {ref} from "vue";

const usePageConfig = pageConfig();

const treeSetting = ref({});

const initFn = async () => {
  const role = await usePageConfig.getPageConfig("shoppingGType");
  treeSetting.value = role.commonTable;
}

initFn();


</script>

<style lang="less" scoped>

</style>