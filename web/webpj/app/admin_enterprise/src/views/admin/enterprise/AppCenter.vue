<template>
  <div class="appBox">
    <div class="appRow">
      <div class="appItem" v-for="(item, index) in state.appList">
        <div class="logoBox">
          <img :src="item.logo">
        </div>
        <div class="name">{{ item.name }}</div>
        <div class="introduction">{{ item.introduction }}</div>
        <div class="btnBox">
          <Button type="success" long shape="circle" icon="md-locate" @click="openApp('')">开通</Button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {onMounted, reactive} from "vue";
import {commonRequest} from '@/api/api.js'
import {loginInfo} from "@/store/loginInfo.js";

const loginInfoSt = loginInfo();


const state = reactive({
  appList: [
    {
      name: '小黑屋',
      introduction: '记录账号信息、失信人等',
      logo: 'https://imgcps.jd.com/ling-cubic/ling4/lab/amZzL3QxLzI3NjQ3LzM5LzE4MTA4LzI2MDI2LzYzMDQ4MDIyRThmZjcxZjNlLzE0Y2ViZDc0MTkyODcyMTgucG5n/5Lqs6YCJ5aW96LSn/5L2g5YC85b6X5oul5pyJ/1635187874214178818/cr/s/q.jpg'
    },
    {
      name: 'H5商城',
      introduction: '成熟稳定的私有化电商系统，可满足全场景和垂直行业的个性化需求,CRM精准营销',
      logo: 'https://imgcps.jd.com/ling-cubic/ling4/lab/amZzL3QxLzI3NjQ3LzM5LzE4MTA4LzI2MDI2LzYzMDQ4MDIyRThmZjcxZjNlLzE0Y2ViZDc0MTkyODcyMTgucG5n/5Lqs6YCJ5aW96LSn/5L2g5YC85b6X5oul5pyJ/1635187874214178818/cr/s/q.jpg'
    },
    {
      name: '点餐系统',
      introduction: '扫码点单、计费结账、出菜上菜、菜品管理、菜单管理、账务管理、经营分析，全方位一步到位',
      logo: 'https://imgcps.jd.com/ling-cubic/ling4/lab/amZzL3QxLzI3NjQ3LzM5LzE4MTA4LzI2MDI2LzYzMDQ4MDIyRThmZjcxZjNlLzE0Y2ViZDc0MTkyODcyMTgucG5n/5Lqs6YCJ5aW96LSn/5L2g5YC85b6X5oul5pyJ/1635187874214178818/cr/s/q.jpg'
    },
    {
      name: '记账系统',
      introduction: '支持创建多个账目，生活工作账目分离，让账目一目了然,支持自定义设置不同风格的类目，适合各类人群',
      logo: 'https://imgcps.jd.com/ling-cubic/ling4/lab/amZzL3QxLzI3NjQ3LzM5LzE4MTA4LzI2MDI2LzYzMDQ4MDIyRThmZjcxZjNlLzE0Y2ViZDc0MTkyODcyMTgucG5n/5Lqs6YCJ5aW96LSn/5L2g5YC85b6X5oul5pyJ/1635187874214178818/cr/s/q.jpg'
    },
    {
      name: '手机管理',
      introduction: '手机管理',
      logo: 'https://imgcps.jd.com/ling-cubic/ling4/lab/amZzL3QxLzI3NjQ3LzM5LzE4MTA4LzI2MDI2LzYzMDQ4MDIyRThmZjcxZjNlLzE0Y2ViZDc0MTkyODcyMTgucG5n/5Lqs6YCJ5aW96LSn/5L2g5YC85b6X5oul5pyJ/1635187874214178818/cr/s/q.jpg'
    }
  ]
})


onMounted(() => {
  commonRequest(loginInfoSt.reLoadUrl("/admin/${eCode}/common/query"), {
    code: 'select_e_product_center'
  }).then((rest) => {
    const {data} = rest;
    state.appList = data;
  })

})


const loadData = () => {


}

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

        img {
          max-width: 100%;
          max-height: 100%;
        }
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