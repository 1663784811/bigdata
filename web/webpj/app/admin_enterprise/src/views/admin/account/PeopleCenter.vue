<template>
  <div class="peopleCenter">
    <div class="userInfo">
      <div class="faceBox" @click="updateFaceFn">
        <img src=""/>
      </div>
      <div class="updatePwdBox">
        <div class="rowTitle">
          修改密码
        </div>
        <div class="row">
          <div class="rowLabel">原密码:</div>
          <div class="rowVal">
            <Input clearable/>
          </div>
        </div>
        <div class="row">
          <div class="rowLabel">新密码:</div>
          <div class="rowVal">
            <Input clearable icon="ios-clock-outline"/>
          </div>
        </div>
        <div class="row">
          <div class="rowLabel">确认密码:</div>
          <div class="rowVal">
            <Input clearable/>
          </div>
        </div>
        <div class="row">
          <div class="rowVal">
            <Button class="dataBtn" type="success" icon="ios-create" @click="updatePwdFn">修改密码</Button>
          </div>
        </div>
      </div>

    </div>
    <div class="baseInfo">
      <div class="rowTitle">
        基本资料
      </div>
      <div class="infoBox">
        <div class="infoRow">
          <div class="rowLabel">昵称:</div>
          <div class="rowVal">
            <Input v-model="state.userData.nickName" clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">真实姓名:</div>
          <div class="rowVal">
            <Input v-model="state.userData.trueName" clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">性别:</div>
          <div class="rowVal">
            <Select v-model="state.userData.sex">
              <Option value="0" key="0">保密</Option>
              <Option value="1" key="1">男</Option>
              <Option value="2" key="2">女</Option>
            </Select>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">身份证号:</div>
          <div class="rowVal ivu-form-item-error">
            <Input v-model="state.userData.idCar" clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">手机号:</div>
          <div class="rowVal">
            <Input v-model="state.userData.sex" clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">地址:</div>
          <div class="rowVal">
            <Input v-model="state.userData.address" clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">个性签名:</div>
          <div class="rowVal">
            <Input v-model="state.userData.personalSignature" clearable type="textarea"/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowVal">
            <Button class="dataBtn" type="success"
                    icon="ios-create"
                    :loading="state.loadingInfo"
                    @click="updateBaseInfo">修改基本资料
            </Button>
          </div>
        </div>
      </div>
      <div class="rowTitle">
        操作日志:
      </div>
      <div class="logBox">
        <div class="logLi" v-for="(item, index) in state.log.data ">【{{ item.createTime }}】 {{ item.log }}</div>
      </div>
      <div class="pageBox" v-if="state.log.data.length>0 || state.log.result.total>0">
        <Page :total="state.log.result.total" @on-change="onChange"/>
      </div>
      <div class="pageBox" v-else>
        <div>没有数据</div>
      </div>
    </div>

  </div>
</template>
<script setup>

import {userInfo, commonQuery} from '@/api/api.js'
import {onMounted, reactive} from "vue";
import {useUploadFileStore} from "@/store/uploadFile";

const fileStore = useUploadFileStore();


const state = reactive({
  loadingInfo: false,
  userData: {
    account: null,
    address: null,
    canLoginTime: null,
    createTime: null,
    email: null,
    enterpriseCode: null,
    id: null,
    idCar: null,
    ip: null,
    lastLoginTime: null,
    nickName: null,
    note: null,
    personalSignature: null,
    phone: null,
    salt: null,
    sex: null,
    status: null,
    trueName: null
  },
  log: {
    data: [],
    result: {
      page: 1,
      size: 30,
      total: 100
    }
  }
})


onMounted(() => {

  userInfo().then((res) => {
    state.userData = res.data
  })
  querySysLog();

})

const updateFaceFn = () => {
  fileStore.uploadFile.show = true;
}

const updatePwdFn = () => {
}

const onChange = (page) => {
  state.log.result.page = page;
  querySysLog();
}

const querySysLog = () => {
  commonQuery({
    code: 'select_sys_log',
    ...state.log.result
  }).then(rest => {
    if (rest.data) {
      console.log(rest)
      state.log.data = rest.data;
      state.log.result = rest.data;
    }
  })
}

const updateBaseInfo = () => {
  state.loadingInfo = true;

}

</script>
<style scoped lang="less">
.peopleCenter {
  min-height: 90vh;
  display: flex;

  .userInfo {
    background: #fff;
    width: 400px;

    .faceBox {
      width: 130px;
      height: 130px;
      margin: 20px auto;
      border-radius: 50%;
      background: #ccc;
      overflow: hidden;
      cursor: pointer;

      img {
        max-height: 100%;
        max-width: 100%;
      }
    }

    .updatePwdBox {
      margin-top: 40px;
      padding: 10px;

      .rowTitle {
        font-size: 16px;
        padding: 10px;
      }

      .row {
        position: relative;
        padding: 10px 10px 10px 90px;
        min-height: 50px;

        .rowLabel {
          position: absolute;
          top: 16px;
          left: 0;
          width: 80px;
          text-align: right;
        }
      }

      .btnBox {
        margin: 20px auto;
        text-align: center;
      }
    }
  }

  .baseInfo {
    background: #fff;
    margin-left: 10px;
    flex: 1;

    .rowTitle {
      font-size: 18px;
      padding: 10px;
    }

    .infoBox {
      padding: 10px;

      .infoRow {
        position: relative;
        padding: 10px 10px 10px 130px;
        min-height: 60px;

        .rowLabel {
          position: absolute;
          top: 16px;
          left: 0;
          width: 120px;
          text-align: right;
        }
      }
    }

    .logBox {
      padding: 0 20px;

      .logLi {
        margin: 6px 0;
      }
    }

    .pageBox {
      text-align: center;
      padding: 10px 0;
    }
  }

}
</style>
