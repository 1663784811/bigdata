<template>
  <div class="product-detail">
    <s-header :name="'商品详情'"></s-header>
    <div class="detail-content">
      <!--   ===================   -->
      <div class="detail-swipe-wrap">
        <van-swipe class="my-swipe" indicator-color="#1baeae">
          <van-swipe-item v-for="(item, index) in state.photoList" :key="index" style="background: #fff">
            <van-image :src="item.photo">
              <template v-slot:error>加载失败</template>
            </van-image>
          </van-swipe-item>
        </van-swipe>
      </div>
      <!--   ===================   -->
      <div class="product-info">
        <div class="product-title">
          {{ state.detail.ggoods.name || '' }}
        </div>
        <div class="product-desc">免邮费 顺丰快递</div>
        <div class="product-price">
          <span>¥{{ state.detail.sellingPrice || '' }}</span>
          <!-- <span>库存203</span> -->
        </div>
      </div>
      <div class="product-intro">
        <ul>
          <li>概述</li>
          <li>参数</li>
          <li>安装服务</li>
          <li>常见问题</li>
        </ul>
        <div class="product-content" v-html="state.detailText || ''"></div>
      </div>
    </div>
    <van-action-bar>
      <van-action-bar-icon icon="chat-o" text="客服"/>
      <van-action-bar-icon icon="cart-o" :badge="!cart.count ? '' : cart.count" @click="goTo()" text="购物车"/>
      <van-action-bar-icon icon="shop-o" text="店铺"/>
      <van-action-bar-button type="warning" @click="state.popup.show = true" text="加入购物车"/>
      <van-action-bar-button type="danger" @click="state.popup.show = true" text="立即购买"/>
    </van-action-bar>

    <van-popup
        v-model:show="state.popup.show"
        round
        closeable
        position="bottom"
        :style="{ maxHeight: '70%' }"
    >
      <div class="skuPopup">

        <div class="skuHeadBox">
          <div class="imgBox">
            <img
                src="https://img11.360buyimg.com/seckillcms/s280x280_jfs/t1/168939/13/21426/134540/61769c47Eba288759/99ffd8f2a2e35261.jpg.avif"
                alt="">
          </div>
          <div class="goodsInfoBox">
            <div class="priceInfo">
              <div class="priceText">$10.00</div>
            </div>
            <div class="skuInfo">
              颜色：红色 大小: 中
            </div>
          </div>
        </div>
        <div class="skuAttrBox">
          <div class="skuRow" v-for="(item,index) in  state.detail.skuAttr" :key="index">
            <div class="skuLabel">
              {{ index }}:
            </div>
            <div class="skuContent">
              <div class="skuContentItem" v-for="(it) in item">
                {{ it }}
              </div>
            </div>
          </div>
        </div>
        <div class="selectNumber">
          <div>数量</div>
          <div>
            <van-stepper/>
          </div>
        </div>
        <div class="noteBox">
          <div class="label"> 说明：</div>
          <div>京东商城向您保证所售商品均为正品行货，京东自营商品开具机打发票或电子发票。</div>
        </div>
      </div>
      <van-action-bar>
        <van-action-bar-button type="warning" @click="handleAddCart" text="加入购物车"/>
        <van-action-bar-button type="danger" @click="goToCart" text="立即购买"/>
      </van-action-bar>
    </van-popup>

  </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {nextTick, onMounted, reactive} from 'vue'
import {useRoute, useRouter} from 'vue-router'
import {useCartStore} from '@/stores/cart'
import {addCart, goodsDetails, goodsDetailsText, goodsPhoto} from '@/service/api'
import {showSuccessToast} from 'vant'

const route = useRoute()
const router = useRouter()
const cart = useCartStore()

const state = reactive({
  detail: {
    goodsCarouselList: [],
    ggoods: {},
    skuAttr: []
  },
  photoList: [],
  detailText: '',
  popup: {
    show: true
  }
})

onMounted(async () => {
  const {id} = route.params
  if (!id || id === "null") {
    router.replace({name: 'error'})
  }
  // cart.updateCart()
  const {data} = await goodsDetails({skuId: id}, route.params.appid)
  state.detail = data;

  console.log(data)
  // 查商品图片
  goodsPhoto({goodsId: data.ggoods.tid}).then(rest => {
    const {data} = rest;
    state.photoList = data;
  });
  // 查商品详情
  goodsDetailsText({goodsId: data.ggoods.tid}).then(rest => {
    const {data} = rest;
    if (data) {
      state.detailText = data.details
    } else {
      state.detailText = '该商品没有详情'
    }
  });

})

nextTick(() => {
  // 一些和DOM有关的东西
  const content = document.querySelector('.detail-content')
  content.scrollTop = 0
})

const goBack = () => {
  router.go(-1)
}

const goTo = () => {
  router.push({path: '/cart'})
}

const handleAddCart = async () => {
  const {resultCode} = await addCart({number: 1, skuId: state.detail.gstoreGoodsSku.tid})
  if (resultCode == 200) showSuccessToast('添加成功')
  cart.updateCart()
}

const goToCart = async () => {
  await addCart({goodsCount: 1, goodsId: state.detail.goodsId})
  cart.updateCart()
  router.push({path: '/cart'})
}

</script>

<style lang="less">
@import '../../common/style/mixin';

.product-detail {
  .detail-header {
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

    .product-name {
      font-size: 14px;
    }
  }

  .detail-content {
    height: calc(100vh - 50px);
    overflow: hidden;
    overflow-y: auto;

    .detail-swipe-wrap {
      height: 300px;
      background: #f3f3f3;

      .my-swipe .van-swipe-item {
        img {
          display: block;
          //width: 100%;
          margin: auto;
          height: 300px;
        }
      }
    }

    .product-info {
      padding: 0 10px;

      .product-title {
        font-size: 18px;
        text-align: left;
        color: #333;
      }

      .product-desc {
        font-size: 14px;
        text-align: left;
        color: #999;
        padding: 5px 0;
      }

      .product-price {
        .fj();

        span:nth-child(1) {
          color: #F63515;
          font-size: 22px;
        }

        span:nth-child(2) {
          color: #999;
          font-size: 16px;
        }
      }
    }

    .product-intro {
      width: 100%;
      padding-bottom: 50px;

      ul {
        .fj();
        width: 100%;
        margin: 10px 0;

        li {
          flex: 1;
          padding: 5px 0;
          text-align: center;
          font-size: 15px;
          border-right: 1px solid #999;
          box-sizing: border-box;

          &:last-child {
            border-right: none;
          }
        }
      }

      .product-content {
        padding: 0 20px;

        img {
          width: 100%;
        }
      }
    }
  }

  .van-action-bar-button--warning {
    background: linear-gradient(to right, #6bd8d8, @primary)
  }

  .van-action-bar-button--danger {
    background: linear-gradient(to right, #0dc3c3, #098888)
  }
}

.skuPopup {
  padding-bottom: 50px;

  .skuHeadBox {
    display: flex;
    padding: 20px 10px;
    align-items: center;

    .imgBox {
      height: 100px;
      width: 100px;
      border: 1px solid #ccc;
      padding: 4px;
      border-radius: 4px;
      margin-right: 20px;

      img {
        max-height: 100%;
        max-width: 100%;
      }
    }

    .goodsInfoBox {
      .priceInfo {
        .priceText {
          font-size: 18px;
          font-weight: bold;
          color: #099292;
        }
      }

      .skuInfo {
        font-size: 13px;
        margin-top: 6px;
      }
    }
  }

  .skuAttrBox {
    border-top: 1px solid #f6f6f6;

    .skuRow {
      display: flex;
      margin: 20px 0;
      align-items: center;

      .skuLabel {
        width: 50px;
        text-align: right;
        margin-right: 6px;
        font-size: 14px;
      }

      .skuContent {
        display: flex;
        align-items: center;

        .skuContentItem {
          border: 1px solid #f2f2f2;
          padding: 4px 10px;
          margin: 0 6px;
          border-radius: 4px;
          background: #f2f2f2;
        }
      }
    }
  }

  .selectNumber {
    padding: 10px;
    margin: 10px 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border: 1px solid #f6f6f6;
  }

  .noteBox {
    margin: 10px 0;
    padding: 0 10px;
    display: flex;

    .label {
      width: 50px;
      text-align: right;
      margin-right: 6px;
    }
  }

}
</style>
