<template>
  <view class="content">

    <view class="taskBox">
      <view class="taskItem" v-for="(item, index) in state.taskList" :key="index" @click="goToFn('/pages/activity/activityDetails/activityDetails')">
        <view class="taskTime">
          2024年3月8日
        </view>
        <view class="taskName">
          我的生日
        </view>
      </view>
    </view>

    <view class="addBtn" @click="goToFn('/pages/activity/addActivity/addActivity')">+</view>

  </view>
</template>

<script setup>
import {reactive} from 'vue'

const state = reactive({
  taskList: [
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {},
  ]
});

const goToFn = (url) => {
  uni.navigateTo({url});
}

</script>

<style lang="less" scoped>
.taskBox {
  padding: 1rpx 0;
  margin: 16rpx;

  .taskItem {
    padding: 10rpx 0;
    background: #fff;
    margin-bottom: 10rpx;
    border-radius: 10rpx;
    position: relative;

    .taskTime {
      font-size: 30rpx;
      padding: 10rpx 20rpx;
      font-weight: bold;
    }

    .taskName {
      font-size: 28rpx;
      padding: 10rpx 20rpx;
    }
  }

}

.addBtn {
  position: fixed;
  bottom: 80rpx;
  right: 80rpx;
  border-radius: 50%;
  box-shadow: 1rpx 1rpx 10rpx #333333;
  width: 100rpx;
  height: 100rpx;
  background: #f6aaaa;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 60rpx;
  color: #fff;

}
</style>