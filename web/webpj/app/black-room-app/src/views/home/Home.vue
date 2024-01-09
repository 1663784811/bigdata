<template>
  <div class="homeContainer">
    <!--  ============================  -->
    <van-search
        shape="round"
        background="#1baeae"
        show-action
        placeholder="搜索关键词"
        v-model="state.reqParameter.account"
    >
      <template #action>
        <div @click.prevent="clickSearch" style="color:#fff">搜索</div>
      </template>
    </van-search>
    <!--  ==============     最新推荐    ==============  -->
    <div class="good" :style="{ paddingBottom: '100px'}">
      <header class="good-header">小房间共: 100人</header>
      <van-skeleton title :row="3" :loading="state.loading">
        <div class="good-box">
          <!------------------->
          <div class="good-item" v-for="(item,index) in state.recommends" :key="index" @click="goToDetail(item)">
            <div>
              <div class="itemTitle" @click="goToDetail(item)">{{ item.name }} {{ item.account }}</div>
              <div class="itemTime" @click="goToDetail(item)">{{ item.startTime }}</div>
              <div class="itemCount" @click="goToDetail(item)">
                <div>类型:</div>
                <div>{{ item.type == 1 ? '微信' : item.type == 2 ? '支付宝' : '其它' }}</div>
              </div>
              <div class="itemStatus">时间:{{ item.createTime }}</div>
            </div>
          </div>
          <!------------------->
        </div>
      </van-skeleton>
      <div class="buttonBox">
        <div v-if="state.pageStatus === 0 ">加载中...</div>
        <div v-if="state.pageStatus === 1 " @click="nextPage">下一页</div>
        <div v-if="state.pageStatus === 2 ">没有数据了...</div>
      </div>
    </div>
  </div>
  <navBar/>
</template>

<script setup>
import {nextTick, onMounted, reactive} from 'vue'
import {useRouter, useRoute} from 'vue-router'
import {commonQuery} from '@/service/api'
import {closeToast, showLoadingToast, showToast} from 'vant'
import {useCartStore} from '@/stores/cart'
import {useUserStore} from "@/stores/user";
import NavBar from "@/components/NavBar.vue";

let userStore = useUserStore();
const cart = useCartStore()
const router = useRouter()
const route = useRoute()
const state = reactive({
  isLogin: false,
  recommends: [],
  loading: true,
  scrollTop: 0,
  pageStatus: 0,
  reqParameter: {
    sort: 'createTime_desc',
    code: 'select_black_room',
    name: '',
    account: '',
    page: 1,
    size: 30
  }
})


onMounted(async () => {
  if (userStore.token) {
    state.isLogin = true;
  }
  loadData();
})

nextTick(() => {
  document.body.addEventListener('scroll', () => {
    let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
    if (scrollTop < 100 && !state.loading) {
      // state.loading = true
      // state.reqParameter.page += 1
      // loadData()
    }
  })
})

const loadData = () => {
  showLoadingToast({
    message: '加载中...',
    forbidClick: true
  });
  commonQuery(state.reqParameter).then((rest) => {
    const {data} = rest
    state.recommends.push(...data)
    if (data.length === 0) {
      state.pageStatus = 2
    } else {
      state.pageStatus = 1
    }
  }).finally(() => {
    state.loading = false
    closeToast()
  })
}
const clickSearch = () => {
  state.reqParameter.name = state.reqParameter.account
  state.reqParameter.page = 1
  state.recommends = []
  loadData()
}

const nextPage = () => {
  state.reqParameter.page += 1
  state.pageStatus = 0
  loadData()
}

const goToDetail = (item) => {
  router.push({
    name: 'BlackPeopleDetails',
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

    .buttonBox {
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
}
</style>