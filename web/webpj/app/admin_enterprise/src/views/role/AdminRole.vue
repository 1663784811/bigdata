<template>
  <div>
    <CommonTable :table-setting="commonTable"/>
  </div>
</template>

<script setup>

import {onMounted, ref, provide} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const commonTable = ref(null);


onMounted(() => {

  initFn();
})


const initFn = async () => {
  const pageCode = 'role'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  commonTable.value = pageData.commonTable;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}


</script>

<style scoped>

</style>
