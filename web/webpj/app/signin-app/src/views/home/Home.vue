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
          <div class="good-item" v-for="(item,index) in state.recommends" :key="index" @click="goToDetail(item)">
            <div class="itemTitle">{{item.title}}</div>
            <div class="itemTime">{{item.startTime}}</div>
            <div class="itemCount">
              共: <sapn>100</sapn>人
              已签到: <span>50</span> 人
              未签到: <span>50</span> 人
            </div>
            <div class="itemStatus">状态:已完成</div>
          </div>
          <!------------------->
        </div>
      </van-skeleton>
    </div>
  </div>
</template>

<script setup>
import {nextTick, onMounted, reactive} from 'vue'
import {useRouter} from 'vue-router'
import {getBanner, searchGoods, findSignInPage} from '@/service/api'
import {closeToast, showLoadingToast, showToast} from 'vant'
import {useCartStore} from '@/stores/cart'
import {enterpriseType} from "@/service/api"
import {enterpriseId} from '@/service/webConfig.js'
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
  scrollTop: 0
})


onMounted(async () => {
  if (userStore.token) {
    state.isLogin = true;
  }
  showLoadingToast({
    message: '加载中...',
    forbidClick: true
  });
  // await getBanner({
  //   enterpriseId
  // }).then(res => {
  //   state.swiperList = res.data;
  // }).catch((err) => {
  //   console.log(err)
  // })
  // await searchGoods({}).then((rest) => {
  //   const {data} = rest;
  //   state.recommends = data;
  // })
  // enterpriseType({
  //   enterpriseId
  // }).then(rest => {
  //   const {data} = rest;
  //   state.categoryList = data;
  // })
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
  router.push({name: 'SignInDetails'})
}

const goToFn = (name) => {
  router.push({name});
}

const tips = () => {
  showToast('敬请期待');
}
</script>

<style lang="less" scoped>
@import '../../common/style/mixin';
.homeContainer{
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
      .itemTitle{
        font-size: 14px;
        font-weight: bold;
      }
      .itemTime{
        color: #666;
        font-size: 12px;
      }
      .itemCount{
        color: #999;
      }
      .itemStatus{
        color: #999;
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
