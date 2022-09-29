<template>
  <div class="home">
    <van-search v-model="value"
                background="#4fc08d"
                placeholder="请输入搜索关键词" />

        dd

    <div class="storeList">
      <div v-for="(item, index) in storeList"  >
          {{item.storeName}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home.vue",

  data() {
    return {
      value:'好评',
      storeList:[
        {
          storeName:'听心一号店'
        },
        {
          storeName:'听心二号店'
        }
      ]
    }
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

</style>
