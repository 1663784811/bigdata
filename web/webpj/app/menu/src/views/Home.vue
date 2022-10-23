<template>
  <div class="home">
    <van-search v-model="value"
                background="#4fc08d"
                placeholder="请输入搜索关键词" />



    <van-grid>
      <van-grid-item v-for="value in 8" :key="value" icon="photo-o" text="文字" />
    </van-grid>


    <div class="storeList">
      <div v-for="(item, index) in storeList"  >
          <div @click="clickStore">
            {{item.storeName}}
          </div>

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
    },
    clickStore(){
      this.$router.push({
        name:'StoreHome'
      });
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
