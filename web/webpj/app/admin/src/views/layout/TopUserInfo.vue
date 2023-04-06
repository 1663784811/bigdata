<template>
  <div class="userInfo">
    <div class="userInfoItem">
      <Icon type="md-notifications"/>
      <div class="infoBox">
        dddddssssss
      </div>
    </div>
    <div class="userInfoItem">
      <Icon type="md-contact"/>
      <div class="infoBox">
        <div class="infoList" @click="logOut">退出登录</div>
      </div>
    </div>
  </div>
</template>

<script setup>

import {useRouter} from "vue-router";

const router = useRouter();

const logOut = function () {
  console.log("sss");

  router.push({
    name: 'login',
    query:{
      eCode:'aaa'
    }
  })
}

</script>

<style scoped lang="less">
.userInfo {
  display: flex;
  justify-items: center;
  align-items: center;
  padding: 0 20px;

  .userInfoItem {
    font-size: 22px;
    padding: 0 16px;
    height: 100%;
    display: flex;
    align-items: center;
    margin: 0 2px;
    position: relative;

    &:hover {
      background: #0a1327;

      .infoBox {
        display: block;
      }
    }

    .infoBox {
      position: absolute;
      right: 0;
      top: 46px;
      border: 1px solid #000;
      z-index: 999;
      width: 200px;
      background: #fff;
      display: none;
    }
  }
}
</style>
