<template>
  <div style="width: 200px; background: #fff">
    <Menu :active-name="store.activeName" :open-names="store.openNames" @on-open-change="openChange"
          v-if="store.leftMenu && store.leftMenu.length>0" width="200px" @on-select="selectFn">
      <template v-for="(item,index) in store.leftMenu" :key="index">
        <Submenu :name="index" v-if="item.children && item.children.length>0">
          <template #title>
            <Icon type="md-settings"/>
            {{ item.title }}
          </template>
          <template v-for="(children, ch) in item.children" :key="ch">
            <Submenu :name="index+'-'+ ch" v-if="children.children && children.children.length>0">
              <template #title>
                <Icon type="md-settings"/>
                {{ children.title }}
              </template>
              <MenuItem :name="index+'-'+ ch+'-'+inxx " v-for="(chch, inxx) in children.children" :key="inxx">
                <Icon type="ios-paper"/>
                {{ chch.title }}
              </MenuItem>
            </Submenu>
            <MenuItem v-else :name="index+'-'+ ch">
              <Icon type="ios-paper"/>
              {{ children.title }}
            </MenuItem>
          </template>
        </Submenu>
        <MenuItem v-else :name="index">
          <div>
            <Icon :type="item.icon?item.icon: 'md-settings'"/>
            {{ item.title }}
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
  let params = {};
  if (item.query) {
    query = item.query;
  }
  if (item.params) {
    params = item.params
  }
  if (item.routeName) {
    router.replace({
      name: item.routeName,
      query: {
        ...query
      },
      params: {
        ...params
      }
    })
  }
}

const selectFn = (name) => {
  store.activeName = name;
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

const openChange = (name) => {
  store.openNames = name;
}


</script>

<style scoped>

</style>
