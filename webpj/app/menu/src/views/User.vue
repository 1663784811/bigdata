<template>
  <!--  -->
  <div class="userInfo">
    <!--  左边  -->
    <div class="infoLeft">
      <van-image
          class="UserFace"
          round
          fit="cover"
          src="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg"
      />
      <div>
        <div>
          登录/注册
        </div>
        <div>
          暂无绑定手机号
        </div>
      </div>
    </div>
    <!--  右边  -->
    <div class="infoRight">
      <div class="qrBox">
        <van-icon name="qr"/>
      </div>
    </div>
  </div>
  <!--  -->
  <div style="margin: 10px 0">
    <van-grid :border="true" :column-num="3">
      <van-grid-item>
        <div class="">
          <span>0.00</span>
          <span>元</span>
        </div>
        <div>
          我的余额
        </div>
      </van-grid-item>
      <van-grid-item>
        <div>
          <span>0</span>
          <span>个</span>
        </div>
        <div>
          我的优惠
        </div>
      </van-grid-item>
      <van-grid-item>
        <div>
          <span>0</span>
          <span>分</span>
        </div>
        <div>
          我的积分
        </div>
      </van-grid-item>
    </van-grid>
  </div>

  <!--  -->
  <van-cell-group>
    <van-cell title="我的订单" is-link icon="bill"/>
    <van-cell title="优惠券" is-link icon="coupon"/>
    <van-cell title="中奖记录" is-link icon="point-gift"/>
  </van-cell-group>

</template>

<script>
export default {
  name: "User",
  data() {
    return {
      profiletitle: '我的',
      username: '登录/注册',           //用户名
      resetname: '',
      mobile: '暂无绑定手机号',             //电话号码
      balance: 0,            //我的余额
      count: 0,             //优惠券个数
      pointNumber: 0,       //积分数
      avatar: '',             //头像地址
      imgBaseUrl: '',
    }
  },
}
</script>

<style lang="scss" scoped>
@import '../style/mixin.scss';

.userInfo {
  background: #3190e8;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .infoLeft {
    display: flex;
    align-items: center;

    .UserFace {
      width: 80px;
      height: 80px;
      margin-right: 10px;
    }
  }

  .infoRight {
    .qrBox {
      font-size: 30px;
      margin-right: 20px;
      color: #666;
    }
  }
}
</style>
