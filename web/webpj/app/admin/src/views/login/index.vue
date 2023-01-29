<template>
  <div>

    <div class="loginBox">
      <login-box/>
    </div>


  </div>
</template>

<script>

import {defineComponent} from 'vue'
import LoginBox from "@/views/login/LoginBox.vue";

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
.loginBox {
  width: 400px;
  margin: 20vh auto auto;
}

</style>
