<template>
  <div class="container">
    sssssssss


    <Button type="success" long shape="circle" @click="openAppFn">开通</Button>
  </div>
</template>
<script setup>
import {onMounted, reactive} from "vue";
import {loginInfo} from "@/store/loginInfo.js";
import {openApp} from '@/api/api.js'

const loginInfoSt = loginInfo();


const state = reactive({})


onMounted(() => {

})


const openAppFn = () => {


  openApp().then((rest) => {


    console.log(rest)
  })

}


</script>

<style scoped lang="less">

</style>