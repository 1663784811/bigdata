<template>
  <div class="winHeight">
    <div class="loginContent">
      <div class="contentBox">
        <div class="loginBox">
          <div>登录</div>
          <div class="loginRow">
            <Input  placeholder="用户名" />
          </div>
          <div class="loginRow">
            <Input  placeholder="密码" />
          </div>
          <div class="loginRow">
            <Input  placeholder="用户名" />
          </div>
          <div>
            sd
          </div>
        </div>
      </div>


    </div>
    <AccountFooter/>
  </div>
</template>

<script setup>

import AccountFooter from "./AccountFooter.vue"</script>
<style scoped lang="less">
.winHeight {
  height: 100vh;

  .loginContent {
    padding: 60px 0;
    background: #c8c8e3;

    .contentBox {
      display: flex;
      flex-direction: row-reverse;
      max-width: 1920px;
      margin: auto;
      background: #333;
      padding:0 60px;
      .loginBox {
        width: 400px;
        border-radius: 6px;
        background: #fff;
      }
    }
  }

}


</style>
