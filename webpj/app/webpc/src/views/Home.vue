<template>
  <div class="bannerBox">
    <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item>
        <img style="width: 100%" src="http://www.seejoys.com/pics/banner/banner_s_1461295181.jpg" alt="">
      </van-swipe-item>
      <van-swipe-item>
        <img style="width: 100%" src="http://www.seejoys.com/pics/banner/banner_s_1450842050.png" alt="">
      </van-swipe-item>
      <van-swipe-item>
        <img style="width: 100%" src="http://www.seejoys.com/pics/banner/banner_s_1461307907.png" alt="">
      </van-swipe-item>
    </van-swipe>
  </div>
  <div>
    <div class="container">
      <div>品牌价值</div>
      <div>
        一起，站到互联网的最高端！
      </div>
      <div>
        <div>sesessee</div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "Home.vue",

  data() {
    return {}
  },
  setup() {

  },
  methods: {
    reload() {
    }
  }
}
</script>

<style lang="scss" scoped>
.bannerBox{
  margin: 10px 0;
}
</style>
