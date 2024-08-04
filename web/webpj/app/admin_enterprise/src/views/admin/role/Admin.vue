<template>
  <data-table :setting="state.newTable"/>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import {useRoute} from "vue-router";

const route = useRoute();
const usePageConfig = pageConfig();
const state = reactive({
  newTable: {
    show: true
  },
})
onMounted(() => {
  initFn();
})

const initFn = async () => {
  const pageCode = 'admin'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.newTable = pageData.newTable;
}
</script>

<style scoped lang="less">
</style>
