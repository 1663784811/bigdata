<template>
  <div class="welcomeBox">

    <div class="eventBox">

      <div class="eventItem" v-for="i in 100">
        <div class="timeBar">2024-01-05</div>
        <div class="eventContent">
          sssdd
        </div>
      </div>
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
  overflow: auto;

  .eventBox {
    .eventItem {
      position: relative;
      clear: both;
      padding-left: 100px;


      .timeBar {
        position: absolute;
        left: 10px;
        top: 10px;
      }

      .eventContent {
        background: #fff;
        padding: 10px;
        margin: 4px;
        border-radius: 8px;
        min-height: 100px;
      }

      &:before, &:after {
        content: "";
        height: 0;
        clear: both;
        display: block;
      }
    }
  }
}
</style>