<template>
  <div class="order-detail-box">
    <s-header :name="'订单详情'" @callback="close"></s-header>
    <div v-if="!state.loading">
      <!--   =======================   -->
      <div class="storeBox" v-if="state.detail.store">
        <van-cell :title="state.detail.store.name||'门店'" is-link icon="shop-o"/>
      </div>
      <!--   =======================   -->
      <div class="goodsBox">
        <div class="good-item" v-for="(goods, gx) in state.detail.detailsList" :key="gx">
          <div class="good-img">
            <img
                :src="goods.photo || 'https://img13.360buyimg.com/seckillcms/s280x280_jfs/t1/170929/22/39881/69113/64d066e8Fdf9a291a/abdc1f554cd06780.jpg.avif'"
                alt="">
          </div>
          <div class="good-desc">
            <div class="good-title">
              {{ goods.name || '--.--' }}
            </div>
            <div>颜色: 红色</div>
            <div class="good-btn">
              <div class="price">¥{{ goods.price || '--:--' }}</div>
              <div>x {{ goods.number || '-' }}</div>
            </div>
          </div>
        </div>
      </div>
      <!--   =======================   -->
      <div class="otherInfo">
        <van-cell title="订单编号" :value="state.detail.order.orderNo"/>
        <van-cell title="订单状态" :value="state.detail.order.status"/>
        <van-cell title="下单时间" :value="state.detail.order.createTime"/>
        <van-cell title="快递" value="0.00"/>
        <van-cell title="商品数量" :value="state.detail.order.number"/>
        <van-cell title="商品总价" :value="state.detail.order.payableAmount"/>
        <van-cell title="优惠" value="0.00"/>
        <van-cell title="结算" :value="state.detail.order.payableAmount"/>

      </div>
      <!--   =======================   -->
      <div class="order-status">
        <van-button v-if="state.detail.orderStatus == 3" style="margin-bottom: 10px" color="#1baeae" block
                    @click="handleConfirmOrder(state.detail.orderNo)">确认收货
        </van-button>
        <van-button v-if="state.detail.orderStatus == 0" style="margin-bottom: 10px" color="#1baeae" block
                    @click="showPayFn">去支付
        </van-button>
        <van-button v-if="!(state.detail.orderStatus < 0 || state.detail.orderStatus == 4)" block
                    @click="handleCancelOrder(state.detail.orderNo)">取消订单
        </van-button>
      </div>
    </div>

  </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {onMounted, reactive} from 'vue'
import {cancelOrder, confirmOrder, getOrderDetail, payOrder} from '@/service/api'
import {closeDialog, closeToast, showConfirmDialog, showLoadingToast, showSuccessToast} from 'vant'
import {useRoute} from 'vue-router'

const route = useRoute()
const state = reactive({
  detail: {},
  loading: true,
  showPay: false
})

onMounted(() => {
  init()
})

const init = async () => {
  showLoadingToast({
    message: '加载中...',
    forbidClick: true
  });
  const {id} = route.query
  const {data} = await getOrderDetail({
    orderId: id
  })
  state.detail = data
  state.loading = false;
  closeToast()
}

const handleCancelOrder = (id) => {
  showConfirmDialog({
    title: '确认取消订单？',
  }).then(() => {
    cancelOrder(id).then(res => {
      if (res.resultCode == 200) {
        showSuccessToast('删除成功')
        init()
      }
    })
  }).catch(() => {
    // on cancel
  });
}

const handleConfirmOrder = (id) => {
  showConfirmDialog({
    title: '是否确认订单？',
  }).then(() => {
    confirmOrder(id).then(res => {
      if (res.resultCode == 200) {
        showSuccessToast('确认成功')
        init()
      }
    })
  }).catch(() => {
    // on cancel
  });
}

const showPayFn = () => {
  state.showPay = true
}

const handlePayOrder = async (id, type) => {
  await payOrder({orderNo: id, payType: type})
  state.showPay = false
  init()
}

const close = () => {
  closeDialog
}
</script>

<style lang="less" scoped>
@import '../common/style/mixin';

.order-detail-box {
  background: #f7f7f7;
  padding-bottom: 1px;
  min-height: 100vh;

  .goodsBox {
    background: #fff;
    margin: 10px 6px;
    border-radius: 6px;

    .good-item {
      display: flex;
      padding: 16px 10px;
      border-bottom: 1px solid #ebebeb;

      .good-img {
        display: flex;
        align-items: center;

        img {
          .wh(70px, 70px)
        }
      }

      .good-desc {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        flex: 1;
        padding: 0 0 0 10px;

        .good-title {
          display: flex;
          justify-content: space-between;
        }

        .good-btn {
          display: flex;
          justify-content: space-between;

          .price {
            font-size: 16px;
            color: red;
            line-height: 28px;
          }

          .van-icon-delete {
            font-size: 20px;
            margin-top: 4px;
          }
        }
      }
    }
  }


  .otherInfo {
    margin: 10px 6px;
    border-radius: 6px;
  }

  .order-status {
    background: #fff;
    margin-bottom: 50px;
    padding: 20px;
    font-size: 15px;

    .status-item {
      margin-bottom: 10px;

      label {
        color: #999;
      }

      span {

      }
    }
  }
}
</style>
