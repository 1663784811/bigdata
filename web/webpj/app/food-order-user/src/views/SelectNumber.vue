<template>
  <div class="selectNum">
    <s-header name='选择用餐人数' :noback="true" :showMenu="false"></s-header>
    <div class="headerBox">
      <div class="headTitle">餐桌号:A001</div>
      <div class="headNote">请选择用餐人数,马上为您上餐具</div>
    </div>
    <div class="selectBox">
      <div class="numberItem" v-for="i in 12">
        <div class="numberBox"> {{ i }}人</div>
      </div>
    </div>
    <div class="btnBox">
      <div class="menuBtn" @click="startOrder">点菜</div>
    </div>
  </div>
</template>

<script setup>
import {reactive, onMounted} from 'vue'
import sHeader from '@/components/SimpleHeader.vue'
import vueImgVerify from '@/components/VueImageVerify.vue'
import {adminLogin, register} from '@/service/api'
import {showSuccessToast, showFailToast} from 'vant'
import {useRouter, useRoute} from 'vue-router'
import {useUserStore} from '@/stores/user.js'
import {crateBoard} from '@/service/api.js'

const router = useRouter();
let userStore = useUserStore();
const route = useRoute();


const state = reactive({})

onMounted(() => {
  if (userStore.token) {
    showFailToast('已经开始点餐了')
    setTimeout(() => {
      if (route.query.replace) {
        router.replace({
          name: route.query.replace,
          query: route.query,
          params: route.params
        });
      } else {
        router.replace({
          name: 'home',
          params: route.params,
          query: route.query
        });
      }
    }, 100);
  }
});

const startOrder = () => {
  // 第一步:提交人数到服务器
  crateBoard({
    boardId: '111',
    number: 2
  }, route.params.appid)
}

// 1. 监听餐台状态, 开餐则跳转点餐页面


</script>

<style lang="less">
.selectNum {
  min-height: 100vh;

  .headerBox {
    padding: 30px 0;

    .headTitle {
      text-align: center;
      font-size: 30px;
      padding: 16px 0;
    }

    .headNote {
      text-align: center;
    }
  }

  .selectBox {
    margin: 0 16px;
    display: flex;
    flex-wrap: wrap;

    .numberItem {
      width: 33.33333%;
      height: 60px;
      text-align: center;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 4px;

      .numberBox {
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 100%;
        height: 100%;
        text-align: center;
        justify-content: center;
        align-items: center;
        display: flex;
      }
    }
  }

  .btnBox {
    text-align: center;
    margin: 50px 0;
    display: flex;
    justify-content: center;

    .menuBtn {
      background: #e5e5e5;
      padding: 10px 40px;
      border-radius: 10px;
      color: #fff;
      font-size: 20px;
    }
  }
}
</style>























