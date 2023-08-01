<template>
  <RouterView/>

  <van-tabbar v-model="active" @change="onChange">
    <van-tabbar-item icon="home-o">标签 1</van-tabbar-item>
    <van-tabbar-item icon="search">标签 2</van-tabbar-item>
    <van-tabbar-item icon="friends-o">标签 3</van-tabbar-item>
    <van-tabbar-item icon="setting-o">标签 4</van-tabbar-item>
  </van-tabbar>

</template>
<script setup>
import { ref } from 'vue';


const active = ref(0);
const onChange = (index) => {
  console.log(index)
};


</script>

<style scoped lang="less">

</style>
