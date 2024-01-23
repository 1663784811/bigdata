<template>
  <div class="winHeight">
    <div class="loginContent">

      <div class="imgContent">
        <img data-v-aa5851c8="" class="login-left-img"
             src="https://admin.spicyboy.cn/assets/png/login_left-VQgr6mRR.png" alt="login">
      </div>
      <div class="contentBox">


        <div class="loginBox">
          <div class="loginTitle">门店登录</div>
          <div class="loginRow">
            <Input placeholder="用户名" v-model="loginParams.userName"/>
          </div>
          <div class="loginRow">
            <Input placeholder="密码" v-model="loginParams.password"/>
          </div>
          <div class="loginRow">
            <Button class="submitBtn" long>重置</Button>
            <Button class="submitBtn" type="success" long @click="clickLogin">登录</Button>
          </div>
        </div>
      </div>
    </div>
    <AccountFooter/>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import AccountFooter from "./AccountFooter.vue"
import {useRouter, useRoute} from "vue-router";
import {logInFn} from "@/api/api.js"
import {loginInfo} from "@/store/loginInfo.js"
import {Message, Modal} from "view-ui-plus";

const router = useRouter();
const route = useRoute();
const loginInfoSt = loginInfo();

const enterprise = ref({
  tid: ''
});

const loginParams = {
  userName: "root",
  password: "root",
  code: "123456"
};


onMounted(() => {

})


/**
 * 点击登录
 */
const clickLogin = function () {
  logInFn(loginParams, route.params.code).then((res) => {
    if (res.data) {
      const {jwtToken, tadmin} = res.data;
      loginInfoSt.token = jwtToken;
      loginInfoSt.userInfo = tadmin;
      Message.success({
        content: `${res.msg}`
      })
      setTimeout(() => {
        loginInfoSt.variable.eCode = route.params.code;
        router.push({name: 'home'})
      }, 500)
    }
  }).catch((err) => {
    console.log(err)
    Message.error({
      content: `${err.msg}`
    });
  });
}

</script>
<style scoped lang="less">
.winHeight {
  height: 100vh;
  background-size: cover;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #e7e7e7;

  .loginContent {
    min-height: 550px;
    height: 90%;
    width: 95%;
    margin: auto;
    padding: 60px;
    background: #fff;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 10px;

    .imgContent {
      width: 600px;
      margin-right: 40px;

      img {
        width: 100%;
      }
    }

    .contentBox {
      display: flex;
      flex-direction: row-reverse;
      max-width: 1920px;
      padding: 30px;

      border-radius: 10px;
      box-shadow: #a3a3a3 0 2px 10px 2px;


      .loginBox {
        width: 420px;
        background: #fff;

        .loginTitle {
          text-align: center;
          margin-bottom: 20px;
          font-size: 20px;
        }

        .loginRow {
          margin: 30px 0;
          display: flex;
        }

        .submitBtn {
          margin: 0 10px;
        }
      }
    }
  }

}
</style>
