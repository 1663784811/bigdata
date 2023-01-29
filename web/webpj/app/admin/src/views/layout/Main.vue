<template>
  <div class="topBox">
    <LogoBox/>
    <TopMenuBox/>
    <TopUserInfo/>
  </div>
  <div class="mainBox">
    <LeftMenu />
    <div class="mainContent">
      <router-view />
    </div>
  </div>
</template>

<script>
import LogoBox from "@/views/layout/LogoBox.vue";
import TopMenuBox from "@/views/layout/TopMenuBox.vue";
import TopUserInfo from "@/views/layout/TopUserInfo.vue";
import LeftMenu from "@/views/layout/LeftMenu.vue";

export default {
  name: "Main",
  components: {
    LogoBox,
    TopMenuBox,
    TopUserInfo,
    LeftMenu
  }
}
</script>

<style scoped>
.topBox {
  background: #17233d;
  display: flex;
  justify-content: left;
}

.mainBox {
  display: flex;

}


</style>
