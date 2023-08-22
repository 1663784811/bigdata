<template>
  <div class="product-list-wrap">
    <!--  ======================  -->
    <div class="product-list-content">
      <!--  =========  -->
      <header class="category-header wrap">
        <i class="nbicon nbfanhui" @click="goBack"></i>
        <div class="header-search">
          <i class="nbicon nbSearch"></i>
          <input
              type="text"
              class="search-title"
              v-model="state.keyword"/>
        </div>
        <span class="search-btn" @click="getSearch">搜索</span>
      </header>
      <!--  =========  -->
      <van-tabs type="card" color="#1baeae" @click-tab="changeTab">
        <van-tab title="推荐" name=""></van-tab>
        <van-tab title="新品" name="new"></van-tab>
        <van-tab title="价格" name="price"></van-tab>
      </van-tabs>
    </div>
    <!--  ======================  -->
    <div class="content">
      <van-pull-refresh v-model="state.refreshing" @refresh="onRefresh" class="product-list-refresh">
        <van-list
            v-model:loading="state.loading"
            :finished="state.finished"
            :finished-text="state.productList.length ? '没有更多了' : '搜索想要的商品'"
            @load="onLoad"
            @offset="10"
        >
          <template v-if="state.productList.length">
            <div class="product-item" v-for="(item, index) in state.productList" :key="index"
                 @click="productDetail(item)">
              <div class="imgBox">
                <img
                    :src="item.ggoods.photo || 'https://img30.360buyimg.com/seckillcms/s280x280_jfs/t1/66291/2/24087/48093/638097ebE4e300953/a0093faa42b695f1.jpg.avif'"/>
              </div>
              <div class="product-info">
                <p class="name">{{ item.ggoods.name }}</p>
                <p class="subtitle">标签</p>
                <span class="price">￥ {{ item.gstoreGoodsSku.price }}</span>
              </div>
            </div>
          </template>
          <img class="empty" v-else src="https://s.yezgea02.com/1604041313083/kesrtd.png" alt="搜索">
        </van-list>
      </van-pull-refresh>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive} from 'vue'
import {useRoute, useRouter} from 'vue-router'
import {search} from '@/service/good'
import {searchGoods} from '@/service/home'

const route = useRoute()
const router = useRouter()
const state = reactive({
  keyword: route.query.keyword || '',
  searchBtn: false,
  seclectActive: false,
  refreshing: false,
  list: [],
  loading: false,
  finished: false,
  productList: [],
  totalPage: 0,
  page: 1,
  orderBy: ''
})

onMounted(() => {
  init();
})

/**
 * 初始化
 */
const init = async () => {
  searchGoods({}).then((rest) => {
    const {data} = rest;
    state.productList = state.productList.concat(data)
  })


  const {categoryId} = route.query
  if (!categoryId && !state.keyword) {
    state.finished = true
    state.loading = false;
    return
  }


  const {data, data: {list}} = await search({
    pageNumber: state.page,
    goodsCategoryId: categoryId,
    keyword: state.keyword,
    orderBy: state.orderBy
  })

  state.productList = state.productList.concat(list)
  state.totalPage = data.totalPage
  state.loading = false;
  if (state.page >= data.totalPage) state.finished = true
}

const goBack = () => {
  router.go(-1)
}

const productDetail = (item) => {
  router.push({path: `/product/${item.goodsId}`})
}

const getSearch = () => {
  onRefresh()
}

const onLoad = () => {
  if (!state.refreshing && state.page < state.totalPage) {
    state.page = state.page + 1
  }
  if (state.refreshing) {
    state.productList = [];
    state.refreshing = false;
  }
  init()
}

const onRefresh = () => {
  state.refreshing = true
  state.finished = false
  state.loading = true
  state.page = 1
  onLoad()
}

const changeTab = ({name}) => {
  console.log('name', name)
  state.orderBy = name
  onRefresh()
}
</script>

<style lang="less" scoped>
@import '../common/style/mixin';

.product-list-wrap {
  max-height: 100vh;
  padding: 1px 0;
}

.product-list-content {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  z-index: 1000;
  background: #fff;

  .category-header {
    .fj();
    width: 100%;
    height: 50px;
    line-height: 50px;
    padding: 0 15px;
    .boxSizing();
    font-size: 15px;
    color: #656771;
    z-index: 10000;

    &.active {
      background: @primary;
    }

    .icon-left {
      font-size: 25px;
      font-weight: bold;
    }

    .header-search {
      display: flex;
      width: 76%;
      line-height: 20px;
      margin: 10px 0;
      padding: 5px 0;
      color: #232326;
      background: #F7F7F7;
      .borderRadius(20px);

      .nbSearch {
        padding: 0 5px 0 20px;
        font-size: 17px;
      }

      .search-title {
        font-size: 12px;
        color: #666;
        background: #F7F7F7;
      }
    }

    .icon-More {
      font-size: 20px;
    }

    .search-btn {
      height: 28px;
      margin: 8px 0;
      line-height: 28px;
      padding: 0 5px;
      color: #fff;
      background: @primary;
      .borderRadius(5px);
      margin-top: 10px;
    }
  }
}

.content {
  height: calc(~"(100vh - 70px)");
  overflow: hidden;
  overflow-y: scroll;
  margin-top: 78px;
}

.product-list-refresh {
  .product-item {
    display: flex;
    padding: 10px;
    border-bottom: 1px solid #e6e5e5;

    .imgBox {
      .wh(100px, 100px);
      background: #f2f2f2;
      display: flex;
      justify-content: center;
      align-items: center;
      border: 1px solid #dcdcdc;

      img {
        max-width: 100%;
        max-height: 100%;
      }
    }


    .product-info {
      padding: 5px;
      flex: 1;

      .name {
        font-size: 15px;
        overflow: hidden;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        text-overflow: ellipsis;
      }

      .subtitle {
        width: 100%;
        line-height: 25px;
        font-size: 13px;
        color: #999;
        overflow: hidden;
      }

      .price {
        color: @primary;
        font-size: 16px;
      }
    }
  }

  .empty {
    display: block;
    width: 150px;
    margin: 50px auto 20px;
  }
}
</style>
