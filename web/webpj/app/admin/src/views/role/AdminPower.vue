<template>
  <div class="adminMain">

    <div class="leftBox">
      <div>部门</div>
      <div>
        asd
      </div>
    </div>
    <div class="adminTable">
      <CommonTable :table-setting="commonTable"/>
    </div>
  </div>
</template>

<script setup>

import {ref} from "vue";
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from '@/store/pageConfig.js'

const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("admin");
  commonTable.value = role.commonTable;
}
initFn();
</script>

<style scoped lang="less">
.adminMain {
  position: relative;
  display: flex;
  height: 100%;
  padding-left:  210px;
  .leftBox {
    position: absolute;
    top: 0;
    left: 0;
    background: #fff;
    width: 200px;
    border-radius: 4px;
    padding: 6px;
    display: block;
    margin-right: 10px;
    min-height: 100%;
  }

  .adminTable {

  }
}
</style>
