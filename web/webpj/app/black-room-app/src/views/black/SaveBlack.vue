<template>
  <s-header :name="'小黑人'"></s-header>
  <div class="fromBox">
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.title" label-align="right" label="标题" placeholder="标题"/>
      </van-cell-group>
    </div>
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.introduction" label-align="right" label="简介" placeholder="活动简介"/>
      </van-cell-group>
    </div>
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.tips" label-align="right" label="温馨提示" placeholder="温馨提示"/>
      </van-cell-group>
    </div>
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.startTime" label-align="right" label="开始时间" placeholder="开始时间"/>
      </van-cell-group>
    </div>
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.endTime" label-align="right" label="结束时间" placeholder="开始时间"/>
      </van-cell-group>
    </div>

    <div class="row cell">
      <div class="label">填写手机号</div>
      <div class="inputDiv">
        <van-radio-group shape="square" v-model="state.saveObj.writePhone">
          <van-radio class="vanRadio" :name="1">无需填写手机号</van-radio>
          <van-radio class="vanRadio" :name="2">建议填写手机号</van-radio>
          <van-radio class="vanRadio" :name="3">需填写并验证手机号</van-radio>
        </van-radio-group>
      </div>
    </div>
    <div class="row cell">
      <div class="label">指定</div>
      <div class="inputDiv">
        <van-radio-group shape="square" v-model="state.saveObj.thePeople">
          <van-radio class="vanRadio" :name="1">无指定</van-radio>
          <van-radio class="vanRadio" :name="2">只可指定人签到</van-radio>
        </van-radio-group>
      </div>
    </div>
    <div class="row cell">
      <div class="label">指定签到人</div>
      <div class="inputDiv">
        <div>
          <div class="qdBox" v-for="(item, i) in state.thePeopleObj" :key="i">
            <div class="rowLeft">
              <div class="numBox">{{ i + 1 }}</div>
              <div class="qdName">{{ item.name }}</div>
              <div class="qdPhone">{{ item.phone }}</div>
            </div>
            <div class="rowRight">
              <div class="qdBtn qdEd" @click="delThePeople(item)">
                <van-icon name="minus"/>
              </div>
            </div>
          </div>
        </div>
        <div>
          <van-button icon="plus" type="primary" size="small" @click='addPeople'>添加</van-button>
        </div>
      </div>
    </div>
    <div class="rowBtn">
      <van-button round block color="#1baeae" native-type="submit" @click="saveSignInLogFn">保存</van-button>
    </div>
  </div>

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
          <van-button round block color="#1baeae" native-type="submit" @click="addPeopleFn">添加</van-button>
        </div>
      </div>
    </div>
  </van-overlay>

</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {reactive} from "vue";
import {saveSiSignIn} from '@/service/api'
import {useRouter} from "vue-router";
import {showFailToast, showSuccessToast} from "vant";


const router = useRouter();


const state = reactive({
  saveObj: {
    title: '',
    introduction: '',
    tips: '',
    startTime: '',
    endTime: '',
    writePhone: 1,
    thePeople: 1
  },
  thePeopleObj: [],
  addPeople: {
    show: false,
    data: {
      name: '',
      phone: ''
    }
  }

})

const delThePeople = (row, index) => {
  state.thePeopleObj.splice(index, 1);
}

const addPeople = () => {
  state.addPeople.data = {
    name: '',
    phone: ''
  }
  state.addPeople.show = true;
}

const addPeopleFn = () => {
  const {data} = state.addPeople;
  state.thePeopleObj.push(data);
  state.addPeople.show = false;
}

const saveSignInLogFn = () => {
  const params = state.saveObj;
  params.signLogList = state.thePeopleObj;
  if (!params.title || params.title.trim() === '') {
    showFailToast('请填写标题')
  } else if (!params.introduction || params.introduction.trim() === '') {
    showFailToast('请填写简介')
  } else if (!params.tips || params.tips.trim() === '') {
    showFailToast('请填写温馨提示')
  } else {
    saveSiSignIn(params).then((rest) => {
      if (rest.code === 2000) {
        showSuccessToast(`${rest.msg}`);
        setTimeout(() => {
          router.replace({name: 'home'})
        }, 1000)
      } else {
        showFailToast(`${rest.msg}`)
      }
    });
  }
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

      .vanRadio {
        margin: 6px 0;
      }

      .qdBox {
        display: flex;
        align-items: center;
        justify-content: space-between;
        border-bottom: 1px solid #f5f5f5;
        margin: 4px 0;

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
            margin-left: 6px;
          }

          .qdTime {
            color: #aaa;
            margin: 0 6px;
            font-size: 10px;
          }
        }

        .rowRight {
          .qdBtn {
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
        }
      }
    }
  }

  .rowBtn {
    margin-top: 20px;
    padding: 0 40px;
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
