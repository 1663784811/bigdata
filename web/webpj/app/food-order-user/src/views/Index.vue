<template>
  <div></div>
</template>
<script setup>

import {useRoute, useRouter} from "vue-router";
import {useUserStore} from "@/stores/user.js";
import {onMounted} from "vue";

const route = useRoute();
const router = useRouter();
const loginInfoSt = useUserStore();
onMounted(async () => {
  const {appid} = loginInfoSt.variable
  const token = loginInfoSt.token
  if (token && appid) {
    await router.replace({
      name: 'home',
      params: {
        appid
      }
    });
  } else {
    loginInfoSt.enterpriseInfo = {}
    await router.replace({name: 'welcomePage'});
  }
})

</script>
<style scoped lang="less">

</style>