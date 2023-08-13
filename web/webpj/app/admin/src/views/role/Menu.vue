<template>
  <div class="menuBox">
    <tree-node v-model="menuArr"/>
  </div>
</template>

<script setup>

import {ref} from "vue";
import treeNode from '../../component/tree/TreeNode.vue'


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


</script>

<style lang="less" scoped>
.menuBox{
  padding: 20px;
  width: 400px;
}
</style>