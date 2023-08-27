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
            忘记密码 、 注册
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
import {logInFn, enterpriseFindPage} from "@/api/api.js"
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
  enterpriseId: "",
  code: "123456"
};


onMounted(() => {
  enterpriseFindPageFn();
})

const enterpriseFindPageFn = async function () {
  const {eCode} = route.query;
  let h = false;
  if (eCode) {
    const {data} = await enterpriseFindPage({
      code: eCode
    });
    if (data && data.length > 0) {
      h = true;
      enterprise.value = data[0];
      loginInfoSt.eCode = eCode;
    }
  }
  if (!h) {
    setTimeout(() => {
      Message.error({
        content: '登录地址错误'
      });
      setTimeout(() => {
        router.replace({
          name: 'register'
        });
      }, 3000)
    })
  }
}

/**
 * 点击登录
 */
const clickLogin = function () {
  loginParams.enterpriseId = enterprise.value.tid;
  logInFn(loginParams).then((res) => {
    console.log("===============", res);
    if (res.data) {
      const {jwtToken} = res.data;
      // 设置token
      loginInfoSt.token = jwtToken;
      router.push({
        path: "/"
      })
    }


  }).catch((err) => {
    console.log(err)
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
