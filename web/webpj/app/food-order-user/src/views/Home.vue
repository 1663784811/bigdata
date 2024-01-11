<template>
  <div id="start">
    <div v-show='showLoading'>
      放loading图片 gif showLoading...........
    </div>
    <div class="start_content" v-show="!showLoading">
      <header class="start_header">
        <img src="../assets/images/canju.png"/> 用餐人数
      </header>
      <p class="notice">请选择正确的用餐人数 ，小二马上给你送餐具</p>
      <div class="content">
        <ul class="user_list">
          <li v-for="(item,key) in userList" :class="{'active':key==0}" :key='key'>
            <span>{{ item }}</span>
          </li>
        </ul>
        <div class="mark_input">
          <input type="text" v-model='p_mark' placeholder="请输入您的口味要求，忌口等（可不填）"/>
        </div>
        <ul class="mark_list">
          <li>
            <span>打包带走</span>
          </li>
          <li>
            <span>不要放辣椒</span>
          </li>
          <li>
            <span>微辣</span>
          </li>
        </ul>
      </div>
    </div>
    <div v-if="!showLoading" id="start" class="start" @click="addPeopleInfo()">
      <span>开始点菜</span>
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

</script>

<style lang="less" scoped>

</style>