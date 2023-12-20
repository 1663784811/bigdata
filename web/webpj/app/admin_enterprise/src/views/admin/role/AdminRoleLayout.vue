<template>
  <div class="adminMain">
    <div class="leftBox">
      <div class="leftTitle">
        <Icon type="ios-film-outline"></Icon>
        角色
      </div>
      <div>
        <tree-type
            :setting="treeSetting"
            style="width: 0;background: none; padding: 0"
            as-title="name"/>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script setup>

import {ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import TreeType from "@/component/tree/TreeType.vue";

const usePageConfig = pageConfig();
const commonTable = ref(null);
const treeSetting = ref({});

const initFn = async () => {
  const role = await usePageConfig.getPageConfig("role");
  treeSetting.value = role.commonTable;
}
initFn();


</script>

<style scoped lang="less">
.adminMain {
  position: relative;
  height: 100%;
  padding-left: 210px;

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

    .leftTitle {
      border-bottom: 1px solid #f1f1f1;
      padding: 6px 0;
      margin-bottom: 6px;
    }
  }
}
</style>
