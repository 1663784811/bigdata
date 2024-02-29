<template>
  <view class="content">
    <view class="myHeadBox">
      <view class="userInfo">
        <view class="leftInfo">
          <view class="face">
            <image class="img-face"/>
          </view>
          <view class="user">
            <view class="userName">听心的原因</view>
            <view class="userTxt">ID: 1234564</view>
          </view>
        </view>
        <view class="rightInfo">
          二维码
        </view>
      </view>
      <view class="statistics">
        <view class="stItem">
          <view class="stNum">5</view>
          <view class="stNote">节日</view>
        </view>
        <view class="stItem">
          <view class="stNum">10</view>
          <view class="stNote">习惯</view>
        </view>
        <view class="stItem">
          <view class="stNum">100</view>
          <view class="stNote">通信录</view>
        </view>
        <view class="stItem">
          <view class="stNum">100</view>
          <view class="stNote">消费历史</view>
        </view>
      </view>
    </view>
    <view class="myServer">
      <view class="title">我的功能</view>
      <view class="serverBox">
        <view class="serverItem" v-for="(item, index) in state.serverList" :key="index">
          <image :src="item.icon" class="img-icon"/>
          <view class="text">
            {{ item.text }}
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script setup>
import {
  onMounted,
  reactive
} from "vue";

const state = reactive({
  serverList: [
    {
      icon: 'static/delivered_selected.png',
      text: '节日',
      router: ''
    },
    {
      icon: 'static/delivered_selected.png',
      text: '习惯',
      router: ''
    },
    {
      icon: 'static/delivered_selected.png',
      text: '通信录',
      router: ''
    },
    {
      icon: 'static/delivered_selected.png',
      text: '关于我们',
      router: ''
    },
    {
      icon: 'static/delivered_selected.png',
      text: '联系客服',
      router: ''
    },
    {
      icon: 'static/delivered_selected.png',
      text: '退出登录',
      router: ''
    },
  ]
})
</script>

<style lang="less" scoped>
.myHeadBox {
  background: #fff;
  padding: 16rpx;
  .userInfo {
    display: flex;
    justify-content: space-between;
    justify-items: center;
    align-items: center;

    .leftInfo {
      display: flex;
      .face {
        .img-face {
          width: 130rpx;
          height: 130rpx;
          background: red;
          border-radius: 12rpx;
        }
      }
      .user{
        margin-left: 10rpx;
        .userName{
          margin: 10rpx;
          font-weight: bold;
          font-size: 30rpx;
        }
        .userTxt{
          margin: 10rpx;
          font-size: 24rpx;
          color: #666;
        }
      }
    }

    .rightInfo {

    }
  }
  .statistics{
    margin-top: 40rpx;
    display: flex;
    justify-content: space-around;
    .stItem{
      text-align: center;
      .stNum{
        font-weight: bold;
        font-size: 30rpx;
        margin-bottom: 6rpx;
      }
      .stNote{
        font-size: 24rpx;
        color: #666;
      }
    }
  }
}

.myServer {
  background: #fff;
  margin: 16rpx;
  padding: 10rpx 0;
  border-radius: 6rpx;

  .title {
    font-size: 30rpx;
    padding: 10rpx 20rpx;
    font-weight: bold;
  }

  .serverBox {
    display: flex;
    flex-wrap: wrap;

    .serverItem {
      width: 33.3333%;
      overflow: hidden;
      text-align: center;
      padding: 28rpx;

      .img-icon {
        width: 70rpx;
        height: 70rpx;
        background: #efefef;
      }

      .text {
        font-size: 24rpx;
        color: #666;
      }

    }
  }
}
</style>