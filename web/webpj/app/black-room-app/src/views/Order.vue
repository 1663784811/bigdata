<template>
  <div class="order-box">
    <s-header :name="'我的订单'" :back="'/user'"></s-header>
    <van-tabs @click-tab="onChangeTab" :color="'#1baeae'" :title-active-color="'#1baeae'" class="order-tab"
              v-model="state.status">
      <van-tab title="全部" name=''></van-tab>
      <van-tab title="待付款" name="0"></van-tab>
      <van-tab title="待确认" name="1"></van-tab>
      <van-tab title="待发货" name="2"></van-tab>
      <van-tab title="已发货" name="3"></van-tab>
      <van-tab title="交易完成" name="4"></van-tab>
    </van-tabs>
    <div class="content">
      <van-pull-refresh v-model="state.refreshing" @refresh="onRefresh" class="order-list-refresh">
        <van-list
            v-model:loading="state.loading"
            :finished="state.finished"
            finished-text="没有更多了"
            @load="onLoad"
            @offset="10"
        >
          <div v-for="(item, index) in state.list" :key="index" class="order-item-box" @click="goTo(item.order.tid)">
            <div class="storeBox">
              <van-cell :title="item.name||'门店'" is-link icon="shop-o"/>
            </div>
            <div class="order-item-header">
              <span>订单时间：{{ item.order.createTime }}</span>
              <span>订单状态：{{ item.order.status }}</span>
            </div>
            <div class="good-item" v-for="(goods, gx) in item.detailsList" :key="gx">
              <div class="good-img">
                <img
                    :src="goods.photo || 'https://img13.360buyimg.com/seckillcms/s280x280_jfs/t1/170929/22/39881/69113/64d066e8Fdf9a291a/abdc1f554cd06780.jpg.avif'"
                    alt="">
              </div>
              <div class="good-desc">
                <div class="good-title">
                  {{ goods.name || '--.--' }}
                </div>
                <div>颜色: 红色</div>
                <div class="good-btn">
                  <div class="price">¥{{ goods.price || '--:--' }}</div>
                </div>
              </div>
            </div>
            <div class="otherInfo">
              <van-cell title="合计">
                <div class="price">¥{{ item.order.payableAmount || '--:--' }}</div>
              </van-cell>
            </div>
          </div>
        </van-list>
      </van-pull-refresh>
    </div>
  </div>
</template>

<script setup>
import {reactive} from 'vue';
import sHeader from '@/components/SimpleHeader.vue'
import {getOrderList} from '@/service/api'
import {useRouter} from 'vue-router'

const router = useRouter()
const state = reactive({
  status: '',
  loading: false,
  finished: false,
  refreshing: false,
  list: [],
  page: 1,
  totalPage: 0
})

const loadData = async () => {
  const {data, data: {list}} = await getOrderList({pageNumber: state.page, status: state.status})
  console.log(list)
  state.list = state.list.concat(data)
  state.totalPage = data.totalPage
  state.loading = false;
  if (state.page >= data.totalPage) state.finished = true
}

const onChangeTab = ({name}) => {
  // 这里 Tab 最好采用点击事件，@click，如果用 @change 事件，会默认进来执行一次。
  state.status = name
  onRefresh()
}

const goTo = (id) => {
  router.push({path: '/order-detail', query: {id}})
}

const goBack = () => {
  router.go(-1)
}

const onLoad = () => {
  if (!state.refreshing && state.page < state.totalPage) {
    console.log(state.page)
    console.log(state.totalPage)
    state.page = state.page + 1
  }
  if (state.refreshing) {
    state.list = [];
    state.refreshing = false;
  }
  loadData()
}

const onRefresh = () => {
  state.refreshing = true
  state.finished = false
  state.loading = true
  state.page = 1
  onLoad()
}
</script>

<style lang="less" scoped>
@import '../common/style/mixin';

.order-box {

  .order-header {
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

    .order-name {
      font-size: 14px;
    }
  }

  .order-tab {
    position: fixed;
    left: 0;
    z-index: 1000;
    width: 100%;
    border-bottom: 1px solid #e9e9e9;
  }

  .skeleton {
    margin-top: 60px;
  }

  .content {
    height: calc(~"(100vh - 70px)");
    overflow: hidden;
    overflow-y: scroll;
    margin-top: 34px;
    background: #f5f5f5;
  }

  .order-list-refresh {
    .van-card__content {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    .van-pull-refresh__head {
      background: #f9f9f9;
    }

    .order-item-box {
      margin: 20px 10px;
      background: #fff;
      border-radius: 6px;

      .storeBox {
        border-bottom: 1px solid #ebebeb;
      }

      .order-item-header {
        padding: 10px;
        display: flex;
        justify-content: space-between;
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
      .otherInfo{
        .price {
          font-size: 16px;
          color: red;
          line-height: 28px;
        }
      }
    }
  }
}
</style>
