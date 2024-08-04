<template>
  <div class="adminMain">
    <div class="leftBox">
      <div class="leftTitle">
        <Icon type="ios-film-outline"></Icon>
        部门
      </div>
      <div>
        <tree-type :setting="state.treeSetting" style="width: 0; background: none; padding: 0"
                   @selectChange="selectChange"/>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script setup>

import {ref, provide, reactive, onMounted} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import TreeType from "@/component/tree/TreeType.vue";

const usePageConfig = pageConfig();
const treeSetting = ref({});

const commonTableSearchData = ref({})
provide("commonTableSearchData", commonTableSearchData);

const state = reactive({
  treeSetting: {}
});

onMounted(() => {
  initFn();
})
const initFn = async () => {
  const department = await usePageConfig.getPageConfig("department");
  state.treeSetting = department.dataTree;
}


const selectChange = (arr, obj) => {
  const departmentArr = []
  for (let i = 0; i < arr.length; i++) {
    departmentArr.push(arr[i].tid)
  }
  commonTableSearchData.value = {"in_department": departmentArr.join(',')}
}


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
