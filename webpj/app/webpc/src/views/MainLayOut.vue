<template>
  <headerTop></headerTop>
  <router-view></router-view>
  <webFooter></webFooter>
</template>

<script>

import {ref} from 'vue';
import headerTop from "@/views/common/headerTop.vue";
import webFooter from "@/views/common/webFooter.vue";

export default {
  name: "LayOut",
  components:{
    headerTop,
    webFooter
  },
  setup() {
    const active = ref('home');
    return {active};
  },
}
</script>

<style lang="scss" scoped>




</style>
