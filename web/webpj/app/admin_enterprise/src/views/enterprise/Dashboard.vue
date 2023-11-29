<template>
  <div class="appBox">
    <div>应用</div>
    <div class="rowBox">
      <div class="rowItem">
        <Card>
          <template #title>
            <Icon type="ios-film-outline"></Icon>
            我的应用
          </template>
          <template #extra>
            <div>
              <Icon type="ios-film-outline"></Icon>
              添加应用
            </div>
          </template>
          <div>
            <div>共5个</div>
            <div>小黑屋</div>
            <div>记账</div>
            <div>点餐</div>
          </div>
        </Card>
      </div>
      <div class="rowItem">
        <Card>
          <template #title>
            <Icon type="ios-film-outline"></Icon>
            用户
          </template>
          <template #extra>
            <div>
              <Icon type="ios-film-outline"></Icon>
              添加应用
            </div>
          </template>
          <div>
            <div>共5个</div>
            <div>小黑屋</div>
            <div>记账</div>
            <div>点餐</div>
          </div>
        </Card>
      </div>

      <div class="rowItem">
        <Card>
          <template #title>
            <Icon type="ios-film-outline"></Icon>
            我的应用
          </template>
          <template #extra>
            <div>
              <Icon type="ios-film-outline"></Icon>
              添加应用
            </div>
          </template>
          <div>
            <div>共5个</div>
            <div>小黑屋</div>
            <div>记账</div>
            <div>点餐</div>
          </div>
        </Card>
      </div>
    </div>
  </div>

  <div>
    支付统计
  </div>

</template>
<script setup>

</script>


<style scoped lang="less">
.appBox {
  .rowBox {
    display: flex;
    background: #fff;

    .rowItem {
      width: 400px;
      margin: 0 10px;
    }
  }
}
</style>