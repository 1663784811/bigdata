<template>
  <div class="winHeight">
    <div class="loginContent">

      <div class="imgContent">
        <img data-v-aa5851c8="" class="login-left-img" src="~@/assets/loginback.png" alt="login">
      </div>
      <div class="contentBox">
        <div class="loginBox" v-if="state.isLoginBox">
          <div class="loginTitle">登录</div>
          <div class="loginRow">
            <Input placeholder="用户名" v-model="state.params.userName" clearable/>
          </div>
          <div class="loginRow">
            <Input placeholder="密码" v-model="state.params.password" clearable/>
          </div>
          <div class="loginRow">
            <Button class="submitBtn" long @click="state.params.userName='';state.params.password='';">重置</Button>
            <Button class="submitBtn" type="success" long :loading="state.isLoading" @click="clickLogin">登录</Button>
          </div>
          <div>
            <span style="cursor: pointer" @click="goToForgetPwd(false)">忘记密码？</span>
          </div>
        </div>
        <div class="loginBox" v-else>
          <div class="loginTitle">重置密码</div>
          <div class="loginRow">
            <Input placeholder="用户名" v-model="state.params.userName" clearable/>
          </div>
          <div class="loginRow">
            <Input placeholder="手机号" v-model="state.params.password" clearable/>
          </div>
          <div class="loginRow">
            <Input placeholder="验证码" v-model="state.forgetPwd.checkCode" clearable enter-button="获取验证码">
              <template #append>
                <Button>获取验证码</Button>
              </template>
            </Input>
          </div>
          <div class="loginRow">
            <Button class="submitBtn" long type="success" disabled>重置密码</Button>
          </div>
          <div>
            <span style="cursor: pointer" @click="goToForgetPwd(true)">用户登录</span>
          </div>
        </div>

      </div>
    </div>
    <AccountFooter/>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from 'vue';
import AccountFooter from "./AccountFooter.vue"
import {useRouter, useRoute} from "vue-router";
import {logInFn} from "@/api/api.js"
import {loginInfo} from "@/store/loginInfo.js"
import {Message, Modal} from "view-ui-plus";

const router = useRouter();
const route = useRoute();
const loginInfoSt = loginInfo();

const state = reactive({
  isLoading: false,
  isLoginBox: true,
  params: {
    userName: "root",
    password: "root",
    code: "123456"
  },
  forgetPwd: {
    checkCode: ''
  }
})


onMounted(() => {

})

const goToForgetPwd = (b) => {
  state.isLoginBox = b;
}


/**
 * 点击登录
 */
const clickLogin = function () {
  state.isLoading = true;
  logInFn(state.params, route.params.code).then((res) => {
    if (res.data) {
      const {jwtToken, tadmin} = res.data;
      loginInfoSt.token = jwtToken;
      loginInfoSt.userInfo = tadmin;
      Message.success({
        content: `${res.msg}`
      })
      setTimeout(() => {
        loginInfoSt.variable.eCode = route.params.code;
        router.push({name: 'dashboard'})
      }, 500)
    }
  }).catch((err) => {
    console.log(err)
    Message.error({
      content: `${err.msg ? err.msg : err.message}`
    });
  }).finally(() => {
    state.isLoading = false;
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
