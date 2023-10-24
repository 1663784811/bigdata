<template>
  <div class="detailsBox">
    <s-header
        :name="state.restData.title || '详情'"
        @clickMenu="clickMenu"
    />
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
          <div class="qdBtn" v-if="item.status != 2" @click="otherSign(item)">帮签</div>
          <div class="qdBtn qdEd" v-if="item.status == 2">已签</div>
        </div>
      </div>
      <div v-else>
        <van-empty description="空空如也"/>
      </div>
    </div>
  </div>

  <van-share-sheet v-model:show="state.shareObj.show" title="" :options="state.shareObj.options"/>

  <van-overlay :show="state.addPeople.show">
    <div class="wrapper" @click="state.addPeople.show = false">
      <div class="block" @click.stop>
        <van-cell-group>
          <van-field v-model="state.addPeople.data.name" label-align="right" label="姓名" placeholder="姓名"/>
        </van-cell-group>
        <van-cell-group>
          <van-field v-model="state.addPeople.data.phone" label-align="right" label="手机号" placeholder="手机号"/>
        </van-cell-group>
        <div class="savePeople">
          <van-button round block color="#1baeae" native-type="submit" @click="Fn">帮签</van-button>
        </div>
      </div>
    </div>
  </van-overlay>


</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import VChart, {THEME_KEY} from "vue-echarts";
import {onMounted, reactive, computed} from "vue";
import {useRouter, useRoute} from 'vue-router'
import {findIdSiSignIn} from '@/service/api'
import {showConfirmDialog} from 'vant';

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
  shareObj:{
    show: true,
    options: [
      [
        { name: '帮签', icon: 'wechat' },
      ]
    ]
  },
  addPeople: {
    show: true,
    data: {
      name: '',
      phone: ''
    }
  }
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
  const data = state.restData
  if (data && data.signLogList && data.signLogList.length > 0) {
    if (state.selectType == 'qdAll') {
      return data.signLogList
    } else if (state.selectType == 'qdEd') {
      const arr = [];
      for (let i = 0; i < data.signLogList.length; i++) {
        const obj = data.signLogList[i];
        if (obj.status == 2) {
          console.log(obj)
          arr.push(obj)
        }
      }
      return arr;
    } else if (state.selectType == 'qdIng') {
      const arr = [];
      for (let i = 0; i < data.signLogList.length; i++) {
        const obj = data.signLogList[i];
        if (obj.status != 2) {
          console.log(obj)
          arr.push(obj)
        }
      }
      return arr;
    }
  }
  return []
});
const qdAll = computed(() => {
  const data = state.restData
  if (data && data.signLogList && data.signLogList.length > 0) {
    return data.signLogList.length;
  }
  return 0;
})

const qdEd = computed(() => {
  const data = state.restData
  if (data && data.signLogList && data.signLogList.length > 0) {
    const arr = [];
    for (let i = 0; i < data.signLogList.length; i++) {
      const obj = data.signLogList[i];
      if (obj.status == 2) {
        console.log(obj)
        arr.push(obj)
      }
    }
    return arr.length;
  }
  return 0;
})

const qdIng = computed(() => {
  const data = state.restData
  if (data && data.signLogList && data.signLogList.length > 0) {
    const arr = [];
    for (let i = 0; i < data.signLogList.length; i++) {
      const obj = data.signLogList[i];
      if (obj.status != 2) {
        console.log(obj)
        arr.push(obj)
      }
    }
    return arr.length;
  }
  return 0;
})

/**
 * 帮签
 */
const otherSign = (item) => {
  console.log(item)
  state.addPeople.show=true;
}

const clickMenu = () => {
  console.log('======================')
}

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
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;

  .block {
    border-radius: 8px;
    padding: 20px 0;
    background-color: #fff;

    .savePeople {
      margin: 30px 20px 10px 20px;
    }
  }
}
</style>
