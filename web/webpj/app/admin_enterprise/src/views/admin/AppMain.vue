<template>
  <router-view/>
</template>
<script setup>

import {onMounted} from "vue";
import {useRoute, useRouter} from "vue-router";
import {enterpriseFindPage} from "@/api/api.js";
import {loginInfo} from "@/store/loginInfo.js"

const route = useRoute();
const router = useRouter();
const loginInfoSt = loginInfo();
onMounted(async () => {
  const {code} = route.params;
  const {data} = await enterpriseFindPage({code}, code);
  if (data && data.code) {
    loginInfoSt.enterpriseInfo = data;
  } else {
    loginInfoSt.enterpriseInfo = {}
    await router.replace({name: 'welcomePage'});
  }
})
</script>
<style scoped lang="less">

</style>