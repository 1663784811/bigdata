<template>
  <div style="width: 200px; background: #fff">
    <Menu v-if="store.leftMenu && store.leftMenu.length>0" width="200px" @on-select="selectFn">
      <template v-for="(item,index) in store.leftMenu" :key="index">
        <Submenu :name="index" v-if="item.children && item.children.length>0">
          <template #title>
            <Icon type="ios-paper"/>
            {{ item.name }}
          </template>
          <MenuItem :name="index+'-'+ ch" v-for="(children, ch) in item.children" :key="ch">
            {{ children.name }}
          </MenuItem>
        </Submenu>
        <MenuItem v-else :name="index">
          <div>
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
  if(item.routeName){
    router.replace({
      name: item.routeName,
      query: {
        ...query
      }
    })
  }
}

const selectFn = (name) => {
  const arr = (name + '').split("-")
  let menuData = store.leftMenu;
  let menuItem = null;
  for (let i = 0; i < arr.length; i++) {
    if (arr.length === 1) {
      menuItem = menuData[arr[i]]
    } else if (arr.length > (i + 1)) {
      menuData = menuData[arr[i]]['children']
    } else {
      menuItem = menuData[arr[i]]
    }
  }
  clickMenu(menuItem);
}


</script>

<style scoped>

</style>
