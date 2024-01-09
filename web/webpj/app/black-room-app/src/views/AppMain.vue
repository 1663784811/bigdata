<template>
  <router-view/>
</template>
<script setup>

import {onMounted} from "vue";
import {commonQuery} from "@/service/api";
import {useRoute, useRouter} from "vue-router";
import {useAppStore} from "@/stores/app";
import {useUserStore} from "@/stores/user";

const route = useRoute();
const router = useRouter();
const appStore = useAppStore();
const loginInfoSt = useUserStore();

onMounted(async () => {
  const {appid} = route.params;
  const {data} = await commonQuery({
    code: 'select_e_application_by_appid',
    appid
  })
  console.log(data);
  if (data && data.length == 1) {
    // 保存app信息
    appStore.appInfo = data[0];
    loginInfoSt.variable.appid = appid;
  } else {
    delete loginInfoSt.variable.appid;
    await router.replace({name: 'welcomePage'});
  }
})

</script>

<style scoped lang="less">

</style>