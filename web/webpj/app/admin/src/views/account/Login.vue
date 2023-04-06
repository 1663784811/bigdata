<template>
  <div class="winHeight">
    <div class="loginContent">
      <div class="contentBox">
        <div class="loginBox">
          <div>登录</div>
          <div class="loginRow">
            <Input placeholder="用户名" v-model="loginParams.userName" />
          </div>
          <div class="loginRow">
            <Input placeholder="密码" v-model="loginParams.password"/>
          </div>
          <div class="loginRow">
            <Input placeholder="验证码" v-model="loginParams.code"/>
          </div>
          <div>
            <Button type="success" long @click="clickLogin">登录</Button>
          </div>
        </div>
      </div>


    </div>
    <AccountFooter/>
  </div>
</template>

<script setup>

import AccountFooter from "./AccountFooter.vue"
import {useRouter} from "vue-router";
import {logInFn} from "@/api/api.js"

const router = useRouter();

const loginParams= {
  userName:"root",
  password:"root",
  enterpriseId:"ss",
  code:"123456"
};


const clickLogin = function () {

  logInFn({
    "code": 123456,
    "codeUuid": "abcdfef",
    "enterpriseId": 123456,
    "password": "password",
    "userName": "why"
  }).then((res) => {
    console.log(res);
  }).catch((err) => {
    console.log(err)
  });

  console.log("sssssss")
  // router.push({
  //   path: "/"
  // });
}

</script>
<style scoped lang="less">
.winHeight {
  height: 100vh;

  .loginContent {
    padding: 60px 0;
    background: #c8c8e3;

    .contentBox {
      display: flex;
      flex-direction: row-reverse;
      max-width: 1920px;
      margin: auto;
      background: #333;
      padding: 0 60px;

      .loginBox {
        width: 400px;
        border-radius: 6px;
        background: #fff;
      }
    }
  }

}


</style>
