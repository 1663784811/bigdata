<template>
  <div class="header">
    <div class="container headerContainer">
      <div class="headerLeft">
        <div class="logoBox">
          <h1>程式科技</h1>
        </div>
      </div>
      <div class="headerRight">
        <div class="linkType" style="display: none">
          <div>
            电话：020-38894803
          </div>
          <div>
            手机：135350617808
          </div>
          <div>
            QQ: 3467840
          </div>
        </div>
        <div class="webMenu">
           <router-link  class="menuItem" to="/home">首页</router-link>
          <router-link  class="menuItem" to="/project">项目案例</router-link>
           <router-link  class="menuItem" to="/appDevelopment">APP定制</router-link>
           <router-link  class="menuItem" to="/wechat">微信开发</router-link>
           <router-link  class="menuItem" to="/about">关于我们</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "headerTop"
}
</script>

<style lang="scss" scoped>
.header {
  color: #111;
  min-width: 1200px;

  .headerContainer {
    display: flex;
    justify-content: space-between;
    .logoBox{
      h1{
        padding: 40px 0;
        font-size: 32px;
      }
    }
    .headerRight {
      display: flex;
      align-items: center;
      .linkType {
        padding-top: 10px;
        display: flex;
        justify-content: space-between;
      }

      .webMenu {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        .menuItem {
          font-size: 16px;
          margin-left: 10px;
          color: #111;
          padding: 6px 6px;
        }
      }
    }
  }

}
</style>
