<template>
  <div style="width: 200px; background: #fff">
    <Menu v-if="store.leftMenu && store.leftMenu.length>0" width="200px">

      <template v-for="(item,index) in store.leftMenu" :key="index">
        <Submenu name="1" v-if="item.children && item.children.length>0">
          <template #title>
            <Icon type="ios-paper"/>
            {{ item.name }}
            <MenuItem name="1-1">文章管理</MenuItem>
            <MenuItem name="1-2">评论管理</MenuItem>
            <MenuItem name="1-3">举报管理</MenuItem>
          </template>
        </Submenu>
        <MenuItem v-else :name="item.tid+'-'+ index">
          <div @click="clickMenu(item)">
            <Icon type="md-settings"/>
            {{ item.name }}
          </div>
        </MenuItem>
      </template>


    </Menu>
  </div>

</template>

<script setup>
import {useAdminMenuStore} from '@/store/adminMenu.js'
import {useRouter} from "vue-router";

const store = useAdminMenuStore();
const router = useRouter();


const clickMenu = (item) => {
  let query = {};
  if (item.query) {
    query = item.query;
  }
  router.replace({
    name: item.routeName,
    query: {
      ...query
    }
  })

}




</script>

<style scoped>

</style>
