<template>
  <div>
    dsdf
  </div>

  <div class="mainContent">
    <van-sidebar>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
      <van-sidebar-item title="标签名称"/>
    </van-sidebar>
    <div>
      <div>se</div>
      <div>se</div>
      <div>se</div>
      <div>se</div>
      <div>se</div>
      <div>se</div>
      <div>se</div>
      <div>se</div>
      <div>se</div>
    </div>
  </div>

  <van-action-bar>
    <van-action-bar-icon icon="chat-o" text="客服"/>
    <van-action-bar-icon icon="cart-o" text="购物车"/>
    <van-action-bar-icon icon="cart-o" text="购物车"/>
    <van-action-bar-icon icon="shop-o" text="店铺"/>
    <van-action-bar-button type="danger" text="结算" @click="clickCount"/>
  </van-action-bar>

</template>

<script>
export default {
  name: "StoreHome",
  methods: {
    clickCount() {
      console.log("sss")
      this.$router.push({
        name: 'Settlement'
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.mainContent {
  display: flex;
}
</style>
