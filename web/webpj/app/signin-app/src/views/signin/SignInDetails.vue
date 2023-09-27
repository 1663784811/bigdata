<template>
  <div class="detailsBox">
    <s-header :name="state.restData.title || '详情'"></s-header>
    <v-chart class="chart" :option="state.option"/>
    <div class="statusBox">
      <div class="statusItem" :class="{active: state.selectType==='qdAll'}" @click="selectTypFn('qdAll')">全部
        ({{ qdAll }}人)
      </div>
      <div class="statusItem" :class="{active: state.selectType==='qdEd'}" @click="selectTypFn('qdEd')">已签到
        ({{ qdEd }}人)
      </div>
      <div class="statusItem" :class="{active: state.selectType==='qdIng'}" @click="selectTypFn('qdIng')">未签到
        ({{ qdIng }}人)
      </div>
    </div>
    <div class="rowBox">
      <div v-if="signList.length>0" class="row" v-for="(item, index) in signList" :key=index>
        <div class="rowLeft">
          <div class="numBox">{{ index + 1 }}</div>
          <div class="qdName">{{ item.name || '**--**' }}</div>
          <div class="qdTime">{{ item.time }}</div>
          <div class="qdPhone">{{ item.phone }}</div>
        </div>
        <div class="rowRight">
          <div class="qdBtn">帮签</div>
          <div class="qdBtn qdEd">已签</div>
        </div>
      </div>
      <div v-else>
        <van-empty description="空空如也" />
      </div>
    </div>
  </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import VChart, {THEME_KEY} from "vue-echarts";
import {onMounted, reactive, computed} from "vue";
import {useRouter, useRoute} from 'vue-router'
import {findIdSiSignIn} from '@/service/api'

const route = useRoute();


const state = reactive({
  option: {
    title: {
      text: "签到详情",
      left: "center"
    },
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
      orient: "vertical",
      left: "left",
      data: ["已签到", "未签到"]
    },
    series: [
      {
        name: "签到详情",
        type: "pie",
        radius: "50%",
        center: ["50%", "50%"],
        data: [
          {value: 335, name: "已签到"},
          {value: 310, name: "未签到"}
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(255, 255, 255)"
          }
        }
      }
    ]
  },
  restData: {},
  selectType: 'qdAll',
})


onMounted(async () => {
  const {id} = route.query;
  const {data} = await findIdSiSignIn({tid: id});
  state.restData = data;
})

const selectTypFn = (type) => {
  state.selectType = type;
}

const signList = computed(() => {
  return []
});
const qdAll = computed(() => {
  return 0;
})

const qdEd = computed(() => {
  return 0;
})

const qdIng = computed(() => {
  return 0;
})

</script>

<style scoped lang="less">


.detailsBox {
  background: #f6f6f6;
  min-height: 100vh;

  .chart {
    height: 200px;
  }

  .statusBox {
    display: flex;
    justify-content: space-around;
    margin: 10px 0;

    background: #fff;
    box-shadow: 0 0 4px #e5e5e5;

    .statusItem {
      font-size: 12px;
      width: 33.3333%;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 10px 0;

      &.active {
        background: #1baeae;
        color: #fff;
        border-radius: 2px;
      }
    }
  }

  .rowBox {
    background: #fff;

    .row {
      padding: 10px;
      display: flex;
      align-items: center;
      justify-content: space-between;
      border-bottom: 1px solid #f5f5f5;

      .rowLeft {
        display: flex;
        align-items: center;

        .numBox {
          margin-right: 6px;
          min-width: 26px;
          height: 20px;
          display: flex;
          align-items: center;
          justify-content: center;
          border-radius: 2px;
          background: #1baeae;
          color: #fff;
        }

        .qdName {
          font-size: 12px;
        }

        .qdPhone {
          color: #aaa;
          font-size: 10px;
        }

        .qdTime {
          color: #aaa;
          margin: 0 6px;
          font-size: 10px;
        }
      }

      .rowRight {
        display: flex;

        .qdBtn {
          background: #1baeae;
          color: #fff;
          padding: 2px 6px;
          margin: 0 2px;
          border-radius: 50px;
          font-size: 10px;
          width: 40px;
          text-align: center;
        }

        .qdEd {
          background: #ddd;
        }

      }

    }
  }

}

</style>
