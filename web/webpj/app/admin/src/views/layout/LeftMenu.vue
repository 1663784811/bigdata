<template>
  <div style="width: 200px; background: #fff">
    <Menu v-if="store.leftMenu && store.leftMenu.length>0" width="200px">

      <template v-for="(item,index) in store.leftMenu" :key="index">
        <Submenu :name="item.name" v-if="item.children && item.children.length>0">
          <template #title>
            <Icon type="ios-paper"/>
            {{ item.name }}
            <MenuItem :name="children.name" v-for="(children, ch) in item.children" :key="ch">{{ children.name }}
            </MenuItem>
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
  console.log('=================', item)
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
