<template>
  <div style="width: 200px; background: #aaa">
    <Menu v-if="store.leftMenu && store.leftMenu.length>0" width="200px">
      <Submenu name="1" v-for="(item,index) in store.leftMenu" :key="index">
        <template #title>
          <Icon type="ios-paper"/>
          内容管理
        </template>
        <MenuItem name="1-1">文章管理</MenuItem>
        <MenuItem name="1-2">评论管理</MenuItem>
        <MenuItem name="1-3">举报管理</MenuItem>
      </Submenu>
    </Menu>
  </div>

</template>

<script setup>
import {useAdminMenuStore} from '@/store/adminMenu.js'

const store = useAdminMenuStore();

console.log('ssssssssssssss', store.leftMenu)

</script>

<style scoped>

</style>
