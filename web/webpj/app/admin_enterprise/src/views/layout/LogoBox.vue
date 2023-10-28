<template>
  <div class="logoBox">
    <Icon type="logo-xbox"/>
    <div class="logoText">听心的原因</div>
  </div>
</template>

<script>
export default {
  name: "LogoBox"
}
</script>

<style scoped lang="less">
.logoBox {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 40px;
  font-size: 18px;
  color: #fff;

  .logoText {
    margin-left: 10px;
  }
}
</style>
