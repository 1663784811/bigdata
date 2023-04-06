<template>
  <div class="userInfo">
    <div class="userInfoItem">
      <Icon type="md-notifications"/>
      <div class="infoBox">
        dddddssssss
      </div>
    </div>
    <div class="userInfoItem">
      <Icon type="md-contact"/>
      <div class="infoBox">
        iniiiiiiiiiiiiiiii
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TopUserInfo"
}
</script>

<style scoped lang="less">
.userInfo {
  display: flex;
  justify-items: center;
  align-items: center;
  padding: 0 20px;

  .userInfoItem {
    font-size: 22px;
    color: #fff;
    padding: 0 16px;
    height: 100%;
    display: flex;
    align-items: center;
    margin: 0 2px;
    position: relative;

    &:hover {
      background: #0a1327;
      .infoBox{
        display: block;
      }
    }

    .infoBox {
      position: absolute;
      right: 0;
      top: 46px;
      background: #fff;
      border: 1px solid #000;
      width: 200px;
      height: 300px;
      z-index: 999;
      display: none;
    }
  }
}
</style>
