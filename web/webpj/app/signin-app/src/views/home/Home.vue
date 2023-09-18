<template>
  <div>
    <!--  ============================  -->
    <header class="home-header wrap" :class="{'active' : state.headerScroll}">
      <div class="header-search">
      </div>
      <router-link class="login" tag="span" to="./user">
        <van-icon name="plus" />
      </router-link>
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
            <div class="itemTitle">
              标题:珠海第一旅游公司，优特汇4楼宴会大厅
            </div>
            <div>
              时间: 2023年10月01日 20时20分
            </div>
            <div>
              状态:已完成
            </div>
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
import {getBanner, searchGoods} from '@/service/home'
import {closeToast, showLoadingToast, showToast} from 'vant'
import {useCartStore} from '@/stores/cart'
import {enterpriseType} from "@/service/good"
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
  categoryList: [
    {
      title: '新蜂超市',
      categoryId: 100001,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E8%B6%85%E5%B8%82%402x.png',
      }
    },
    {
      title: '新蜂服饰',

      categoryId: 100003,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E6%9C%8D%E9%A5%B0%402x.png',
      }
    },
    {
      title: '全球购',

      categoryId: 100002,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E5%85%A8%E7%90%83%E8%B4%AD%402x.png',
      }
    },
    {
      title: '新蜂生鲜',

      categoryId: 100004,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E7%94%9F%E9%B2%9C%402x.png',
      }
    },
    {
      title: '新蜂到家',

      categoryId: 100005,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E5%88%B0%E5%AE%B6%402x.png',
      }
    },
    {
      title: '充值缴费',

      categoryId: 100006,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E5%85%85%E5%80%BC%402x.png',
      }
    },
    {
      title: '9.9元拼',
      categoryId: 100007,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/9.9%402x.png',
      }
    },
    {
      title: '领劵',
      categoryId: 100008,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E9%A2%86%E5%88%B8%402x.png',
      }
    },
    {
      title: '省钱',
      categoryId: 100009,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E7%9C%81%E9%92%B1%402x.png',
      }
    },
    {
      title: '全部',
      categoryId: 100010,
      data: {
        img: 'https://s.yezgea02.com/1604041127880/%E5%85%A8%E9%83%A8%402x.png',
      }
    }
  ],
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
  await getBanner({
    enterpriseId
  }).then(res => {
    state.swiperList = res.data;
  }).catch((err) => {
    console.log(err)
  })
  await searchGoods({}).then((rest) => {
    const {data} = rest;
    state.recommends = data;
  })
  enterpriseType({
    enterpriseId
  }).then(rest => {
    const {data} = rest;
    state.categoryList = data;
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
  router.push({name:'SignInDetails'})
}

const tips = () => {
  showToast('敬请期待');
}
</script>

<style lang="less" scoped>
@import '../../common/style/mixin';

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

.headerBox{
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
      background: #ccc;

      .imgBox {
        height: 120px;

        img {
          display: block;
          height: 100%;
          margin: 0 auto;
          background: #f7f7f7;
        }
      }

      .good-desc {
        font-size: 14px;
        padding: 10px 0;

        .title {
          color: #222333;
          overflow: hidden;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
          text-overflow: ellipsis;
        }

        .price {
          color: @primary;
        }
      }

      &:nth-child(2n + 1) {
        border-right: 1PX solid #e9e9e9;
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
