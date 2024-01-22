<template>
  <div></div>
</template>
<script setup>

import {useRoute, useRouter} from "vue-router";
import {loginInfo} from "@/store/loginInfo.js";
import {onMounted} from "vue";

const route = useRoute();
const router = useRouter();
const loginInfoSt = loginInfo();
onMounted(async () => {
  const {eCode} = loginInfoSt.variable
  const token = loginInfoSt.token
  if (token && eCode) {
    await router.replace({
      name: 'home',
      params: {
        code: eCode
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