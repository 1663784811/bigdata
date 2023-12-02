<template>
  <tree-type :treeSetting="treeSetting" :editer="true"/>
</template>

<script setup>
import {ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import TreeType from "@/component/tree/TreeType.vue";

const usePageConfig = pageConfig();
const treeSetting = ref({});

const initFn = async () => {
  const role = await usePageConfig.getPageConfig("adminMenu");
  treeSetting.value = role.commonTable;
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