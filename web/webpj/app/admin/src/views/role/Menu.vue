<template>
  <div class="menuBox">
    <Tree :data="menuArr" @on-contextmenu="handleContextMenu" show-checkbox>
      <template #contextMenu>
        <DropdownItem @click="handleContextMenuSave(false)">添加</DropdownItem>
        <DropdownItem @click="handleContextMenuSave(true)">编辑</DropdownItem>
        <DropdownItem @click="handleContextMenuDelete" style="color: #ed4014">删除</DropdownItem>
      </template>
    </Tree>
  </div>

  <ModalDataList
      v-model="saveData.show"
      :modalSetting="saveData"
  />

</template>

<script setup>

import {onMounted, ref} from "vue";
import {queryMenu, delMenu} from "@/api/api.js"
import ModalDataList from '@/component/ModalDataList.vue'
import {pageConfig} from '@/store/pageConfig.js'
import {getAddColumns} from '@/api/webUtil.js'

const usePageConfig = pageConfig();

const saveData = ref({
  url: '',
  columns: [],
  data: {},
  show: false
});


const menuArr = ref([
  {
    title: '全部',
    expand: true,
    contextmenu: true,
    children: []
  }
]);


const selectData = ref({});

const initFn = async () => {
  const role = await usePageConfig.getPageConfig("adminMenu");
  saveData.value.columns = getAddColumns(role.commonTable.columns);
}
initFn();

onMounted(() => {
  mountedInitFn();
})

const mountedInitFn = () => {
  queryMenu({}).then((res) => {
    const arr = [];
    if (res.data && res.data.root) {
      const rootArr = res.data.root;
      arr.push(...reTree(rootArr));
    }
    menuArr.value[0].children = arr;
  })
}

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


const handleContextMenu = (data, event, position) => {
  selectData.value = data;
}
/**
 * 编辑
 */
const handleContextMenuSave = (isEditor) => {
  saveData.value.show = true;
  if (isEditor) {

  } else {

  }
}

/**
 * 删除
 */
const handleContextMenuDelete = () => {
  delMenu(selectData.value.data).then((res) => {
    console.log(res)
  }).finally(() => {
    initFn()
  })
}


</script>

<style lang="less" scoped>
.menuBox {
  padding: 20px;
  width: 400px;
  background: #fff;
}
</style>