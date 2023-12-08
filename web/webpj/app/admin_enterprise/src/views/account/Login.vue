<template>
  <div class="winHeight">
    <div class="loginContent">
      <div class="contentBox">
        <div class="loginBox">
          <div class="loginTitle">登录</div>
          <div class="loginRow">
            <Input placeholder="用户名" v-model="loginParams.userName"/>
          </div>
          <div class="loginRow">
            <Input placeholder="密码" v-model="loginParams.password"/>
          </div>
          <div class="loginRow">
            <Input placeholder="验证码" v-model="loginParams.code"/>
            <div style="width: 150px;height: 30px ; background: red; margin-left: 10px">
              <img src="" alt="">
            </div>
          </div>
          <div class="loginRow">
            <Input placeholder="验证码" v-model="loginParams.code"/>
            <div style="margin-left: 10px">
              <Button>获取验证码</Button>
            </div>
          </div>
          <div class="loginRow">
            <Button type="success" long @click="clickLogin">登录</Button>
          </div>
          <div>
            忘记密码 、
            <span @click="router.push({name:'register'})">注册</span>
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

  .loginContent {
    padding: 60px 0;
    background: #e93854;

    .contentBox {
      display: flex;
      flex-direction: row-reverse;
      max-width: 1920px;
      margin: auto;
      background: #333;
      background: url("https://passport.jd.com/new/misc/2015/background.png") no-repeat center center;
      padding: 60px 60px;

      .loginBox {
        width: 400px;
        padding: 20px 20px;
        border-radius: 6px;
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
      }
    }
  }

}
</style>
