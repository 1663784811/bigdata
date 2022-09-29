<template>
  <router-view></router-view>
  <div>
    <van-tabbar v-model="active">
      <van-tabbar-item icon="wap-home">首页</van-tabbar-item>
      <van-tabbar-item icon="shopping-cart">订单</van-tabbar-item>
      <van-tabbar-item icon="manager">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>

import {ref} from 'vue';

export default {
  name: "LayOut",
  setup() {
    const active = ref('home');
    return {active};
  },
}
</script>

<style scoped>

</style>
