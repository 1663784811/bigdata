<template>
  <div class="create-order">
    <s-header :name="'生成订单'" @callback="deleteLocal"></s-header>
    <!--  =================  -->
    <div v-if="state.address" class="address-wrap" @click="goTo">
      <div class="name">
        <span>{{ state.address.name }} </span>
        <span>{{ state.address.phone }}</span>
      </div>
      <div class="address">
        {{ state.address.province }} {{ state.address.city }} {{ state.address.district }}
        {{ state.address.address }}
      </div>
      <van-icon class="arrow" name="arrow"/>
    </div>
    <div  v-else class="addressAdd">
      <van-contact-card style="margin-top: 10px" type="add" @click="goTo"/>
    </div>
    <!--  =================  -->
    <div class="goodBox">
      <div v-for="(item, index) in state.cartList" :key="index">
        <div class="good-item" v-for="(goods, gInx) in item.goodsRestList" :key="gInx">
          <div class="good-img">
            <img :src="goods.ggoods.photo" alt="">
          </div>
          <div class="good-desc">
            <div class="good-title">
              <span>{{ goods.ggoods.name }}</span>
              <span>x{{ goods.number }}</span>
            </div>
            <div>
              颜色：红色
            </div>
            <div class="good-btn">
              <div class="price">¥{{ goods.price }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--  =================  -->
    <div class="otherInfo">
      <van-cell title="快递" value="0.00"/>
      <van-cell title="商品总价" :value="state.priceObj.goodsTotalPrice"/>
      <van-cell title="商品数量" :value="state.priceObj.goodsNum"/>
      <van-cell title="优惠" value="0.00"/>
      <van-cell title="结算" :value="state.priceObj.allTotalPrice"/>
    </div>
    <!--  =================  -->
    <div class="pay-wrap">
      <div class="price">
        <span>总价</span>
        <span>¥{{ state.priceObj.allTotalPrice }}</span>
      </div>
      <van-button @click="handleCreateOrder" class="pay-btn" color="#1baeae" type="primary" block>
        提交订单
      </van-button>
    </div>
  </div>
</template>

<script setup>
import {reactive, onMounted, computed} from 'vue'
import sHeader from '@/components/SimpleHeader.vue'
import {createOrder, payOrder, countGoodsPrice,getDefaultAddress} from '@/service/api'
import {setLocal, getLocal} from '@/common/js/utils'
import {showLoadingToast, closeToast, showSuccessToast, showFailToast} from 'vant'
import {useRoute, useRouter} from 'vue-router'

const router = useRouter()
const route = useRoute()
const state = reactive({
  cartList: [],
  address: {},
  orderNo: '',
  cartItemIds: [],
  priceObj: {},
  goodsList: []
})

onMounted(() => {
  init()
})

const init = async () => {
  showLoadingToast({message: '加载中...', forbidClick: true});
  const {addressId, cartItemIds} = route.query
  const goodsList = JSON.parse(cartItemIds);
  state.goodsList = goodsList;
  await countGoodsPrice({
    goodsList
  }).then(rest => {
    const {data} = rest;
    console.log(data)
    state.cartList = data.storeRestList
    state.priceObj = data;
  })
  await getDefaultAddress({addressId}, route.params.appid).then(rest => {
    const {data} = rest;
    state.address = data;
  })
  closeToast()
}

const goTo = () => {
  router.push({name: 'address', query: {cartItemIds: JSON.stringify(state.cartItemIds), from: 'createOrder'}})
}

const deleteLocal = () => {
  setLocal('cartItemIds', '')
}

/**
 * 提交订单
 */
const handleCreateOrder = async () => {
  showLoadingToast({message: '正在生成订单...', forbidClick: true});
  const params = {
    addressId: state.address.addressId,
    goodsList: state.goodsList
  }
  const {data, msg, code} = await createOrder(params)
  closeToast();
  if (code !== 2000) {
    showFailToast(`${msg}`);
  } else {
    showSuccessToast("订单提交成功")
    //跳转支付页面
    router.replace({name: 'payOrder', query: {orderId: data.tid}})

  }

}
</script>

<style lang="less" scoped>
@import '../../common/style/mixin';

.create-order {
  background: #f9f9f9;
  min-height: 100vh;

  .address-wrap {
    margin-bottom: 20px;
    background: #fff;
    position: relative;
    font-size: 14px;
    padding: 15px;
    color: #222333;

    .name, .address {
      margin: 10px 0;
    }

    .arrow {
      position: absolute;
      right: 10px;
      top: 50%;
      transform: translateY(-50%);
      font-size: 20px;
    }

    &::before {
      position: absolute;
      right: 0;
      bottom: 0;
      left: 0;
      height: 2px;
      background: -webkit-repeating-linear-gradient(135deg, #ff6c6c 0, #ff6c6c 20%, transparent 0, transparent 25%, #1989fa 0, #1989fa 45%, transparent 0, transparent 50%);
      background: repeating-linear-gradient(-45deg, #ff6c6c 0, #ff6c6c 20%, transparent 0, transparent 25%, #1989fa 0, #1989fa 45%, transparent 0, transparent 50%);
      background-size: 80px;
      content: '';
    }
  }
  .addressAdd{
    margin: 10px 0;
  }

  .good-item {
    padding: 10px;
    background: #fff;
    display: flex;
    align-items: center;
    border-bottom: 1px solid #eeeeee;

    .good-img {
      background: #f6f6f6;
      .wh(70px, 70px);

      img {
        max-height: 100%;
        max-width: 100%;
      }
    }

    .good-desc {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      flex: 1;
      padding: 6px;

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

  .otherInfo {
    margin-top: 10px;
    margin-bottom: 100px;
  }

  .pay-wrap {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    background: #fff;
    padding: 10px 0;
    padding-bottom: 50px;
    border-top: 1px solid #e9e9e9;

    > div {
      display: flex;
      justify-content: space-between;
      padding: 0 5%;
      margin: 10px 0;
      font-size: 14px;

      span:nth-child(2) {
        color: red;
        font-size: 18px;
      }
    }

    .pay-btn {
      position: fixed;
      bottom: 7px;
      right: 0;
      left: 0;
      width: 90%;
      margin: 0 auto;
    }
  }
}
</style>
