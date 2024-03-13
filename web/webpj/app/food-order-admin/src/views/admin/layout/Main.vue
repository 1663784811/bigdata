<template>
  <div class="topBox">
    <LogoBox/>
    <TopMenuBox/>
    <TopUserInfo/>
  </div>
  <div class="mainBox">
    <LeftMenu/>
    <div class="mainContent">
      <div style="height: 100%">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script setup>
import LogoBox from "@/views/admin/layout/LogoBox.vue";
import TopMenuBox from "@/views/admin/layout/TopMenuBox.vue";
import TopUserInfo from "@/views/admin/layout/TopUserInfo.vue";
import LeftMenu from "@/views/admin/layout/LeftMenu.vue";
import {userInfo} from "@/api/api.js"
import {loginInfo} from "@/store/loginInfo.js"
import {onMounted, ref} from 'vue';
import {useRouter} from "vue-router";

const router = useRouter();
const loginInfoSt = loginInfo();
onMounted( () => {
  if (!loginInfoSt.token) {
    router.replace({
      name: 'login',
      query: {
        eCode: loginInfoSt.variable.eCode
      }
    })
  } else {
    userInfo({}, loginInfoSt.variable.eCode).then((rest) => {
      console.log(rest);
    })
  }
})


</script>

<style scoped lang="less">
.topBox {
  background: #17233d;
  display: flex;
  justify-content: left;
}

.mainBox {
  display: flex;
  height: calc(100% - 50px);

  .mainContent {
    flex: 1;
    padding: 15px;
    height: 100%;
    overflow: auto;
  }
}


</style>
