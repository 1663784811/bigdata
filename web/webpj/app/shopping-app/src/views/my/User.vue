<template>
  <div class="user-box">
    <s-header :name="'我的'"></s-header>
    <!--  =====================  -->
    <div class="userInfoBox">
      <van-skeleton title :avatar="true" :row="3" :loading="state.loading">
        <div class="user-info">
          <div class="info">
            <img :src="state.user.face||'https://s.yezgea02.com/1604040746310/aaaddd.png'"/>
            <div class="user-desc">
              <span>昵称：{{ state.user.nickName }}</span>
              <span>登录名：{{ state.user.account }}</span>
              <span class="name">个性签名：{{ state.user.introduceSign }}</span>
            </div>
          </div>
        </div>
      </van-skeleton>
    </div>

    <!--  =====================  -->
    <ul class="user-list">
      <li class="van-hairline--bottom" @click="goTo('order')">
        <span>我的订单</span>
        <van-icon name="arrow"/>
      </li>
      <li class="van-hairline--bottom" @click="goTo('setting')">
        <span>账号管理</span>
        <van-icon name="arrow"/>
      </li>
      <li class="van-hairline--bottom" @click="goTo('address', { from: 'mine' })">
        <span>地址管理</span>
        <van-icon name="arrow"/>
      </li>
      <li @click="goTo('about')">
        <span>关于我们</span>
        <van-icon name="arrow"/>
      </li>
    </ul>

    <ul class="user-list">
      <li @click="handleLogout" style="justify-content: center; color: #ff8f8f">
        <span>退出登录</span>
      </li>
    </ul>


    <nav-bar/>
  </div>
</template>

<script setup>
import {reactive, onMounted, toRefs} from 'vue'
import navBar from '@/components/NavBar.vue'
import sHeader from '@/components/SimpleHeader.vue'
import {getUserInfo, logout} from '@/service/api'
import {useRoute, useRouter} from 'vue-router'
import {showSuccessToast} from "vant";

const router = useRouter()
const route = useRoute()


const state = reactive({
  user: {},
  loading: true
})

onMounted(async () => {
  const {data} = await getUserInfo({}, route.params.appid)
  if (data) {
    state.user = data
  }
  state.loading = false
})

const handleLogout = () => {
  logout()
  // userStore.token = '';
  showSuccessToast('退出成功')
  setTimeout(() => {
    router.replace({name: 'login'})
  }, 1000);

}

const goBack = () => {
  router.go(-1)
}

const goTo = (r, query) => {
  router.push({name: r, query: query || {}})
}
</script>

<style lang="less" scoped>
@import '../../common/style/mixin';

.user-box {
  background: #f6f6f6;
  min-height: 100vh;

  .user-header {
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

    .user-name {
      font-size: 14px;
    }
  }

  .userInfoBox {
    background: #fff;
    padding: 20px 10px;
    display: flex;
    align-items: center;
    justify-content: center;

    .user-info {
      width: 100%;
      height: 115px;
      background: linear-gradient(90deg, @primary, #51c7c7);
      box-shadow: 0 2px 5px #269090;
      border-radius: 6px;

      .info {
        position: relative;
        display: flex;
        width: 100%;
        height: 100%;
        padding: 25px 20px;
        .boxSizing();

        img {
          .wh(60px, 60px);
          border-radius: 50%;
          margin-top: 4px;
        }

        .user-desc {
          display: flex;
          flex-direction: column;
          margin-left: 10px;
          line-height: 20px;
          font-size: 14px;
          color: #fff;

          span {
            color: #fff;
            font-size: 14px;
            padding: 2px 0;
          }
        }

        .account-setting {
          position: absolute;
          top: 10px;
          right: 20px;
          font-size: 13px;
          color: #fff;

          .van-icon-setting-o {
            font-size: 16px;
            vertical-align: -3px;
            margin-right: 4px;
          }
        }
      }
    }
  }


  .user-list {
    padding: 0 20px;
    margin-top: 10px;
    background: #fff;

    li {
      height: 48px;
      line-height: 48px;
      display: flex;
      justify-content: space-between;
      font-size: 14px;

      .van-icon-arrow {
        margin-top: 13px;
      }
    }
  }
}
</style>
