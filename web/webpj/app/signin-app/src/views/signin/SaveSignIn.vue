<template>
  <s-header :name="'保存签到列表'"></s-header>
  <div>保存签到列表</div>


  <div>
    <div class="row">
      <div class="label">标题</div>
      <div class="inputDiv">
        <input/>
      </div>
    </div>
    <div class="row">
      <div class="label">活动时间:</div>
      <div class="inputDiv">
        <input/>
      </div>
    </div>
    <div class="row">
      <div class="label">活动简介</div>
      <div class="inputDiv">
        <input/>
      </div>
    </div>
    <div class="row">
      <div class="label">指定签到人</div>
      <div class="inputDiv">
        <input/>
      </div>
    </div>
    <div class="row">
      <van-button round block color="#1baeae" native-type="submit">保存</van-button>
    </div>
  </div>


</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'

</script>

<style scoped lang="less">

</style>
