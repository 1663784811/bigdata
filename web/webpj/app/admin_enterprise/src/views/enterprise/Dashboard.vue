<template>
  <div class="appBox">
    <div class="titleBox">我的应用</div>
    <div class="appRowBox">
      <div class="rowItem" v-for="item in 10">
        <div class="appLogo">
          <img
              src="https://img10.360buyimg.com/seckillcms/s280x280_jfs/t1/115175/20/31509/35660/63abdd50F6828629c/5ede778905042c6a.jpg.avif">
        </div>
        <div class="appTitle">
          小黑屋
        </div>
      </div>
      <div class="rowItem">
        <div class="appLogo">
          <img
              src="https://img10.360buyimg.com/seckillcms/s280x280_jfs/t1/115175/20/31509/35660/63abdd50F6828629c/5ede778905042c6a.jpg.avif">
        </div>
        <div class="appTitle">
          小黑屋
        </div>
      </div>
      <div class="rowItem">
        <div class="appLogo">
          <img
              src="https://img10.360buyimg.com/seckillcms/s280x280_jfs/t1/115175/20/31509/35660/63abdd50F6828629c/5ede778905042c6a.jpg.avif">
        </div>
        <div class="appTitle">
          小黑屋
        </div>
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
import {onMounted} from "vue";
import {commonRequest} from '@/api/api.js'


onMounted(() => {


})


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
    flex-wrap: wrap;
    margin-bottom: 20px;
    background: #fff;
    padding: 10px 10px 0 10px;

    .rowItem {
      width: 160px;
      height: 160px;
      margin: 0 10px 10px 0;
      background: #f7f7f7;

      &:hover {
        background: #d5d5d5;
        cursor: pointer;
      }

      .appLogo {
        width: 120px;
        height: 120px;
        margin: 10px auto auto;

        img {
          max-width: 100%;
        }
      }

      .appTitle {
        text-align: center;
        overflow: hidden;

      }
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