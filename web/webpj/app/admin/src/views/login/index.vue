<template>
  <div>


    <login-box />

  </div>
</template>

<script>

import {defineComponent} from 'vue'
import LoginBox from "@/views/login/LoginBox.vue";

export default defineComponent({
  name: 'Login',
  components:{
    'login-box':LoginBox
  },
  setup() {
    return {}
  },
})
</script>
<style>

</style>
