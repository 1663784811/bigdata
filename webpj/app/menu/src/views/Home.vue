<template>
  <div>
    sesesssssss

    jj

    <van-button type="primary">主要按钮</van-button>

    <van-dropdown-menu>
      <van-dropdown-item v-model="value1" :options="option1" />
      <van-dropdown-item v-model="value2" :options="option2" />
    </van-dropdown-menu>


  </div>
</template>

<script>
export default {
  name: "Home.vue",
  setup() {
    const value1 = 1;
    const value2 = 'a' ;
    const option1 = [
      { text: '全部商品', value: 0 },
      { text: '新款商品', value: 1 },
      { text: '活动商品', value: 2 },
    ];
    const option2 = [
      { text: '默认排序', value: 'a' },
      { text: '好评排序', value: 'b' },
      { text: '销量排序', value: 'c' },
    ];

    return {
      value1,
      value2,
      option1,
      option2,
    };
  },
}
</script>

<style scoped>

</style>
