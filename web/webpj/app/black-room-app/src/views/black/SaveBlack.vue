<template>
  <s-header :name="'小黑人'"></s-header>
  <div class="fromBox">
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.name" label-align="right" label="名称" placeholder="名称"/>
      </van-cell-group>
    </div>
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.account" label-align="right" label="账号" placeholder="账号"/>
      </van-cell-group>
    </div>
    <div class="row cell">
      <div class="label">类型</div>
      <div class="inputDiv">
        <van-radio-group shape="square" v-model="state.saveObj.type">
          <van-radio class="vanRadio" :name="1">微信</van-radio>
          <van-radio class="vanRadio" :name="2">支付宝</van-radio>
          <van-radio class="vanRadio" :name="3">QQ</van-radio>
          <van-radio class="vanRadio" :name="0">其它</van-radio>
        </van-radio-group>
      </div>
    </div>
    <div class="row" v-if="state.saveObj.type == 0">
      <van-cell-group>
        <van-field v-model="state.saveObj.otherType" label-align="right" label="其它类型名称"
                   placeholder="其它类型名称"/>
      </van-cell-group>
    </div>
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.price" type="number" label-align="right" label="RMB" placeholder="RMB"/>
      </van-cell-group>
    </div>
    <div class="row">
      <van-cell-group>
        <van-field v-model="state.saveObj.userNote" label-align="right" label="描述" placeholder="描述"/>
      </van-cell-group>
    </div>
    <div class="row cell">
      <div class="label">图片</div>
      <div class="inputDiv">
        <div>
          <div class="qdBox" v-for="(item, i) in state.thePeopleObj" :key="i">
            <div class="rowLeft">
              <div class="numBox">{{ i + 1 }}</div>
              <div class="qdName">{{ item.name }}</div>
              <div class="qdPhone">{{ item.phone }}</div>
            </div>
            <div class="rowRight">
              <div class="qdBtn qdEd">
                <van-icon name="minus"/>
              </div>
            </div>
          </div>
        </div>
        <div>
          <van-button icon="plus" type="primary" size="small" >添加</van-button>
        </div>
      </div>
    </div>
    <div class="rowBtn">
      <van-button round block color="#1baeae" native-type="submit" @click="saveSignInLogFn">保存小黑人</van-button>
    </div>
  </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {reactive} from "vue";
import {commonSave} from '@/service/api'
import {useRouter} from "vue-router";
import {showFailToast, showSuccessToast} from "vant";

const router = useRouter();

const state = reactive({
  saveObj: {
    name: '',
    account: '',
    type: 1,
    otherType: '',
    price: null,
    userNote: '',
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

const saveSignInLogFn = () => {
  const params = state.saveObj;
  if (!params.name || params.name.trim() === '') {
    showFailToast('请填名称')
  } else if (!params.account || params.account.trim() === '') {
    showFailToast('请填写对方账号')
  } else {
    commonSave({
      code: 'save_black_room',
      data: params
    }).then((rest) => {
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

const delThePeople = (row, index) => {
  state.thePeopleObj.splice(index, 1);
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
