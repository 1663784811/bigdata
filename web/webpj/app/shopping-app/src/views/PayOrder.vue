<template>
  <div class="payOrder">
    <s-header :name="'支付订单'"></s-header>
    <div v-if="!state.loading">
      <div class="noteBox">
        <div class="priceText">￥{{ state.orderDetail.order.payableAmount }}</div>
        <div class="statusText">已经支付</div>
      </div>
      <div class="btnBox">
        <div class="btnItem">
          <van-button type="success" block @click="wxPay">微信支付</van-button>
        </div>
        <div class="btnItem">
          <van-button type="primary" block @click="aliPay">支付宝支付</van-button>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {getOrderDetail, cancelOrder, confirmOrder, payOrder} from '@/service/order'
import {onMounted, reactive} from "vue";
import {closeToast, showLoadingToast} from "vant";
import {useRoute} from "vue-router";

const route = useRoute()

const state = reactive({
  orderDetail: {},
  loading: true
})

onMounted(() => {
  init();
})

const init = async () => {
  showLoadingToast({message: '加载中...', forbidClick: true});
  const {orderId} = route.query
  const {data} = await getOrderDetail({
    orderId
  })
  state.orderDetail = data
  state.loading = false;
  closeToast()
}

const wxPay = () => {

}

const aliPay = () => {


}


</script>

<style scoped lang="less">
.payOrder {
  .noteBox {
    margin-top: 30px;
    padding: 40px 0;

    .priceText {
      font-size: 30px;
      display: flex;
      justify-content: center;
    }

    .statusText {
      padding: 10px 0;
      font-size: 16px;
      display: flex;
      justify-content: center;
    }

  }

  .btnBox {
    padding: 10px;

    .btnItem {
      margin: 20px 30px;
    }
  }
}
</style>