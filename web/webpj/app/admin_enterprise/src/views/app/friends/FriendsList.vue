<template>
  <data-table :setting="state.newTable"/>
</template>

<script setup>

import {onMounted, provide, reactive, ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const commonTableSearchData = ref({})
provide("commonTableSearchData", commonTableSearchData);

const state = reactive({
  pageData: {},
  drawerSetting: {},
  newTable: {
    show: true
  },
})

onMounted(()=>{
  initFn();
})

const initFn = async () => {
  const pageCode = 'ent_friends_user'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.newTable = pageData.newTable;
  commonTableSearchData.value = {"appId": 'sss'}
}

</script>

<style scoped lang="less">
</style>
