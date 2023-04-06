<template>
  <div class="winHeight">
    <div class="loginContent">
      <div class="contentBox">
        <div class="loginBox">
          <div>登录</div>
          <div class="loginRow">
            <Input placeholder="用户名" v-model="loginParams.userName"/>
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
import {onMounted, ref} from 'vue';
import AccountFooter from "./AccountFooter.vue"
import {useRouter, useRoute} from "vue-router";
import {logInFn, enterpriseFindPage} from "@/api/api.js"

const router = useRouter();
const route = useRoute();

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
  const {eCode} = route.query;
  if (eCode) {
    enterpriseFindPage({
      code: eCode
    }).then((rest) => {
      if (rest.data && rest.data.length > 0) {
        enterprise.value = rest.data[0];
      }
    }).catch((err) => {
      console.log(err)
    })
  }
})

/**
 * 点击登录
 */
const clickLogin = function () {
  loginParams.enterpriseId = enterprise.value.tid;
  logInFn(loginParams).then((res) => {
    console.log("===============",res);
    router.push({
      path: "/"
    })
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
