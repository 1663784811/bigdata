<template>
  <router-view/>
  <modal-files/>
  <select-panel/>
  <config-drawer/>
  <common-modal-data-list/>
</template>
<script setup>
import ModalFiles from '@/component/modal/ModalFiles.vue'
import SelectPanel from '@/component/modal/SelectPanel.vue'
import ConfigDrawer from '@/component/comfig/ConfigDrawer.vue'
import CommonModalDataList from '@/component/modal/CommonModalDataList.vue'


</script>

<style scoped>

</style>
<style>
ul {
  list-style: none;
}

.commonTable {
  background: #fff;
}
</style>