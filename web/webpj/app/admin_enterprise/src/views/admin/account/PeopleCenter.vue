<template>
  <div class="peopleCenter">
    <div class="userInfo">
      <div class="faceBox">
        头像
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
            <Input clearable/>
          </div>
        </div>
        <div class="row">
          <div class="rowVal">
            <Button class="dataBtn" type="primary" icon="md-cloud-upload">修改</Button>
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
            <Input clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">真实姓名:</div>
          <div class="rowVal">
            <Input clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">性别:</div>
          <div class="rowVal">
            <Input clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">身份证号:</div>
          <div class="rowVal">
            <Input clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">手机号:</div>
          <div class="rowVal">
            <Input clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">地址:</div>
          <div class="rowVal">
            <Input clearable/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowLabel">个性签名:</div>
          <div class="rowVal">
            <Input clearable type="textarea"/>
          </div>
        </div>
        <div class="infoRow">
          <div class="rowVal">
            <Button class="dataBtn" type="primary" icon="md-cloud-upload">修改</Button>
          </div>
        </div>
      </div>
      <div class="rowTitle">
        操作日志:
      </div>
      <div class="logBox">
        <div class="logLi">2024-01-12 01:10:00 登录系统</div>
        <div class="logLi">2024-01-12 01:10:00 登录系统</div>
        <div class="logLi">2024-01-12 01:10:00 登录系统</div>
        <div class="logLi">2024-01-12 01:10:00 登录系统</div>
        <div class="logLi">2024-01-12 01:10:00 登录系统</div>
        <div class="logLi">2024-01-12 01:10:00 登录系统</div>
      </div>
    </div>

  </div>
</template>
<script setup>


import {Input} from "view-ui-plus";
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
      padding: 10px;
      border-radius: 50%;
      background: #ccc;
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
  }

}
</style>
