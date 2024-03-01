<template>
  <view class="content">

    <view class="logoContent">

      <view class="logoBox">
        {{ state.userInfo }}
      </view>
    </view>



    <button class="loginBtn"  loadding type="primary" @click="getUserInfo">
      微信用户一键登录
    </button>

    <view class="">
      <view>11</view>
      <view>我已经阅读并同意</view>
    </view>
  </view>
</template>

<script setup>
import {
  onMounted, reactive
} from 'vue';
import {wxLogin} from '@/api/api.js'


const state = reactive({
  userInfo: {}
})

onMounted(() => {

  initFn();
})

const initFn = () => {
  uni.getUserInfo({
    provider: 'weixin',
    success: (res) => {
      console.log('getUserInfo', res);
      state.userInfo = res.userInfo;
    },
  });
}

const getUserInfo = () => {
  uni.getUserProfile({
    desc: '登录后可同步数据',
    lang: 'zh_CN',
    success: (res) => {
      console.log('getUserProfile', res);
    }
  });
}
</script>

<style lang="less" scoped>
.logoContent {
  width: 300rpx;
  height: 300rpx;
  background: #ccc;
  margin: 150rpx auto;
  padding: 10rpx;
  border-radius: 10rpx;
  .logoBox {

  }
}

.loginBtn {
  margin: 10rpx 80rpx;
}
</style>