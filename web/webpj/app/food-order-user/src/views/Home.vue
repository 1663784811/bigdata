<template>
  <div class="mainMenu">
    <van-sidebar>
      <van-sidebar-item v-for="(item, index) in state.typeArr" :key="index" :title="item.name"/>
    </van-sidebar>
    <div class="menuItemBox">
      <div v-for="item in 10" :key="item">
        <div>分类一</div>
        <div class="goodsBox" v-for="it in 5" :key="it">
          <div class="imgBox">
            <img src="https://cdn.it120.cc/apifactory/2019/06/20/cd126315-5f7d-43b7-92fe-b9444a293f21.jpg" alt="">
          </div>
          <div class="goodsInfo">
            <div>
              米家互联网空调[砍调[砍价]米家互联网空调[砍价]
            </div>
            <div>
              <div>$100</div>
            </div>
            <div>
              <van-stepper @change="updateCartFn" name="aaaaa"/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="carBox">
    <div class="carLogo">
      <div class="imgBox">
        <img src="@/assets/reserv_hot.png" alt="">
      </div>
    </div>
    <div class="carInfo">
      <div class="price">￥20</div>
      <div class="priceNote">查看明细</div>
    </div>
    <div class="submitBtn">去结算</div>
  </div>
  <navBar/>
</template>

<script setup>
import {onMounted, reactive} from 'vue'
import {useRouter, useRoute} from 'vue-router'
import {commonQuery, updateCart, findMyCart} from '@/service/api'
import {useCartStore} from '@/stores/cart'
import {useUserStore} from "@/stores/user";
import NavBar from "@/components/NavBar.vue";


let userStore = useUserStore();
const cart = useCartStore()
const router = useRouter()
const route = useRoute()
const state = reactive({
  isLogin: false,
  loading: true,
  typeArr: [],
})

onMounted(async () => {
  if (userStore.token) {
    state.isLogin = true;
  }
  goodsType();
  findMyCartFn();
})


/**
 * 查询菜单
 */
const goodsType = () => {
  commonQuery({
    code: 'select_g_type_by_app_id',
    appid: route.params.appid,
    page: 1,
    size: 200
  }).then((res) => {
    if (res.data && res.data.length > 0) {
      state.typeArr = res.data;
      for (let i = 0; i < state.typeArr.length; i++) {
        storeGoods(state.typeArr[i].tid);
      }
    } else {
      state.typeArr = [];
    }
    console.log('sssssssssssssss', res)
  })
}

const storeGoods = (typeCode) => {
  commonQuery({
    code: 'select_g_goods_by_store_id',
    storeId: route.params.appid,
    page: 1,
    size: 200,
    typeCode
  }).then((res) => {
    console.log('sssssssssssssss', res)
  })
}


const updateCartFn = (value, detail) => {
  updateCart({
    number: 1,
    skuId: 'ss',
    boardId: route.params.code
  }).then((rest) => {
    console.log(rest)
  })
  console.log(value, detail)
}

const findMyCartFn = () => {
  findMyCart({
    boardId: route.params.code
  }).then((rest) => {
    console.log(rest)
  })

}

</script>

<style lang="less" scoped>
.mainMenu {
  height: 100vh;
  display: flex;
  background: #efefef;

  .menuItemBox {
    flex: 1;
    height: 100%;
    overflow-y: auto;
  }

  .goodsBox {
    margin-bottom: 1px;
    background: #fff;
    display: flex;
    align-items: center;
    padding: 6px;

    .imgBox {
      width: 100px;
      height: 100px;
      background: #ccc;
      border-radius: 10px;

      img {
        max-height: 100%;
        max-width: 100%;
      }
    }

    .goodsInfo {
      flex: 1;
    }
  }
}

.carBox {
  position: fixed;
  bottom: 66px;
  left: 10px;
  right: 10px;
  background: #212526;
  border-radius: 40px;
  display: flex;
  align-items: center;
  height: 45px;

  .carLogo {
    position: relative;

    .imgBox {
      position: absolute;
      bottom: -20px;
      left: 12px;
      width: 44px;

      img {
        max-width: 100%;
        max-height: 100%;
      }
    }
  }

  .carInfo {
    flex: 1;
    padding-left: 60px;
    display: flex;
    align-items: center;

    .price {
      color: #fff;
      font-size: 22px;
    }

    .priceNote {
      color: #fff;
      margin-left: 10px;
      font-size: 12px;
    }
  }

  .submitBtn {
    height: 100%;
    background: #ffdf20;
    border-radius: 0 30px 30px 0;
    display: flex;
    align-items: center;
    padding: 0 20px;
    font-size: 16px;
  }
}
</style>