<template>
  <div>
    <CommonTable :table-setting="tableSetting"/>
  </div>
</template>

<script setup>

import {onMounted, ref, provide} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'
import {useRoute, useRouter} from "vue-router";


const usePageConfig = pageConfig();
const tableSetting = ref(null);


onMounted(() => {
  initFn();
})


const initFn = async () => {
  const pageCode = 'role'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  tableSetting.value = pageData.commonTable;
  usePageConfig.componentConfig.pageCodeList[pageCode] = pageCode
}


</script>

<style scoped>

</style>
