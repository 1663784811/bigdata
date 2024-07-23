<template>
  <div>
    <data-table :setting="state.newTable"/>
  </div>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();

const state = reactive({
  pageData: {},
  drawerSetting: {},
  newTable: {
    show: true
  },
})

onMounted(() => {
  initFn();
})

const initFn = async () => {
  const pageCode = 'shoppingStore'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.newTable = pageData.newTable;
}

</script>

<style scoped>

</style>
