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
  <router-view></router-view>
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

import {ref} from 'vue';

export default {
  name: "LayOut",
  setup() {
    const active = ref('home');
    return {active};
  },
}
</script>

<style lang="scss" scoped>
.header {
  background: #343434;
  color: #f1f1f1;
  min-width: 1200px;
  .headerContainer{
    display: flex;
    justify-content: space-between;
    .headerRight{
      .linkType{
        display: flex;
        justify-content: space-between;
      }
      .webMenu{
        display: flex;

        }
    }
  }

}

.footer {
  min-height: 300px;
  background: #343434;
  min-width: 1200px;
  color: #f1f1f1;
  .footerWebInfo{
    padding: 20px;
    display: flex;
    justify-content: space-between;
    .webInfoMiddle{

    }
    .webInfoRight{
      margin-top: 60px;
      .infoText{
        text-align: center;
        margin: 16px 0;
      }
    }
  }
  .recordInfo{
    padding: 46px 0;
    text-align: center;
  }
}

</style>
