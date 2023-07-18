<template>
  <div>
    <CommonTable :table-setting="commonTable"/>


  </div>
</template>

<script setup>

import {ref} from "vue";

import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const config = usePageConfig.getPageConfig("user");

const commonTable = config.commonTable;

</script>

<style scoped>

</style>
