<template>
  <div class="topBox">
    <LogoBox/>
    <TopMenuBox/>
    <TopUserInfo/>
  </div>
  <div class="mainBox">
    <LeftMenu/>
    <div class="mainContent">
      <div style="background: #fff">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script setup>
import LogoBox from "@/views/layout/LogoBox.vue";
import TopMenuBox from "@/views/layout/TopMenuBox.vue";
import TopUserInfo from "@/views/layout/TopUserInfo.vue";
import LeftMenu from "@/views/layout/LeftMenu.vue";
import {userInfo} from "@/api/api.js"
import {loginInfo} from "@/store/loginInfo.js"
import {onMounted, ref} from 'vue';
import {useRouter} from "vue-router";

const router = useRouter();
const loginInfoSt = loginInfo();


userInfo({}).then((rest) => {
  console.log(rest);

})
onMounted(() => {
  if (!loginInfoSt.token) {
    router.replace({
      name: 'login',
      query: {
        eCode: loginInfoSt.eCode
      }
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
  }
}


</style>
