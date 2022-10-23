<template>
  <div class="footer">
    <div class="container">
      <div class="footerWebInfo">
        <div class="">
          <h3>我们业务</h3>
          <p>APP定制</p>
          <p>系统开发</p>
          <p>微信开发</p>
        </div>
        <div class="webInfoMiddle">
          <h3>联系我们</h3>
          <p>电话：18926993229</p>
          <p>邮箱：any9877@aliyun.com</p>
          <p>地址：广东省珠海市香洲区拱北迎宾广场八座1102</p>
        </div>

        <div class="webInfoRight">
          <div class="infoText">微信公众号</div>
          <div>
            <img src="http://www.seejoys.com/Tpl/SJ_Home/conf/images/erweima.jpg" alt="">
          </div>
        </div>
      </div>
      <div class="recordInfo">
        <p>©Copyright 2018 珠海程式科技有限公司 粤ICP备19146905号</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "footer"
}
</script>

<style lang="scss" scoped>
.footer {
  min-height: 300px;
  background: #343434;
  min-width: 1200px;
  color: #f1f1f1;
  padding-bottom: 50px;

  .footerWebInfo {
    padding: 20px;
    display: flex;
    justify-content: space-between;

    h3 {
      margin: 20px 0;
    }

    p {
      margin: 20px 0;
    }

    .webInfoMiddle {

    }

    .webInfoRight {
      margin-top: 10px;
      .infoText {
        text-align: center;
        margin: 16px 0;
      }
    }
  }

  .recordInfo {
    padding: 40px;
    text-align: center;
  }
}
</style>
