<template>

  <van-swipe class="my-swipe" :autoplay="3000" indicator-color="#1baeae">
    <van-swipe-item v-for="(item, index) in list" :key="index">
      <img :src="item.carouselUrl" alt="" @click="goTo(item.redirectUrl)">
    </van-swipe-item>
  </van-swipe>

  <div class="category-list">
    <div v-for="item in categoryList" v-bind:key="item.categoryId" @click="tips">
      <img :src="item.imgUrl">
      <span>{{item.name}}</span>
    </div>
  </div>

  <div class="home">ssees</div>
  <van-button type="primary">主要按钮</van-button>
  <van-button type="success">成功按钮</van-button>
  <van-button type="default">默认按钮</van-button>
  <van-button type="warning">警告按钮</van-button>
  <van-button type="danger">危险按钮</van-button>

</template>


<script setup>
import {ref} from "vue";

const list = ref([
  {
    carouselUrl: 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner-mate-x3.png',
  },
  {
    carouselUrl: 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner-mate-x3.png',
  },
  {
    carouselUrl: 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner-mate-x3.png',
  },
  {
    carouselUrl: 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner-mate-x3.png',
  }
]);

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

const goTo = (url) => {
  window.open(url)
}

</script>

<style scoped lang="less">
@import '../assets/mixin.less';

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

</style>
