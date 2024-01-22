<template>
  <div class="dataInfo">
    <div class="row">
      <div class="item-wrapper">
        <div class="cardHeader">
          <div class="cardLeft">
            <Icon type="ios-film-outline"></Icon>
            今日访问量
          </div>
          <div class="cardRight">
            <Icon type="ios-film-outline"></Icon>
          </div>
        </div>
        <div class="cardContent">
          <p>+1000</p>
          <p>较昨日新增：1000</p>
          <p>较上周新增：2350</p>
        </div>
      </div>
      <div class="item-wrapper">
        <div class="cardHeader">
          <div class="cardLeft">
            <Icon type="ios-film-outline"></Icon>
            新增用户
          </div>
          <div class="cardRight">
            <Icon type="ios-film-outline"></Icon>
          </div>
        </div>
        <div class="cardContent">
          <p>+1000</p>
          <p>较昨日新增：1000</p>
          <p>较上周新增：2350</p>
        </div>
      </div>
      <div class="item-wrapper">
        <div class="cardHeader">
          <div class="cardLeft">
            <Icon type="ios-film-outline"></Icon>
            当月销售额
          </div>
          <div class="cardRight">
            <Icon type="ios-film-outline"></Icon>
          </div>
        </div>
        <div class="cardContent">
          <p>￥50000</p>
          <p>较昨日新增：1000</p>
          <p>较上周新增：2350</p>
        </div>
      </div>
      <div class="item-wrapper">
        <div class="cardHeader">
          <div class="cardLeft">
            <Icon type="ios-film-outline"></Icon>
            当月订单量
          </div>
          <div class="cardRight">
            <Icon type="ios-film-outline"></Icon>
          </div>
        </div>
        <div class="cardContent">
          <p>+1000</p>
          <p>较昨日新增：1000</p>
          <p>较上周新增：2350</p>
          <p>较上周新增：2350</p>
          <p>较上周新增：2350</p>
          <p>较上周新增：2350</p>
          <p>较上周新增：2350</p>
        </div>
      </div>
    </div>
  </div>
  <div class="salesBox">
    <div class="cardHeader">
      <div class="cardLeft">
        <Icon type="ios-film-outline"></Icon>
        半年销售额分析图
      </div>
      <div class="cardRight">
        <Icon type="ios-film-outline"></Icon>
      </div>
    </div>
    <div class="cardContent">
      ssss
    </div>
  </div>

</template>

<script setup>

</script>

<style scoped lang="less">
.dataInfo {
  .row {
    display: grid;
    width: 100%;
    grid-template-columns: repeat(4, minmax(0px, 1fr));
    gap: 5px;
    margin-bottom: 5px;

    .item-wrapper {
      grid-column: span 1 / span 1;
      background: #fff;

      .cardHeader {
        padding: 5px 10px;
        display: flex;
        justify-content: space-between;
        border-bottom: 1px solid #f0f2f5;
      }

      .cardContent {
        padding: 5px 10px;
      }
    }
  }
}

.salesBox {
  width: 100%;
  background: #fff;
  margin-top: 10px;
  .cardHeader {
    padding: 5px 10px;
    display: flex;
    justify-content: space-between;
    border-bottom: 1px solid #f0f2f5;
  }

  .cardContent {
    padding: 5px 10px;
    height: 450px;
  }
}
</style>
