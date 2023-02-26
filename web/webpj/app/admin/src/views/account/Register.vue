<template>
  <div class="container">
    <div class="title">企业注册</div>
    <div class="steps">
      <Steps :current="1">
        <Step title="企业" content="请填写企业基本信息"></Step>
        <Step title="负责人" content="请填写负责人信息"></Step>
        <Step title="完成" content="注册完成"></Step>
      </Steps>
    </div>
    <div class="registerBox">

      <div class="inputBox">
        <div class="inputRow">
          <div class="label">企业名称:</div>
          <Input prefix="ios-contact" placeholder="企业名称"/>
        </div>
        <div class="inputRow">
          <div class="label">简称:</div>
          <Input prefix="ios-contact" placeholder=""/>
        </div>
        <div class="inputRow">
          <div class="label">地址:</div>
          <Input prefix="ios-contact" placeholder="Enter name"/>
        </div>
        <div class="inputRow">
          <div class="label">行业:</div>
          <Input prefix="ios-contact" placeholder="Enter name"/>
        </div>
      </div>

      <div class="inputBox">
        <div class="inputRow">
          <div class="label">负责人名称:</div>
          <Input prefix="ios-contact" placeholder="企业名称"/>
        </div>
        <div class="inputRow">
          <div class="label">手机号:</div>
          <Input prefix="ios-contact" placeholder="Enter name"/>
        </div>
        <div class="inputRow">
          <div class="label">密码:</div>
          <Input prefix="ios-contact" placeholder=""/>
        </div>
        <div class="inputRow">
          <div class="label">确认密码:</div>
          <Input prefix="ios-contact" placeholder="Enter name"/>
        </div>
      </div>


      <div class="inputBox">
        <Result type="success" title="注册成功">
          <template #desc>
            注册成功,您的后台登录地址: http://cyyyaw.com/account/login/xxxxx
          </template>
        </Result>
      </div>



      <div class="btnBox">
        <Button type="success">上一步</Button>
        <Button type="success">下一步</Button>
      </div>
    </div>
  </div>
</template>

<script setup>


</script>
<style scoped lang="less">
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

  .btnBox {
    padding: 20px 0;
    display: flex;
    align-items: center;
    justify-content: center
  }
}
</style>
