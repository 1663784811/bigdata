<template>
  <div class="cart-box">
    <s-header :name="'购物车'" :noback="true"></s-header>
    <!--  =========================  -->
    <div class="cart-body">
      <van-checkbox-group v-model="state.result" @change="changeSelect" shape="square">
        <div class="storeItem" v-for="(item, index) in state.list" :key="index">
          <div class="storeBox" @click="goToStore(item.estore)">
            <van-cell :title="item.estore.name" is-link icon="shop-o"/>
          </div>
          <van-swipe-cell v-for="(goods, gIndex) in item.cartList" :key="gIndex">
            <div class="good-item">
              <van-checkbox :name="goods"/>
              <div class="good-img">
                <img
                    :src="goods.goodsSku.photo || 'https://img13.360buyimg.com/seckillcms/s280x280_jfs/t1/170929/22/39881/69113/64d066e8Fdf9a291a/abdc1f554cd06780.jpg.avif'"
                    alt="">
              </div>
              <div class="good-desc">
                <div class="good-title">
                  <span>{{ goods.goods.name || '--.--' }}</span>
                </div>
                <div>颜色: 红色</div>
                <div class="good-btn">
                  <div class="price">¥{{ goods.goodsSku.price || '--:--' }}</div>
                  <van-stepper
                      integer
                      :min="1"
                      :model-value="goods.number"
                      :name="goods.skuId"
                      async-change
                      @change="onChange"
                  />
                </div>
              </div>
            </div>
            <template #right>
              <van-button
                  square
                  icon="delete"
                  type="danger"
                  class="delete-button"
                  @click="deleteGood(goods)"
              />
            </template>
          </van-swipe-cell>
        </div>
      </van-checkbox-group>
    </div>
    <!--  =========================  -->
    <van-submit-bar
        v-if="state.list.length > 0"
        class="submit-all van-hairline--top"
        :price="state.countData.allTotalPrice * 100"
        button-text="结算"
        button-type="primary"
        @submit="onSubmit"
    >
      <van-checkbox @click="allCheck" v-model="state.checkAll">全选</van-checkbox>
    </van-submit-bar>
    <!--  =========================  -->
    <div class="empty" v-if="!state.list.length">
      <img class="empty-cart" src="https://s.yezgea02.com/1604028375097/empty-car.png" alt="空购物车">
      <div class="title">购物车空空如也</div>
      <van-button round color="#1baeae" type="primary" @click="goTo" block>前往选购</van-button>
    </div>
    <nav-bar></nav-bar>
  </div>
</template>

<script setup>
import {reactive, onMounted, computed} from 'vue'
import {useRouter} from 'vue-router'
import {useCartStore} from '@/stores/cart'
import {showToast, showLoadingToast, closeToast, showFailToast} from 'vant'
import navBar from '@/components/NavBar.vue'
import sHeader from '@/components/SimpleHeader.vue'
import {getCart, deleteCartItem, countGoodsPrice, addCart} from '@/service/cart'

const router = useRouter()
const cart = useCartStore()
const state = reactive({
  checked: false,
  list: [],
  all: false,
  result: [],
  checkAll: false,
  countData: {
    allTotalPrice: 0
  }
})

onMounted(() => {
  init()
})

const init = async () => {
  showLoadingToast({message: '加载中...', forbidClick: true});
  const {data} = await getCart({pageNumber: 1})
  if (data) {
    state.list = data
  }
  closeToast()
}


const goTo = () => {
  router.push({path: '/home'})
}

const onChange = async (value, detail) => {
  const {list} = state;
  showLoadingToast({message: '修改中...', forbidClick: true});
  const {skuId, number} = getSku(detail.name);
  const num = value - number;
  const {data} = await addCart({number: num, skuId});
  for (let i = 0; i < list.length; i++) {
    const listItem = list[i].cartList;
    for (let j = 0; j < listItem.length; j++) {
      if (listItem[j].skuId === skuId) {
        listItem[j].number = data.number;
      }
    }
  }
  updateCartFn();
  closeToast();
}

const onSubmit = async () => {
  if (state.result.length == 0) {
    showFailToast('请选择商品进行结算')
    return
  }
  const cartArr = state.result;
  const skuIdList = [];
  for (let i = 0; i < cartArr.length; i++) {
    skuIdList.push({
      skuId: cartArr[i].skuId,
      number: cartArr[i].number
    })
  }
  const params = JSON.stringify(skuIdList)
  await router.push({path: '/create-order', query: {cartItemIds: params}})
}

const deleteGood = async (goods) => {
  await deleteCartItem(goods)
  cart.updateCart()
  init()
}

/**
 * 选择所有
 */
const allCheck = () => {
  const {list} = state;
  const selectAll = isSelectAll();
  if (selectAll) {
    state.result = [];
  } else {
    for (let i = 0; i < list.length; i++) {
      const listItem = list[i].cartList;
      for (let j = 0; j < listItem.length; j++) {
        state.result.push(listItem[j])
      }
    }
  }
  updateCartFn();
}

const goToStore = (store) => {
  if (store && store.tid) {
    router.push({name: 'store', query: {id: store.tid}})
  }
}

const updateCartFn = () => {
  const {result, checkAll} = state;
  changeSelect(result);
  // 判断是否选
  state.checkAll = isSelectAll();
}

const changeSelect = (cartArr) => {
  const skuIdList = [];
  for (let i = 0; i < cartArr.length; i++) {
    skuIdList.push({
      skuId: cartArr[i].skuId,
      number: cartArr[i].number
    })
  }
  countPrice(skuIdList);
}

/**
 * 计算商品价格
 */
const countPrice = (skuIdList) => {
  countGoodsPrice({
    goodsList: skuIdList
  }).then(rest => {
    const {data} = rest;
    state.countData = data;
  })
}

/**
 * 获取sku
 */
const getSku = (skuId) => {
  if (skuId) {
    const {list} = state;
    for (let i = 0; i < list.length; i++) {
      const listItem = list[i].cartList;
      for (let j = 0; j < listItem.length; j++) {
        if (listItem[j].skuId === skuId) {
          return listItem[j];
        }
      }
    }
  }
}

const isSelectAll = () => {
  const {list, result} = state;
  for (let i = 0; i < list.length; i++) {
    const listItem = list[i].cartList;
    for (let j = 0; j < listItem.length; j++) {
      const skuId = listItem[j].skuId;
      let h = false;
      for (let k = 0; k < result.length; k++) {
        const resultObj = result[k];
        if (resultObj.skuId === skuId) {
          h = true;
        }
      }
      if (!h) {
        return false;
      }
    }
  }
  if (result.length > 0) {
    return true;
  } else {
    return false;
  }
}

</script>

<style lang="less">
@import '../common/style/mixin';

.cart-box {
  .cart-header {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10000;
    .fj();
    .wh(100%, 44px);
    line-height: 44px;
    padding: 0 10px;
    .boxSizing();
    color: #252525;
    background: #fff;
    border-bottom: 1px solid #dcdcdc;

    .cart-name {
      font-size: 14px;
    }
  }

  .cart-body {
    padding-top: 1px;
    background: #f3f3f3;
    min-height: 90vh;
    margin-bottom: 100px;

    .storeItem {
      background: #fff;
      margin: 10px 4px;
      border-radius: 6px;

      .storeBox {
        border-bottom: 1px solid #ebebeb;
      }

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


    .delete-button {
      width: 50px;
      height: 100%;
    }
  }

  .empty {
    width: 50%;
    margin: 0 auto;
    text-align: center;
    margin-top: 200px;

    .empty-cart {
      width: 150px;
      margin-bottom: 20px;
    }

    .van-icon-smile-o {
      font-size: 50px;
    }

    .title {
      font-size: 16px;
      margin-bottom: 20px;
    }
  }

  .submit-all {
    margin-bottom: 64px;

    .van-checkbox {
      margin-left: 10px
    }

    .van-submit-bar__text {
      margin-right: 10px
    }

    .van-submit-bar__button {
      background: @primary;
    }
  }

  .van-checkbox__icon--checked .van-icon {
    background-color: @primary;
    border-color: @primary;
  }
}
</style>
