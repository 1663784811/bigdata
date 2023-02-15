<template>
  <div class="topBox">
    <LogoBox/>
    <TopMenuBox/>
    <TopUserInfo/>
  </div>
  <div class="mainBox">
    <LeftMenu />
    <div class="mainContent">
      <router-view/>
    </div>
  </div>
</template>

<script setup>
import LogoBox from "@/views/layout/LogoBox.vue";
import TopMenuBox from "@/views/layout/TopMenuBox.vue";
import TopUserInfo from "@/views/layout/TopUserInfo.vue";
import LeftMenu from "@/views/layout/LeftMenu.vue";






</script>

<style scoped lang="less">
.topBox {
  background: #17233d;
  display: flex;
  justify-content: left;
}

.mainBox {
  display: flex;

  .mainContent {
    flex: 1;
  }
}


</style>
