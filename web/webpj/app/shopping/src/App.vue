<template>
  <RouterView/>

  <van-tabbar v-model="active" @change="onChange">
    <van-tabbar-item icon="wap-home">首页</van-tabbar-item>
    <van-tabbar-item icon="bag">分类</van-tabbar-item>
    <van-tabbar-item icon="shopping-cart">购物车</van-tabbar-item>
    <van-tabbar-item icon="manager">我的</van-tabbar-item>
  </van-tabbar>

</template>
<script setup>
import {ref} from 'vue';


const active = ref(0);
const onChange = (index) => {
  console.log(index)
};

</script>

<style scoped lang="less">

</style>
