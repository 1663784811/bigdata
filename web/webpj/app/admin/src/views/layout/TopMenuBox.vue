<template>
  <div class="menuBox">
    <div class="menuItem" v-for="(item,index) in store.topMenu" :key="index" @click="gotoPage(item)">
      <Icon type="md-settings"/>
      {{ item.name }}
    </div>
  </div>
</template>

<script setup>
import {useRouter} from 'vue-router'
import {adminMenu} from '@/store/adminMenu.js'

const store = adminMenu()
const router = useRouter();
const gotoPage = function (item) {
  console.log(item)
  store.setNowMenu(item)
  router.push({name: item.routeName})
}


</script>

<style scoped lang="less">
.menuBox {
  flex: 1;
  display: flex;
  align-items: center;

  .menuItem {
    color: #fff;
    padding: 0 10px;

    &:hover {
      color: #ccc;
      cursor: pointer;
    }
  }
}
</style>
