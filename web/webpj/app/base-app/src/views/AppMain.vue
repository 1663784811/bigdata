<template>
  <router-view/>
</template>
<script setup>

import {onMounted} from "vue";
import {commonQuery} from "@/service/api";
import {useRoute, useRouter} from "vue-router";
import {useAppStore} from "@/stores/app";

const route = useRoute();
const router = useRouter();
const appStore = useAppStore();


onMounted(async () => {
  const {appid} = route.params;
  if (appid) {
    appStore.appInfo.code = appid
    const {data} = await commonQuery({
      code: 'select_e_application_by_appid',
      appid
    }, appid)
    if (data && data.length === 1) {
      appStore.appInfo = data[0];
    } else {
      await router.replace({name: 'welcomePage'});
    }
  } else {
    await router.replace({name: 'welcomePage'});
  }
})

</script>

<style scoped lang="less">

</style>