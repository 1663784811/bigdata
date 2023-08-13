<template>
  <div class="menuBox">
    <tree-node v-model="menuArr"/>
  </div>
  <div>sesse</div>
</template>

<script setup>

import {onMounted, ref} from "vue";
import treeNode from '../../component/tree/TreeNode.vue'
import {logInFn, enterpriseFindPage} from "@/api/api.js"


const menuArr = ref([
  {
    title: 'parent 1',
    expand: true,
    children: [
      {
        title: 'parent 1-1',
        expand: true,
        children: [
          {
            title: 'leaf 1-1-1'
          },
          {
            title: 'leaf 1-1-2'
          }
        ]
      },
      {
        title: 'parent 1-2',
        expand: true,
        children: [
          {
            title: 'leaf 1-2-1'
          },
          {
            title: 'leaf 1-2-1'
          }
        ]
      }
    ]
  },
  {
    title: 'parent 2',
    expand: true,
    children: [
      {
        title: 'parent 1-1',
        expand: true,
        children: [
          {
            title: 'leaf 1-1-1'
          },
          {
            title: 'leaf 1-1-2'
          }
        ]
      },
      {
        title: 'parent 1-2',
        expand: true,
        children: [
          {
            title: 'leaf 1-2-1'
          },
          {
            title: 'leaf 1-2-1'
          }
        ]
      }
    ]
  }
]);


onMounted(() => {

})


</script>

<style lang="less" scoped>
.menuBox{
  padding: 20px;
  width: 400px;
}
</style>