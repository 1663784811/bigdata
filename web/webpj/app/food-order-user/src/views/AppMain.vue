<template>
  <router-view/>
</template>
<script setup>

import {onMounted} from "vue";
import {commonQuery} from "@/service/api";
import {useRoute, useRouter} from "vue-router";
import {useUserStore} from "@/stores/user";

const route = useRoute();
const router = useRouter();
const loginInfoSt = useUserStore();

onMounted(async () => {
  const {appid} = route.params;
  const {data} = await commonQuery({
    code: 'select_e_application_by_appid',
    appid
  })
  if (data && data.length == 1) {
    // 保存app信息
    loginInfoSt.variable.appid = appid;
  } else {
    delete loginInfoSt.variable.appid;
    await router.replace({name: 'welcomePage'});
  }
  // 查询当前餐台状态

})

// 使用websocket 工具
/**
 * 需要接收的消息
 * 1.选择人数, 开始点菜
 * 2.添加菜品消息,减少菜品消息
 * 3.提交菜品消息
 * 4.支付消息
 */


</script>

<style scoped lang="less">

</style>