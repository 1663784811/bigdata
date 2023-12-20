<template>
  <div class="welcomeBox">
    <div>
      <p>一万个美丽的未来，</p>
      <p>抵不上一个温暖的现在</p>
      <p>每一个真实的现在</p>
      <p>都是我们曾经幻想的未来</p>
      <p>愿你爱上现在</p>
      <p>梦见未来</p>
    </div>

    <div class="btnBox">
      <Button type="success" @click="goFn('register')">去注册</Button>
    </div>
  </div>
</template>

<script setup>

import {useRouter} from "vue-router";

const router = useRouter();


const goFn = (name) => {
  router.push({
    name: name
  })
}
</script>

<style scoped lang="less">
.welcomeBox {
  height: 100vh;
  text-align: center;
  padding: 50px 0;

  .btnBox {
    margin-top: 70px;
  }
}
</style>