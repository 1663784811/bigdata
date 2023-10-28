<template>
  <div class="userInfo">
    <div class="userInfoItem">
      <div class="textNote">企业模式</div>
    </div>
    <div class="userInfoItem">
      <Poptip trigger="hover">
        <div class="textNote">
          <Badge :count="3">
            <a href="#" class="demo-badge"></a>
          </Badge>
        </div>
        <template #content>
          <div>商城模式</div>
          <Divider orientation="left" size="small">门店</Divider>
          <div>听心一号店</div>
          <div>听心二号店</div>
          <div>听心二号店</div>
          <div>听心二号店</div>
          <div>听心二号店</div>
          <div>听心二号店</div>
          <div>听心二号店</div>
          <div>听心二号店</div>
        </template>
      </Poptip>
    </div>

    <div class="userInfoItem">
      <div class="textNote">
        <Icon type="md-contact"/>
      </div>
      <div class="infoBox">
        <div class="infoList" @click="logOut">退出登录</div>
      </div>
    </div>
  </div>
</template>

<script setup>

import {useRouter} from "vue-router";
import {useAdminMenuStore} from "@/store/adminMenu";
import {loginInfo} from "@/store/loginInfo";


const store = useAdminMenuStore();
const loginInfoSt = loginInfo();

console.log(store)

const router = useRouter();

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
      border: 1px solid #000;
      z-index: 999;
      width: 200px;
      background: #fff;
      display: none;
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
