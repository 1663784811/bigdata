<template>
  <router-view/>
  <modal-files />
  <select-panel />
</template>
<script setup>
import ModalFiles from '@/component/modal/ModalFiles.vue'
import SelectPanel from '@/component/modal/SelectPanel.vue'
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