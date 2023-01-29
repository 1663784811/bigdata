<template>
  <div class="userInfo">
    <div class="userInfoItem">
      <Icon type="md-notifications" />
    </div>
    <div class="userInfoItem">
      <Icon type="md-contact" />
    </div>
  </div>
</template>

<script>
export default {
  name: "TopUserInfo"
}
</script>

<style scoped lang="less">
.userInfo{
  display: flex;
  justify-items: center;
  align-items: center;
  padding: 0 20px;
  .userInfoItem{
    font-size: 22px;
    color: #fff;
    padding: 0 16px;
    cursor: pointer;
  }
}
</style>
