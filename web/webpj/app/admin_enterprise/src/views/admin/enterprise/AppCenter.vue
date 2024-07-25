<template>
  <div class="appBox">
    <div class="appRow">
      <div class="appItem" v-for="(item, index) in state.appList">
        <div class="logoBox">
          <img :src="item.logo">
        </div>
        <div class="name">{{ item.name }}</div>
        <div class="introduction">{{ item.introduction }}</div>
        <div class="btnBox">
          <Button type="success" long shape="circle" @click="openApp(item)">开通</Button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {onMounted, reactive} from "vue";
import {commonQuery} from '@/api/api.js'
import {loginInfo} from "@/store/loginInfo.js";
import router from "@/router";

const loginInfoSt = loginInfo();


const state = reactive({
  appList: []
})


onMounted(() => {
  commonQuery({
    code: 'select_e_product_center'
  }).then((rest) => {
    const {data} = rest;
    state.appList = data;
  })

})


const loadData = () => {


}

const openApp = (row) => {
  console.log(row)

  router.push({
    name:'openApp'
  })
}


</script>

<style scoped lang="less">
.appBox {
  .appRow {
    display: flex;
    flex-wrap: wrap;

    .appItem {
      width: 400px;
      height: 300px;
      padding: 10px;
      margin: 6px;
      background: #fff;
      border-radius: 8px;

      .logoBox {
        height: 150px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 60px;

        img {
          max-width: 100%;
          max-height: 100%;
        }
      }

      .name {
        font-size: 20px;
        text-align: center;
      }

      .introduction {
        color: #999;
        padding: 10px 0;
        text-align: center;
        height: 62px;
      }

      .btnBox {
        padding: 0 20px;
      }
    }
  }
}
</style>