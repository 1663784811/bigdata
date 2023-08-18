<template>
  <div class="swipeBox">
    <van-swipe class="my-swipe" :autoplay="5000" indicator-color="#1baeae">
      <van-swipe-item v-for="(item, index) in list" :key="index">
        <img :src="item.img" alt="" @click="goTo(item.url)">
      </van-swipe-item>
    </van-swipe>
  </div>
</template>

<script>
export default {
  props: {
    list: Array
  },
  methods: {
    goTo(url) {
      window.open(url)
    }
  }
}
</script>

<style lang='less' scoped>
.swipeBox {
  min-height: 50px;
  .my-swipe {
    img {
      width: 100%;
      height: 100%;
    }
  }
}

</style>
