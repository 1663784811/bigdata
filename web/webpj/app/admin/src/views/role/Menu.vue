<template>
  <div class="menuBox">
    <Tree :data="menuArr" @on-contextmenu="handleContextMenu" show-checkbox>
      <template #contextMenu>
        <DropdownItem @click="handleContextMenuEdit">添加</DropdownItem>
        <DropdownItem @click="handleContextMenuEdit">编辑</DropdownItem>
        <DropdownItem @click="handleContextMenuDelete" style="color: #ed4014">删除</DropdownItem>
      </template>
    </Tree>
  </div>
</template>

<script setup>

import {onMounted, ref} from "vue";
import treeNode from '../../component/tree/TreeNode.vue'
import {queryMenu} from "@/api/api.js"


const menuArr = ref([
  {
    title: '全部',
    expand: true,
    contextmenu: true,
    children: []
  }
]);


onMounted(() => {

  queryMenu({}).then((res) => {
    const arr = [];
    if (res.data && res.data.root) {
      const rootArr = res.data.root;
      arr.push(...reTree(rootArr));
    }
    menuArr.value[0].children = arr;
  })

})

/**
 * 处理树结构
 */
const reTree = (list) => {
  for (let i = 0; i < list.length; i++) {
    let itemObj = list[i];
    itemObj.expand = true;
    itemObj.contextmenu = true;
    if (itemObj.children && itemObj.children.length > 0) {
      itemObj.children = reTree(itemObj.children);
    }
  }
  return list;
}


const handleContextMenu = (data) => {


}
const handleContextMenuEdit = () => {
  this.$Message.info('Click edit of');
}
const handleContextMenuDelete = () => {
  this.$Message.info('Click delete of');
}


</script>

<style lang="less" scoped>
.menuBox {
  padding: 20px;
  width: 400px;
  background: #fff;
}
</style>