<template>

  <!--  ============================  -->
  <header class="home-header wrap" :class="{'active' : headerScroll}">
    <router-link tag="i" to="./category"><i class="nbicon nbmenu2"></i></router-link>
    <div class="header-search">
      <span class="app-name">新蜂商城</span>
      <i class="iconfont icon-search"></i>
      <router-link tag="span" class="search-title" to="./product-list?from=home">山河无恙，人间皆安</router-link>
    </div>
    <router-link class="login" tag="span" to="./login" v-if="!isLogin">登录</router-link>
    <router-link class="login" tag="span" to="./user" v-else>
      <van-icon name="manager-o"/>
    </router-link>
  </header>
  <!--  ============================  -->
  <van-swipe class="my-swipe" :autoplay="3000" indicator-color="#1baeae">
    <van-swipe-item v-for="(item, index) in list" :key="index">
      <img :src="item.img" alt="" @click="goTo(item.url)">
    </van-swipe-item>
  </van-swipe>
  <!--  ============================  -->
  <div class="category-list">
    <div v-for="item in categoryList" v-bind:key="item.categoryId" @click="tips">
      <img :src="item.imgUrl">
      <span>{{ item.name }}</span>
    </div>
  </div>
  <!--  ============================  -->
  <div class="good">
    <header class="good-header">新品上线</header>
    <van-skeleton title :row="3" :loading="loading">
      <div class="good-box">
        <div class="good-item" v-for="item in newGoodses" :key="item.goodsId" @click="goToDetail(item)">
          <img :src="$filters.prefix(item.goodsCoverImg)" alt="">
          <div class="good-desc">
            <div class="title">{{ item.goodsName }}</div>
            <div class="price">¥ {{ item.sellingPrice }}</div>
          </div>
        </div>
      </div>
    </van-skeleton>
  </div>
  <!--  ============================  -->
  <div class="good">
    <header class="good-header">热门商品</header>
    <van-skeleton title :row="3" :loading="loading">
      <div class="good-box">
        <div class="good-item" v-for="item in hots" :key="item.goodsId" @click="goToDetail(item)">
          <img :src="$filters.prefix(item.goodsCoverImg)" alt="">
          <div class="good-desc">
            <div class="title">{{ item.goodsName }}</div>
            <div class="price">¥ {{ item.sellingPrice }}</div>
          </div>
        </div>
      </div>
    </van-skeleton>
  </div>
  <!--  ============================  -->
  <div class="good" :style="{ paddingBottom: '100px'}">
    <header class="good-header">最新推荐</header>
    <van-skeleton title :row="3" :loading="loading">
      <div class="good-box">
        <div class="good-item" v-for="item in recommends" :key="item.goodsId" @click="goToDetail(item)">
          <img :src="$filters.prefix(item.goodsCoverImg)" alt="">
          <div class="good-desc">
            <div class="title">{{ item.goodsName }}</div>
            <div class="price">¥ {{ item.sellingPrice }}</div>
          </div>
        </div>
      </div>
    </van-skeleton>
  </div>


</template>


<script setup>
import {ref, nextTick, onMounted} from "vue";
import {getBanner, getEnterpriseType} from '../api/api.js'

const list = ref([]);
const categoryList = ref([

  {
    name: '新蜂超市',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E8%B6%85%E5%B8%82%402x.png',
    categoryId: 100001
  }, {
    name: '新蜂服饰',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E6%9C%8D%E9%A5%B0%402x.png',
    categoryId: 100003
  }, {
    name: '全球购',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E5%85%A8%E7%90%83%E8%B4%AD%402x.png',
    categoryId: 100002
  }, {
    name: '新蜂生鲜',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E7%94%9F%E9%B2%9C%402x.png',
    categoryId: 100004
  }, {
    name: '新蜂到家',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E5%88%B0%E5%AE%B6%402x.png',
    categoryId: 100005
  }, {
    name: '充值缴费',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E5%85%85%E5%80%BC%402x.png',
    categoryId: 100006
  }, {
    name: '9.9元拼',
    imgUrl: 'https://s.yezgea02.com/1604041127880/9.9%402x.png',
    categoryId: 100007
  }, {
    name: '领劵',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E9%A2%86%E5%88%B8%402x.png',
    categoryId: 100008
  }, {
    name: '省钱',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E7%9C%81%E9%92%B1%402x.png',
    categoryId: 100009
  }, {
    name: '全部',
    imgUrl: 'https://s.yezgea02.com/1604041127880/%E5%85%A8%E9%83%A8%402x.png',
    categoryId: 100010
  }
]);

onMounted(async () => {
  // 获取banner
  getBanner({
    enterpriseId: '2df777640d934a7ca63de6bd0bccb664'
  }).then((res) => {
    list.value = res.data;
  })
  // 获取分类
  getEnterpriseType({
    enterpriseId: '2df777640d934a7ca63de6bd0bccb664'
  }).then((res) => {
    console.log(res)
  })


})


const goTo = (url) => {
  if (url) {
    window.open(url)
  }
}

const isLogin = ref(false);
const loading = ref(false);
const newGoodses = ref([]);
const hots = ref([]);
const recommends = ref([]);

const headerScroll = ref(false);

nextTick(() => {
  document.body.addEventListener('scroll', () => {
    let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
    headerScroll.value = scrollTop > 100
  })
})


</script>

<style scoped lang="less">
@import '../assets/mixin.less';

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

  .nbmenu2 {
    color: @primary;
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
    background: rgba(255, 255, 255, .7);
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
    color: @primary;
    line-height: 52px;

    .van-icon-manager-o {
      font-size: 20px;
      vertical-align: -3px;
    }
  }
}

.my-swipe {
  img {
    width: 100%;
    height: 100%;
  }
}

.category-list {
  display: flex;
  flex-shrink: 0;
  flex-wrap: wrap;
  width: 100%;
  padding-bottom: 13px;

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
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;

    .good-item {
      box-sizing: border-box;
      width: 50%;
      border-bottom: 1PX solid #e9e9e9;
      padding: 10px 10px;

      img {
        display: block;
        width: 120px;
        margin: 0 auto;
      }

      .good-desc {
        text-align: center;
        font-size: 14px;
        padding: 10px 0;

        .title {
          color: #222333;
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


</style>
