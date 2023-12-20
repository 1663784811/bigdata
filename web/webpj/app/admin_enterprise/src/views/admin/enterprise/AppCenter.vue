<template>
  <div class="appBox">
    <div class="appRow">
      <div class="appItem">
        <div class="logoBox">
          <Icon type="ios-alert"/>
        </div>
        <div class="name">小黑屋</div>
        <div class="introduction">
          记录账号信息、失信人等
        </div>
        <div class="btnBox">
          <Button type="success" long shape="circle" icon="md-locate" @click="openApp('')">开通</Button>
        </div>
      </div>
      <div class="appItem">
        <div class="logoBox">
          <Icon type="ios-alert"/>
        </div>
        <div class="name">H5商城</div>
        <div class="introduction">
          成熟稳定的私有化电商系统，可满足全场景和垂直行业的个性化需求,CRM精准营销
        </div>
        <div class="btnBox">
          <Button type="success" long shape="circle" icon="md-locate">开通</Button>
        </div>
      </div>
      <div class="appItem">
        <div class="logoBox">
          <Icon type="ios-alert"/>
        </div>
        <div class="name">点餐系统</div>
        <div class="introduction">
          扫码点单、计费结账、出菜上菜、菜品管理、菜单管理、账务管理、经营分析，全方位一步到位
        </div>
        <div class="btnBox">
          <Button type="success" long shape="circle" icon="md-locate">开通</Button>
        </div>
      </div>
      <div class="appItem">
        <div class="logoBox">
          <Icon type="ios-alert"/>
        </div>
        <div class="name">记账系统</div>
        <div class="introduction">
          支持创建多个账目，生活工作账目分离，让账目一目了然,支持自定义设置不同风格的类目，适合各类人群
        </div>
        <div class="btnBox">
          <Button type="success" long shape="circle" icon="md-locate">开通</Button>
        </div>
      </div>
      <div class="appItem">
        <div class="logoBox">
          <Icon type="ios-alert"/>
        </div>
        <div class="name">手机管理</div>
        <div class="introduction">
          手机管理
        </div>
        <div class="btnBox">
          <Button type="success" long shape="circle" icon="md-locate">开通</Button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {onMounted} from "vue";


onMounted(() => {


})

const openApp = (appName) => {
  console.log(appName)


}


</script>

<style scoped lang="less">
.appBox {
  .appRow {
    display: flex;
    flex-wrap: wrap;

    .appItem {
      width: 400px;
      height: 300px;
      padding: 10px;
      margin: 6px;
      background: #fff;

      .logoBox {
        height: 150px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 60px;
      }

      .name {
        font-size: 20px;
        text-align: center;
      }

      .introduction {
        color: #999;
        padding: 10px 0;
        text-align: center;
        height: 62px;
      }

      .btnBox {
        padding: 0 20px;
      }
    }
  }
}
</style>