<template>
  <div>
    <CommonTable
        :table-setting="commonTable"
    />
  </div>
</template>

<script setup>
import {ref} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("enterprise");
  commonTable.value = role.commonTable;
}
initFn();


</script>

<style scoped>

</style>
