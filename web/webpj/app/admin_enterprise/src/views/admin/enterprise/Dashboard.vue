<template>
  <div class="appBox">
    <div class="titleBox">我的应用</div>
    <div class="appRowBox">
      <div class="rowItem" v-for="(item, index) in state.appList" :key="index">
        <div class="appLogo">
          <img :src="item.logo">
        </div>
        <div class="appTitle">{{ item.name }}</div>
      </div>

    </div>
  </div>

  <div class="dataBox">
    <div class="saleData">
      <TitleBox title="销售额"/>
      <div class="saleContent" style="height: 400px">
        <v-chart class="chart" :option="option" autoresize/>
      </div>
    </div>
    <div class="logBox">
      <TitleBox title="日志"/>
      <div class="logContent">
        ssss
      </div>
    </div>
  </div>

  <div class="userCount">
    <TitleBox title="用户统计IP"/>
    <div style="height: 400px">
      <v-chart class="chart" :option="option" autoresize/>
    </div>
  </div>

</template>
<script setup>
import TitleBox from '@/component/TitleBox.vue'
import {onMounted, reactive, ref} from "vue";
import {commonRequest, commonQuery} from '@/api/api.js'
import {loginInfo} from "@/store/loginInfo.js"

import VChart, {THEME_KEY} from 'vue-echarts';


const loginInfoSt = loginInfo();


const state = reactive({
  appList: []
})


onMounted(() => {
  loadData();
})

const loadData = () => {
  commonQuery({
    code: 'select_e_application'
  }).then((rest) => {
    const {data} = rest;
    state.appList = data;
  })

}


const option = ref({
  title: {
    text: 'Traffic Sources',
    left: 'center',
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)',
  },
  legend: {
    orient: 'vertical',
    left: 'left',
    data: ['Direct', 'Email', 'Ad Networks', 'Video Ads', 'Search Engines'],
  },
  series: [
    {
      name: 'Traffic Sources',
      type: 'pie',
      radius: '55%',
      center: ['50%', '60%'],
      data: [
        {value: 335, name: 'Direct'},
        {value: 310, name: 'Email'},
        {value: 234, name: 'Ad Networks'},
        {value: 135, name: 'Video Ads'},
        {value: 1548, name: 'Search Engines'},
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)',
        },
      },
    },
  ],
});

</script>


<style scoped lang="less">
.appBox {
  .titleBox {
    padding: 10px;
    box-shadow: 0 0 6px #bdd3f5;
    background: #fff;
    border-bottom: 1px solid #efefef;
  }

  .appRowBox {
    display: flex;
    flex-wrap: wrap;
    margin-bottom: 20px;
    background: #fff;
    padding: 10px 10px 0 10px;
    min-height: 120px;

    .rowItem {
      width: 100px;
      height: 100px;
      margin: 0 10px 10px 0;
      background: #f7f7f7;
      border-radius: 6px;

      &:hover {
        background: #d5d5d5;
        cursor: pointer;
      }

      .appLogo {
        width: 60px;
        height: 60px;
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

.userCount {
  background: #fff;
  margin-top: 10px;
  min-height: 400px;
}
</style>