<template>
  <div class="header">
    <div class="container headerContainer">
      <div class="headerLeft">
        <div class="logoBox">
          <h1>程式科技</h1>
        </div>
      </div>

      <div class="headerRight">
        <div class="linkType">
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
          <div class="menuItem">首页</div>
          <div class="menuItem">APP定制</div>
          <div class="menuItem">微信开发</div>
          <div class="menuItem">项目案例</div>
          <div class="menuItem">关于我们</div>
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
  background: #343434;
  color: #f1f1f1;
  min-width: 1200px;

  .headerContainer {
    display: flex;
    justify-content: space-between;

    .headerRight {
      .linkType {
        padding-top: 10px;
        display: flex;
        justify-content: space-between;
      }

      .webMenu {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        padding: 20px 0;
        .menuItem {
          font-size: 16px;
          margin-left: 20px;
        }
      }
    }
  }

}
</style>
