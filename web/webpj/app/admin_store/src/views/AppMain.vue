<template>
  <router-view/>
</template>
<script setup>

import {onMounted} from "vue";
import {useRoute, useRouter} from "vue-router";
import {commonQuery} from "@/api/api.js";
import {loginInfo} from "@/store/loginInfo.js"

const route = useRoute();
const router = useRouter();
const loginInfoSt = loginInfo();
onMounted(async () => {
  const {code} = route.params;
  const {data} = await commonQuery({
    code: 'select_e_store_by_code',
    storeId: code
  }, code);
  console.log("sssssssssssssssss", data)
  if (data && data.length === 1) {
    loginInfoSt.storeInfo = data[0];
  } else {
    loginInfoSt.storeInfo = {}
    await router.replace({name: 'welcomePage'});
  }

})
</script>
<style scoped lang="less">

</style>