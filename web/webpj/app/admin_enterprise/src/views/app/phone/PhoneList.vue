<template>
  <div class="phoneList">
    <div class="phoneBox">
      <div class="phoneItem" v-for="(item,index) in state.phoneList" :key="index">
        <div class="imgBox">
          <img :src="item.img">
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>

import {onMounted, reactive} from "vue";
import {getPhoneList} from '@/api/api.js'

const state = reactive({
  phoneList: []
})

onMounted(() => {

  getPhoneList({}).then((rest) => {
    const {data} = rest;
    state.phoneList = data;
    updateImage()
  })
})

const updateImage = () => {
  const {phoneList} = state;
  if (phoneList.length > 0) {
    for (let i = 0; i < phoneList.length; i++) {
      state.phoneList[i].img = 'http://127.0.0.1:8080/admin/phone/phone/phoneImage/' + state.phoneList[i].name + "?t=" + new Date().getTime()
    }
    setTimeout(updateImage, 2000)
  }
}


</script>
<style scoped lang="less">
.phoneList {
  .phoneBox {
    display: flex;

    .phoneItem {
      background: #fff;
      margin: 10px;
      width: 300px;
      min-height: 400px;
      padding: 6px;
      border-radius: 4px;
      box-shadow: 0 0 10px #ccc;

      .imgBox {
        img {
          max-width: 100%;
        }
      }
    }
  }


}
</style>