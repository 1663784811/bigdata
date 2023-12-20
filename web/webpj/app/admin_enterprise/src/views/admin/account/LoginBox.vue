<template>
  <Login @on-submit="handleSubmit">
    <UserName name="username"/>
    <Password name="password"/>
    <Submit/>
  </Login>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "LoginBox",
  data() {
    return {
      autoLogin: true
    }
  },
  methods: {
    handleSubmit(valid, {username, password}) {
      if (valid) {
        this.$Modal.info({
          title: '输入的内容如下：',
          content: 'username: ' + username + ' | password: ' + password
        });
      }
    }
  }
})
</script>

<style scoped>
.LoginBox {

}
</style>
