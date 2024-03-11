<template>
  <div class="order-detail-box">
    <div v-if="!state.loading && state.detail.detailsList && state.detail.detailsList.length>0">
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
        <van-cell title="商品数量" :value="state.detail.order.number"/>
        <van-cell title="商品总价" :value="state.detail.order.payableAmount"/>
        <van-cell title="优惠" value="0.00"/>
        <van-cell title="结算" :value="state.detail.order.payableAmount"/>
      </div>
      <!--   =======================   -->
      <div class="order-status">
        <van-button block round type="success">加菜</van-button>
      </div>
    </div>
    <van-empty
        v-if="!state.loading && state.detail.detailsList && state.detail.detailsList.length == 0"
        description="您还没有点餐"/>
  </div>

  <navBar/>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {onMounted, reactive} from 'vue'
import {getBoardOrder} from '@/service/api.js'
import {closeDialog, closeToast, showConfirmDialog, showLoadingToast, showSuccessToast} from 'vant'
import {useRoute} from 'vue-router'
import NavBar from "@/components/NavBar.vue";

const route = useRoute()
const state = reactive({
  detail: {
    order: {},
    detailsList: []
  },
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
  const {data} = await getBoardOrder({
    boardId: route.params.code
  }, route.params.appid)
  if (data) {
    state.detail.order = data;
    state.detail.detailsList = data.odetailsList;
  }
  // state.detail = data
  state.loading = false;
  closeToast()
}

const handleCancelOrder = (id) => {

}

const handleConfirmOrder = (id) => {

}

const showPayFn = () => {
  state.showPay = true
}


</script>

<style lang="less" scoped>
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
      padding: 6px 10px;
      border-bottom: 1px solid #ebebeb;
      align-items: center;

      .good-img {
        display: flex;
        align-items: center;
        width: 70px;
        height: 70px;

        img {
          max-width: 100%;
          max-height: 100%;
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
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
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
    margin-bottom: 120px;
    padding: 20px;
    font-size: 15px;

  }
}
</style>
