<template>
  <router-view/>
  <modal-files/>
  <select-panel/>
  <config-drawer/>
</template>
<script setup>
import ModalFiles from '@/component/modal/ModalFiles.vue'
import SelectPanel from '@/component/modal/SelectPanel.vue'
import ConfigDrawer from '@/component/ConfigDrawer.vue'

</script>

<style scoped>

</style>
<style>
ul{
  list-style: none;
}
.commonTable{
  background: #fff;
}
</style>