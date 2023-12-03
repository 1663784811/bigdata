<template>
  <div class="appBox">
    <div class="titleBox">我的应用</div>
    <div class="appRowBox">
      <div class="rowItem">
        ss
      </div>
      <div class="rowItem">
        ddd
      </div>
      <div class="rowItem">
        ssss
      </div>
    </div>
  </div>

  <div class="dataBox">
    <div class="saleData">
      <div class="titleBox">销售额</div>
      <div class="saleContent">
        ssss
      </div>
    </div>
    <div class="logBox">
      <div class="titleBox">日志</div>
      <div class="logContent">

        ssss
      </div>
    </div>


  </div>

  <div>
    用户统计IP
  </div>

</template>
<script setup>

</script>


<style scoped lang="less">
.appBox {
  .titleBox {
    margin-bottom: 10px;
    padding: 10px;
    box-shadow: 0 0 6px #bdd3f5;
    background: #fff;
  }

  .appRowBox {
    display: flex;
    margin-bottom: 20px;
    background: #fff;

    .rowItem {
      width: 200px;
      height: 160px;
      margin: 0 10px;
    }
  }
}

.dataBox {
  display: flex;
  height: 400px;

  .titleBox {
    padding: 10px;
    border-bottom: 1px solid #efefef;
    background: #fff;
  }

  .saleData {
    flex: 1;
    background: #fff;
  }

  .logBox {
    background: #fff;
    width: 600px;
    margin-left: 10px;

    .logContent {
      padding: 10px;

    }
  }
}
</style>