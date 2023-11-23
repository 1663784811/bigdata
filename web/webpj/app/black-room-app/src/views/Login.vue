<template>
  <div class="login">

    <s-header :name="type == 'login' ? '登录' : '注册'" :back="'/home'"></s-header>
    <img class="logo" src="https://s.yezgea02.com/1604045825972/newbee-mall-vue3-app-logo.png" alt="">

    <!--  ===============   登录  ===============  -->
    <div v-if="state.type == 'login'" class="login-body login">
      <van-form @submit="onSubmit">
        <van-field
            v-model="state.username"
            name="username"
            label="用户名"
            placeholder="用户名"
            :rules="[{ required: true, message: '请填写用户名' }]"
        />
        <van-field
            v-model="state.password"
            type="password"
            name="password"
            label="密码"
            placeholder="密码"
            :rules="[{ required: true, message: '请填写密码' }]"
        />
        <van-field
            center
            clearable
            label="验证码"
            placeholder="输入验证码"
            v-model="state.verify"
        >
          <template #button>
            <vue-img-verify ref="verifyRef"/>
          </template>
        </van-field>
        <div style="margin: 16px;">
          <!--          <div class="link-register" @click="toggle('register')">立即注册</div>-->
          <van-button round block color="#1baeae" native-type="submit" :loading="state.loading">登录</van-button>
        </div>
      </van-form>
    </div>

    <!--  ===============   注册  ===============  -->
    <div v-else class="login-body register">
      <van-form @submit="onSubmit">
        <van-field
            v-model="state.username1"
            name="username1"
            label="用户名"
            placeholder="用户名"
            :rules="[{ required: true, message: '请填写用户名' }]"
        />
        <van-field
            v-model="state.password1"
            type="password"
            name="password1"
            label="密码"
            placeholder="密码"
            :rules="[{ required: true, message: '请填写密码' }]"
        />
        <van-field
            center
            clearable
            label="验证码"
            placeholder="输入验证码"
            v-model="state.verify"
        >
          <template #button>
            <vue-img-verify ref="verifyRef"/>
          </template>
        </van-field>
        <div style="margin: 16px;">
          <div class="link-login" @click="toggle('login')">已有登录账号</div>
          <van-button round block color="#1baeae" native-type="submit">注册</van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<script setup>
import {reactive, ref, onMounted} from 'vue'
import sHeader from '@/components/SimpleHeader.vue'
import vueImgVerify from '@/components/VueImageVerify.vue'
import {login, register} from '@/service/api'
import {setLocal} from '@/common/js/utils'
import md5 from 'js-md5'
import {showSuccessToast, showFailToast} from 'vant'
import {useRouter, useRoute} from 'vue-router'
import {useUserStore} from '@/stores/user.js'

const router = useRouter();
let userStore = useUserStore();
const route = useRoute();


const verifyRef = ref(null)
const state = reactive({
  username: '',
  password: '',
  username1: '',
  password1: '',
  type: 'login',
  imgCode: '',
  verify: '',
  loading: false
})

onMounted(() => {
  console.log(userStore.token)
  if (userStore.token) {
    // 已经登录过的了
    showFailToast('您已经登录')
    setTimeout(() => {
      if (route.query.replace) {
        router.replace({
          name: route.query.replace
        });
      } else {
        router.replace({
          name: 'home'
        });
      }
    }, 1000);
  }

});

// 切换登录和注册两种模式
const toggle = (v) => {
  state.type = v
  state.verify = ''
}

// 提交登录或注册表单
const onSubmit = async (values) => {
  state.imgCode = verifyRef.value.state.imgCode || ''
  console.log("验证码", state.imgCode)
  if (state.verify.toLowerCase() != state.imgCode.toLowerCase()) {
    showFailToast('验证码有误')
    return
  }
  if (state.type == 'login') {
    state.loading = true;
    const {data, msg, code} = await login({
      "userName": values.username,
      "password": values.password
    })
    state.loading = false;
    if (data && code && data.jwtToken) {
      userStore.token = data.jwtToken;
      showSuccessToast(msg);
      setTimeout(() => {
        if (route.query.replace) {
          router.replace({
            name: route.query.replace
          });
        } else {
          router.replace({
            name: 'home'
          });
        }
      }, 1000);
    } else {
      showFailToast(msg)
    }
  } else {
    await register({
      "userName": values.username1,
      "password": values.password1
    })
    showSuccessToast('注册成功')
    state.type = 'login'
    state.verify = ''
  }
}
</script>

<style lang="less">
.login {
  .logo {
    width: 120px;
    height: 120px;
    display: block;
    margin: 80px auto 20px;
  }

  .login-body {
    padding: 0 20px;
  }

  .login {
    .link-register {
      font-size: 14px;
      margin-bottom: 20px;
      color: #1989fa;
      display: inline-block;
    }
  }

  .register {
    .link-login {
      font-size: 14px;
      margin-bottom: 20px;
      color: #1989fa;
      display: inline-block;
    }
  }

  .verify-bar-area {
    margin-top: 24px;

    .verify-left-bar {
      border-color: #1baeae;
    }

    .verify-move-block {
      background-color: #1baeae;
      color: #fff;
    }
  }

  .verify {
    > div {
      width: 100%;
    }

    display: flex;
    justify-content: center;

    .cerify-code-panel {
      margin-top: 16px;
    }

    .verify-code {
      width: 40% !important;
      float: left !important;
    }

    .verify-code-area {
      float: left !important;
      width: 54% !important;
      margin-left: 14px !important;

      .varify-input-code {
        width: 90px;
        height: 38px !important;
        border: 1px solid #e9e9e9;
        padding-left: 10px;
        font-size: 16px;
      }

      .verify-change-area {
        line-height: 44px;
      }
    }
  }
}
</style>
