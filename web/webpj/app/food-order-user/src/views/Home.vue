<template>
  <div id="start">
    sssss
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