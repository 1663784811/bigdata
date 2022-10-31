<template>
  <n-notification-provider>
    <n-message-provider>
      <router-view />
    </n-message-provider>
  </n-notification-provider>
</template>

<script lang="ts">
  import { defineComponent } from 'vue'
  import pageConfig from '@/store/modules/pageConfig'

  export default defineComponent({
    name: 'App',
    setup: function () {
      let pageConfig1 = pageConfig().getPageConfig({})

      console.log('eeeeeeeeeeeeeeeeeeeeeeeeeeee', pageConfig1)
      // getPageConfig

      return {}
    },
  })
</script>
