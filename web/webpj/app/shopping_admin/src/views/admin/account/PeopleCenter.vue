<template>
  <div class="peopleCenter">
    <div class="userInfo">
      <div>
        头像
      </div>

      <div>
        修改密码
        <div>
          原密码
        </div>
        <div>
          新密码
        </div>
        <div>提交</div>
      </div>

    </div>
    <div class="baseInfo">
      <div>
        基本资料
      </div>
      <div>
        <div>昵称:</div>
        <div>真实姓名:</div>
        <div>性别:</div>
        <div>身份证号:</div>
        <div>手机号:</div>
        <div>地址:</div>
        <div>个性签名:</div>
      </div>
      <div>
        操作日志:
      </div>
      <div>
        <div>2024-01-12 01:10:00 登录系统</div>
        <div>2024-01-12 01:10:00 登录系统</div>
        <div>2024-01-12 01:10:00 登录系统</div>
        <div>2024-01-12 01:10:00 登录系统</div>
        <div>2024-01-12 01:10:00 登录系统</div>
        <div>2024-01-12 01:10:00 登录系统</div>
      </div>
    </div>


  </div>
</template>
<script setup>


</script>
<style scoped lang="less">
.peopleCenter {
  min-height: 90vh;
  display: flex;

  .userInfo {
    background: #fff;
    width: 400px;
  }

  .baseInfo {
    background: #fff;
    margin-left: 10px;
    flex: 1;
  }

}
</style>
