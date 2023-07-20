<template>
  <div class="home">
    <van-search v-model="value"
                background="#4fc08d"
                placeholder="请输入搜索关键词"/>

    <div class="webBannerBox">
      <van-swipe :autoplay="3000" lazy-render>
        <van-swipe-item v-for="image in images" :key="image">
          <img :src="image"/>
        </van-swipe-item>
      </van-swipe>
    </div>


    <div>
      <van-grid>
        <van-grid-item v-for="value in 8" :key="value" icon="photo-o" text="文字"/>
      </van-grid>

    </div>


    <div class="storeList">
      <div v-for="(item, index) in storeList">
        <div @click="clickStore">
          {{ item.storeName }}
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>


import {ref} from "vue";

const images = ref([
  'https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg',
  'https://fastly.jsdelivr.net/npm/@vant/assets/apple-2.jpeg',
]);

const storeList = ref([
  {
    storeName: '听心一号店'
  },
  {
    storeName: '听心二号店'
  }
]);

const clickStore = () => {

}

</script>

<style lang="scss" scoped>
.webBannerBox {
  height: 250px;
  overflow: hidden;
  img{
    max-height: 100%;
    max-width: 100%;
  }
}
</style>
