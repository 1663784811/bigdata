<template>
  <Menu active-name="1">

    <MenuItem name="1">
      <Icon type="md-document" />
      文章管理
    </MenuItem>
    <MenuItem name="2">
      <Icon type="md-chatbubbles" />
      评论管理
    </MenuItem>
    <MenuGroup title="统计分析">
      <MenuItem name="3">
        <Icon type="md-heart" />
        用户留存
      </MenuItem>
      <MenuItem name="4">
        <Icon type="md-leaf" />
        流失用户
      </MenuItem>
    </MenuGroup>
  </Menu>
</template>

<script setup>
import {adminMenu} from '@/store/adminMenu.js'
const store = adminMenu()






</script>

<style scoped>

</style>
