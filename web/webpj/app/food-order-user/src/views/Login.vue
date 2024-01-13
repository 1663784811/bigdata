<template>
  <div class="selectNum">
    <s-header name='选择用餐人数' :noback="true" :showMenu="false"></s-header>

    <div class="headerBox">
      <div>餐桌号:A001</div>
      <div>请选择正确用餐人数,小马上为您上餐具</div>
    </div>
    <div>
      <div v-for="i in 12">
        {{ i }}
      </div>
    </div>
    <div class="btnBox">
      <div @click="startOrder">点菜</div>
    </div>
  </div>
</template>

<script setup>
import {reactive, ref, onMounted} from 'vue'
import sHeader from '@/components/SimpleHeader.vue'
import vueImgVerify from '@/components/VueImageVerify.vue'
import {adminLogin, register} from '@/service/api'
import {showSuccessToast, showFailToast} from 'vant'
import {useRouter, useRoute} from 'vue-router'
import {useUserStore} from '@/stores/user.js'

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
          name: route.query.replace
        });
      } else {
        router.replace({
          name: 'home'
        });
      }
    }, 100);
  }
});

const startOrder = () => {
  // 第一步:提交人数到服务器
  console.log('ssssssssssss')
}

// 1. 监听餐台状态, 开餐则跳转点餐页面


</script>

<style lang="less">

</style>
