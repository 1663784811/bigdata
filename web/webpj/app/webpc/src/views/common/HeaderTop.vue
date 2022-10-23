<template>
  <div class="header">
    <div class="container headerContainer">
      <div class="headerLeft">
        <div class="logoBox">
          <h1>程式科技</h1>
        </div>
      </div>
      <div class="headerRight">
        <div class="webMenu">
           <router-link  class="menuItem active" to="/home">首页</router-link>
          <router-link  class="menuItem" to="/project">项目案例</router-link>
           <router-link  class="menuItem" to="/appDevelopment">APP/微信开发</router-link>
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
          margin: 0 6px;
          color: #111;
          padding: 8px 26px;
          &.active{
            background: #006a93;
            padding: 8px 20px;
            border-radius: 6px;
            color: #e9e8e8;
          }
          &:hover{
            background: #006a93;
            border-radius: 6px;
            color: #e9e8e8;
          }
        }
      }
    }
  }

}
</style>
