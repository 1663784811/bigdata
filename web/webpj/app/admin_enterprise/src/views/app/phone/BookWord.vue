<template>
  <div>
    <div>
      上传书
    </div>
    <div>
      aaassss
    </div>
  </div>

</template>

<script setup>

import {onMounted, provide, reactive, ref} from "vue";
import {useRoute, useRouter} from "vue-router";

const router = useRouter();
const route = useRoute();



const state = reactive({
  pageData: {},
  drawerSetting: {},
  newTable: {
    show: true
  },
})

onMounted(() => {

})


</script>

<style scoped lang="less">
</style>
