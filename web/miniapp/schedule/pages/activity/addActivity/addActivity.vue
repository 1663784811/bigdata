<template>
  <view class="content">
    <view class="activityBox">
      <view class="activityItem">
        <view class="itemLabel">活动名称:</view>
        <view class="itemInput">
          <input/>
        </view>
      </view>
      <view class="activityItem">
        <view class="itemLabel">开始时间:</view>
        <view class="itemInput">
          <input/>
        </view>
      </view>
      <view class="activityItem">
        <view class="itemLabel">结束时间:</view>
        <view class="itemInput">
          <input/>
        </view>
      </view>
      <view class="activityItem">
        <view class="itemLabel">添加参与人:</view>
        <view class="itemInput">
          <input/>
        </view>
      </view>
      <view class="activityItem">
        <view class="itemLabel">其他人:</view>
        <view class="itemInput">
          <input/>
        </view>
      </view>
      <view class="activityItem">
        <view class="itemLabel">手机号:</view>
        <view class="itemInput">
          <input/>
        </view>
      </view>
      <view class="activityItem">
        <view class="itemLabel">验证手机号:</view>
        <view class="itemInput">
          <input/>
        </view>
      </view>
      <view class="activityItem">
        <view class="itemLabel">活动详情:</view>
        <view class="itemInput">
          <input/>
        </view>
      </view>

      <view class="activityItem">
        <view class="itemLabel"></view>
        <view class="itemInput">
          <view>添加活动</view>
        </view>
      </view>
    </view>


  </view>
</template>

<script>
export default {
  data() {
    return {}
  },
  methods: {}
}
</script>

<style lang="less" scoped>
.activityBox {
  margin: 0 16rpx;

  .activityItem {
    background: #fff;
    display: flex;
    margin-bottom: 1rpx;

    .itemLabel {
      width: 180rpx;
      text-align: right;
      margin-right: 10rpx;
    }

    .itemInput {
      flex: 1;
    }
  }
}
</style>
