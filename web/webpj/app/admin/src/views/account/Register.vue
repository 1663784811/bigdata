<template>
  <div class="container">
    <div>注册</div>
    <div class="steps">
      <Steps :current="1">
        <Step title="企业" content="请填写企业基本信息"></Step>
        <Step title="负责人" content="请填写负责人信息"></Step>
        <Step title="完成" content="注册完成"></Step>
      </Steps>
    </div>
    <div class="registerBox">

      <div>


      </div>

      <div>
        <Button type="success">上一步</Button>
        <Button type="success">下一步</Button>
      </div>

    </div>
  </div>
</template>

<script setup>



</script>
<style scoped>
.steps{
  margin-top: 60px;
}
</style>
