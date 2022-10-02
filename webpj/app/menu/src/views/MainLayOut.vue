<template>
  <router-view></router-view>
  <div>
    <van-tabbar v-model="active">
      <van-tabbar-item replace to="/home" icon="wap-home">首页</van-tabbar-item>
      <van-tabbar-item replace to="/order" icon="shopping-cart">订单</van-tabbar-item>
      <van-tabbar-item replace to="/user" icon="manager">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>

import {ref} from 'vue';

export default {
  name: "LayOut",
  setup() {
    const active = ref(0);
    return {active};
  },
}
</script>

<style scoped>

</style>
