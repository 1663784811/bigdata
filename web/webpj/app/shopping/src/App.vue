<template>
  <RouterView/>

  <van-tabbar v-model="active" @change="onChange">
    <van-tabbar-item v-for="(item, index) in tabbarArr"
                     :key="index"
                     :icon="item.icon"
    >
      {{ item.name }}
    </van-tabbar-item>
  </van-tabbar>

</template>
<script setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router'

const router = useRouter()
const active = ref(0);
const tabbarArr = ref([
  {
    icon: 'wap-home',
    name: '首页',
    routerName: 'home'
  },
  {
    icon: 'bag',
    name: '分类',
    routerName: 'category'
  },
  {
    icon: 'shopping-cart',
    name: '购物车',
    routerName: 'cart'
  },
  {
    icon: 'manager',
    name: '我的',
    routerName: 'my'
  },
])
const onChange = (index) => {
  console.log()
  let routerName = tabbarArr.value[index].routerName;
  router.push({
    name: routerName
  })
};

</script>

<style scoped lang="less">

</style>
