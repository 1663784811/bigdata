<template>
  <div class="homeTitle">
    <div class="container">
      <div>品牌价值</div>
      <div>
        <h3>不断推陈出新，为客户创造更多的品牌价值</h3>
      </div>
      <van-grid :border="false">
        <van-grid-item v-for="(item,index) in gridList" icon="photo-o" text="文字">
          <img src="" alt="">
          <div>
            <div>{{ item.text }}</div>
            <div>{{ item.note }}</div>
          </div>
        </van-grid-item>
      </van-grid>
    </div>
  </div>
</template>

<script>
export default {
  name: "IntroduceBox",
  data() {
    return {
      gridList: [
        {
          text: 'APP定制',
          note: 'APP Custom'
        },
        {
          text: 'APP定制',
          note: 'APP Custom'
        },
        {
          text: 'APP定制',
          note: 'APP Custom'
        },
        {
          text: 'APP定制',
          note: 'APP Custom'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.homeTitle {
  background: #fff;
  width: 100%;
  height: auto;
  padding: 70px 0;
  h3 {
    font-weight: normal;
    color: #2c3336;
    font-size: 24px;
    margin-top: 5px;
    text-align: center;
  }
}
</style>
