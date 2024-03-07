<template>
  <div class="mainMenu">
    <van-sidebar>
      <van-sidebar-item title="标签名 1"/>
      <van-sidebar-item title="标签名 2"/>
      <van-sidebar-item title="标签名 3"/>
    </van-sidebar>
    <div class="menuItemBox">
      <div v-for="item in 10" :key="item">
        <div>分类一</div>
        <div class="goodsBox" v-for="it in 5" :key="it">
          <div class="imgBox">
            <img src="https://cdn.it120.cc/apifactory/2019/06/20/cd126315-5f7d-43b7-92fe-b9444a293f21.jpg" alt="">
          </div>
          <div class="goodsInfo">
            <div>
              米家互联网空调[砍价]米家互联网空调[砍价]米家互联网空调[砍价]米家互联网空调[砍价]米家互联网空调[砍价]米家互联网空调[砍价]米家互联网空调[砍价]米家互联网空调[砍价]
            </div>
            <div>
              <div>$100</div>
            </div>
            <div>
              <van-stepper/>
            </div>
          </div>
        </div>
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
})


/**
 * 查询菜单
 */
const aaa = (rest) => {



}


</script>

<style lang="less" scoped>
.mainMenu {
  height: 100vh;
  display: flex;
  background: #efefef;

  .menuItemBox {
    flex: 1;
    height: 100%;
    overflow-y: auto;
  }

  .goodsBox {
    margin-bottom: 1px;
    background: #fff;
    display: flex;
    align-items: center;

    .imgBox {
      width: 100px;
      height: 100px;
      background: #ccc;
      border-radius: 10px;

      img {
        max-height: 100%;
        max-width: 100%;
      }
    }

    .goodsInfo {
      flex: 1;
    }
  }

}
</style>