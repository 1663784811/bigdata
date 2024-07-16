<template>
  <div class="userInfo">
    <div class="userInfoItem">
      <div class="textNote">企业: {{ loginInfoSt.enterpriseInfo.name }}</div>
    </div>

    <div class="userInfoItem">
      <Switch v-model="configModule.configPage.showOperation" />
    </div>

    <div class="userInfoItem">
      <div class="textNote" @click="debuggerFn">
        <Icon class="icon" type="md-notifications"/>
      </div>
    </div>

    <div class="userInfoItem">
      <div class="textNote">
        <Icon class="icon" type="md-contract"/>
      </div>
    </div>


    <div class="userInfoItem">
      <div class="textNote">
        <Icon class="icon" type="md-notifications"/>
      </div>
      <div class="infoBox">
        <div class="infoList" @click="goFn('messageDetails')">
          <Icon class="icon" type="md-contact"/>
          <div class="menuName">消息1</div>
        </div>
        <div class="infoList" @click="goFn('messageDetails')">
          <Icon class="icon" type="md-contact"/>
          <div class="menuName">消息1</div>
        </div>
        <div class="infoList" @click="goFn('messageDetails')">
          <Icon class="icon" type="md-contact"/>
          <div class="menuName">消息1</div>
        </div>
        <div class="infoList" @click="goFn('messageDetails')">
          <Icon class="icon" type="md-contact"/>
          <div class="menuName">消息1</div>
        </div>
      </div>
    </div>

    <div class="userInfoItem">
      <div class="textNote">
        <Icon class="icon" type="md-contact"/>
      </div>
      <div class="infoBox">
        <div class="infoList" @click="goFn('peopleCenter')">
          <Icon class="icon" type="md-contact"/>
          <div class="menuName">个人中心</div>
        </div>
        <div class="infoList logout" @click="logOut">
          <Icon class="icon" type="md-contact"/>
          <div class="menuName">退出登录</div>
        </div>
      </div>
    </div>


  </div>
</template>

<script setup>

import {useRoute, useRouter} from "vue-router";
import {useAdminMenuStore} from "@/store/adminMenu.js";
import {loginInfo} from "@/store/loginInfo.js";
import {useConfigModule} from "@/store/configModule.js";

import {commonQuery} from '@/api/api.js'
import {onMounted} from "vue";

const store = useAdminMenuStore();
const loginInfoSt = loginInfo();
const configModule = useConfigModule();

const router = useRouter();
const route = useRoute();

onMounted(() => {

  // commonQuery({
  //   code: ""
  // }, route.params.code).then((rest) => {
  //   console.log(rest)
  // })
})


const logOut = function () {
  loginInfoSt.token = ''
  loginInfoSt.userInfo = {}
  const eCode = loginInfoSt.eCode;
  router.push({
    name: 'login',
    query: {
      eCode
    }
  })
}

const goFn = (name) => {
  router.push({name})
}

const debuggerFn = () => {
  configModule.configPage.showOperation = true;
}


</script>

<style scoped lang="less">
.userInfo {
  display: flex;
  justify-items: center;
  align-items: center;
  padding: 0 20px;

  .userInfoItem {
    display: flex;
    align-items: center;
    position: relative;

    .textNote {
      color: #ddd;
      margin: 0 6px;
      padding: 0 10px;
      height: 47px;
      display: flex;
      justify-content: center;
      align-items: center;

      .icon {
        font-size: 22px;
        color: #fff;
      }
    }

    &:hover {
      background: #0a1327;

      .infoBox {
        display: block;
      }
    }

    .infoBox {
      position: absolute;
      right: 0;
      top: 46px;
      box-shadow: 2px 2px 2px #ccc;
      z-index: 999;
      width: 260px;
      min-height: 300px;
      background: #fff;
      display: none;
      padding-bottom: 40px;
      border-radius: 0 0 4px 4px;
      overflow: hidden;

      .infoList {
        cursor: pointer;
        padding: 5px 10px;
        display: flex;
        justify-items: center;
        align-items: center;
        background: #f6f6f6;
        margin-bottom: 2px;

        &:hover {
          background: #35456b;
          color: #fff;
        }

        .icon {
          font-size: 16px;
        }

        .menuName {
          margin-left: 4px;

        }
      }

      .logout {
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        justify-content: center;
        margin: 0;
      }
    }

    .modelBox {
      width: 200px;

      .modelRow {
        padding: 0 10px;
        margin: 10px 0;
        cursor: pointer;

        &:hover {
          color: red;
        }
      }
    }

  }
}
</style>
