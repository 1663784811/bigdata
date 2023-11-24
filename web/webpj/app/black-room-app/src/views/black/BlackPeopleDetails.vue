<template>
  <s-header :name="'小黑人'"></s-header>
  <div class="fromBox" v-if="state.dataObj && !state.reqParameter.loading">
    <div class="row cell">
      <div class="label">名称</div>
      <div class="inputDiv">{{ state.dataObj.name }}</div>
    </div>
    <div class="row cell">
      <div class="label">账号</div>
      <div class="inputDiv">{{ state.dataObj.account }}</div>
    </div>
    <div class="row cell">
      <div class="label">类型</div>
      <div class="inputDiv">{{ state.dataObj.type == 1 ? '微信' : state.dataObj.type == 2 ? '支付宝' : '其它' }}</div>
    </div>
    <div class="row cell">
      <div class="label">其它名称</div>
      <div class="inputDiv">{{ state.dataObj.otherType }}</div>
    </div>
    <div class="row cell">
      <div class="label">RMB</div>
      <div class="inputDiv">{{ state.dataObj.price <= 0 ? '' : state.dataObj.price }}</div>
    </div>
    <div class="row cell">
      <div class="label">描述</div>
      <div class="inputDiv">{{ state.dataObj.userNote }}</div>
    </div>
    <div class="row imgBox">
      <div><img
          src="https://imgcps.jd.com/img-cubic/creative_server_cia_jdcloud/v2/2000366/10028948744257/FocusFullshop/CkNqZnMvdDEvOTc3OTgvMjQvNDM2NDcvMTk4NDUyLzY1NGQzNDRlRmVkM2MzZmJmL2Q5M2U1OTEzNTc3YWMyZTkucG5nEgk1LXR5XzBfNTYwAjjui3pCEwoP5pif5be05YWL5ZKW5ZWhEAFCEAoM5Yqy54iG5ZWG5ZOBEAJCEAoM56uL5Y2z5oqi6LStEAZCCgoG5LyY6YCJEAdYwdC13_CjAg/cr/s/q.jpg"
          alt=""></div>
      <div><img
          src="https://imgcps.jd.com/img-cubic/creative_server_cia_jdcloud/v2/2000366/10028948744257/FocusFullshop/CkNqZnMvdDEvOTc3OTgvMjQvNDM2NDcvMTk4NDUyLzY1NGQzNDRlRmVkM2MzZmJmL2Q5M2U1OTEzNTc3YWMyZTkucG5nEgk1LXR5XzBfNTYwAjjui3pCEwoP5pif5be05YWL5ZKW5ZWhEAFCEAoM5Yqy54iG5ZWG5ZOBEAJCEAoM56uL5Y2z5oqi6LStEAZCCgoG5LyY6YCJEAdYwdC13_CjAg/cr/s/q.jpg"
          alt=""></div>
      <div><img
          src="https://imgcps.jd.com/img-cubic/creative_server_cia_jdcloud/v2/2000366/10028948744257/FocusFullshop/CkNqZnMvdDEvOTc3OTgvMjQvNDM2NDcvMTk4NDUyLzY1NGQzNDRlRmVkM2MzZmJmL2Q5M2U1OTEzNTc3YWMyZTkucG5nEgk1LXR5XzBfNTYwAjjui3pCEwoP5pif5be05YWL5ZKW5ZWhEAFCEAoM5Yqy54iG5ZWG5ZOBEAJCEAoM56uL5Y2z5oqi6LStEAZCCgoG5LyY6YCJEAdYwdC13_CjAg/cr/s/q.jpg"
          alt=""></div>
      <div><img
          src="https://imgcps.jd.com/img-cubic/creative_server_cia_jdcloud/v2/2000366/10028948744257/FocusFullshop/CkNqZnMvdDEvOTc3OTgvMjQvNDM2NDcvMTk4NDUyLzY1NGQzNDRlRmVkM2MzZmJmL2Q5M2U1OTEzNTc3YWMyZTkucG5nEgk1LXR5XzBfNTYwAjjui3pCEwoP5pif5be05YWL5ZKW5ZWhEAFCEAoM5Yqy54iG5ZWG5ZOBEAJCEAoM56uL5Y2z5oqi6LStEAZCCgoG5LyY6YCJEAdYwdC13_CjAg/cr/s/q.jpg"
          alt=""></div>
    </div>

  </div>
  <div v-else-if="state.reqParameter.loading"></div>
  <van-empty v-else description="没有数据"/>


</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {onMounted, reactive} from "vue";
import {commonQuery} from '@/service/api'
import {useRoute} from "vue-router";
import {showFailToast, showSuccessToast} from "vant";


const route = useRoute();

const state = reactive({
  dataObj: {
    name: '',
    account: '',
    type: 0,
    otherType: '',
    price: -1,
    userNote: ''
  },
  reqParameter: {
    code: 'select_black_room',
    tid: '',
  },
  loading: true
})

onMounted(() => {
  state.reqParameter.tid = route.query.id
  if (state.reqParameter.tid && state.reqParameter.tid.length > 0) {
    loadData();
  } else {
    state.dataObj = null
    state.loading = false
  }
})


const loadData = () => {
  state.loading = true
  commonQuery(state.reqParameter).then((rest) => {
    const {data} = rest
    if (data.length > 0) {
      state.dataObj = data[0]
    } else {
      state.dataObj = null
    }
    state.loading = false
  })

}

</script>

<style scoped lang="less">
.fromBox {
  padding-bottom: 100px;

  .cell {
    position: relative;
    display: flex;
    padding: var(--van-cell-vertical-padding) var(--van-cell-horizontal-padding);
    border-bottom: 1px solid #ebedf0;

    .label {
      flex: none;
      text-align: right;
      width: var(--van-field-label-width);
      margin-right: var(--van-field-label-margin-right);
      color: var(--van-field-label-color);
    }
  }

  .row {
    .inputDiv {
      flex: 1;
    }
  }

  .imgBox {
    padding: 10px;

    img {
      max-width: 100%;
    }

    & > div {
      display: flex;
    }
  }
}
</style>
