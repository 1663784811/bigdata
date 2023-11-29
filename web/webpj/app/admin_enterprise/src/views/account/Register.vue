<template>
  <div>&nbsp;</div>
  <div class="registerContainer">
    <div class="title">企业注册</div>
    <div class="steps">
      <Steps :current="stepIndex">
        <Step title="企业" content="请填写企业基本信息"></Step>
        <Step title="负责人" content="请填写负责人信息"></Step>
        <Step title="完成" content="注册完成"></Step>
      </Steps>
    </div>
    <div class="registerBox">

      <div class="inputBox" v-show="stepIndex===0">
        <div class="inputRow">
          <div class="label">企业名称:</div>
          <Input v-model="registerData.eEnterprise.name"/>
        </div>
        <div class="inputRow">
          <div class="label">简称:</div>
          <Input v-model="registerData.eEnterprise.shortName"/>
        </div>
        <div class="inputRow">
          <div class="label">地址:</div>
          <Input v-model="registerData.eEnterprise.address"/>
        </div>
        <div class="inputRow">
          <div class="label">行业:</div>
          <Input/>
        </div>
      </div>

      <div class="inputBox" v-show="stepIndex===1">
        <div class="inputRow">
          <div class="label">负责人名称:</div>
          <Input v-model="registerData.admin.userName"/>
        </div>
        <div class="inputRow">
          <div class="label">密码:</div>
          <Input v-model="registerData.admin.password"/>
        </div>
        <div class="inputRow">
          <div class="label">确认密码:</div>
          <Input v-model="registerData.admin.passwordEd"/>
        </div>
        <div class="inputRow">
          <div class="label">手机号:</div>
          <Input v-model="registerData.admin.phone"/>
        </div>
        <div class="inputRow">
          <div class="label">验证码:</div>
          <Input v-model="registerData.admin.code"/>
        </div>

      </div>
      <div class="inputBox" v-show="stepIndex===2">
        <Result type="success" title="注册成功">
          <template #desc>
            <div>注册成功,您的后台登录地址</div>
            <div>{{ registerData.successData.enterprise.url }}</div>
            <div>正在为您跳转...</div>
          </template>
        </Result>
      </div>
    </div>
    <div class="btnBox" v-if="stepIndex != 2">
      <Button type="success" @click="clickNext" long  >下一步</Button>
    </div>
    <div class="btnBox" v-if="stepIndex ==1">
      <Button @click="clickPrevious" long>上一步</Button>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {enterpriseRegister} from "@/api/api.js"
import {Message} from "view-ui-plus";


const stepIndex = ref(0);
const registerData = ref({
  eEnterprise: {
    name: '',
    shortName: '',
    address: '',
    logo: '',
  },
  admin: {
    userName: '',
    password: '',
    passwordEd: '',
    code: '',
    phone: '',
  },
  successData: {
    admin: {},
    enterprise: {
      url: ''
    }
  }
});


const clickNext = () => {
  const rData = registerData.value;
  const {eEnterprise, admin} = rData;
  if (stepIndex.value == 1) {
    if (!admin.userName) {
      Message.error({content: '负责人不能为空'});
    } else if (!admin.password) {
      Message.error({content: '密码不能为空'});
    } else if (admin.password != admin.passwordEd) {
      Message.error({content: '两次输入的密码不一至'});
    } else if (!admin.phone) {
      Message.error({content: '手机号不能为空'});
    } else if (!admin.code) {
      Message.error({content: '验证码不能为空'});
    } else {
      enterpriseRegister(registerData.value).then((rest) => {
        Message.success({
          content: `${rest.msg}`
        });
        const {data} = rest;
        registerData.value.successData = data;
        stepIndex.value++;
        setTimeout(() => {
          if (data.enterprise.url) {
            window.location.href = data.enterprise.url;
          }
        }, 30000)
      })
    }
  } else {
    // 验证数据
    if (!eEnterprise.name) {
      Message.error({content: '企业名称不能为空'});
    } else if (!eEnterprise.shortName) {
      Message.error({content: '简称不能为空'});
    } else if (!eEnterprise.address) {
      Message.error({content: '地址不能为空'});
    } else {
      stepIndex.value++;
    }
  }
}

const clickPrevious = () => {
  stepIndex.value--;
}

</script>
<style scoped lang="less">
.registerContainer {
  background: #fff;
  max-width: 1200px;
  margin: 100px auto auto;
  padding: 60px 0;

  .title {

    text-align: center;
    font-size: 20px;
    padding: 60px 0 20px 0;
  }

  .steps {
    padding: 60px 0;
    max-width: 800px;
    margin: auto;
  }

  .registerBox {
    max-width: 800px;
    margin: auto;

    .inputBox {
      .inputRow {
        display: flex;
        justify-content: center;
        justify-items: center;
        align-items: center;
        padding: 10px 0;

        .label {
          width: 120px;
          font-size: 14px;
          font-weight: bold;
          padding-right: 10px;
          text-align: right;
        }
      }

    }
  }

  .btnBox {
    max-width: 600px;
    margin: auto;
    padding: 20px 0;
    justify-content: center
  }
}
</style>
