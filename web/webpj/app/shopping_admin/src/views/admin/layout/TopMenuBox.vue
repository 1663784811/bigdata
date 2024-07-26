<template>
  <div class="menuBox">
    <div class="menuItem" v-for="(item,index) in store.topMenu" :key="index" @click="gotoPage(item)">
      <Icon type="md-settings"/>
      {{ item.title }}
    </div>
  </div>
</template>

<script setup>
import {useRouter} from 'vue-router'
import {useAdminMenuStore} from '@/store/adminMenu.js'
import {onMounted, ref} from 'vue';

const store = useAdminMenuStore()
const router = useRouter();

onMounted(() => {
  store.loadMenu();
});

/**
 * 跳转页面
 * @param item
 */
const gotoPage = function (item) {
  store.leftMenu = store.setNowMenu(item);
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
