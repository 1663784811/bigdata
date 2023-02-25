<template>
  <div class="winHeight">

    <div class="loginBox">
      <login-box/>
    </div>
  </div>
</template>

<script>

import {defineComponent} from 'vue'
import LoginBox from "@/views/account/LoginBox.vue";

export default defineComponent({
  name: 'Login',
  components: {
    'login-box': LoginBox
  },
  setup() {
    return {}
  },
})
</script>
<style scoped>
.winHeight{
  height: 100vh;
}
.loginBox {
  width: 400px;
}

</style>
