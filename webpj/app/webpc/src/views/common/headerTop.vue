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
          <p>电话：020-38894803</p>
          <p>手机：135350617808</p>
          <p>QQ: 3467840</p>
        </div>
        <div class="webMenu">
          <div>首页</div>
          <div>APP定制</div>
          <div>微信开发</div>
          <div>项目案例</div>
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
        display: flex;
        justify-content: space-between;
      }

      .webMenu {
        display: flex;

      }
    }
  }

}
</style>
