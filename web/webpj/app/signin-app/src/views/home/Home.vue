<template>
  <div class="homeContainer">
    <!--  ============================  -->
    <header class="home-header wrap" :class="{'active' : state.headerScroll}">
      <div class="header-search">
      </div>
      <div class="login" @click="goToFn('saveSignIn')">
        <van-icon name="plus"/>
      </div>
    </header>
    <!--  ============================  -->
    <nav-bar/>
    <div class="headerBox"></div>
    <!--  ==============     最新推荐    ==============  -->
    <div class="good" :style="{ paddingBottom: '100px'}">
      <header class="good-header">签到列表</header>
      <van-skeleton title :row="3" :loading="state.loading">
        <div class="good-box">
          <!------------------->
          <div class="good-item" v-for="(item,index) in state.recommends" :key="index">
            <div>
              <div class="itemTitle" @click="goToDetail(item)">{{ item.title }}</div>
              <div class="itemTime" @click="goToDetail(item)">{{ item.startTime }}</div>
              <div class="itemCount" @click="goToDetail(item)">
                <div>共:</div>
                <div>100人</div>
                <div>已签到:</div>
                <div>50 人</div>
                <div>未签到:</div>
                <div>50 人</div>
              </div>
              <div class="itemStatus">状态:已完成</div>
            </div>
            <div class="qrCode" @click="showQrCode(item)">
              <van-icon name="qr"/>
            </div>
          </div>
          <!------------------->
        </div>
      </van-skeleton>
    </div>
    <van-popup v-model:show="state.qrObj.show">内容</van-popup>
  </div>
</template>

<script setup>
import {nextTick, onMounted, reactive} from 'vue'
import {useRouter} from 'vue-router'
import {findSignInPage} from '@/service/api'
import {closeToast, showLoadingToast, showToast} from 'vant'
import {useCartStore} from '@/stores/cart'
import {useUserStore} from "@/stores/user";

let userStore = useUserStore();
const cart = useCartStore()
const router = useRouter()
const state = reactive({
  swiperList: [],
  isLogin: false,
  headerScroll: false,
  hots: [],
  newGoodses: [],
  recommends: [],
  categoryList: [],
  loading: true,
  scrollTop: 0,
  qrObj: {
    show: false
  }
})


onMounted(async () => {
  if (userStore.token) {
    state.isLogin = true;
  }
  showLoadingToast({
    message: '加载中...',
    forbidClick: true
  });
  await findSignInPage({}).then((rest) => {
    const {data} = rest;
    state.recommends = data;
    console.log('ssssssssss', rest)
  })
  state.loading = false
  closeToast()
})

nextTick(() => {
  document.body.addEventListener('scroll', () => {
    let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
    scrollTop > 100 ? state.headerScroll = true : state.headerScroll = false
  })
})

const goToDetail = (item) => {
  console.log(item)
  router.push({
    name: 'SignInDetails',
    query: {
      id: item.tid
    }
  })
}

const goToFn = (name) => {
  router.push({name});
}

const tips = () => {
  showToast('敬请期待');
}

/**
 * 显示二维码
 */
const showQrCode = (row) => {
  state.qrObj.show = true;
}
</script>

<style lang="less" scoped>
@import '../../common/style/mixin';

.homeContainer {
  background: #f6f6f6;
  min-height: 100vh;
}

.home-header {
  position: fixed;
  left: 0;
  top: 0;
  .wh(100%, 50px);
  .fj();
  line-height: 50px;
  padding: 0 15px;
  .boxSizing();
  font-size: 15px;
  color: #fff;
  z-index: 10000;
  background: #1baeae;

  .nbmenu2 {
    color: #fff;
  }

  &.active {
    background: @primary;

    .nbmenu2 {
      color: #fff;
    }

    .login {
      color: #fff;
    }
  }

  .header-search {
    display: flex;
    width: 74%;
    line-height: 20px;
    margin: 10px 0;
    padding: 5px 0;
    color: #232326;
    border-radius: 20px;

    .app-name {
      padding: 0 10px;
      color: @primary;
      font-size: 20px;
      font-weight: bold;
      border-right: 1px solid #666;
    }

    .icon-search {
      padding: 0 10px;
      font-size: 17px;
    }

    .search-title {
      font-size: 12px;
      color: #666;
      line-height: 21px;
    }
  }

  .icon-iconyonghu {
    color: #fff;
    font-size: 22px;
  }

  .login {
    color: #fff;
    line-height: 52px;

    .van-icon-manager-o {
      font-size: 20px;
      vertical-align: -3px;
    }
  }
}

.headerBox {
  height: 50px;
}

.category-list {
  display: flex;
  flex-shrink: 0;
  flex-wrap: wrap;
  width: 100%;
  padding-bottom: 13px;
  border-top: 1px solid #ebebeb;
  border-bottom: 1px solid #ebebeb;

  div {
    display: flex;
    flex-direction: column;
    width: 20%;
    text-align: center;

    img {
      .wh(36px, 36px);
      margin: 13px auto 8px auto;
    }
  }
}

.good {
  .good-header {
    background: #f9f9f9;
    height: 50px;
    line-height: 50px;
    text-align: center;
    color: @primary;
    font-size: 16px;
    font-weight: 500;
  }

  .good-box {

    .good-item {
      box-sizing: border-box;
      margin: 10px 10px;
      padding: 10px;
      background: #fff;
      border-radius: 6px;
      box-shadow: 0 0 1px #888;
      display: flex;
      align-items: center;
      justify-content: space-between;

      .itemTitle {
        font-size: 14px;
        font-weight: bold;
      }

      .itemTime {
        color: #666;
        font-size: 12px;
      }

      .itemCount {
        color: #999;
        display: flex;
      }

      .itemStatus {
        color: #999;
      }

      .qrCode {
        font-size: 20px;
        font-weight: bold;
      }
    }
  }
}

.floor-list {
  width: 100%;
  padding-bottom: 50px;

  .floor-head {
    width: 100%;
    height: 40px;
    background: #F6F6F6;
  }

  .floor-content {
    display: flex;
    flex-shrink: 0;
    flex-wrap: wrap;
    width: 100%;
    .boxSizing();

    .floor-category {
      width: 50%;
      padding: 10px;
      border-right: 1px solid #dcdcdc;
      border-bottom: 1px solid #dcdcdc;
      .boxSizing();

      &:nth-child(2n) {
        border-right: none;
      }

      p {
        font-size: 17px;
        color: #333;

        &:nth-child(2) {
          padding: 5px 0;
          font-size: 13px;
          color: @primary;
        }
      }

      .floor-products {
        .fj();
        width: 100%;

        img {
          .wh(65px, 65px);
        }
      }
    }
  }
}
</style>
