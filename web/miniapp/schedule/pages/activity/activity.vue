<template>
  <view class="content">

    <view class="taskBox">
      <view class="taskItem" v-for="(item, index) in state.taskList" :key="index">
        <view class="taskTime">
          2024年3月8日
        </view>
        <view class="taskName">
          2022地产四月月度楼盘暖场活动策湖方案
        </view>
        <view class="statistics">
          <view>参与:10人</view>
          <view>确认:10人</view>
          <view>已签到:10人</view>
        </view>
        <view class="status">进行中</view>
      </view>
    </view>

    <view class="addBtn">

    </view>

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


</script>

<style lang="less">
.taskBox {
  padding: 1rpx 0;
  margin: 16rpx;

  .taskItem {
    padding: 10rpx 0;
    background: #fff;
    margin-bottom: 10rpx;
    border-radius: 10rpx;
    position: relative;
    .taskTime{
      font-size: 30rpx;
      padding: 10rpx 20rpx;
      font-weight: bold;
    }
    .taskName{
      font-size: 28rpx;
      padding: 10rpx 20rpx;
    }
    .statistics{
      display: flex;
      font-size: 24rpx;
      padding: 10rpx 20rpx;
    }
    .status{
      position: absolute;
      top: 0;
      right: 0;
      font-size: 20rpx;
      background: #f6cbcb;
    }
  }

}

.addBtn{
  position: fixed;
  bottom: 80rpx;
  right: 80rpx;
  border-radius: 50%;
  box-shadow: 1rpx 1rpx 10rpx #333333;
  width: 100rpx;
  height: 100rpx;
  background: #f6aaaa;
}
</style>
























