<template>
  <div class="menuBox">
    <div class="menuItem">
      <Icon type="md-settings" />系统设置
    </div>
    <div class="menuItem">
      <Icon type="md-settings" />系统设置
    </div>
    <div class="menuItem">
      <Icon type="md-settings" />系统设置
    </div>
    <div class="menuItem">
      <Icon type="md-settings" />系统设置
    </div>
    <div class="menuItem">
      <Icon type="md-settings" />系统设置
    </div>
    <div class="menuItem">
      <Icon type="md-settings" />系统设置
    </div>
  </div>
</template>

<script>
export default {
  name: "TopMenuBox"
}
</script>

<style scoped lang="less">
.menuBox {
  flex: 1;
  display: flex;
  align-items: center;
  .menuItem{
    color: #fff;
    padding: 0 10px;
    &:hover{
      color: #ccc;
      cursor: pointer;
    }
  }
}
</style>
