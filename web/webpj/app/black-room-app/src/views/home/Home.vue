<template>
  <div class="homeContainer">
    <!--  ============================  -->
    <van-search
        shape="round"
        background="#1baeae"
        show-action
        placeholder="搜索关键词"
    >
      <template #action>
        <div>搜索</div>
      </template>
    </van-search>
    <!--  ============================  -->
    <nav-bar/>
    <!--  ==============     最新推荐    ==============  -->
    <div class="good" :style="{ paddingBottom: '100px'}">
      <header class="good-header">小房间共: 100人</header>
      <van-skeleton title :row="3" :loading="state.loading">
        <div class="good-box">
          <!------------------->
          <div class="good-item" v-for="(item,index) in state.recommends" :key="index">
            <div>
              <div class="itemTitle" @click="goToDetail(item)">{{ item.title }}</div>
              <div class="itemTime" @click="goToDetail(item)">{{ item.startTime }}</div>
              <div class="itemCount" @click="goToDetail(item)">
                <div>共:</div>
                <div>{{ (item.signEd || 0) + (item.signEd || 0) }}人</div>
                <div> 已签到:</div>
                <div>{{ item.signEd || 0 }} 人</div>
                <div>未签到:</div>
                <div>{{ item.signEd || 0 }} 人</div>
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
    <van-popup v-model:show="state.qrObj.show">
      <vue-qr :text="state.qrObj.data"></vue-qr>
    </van-popup>
  </div>
</template>

<script setup>
import {nextTick, onMounted, reactive} from 'vue'
import {useRouter, useRoute} from 'vue-router'
import {findSignInPage} from '@/service/api'
import {closeToast, showLoadingToast, showToast} from 'vant'
import {useCartStore} from '@/stores/cart'
import {useUserStore} from "@/stores/user";
import vueQr from 'vue-qr/src/packages/vue-qr.vue'

let userStore = useUserStore();
const cart = useCartStore()
const router = useRouter()
const route = useRoute()
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
    show: false,
    data: ''
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
  await findSignInPage({
    sort: 'createTime_desc'
  }).then((rest) => {
    const {data} = rest;
    state.recommends = data;
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
  const {appid} = route.params;
  state.qrObj.data = window.location.origin + '/#/' + appid + "/SignInPage?id=" + row.tid;

}
</script>

<style lang="less" scoped>
@import '../../common/style/mixin';

.homeContainer {
  background: #f6f6f6;
  min-height: 100vh;

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

    .statusBox {
      margin: 10px 10px;
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
}
</style>