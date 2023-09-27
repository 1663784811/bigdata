<template>
  <div class="qdPageBox">
    <s-header :name="state.signInObj.title || '签到'"></s-header>
    <div class="qdContainer" v-if="state.showPage == 'qd'">
      <div class="qdHeader">
        <div class="itemTitle">{{ state.signInObj.title || '' }}</div>
        <div class="qdTime">{{ state.signInObj.startTime }}</div>
      </div>
      <div class="inputBox">
        <div class="inputRow">
          <input v-model="state.signLog.name" placeholder="姓名"/>
        </div>
        <div class="inputRow">
          <input v-model="state.signLog.phone" placeholder="手机号"/>
        </div>
        <div class="inputRow">
          <van-button round block color="#1baeae" @click="qdSaveFn" :loading="state.loading">签到</van-button>
        </div>
      </div>
      <div class="noteBox">
        <div>温馨提示：</div>
        <div class="noteContent">1.谈起阿胶无人不晓，它是以马科动物驴的皮经煎煮、浓缩制成的固体胶</div>
      </div>
    </div>
    <div v-else-if="state.showPage == 'success'">
      签到成功
    </div>
    <div v-else>
      加载中...
    </div>
    <div class="headImage">
      sss
    </div>
    <div class="bottomImage">
      sss
    </div>
  </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {onMounted, reactive} from "vue";
import {findIdSiSignIn, signInLogSave} from "@/service/api";
import {useRoute} from "vue-router";
import {showFailToast} from "vant";

const route = useRoute();

const state = reactive({
  signInObj: {},
  signLog: {
    name: '',
    phone: ''
  },
  loading: false,
  showPage: ''
})

onMounted(async () => {
  const {id} = route.query;
  state.signLog.signInId = id;
  const {data} = await findIdSiSignIn({tid: id});
  state.signInObj = data;
  state.showPage = 'qd';
})

const qdSaveFn = async () => {
  state.loading = true;
  const {data, msg} = await signInLogSave(state.signLog);
  state.loading = false;
  if (data) {
    showFailToast('签到成功')
    setTimeout(() => {
      state.showPage = 'success';
    }, 1000)
  } else {
    showFailToast(`${msg || '错误'}`)
  }
}


</script>

<style scoped lang="less">
.qdPageBox {
  min-height: 100vh;
  position: relative;
  padding-bottom: 200px;

  .qdContainer {
    margin-top: 30px;
    padding: 10px 20px;

    .qdHeader {
      .itemTitle {
        font-size: 16px;
        text-align: center;
      }

      .qdTime {
        text-align: center;
        margin-top: 10px;
        color: #999;
        font-size: 12px;
      }
    }

    .inputBox {
      margin: 20px 0;

      .inputRow {
        padding: 14px 50px;
        display: flex;
        justify-content: center;
        align-items: center;

        input {
          width: 100%;
          font-size: 16px;
          padding: 10px 20px;
          border-radius: 6px;
          border: 1px solid #ddd;
          color: #999;
        }
      }
    }

    .noteBox {
      background: #efefef;
      padding: 10px;
      border-radius: 4px;
      color: #1baeae;

      .noteContent {
        padding: 10px 20px;
      }
    }
  }

  .headImage {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 400px;
    z-index: -99;
    background: #ccc;
  }

  .bottomImage {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    height: 180px;
  }
}

</style>
