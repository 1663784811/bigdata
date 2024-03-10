<template>
  <div class="nav-bar van-hairline--top">
    <ul class="nav-list">
      <div class="nav-list-item active" @click="goToFn('home')">
        <i class="nbicon nblvsefenkaicankaoxianban-1"></i>
        <span>点菜</span>
      </div>
      <div class="nav-list-item" @click="goToFn('order')">
        <i class="nbicon nbfenlei"></i>
        <span>账单</span>
      </div>
    </ul>
  </div>
</template>

<script setup>
import {onMounted} from 'vue'
import {useRoute, useRouter} from 'vue-router'

const router = useRouter()
const route = useRoute()

onMounted(() => {

})

const goToFn = (name) => {
  router.push({
    name,
    params: route.params,
    query: route.query
  })
}


</script>

<style lang="less" scoped>
@import '../common/style/mixin';

.nav-bar {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  padding: 5px 0;
  z-index: 1000;
  background: #fff;
  transform: translateZ(0);
  -webkit-transform: translateZ(0);
  border-top: 1px solid #eee;

  .nav-list {
    width: 100%;
    .fj();
    flex-direction: row;
    padding: 0;

    .nav-list-item {
      display: flex;
      flex: 1;
      flex-direction: column;
      text-align: center;
      color: #666;

      &.router-link-active {
        color: @primary;
      }

      i {
        text-align: center;
        font-size: 22px;
      }

      span {
        font-size: 12px;
      }

      .van-icon-shopping-cart-o {
        margin: 0 auto;
        margin-bottom: 2px;
      }
    }
  }
}
</style>
