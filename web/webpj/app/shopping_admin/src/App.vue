<template>
  <router-view/>
  <modal-files  />
  <select-panel/>
  <common-modal-data-list/>
  <ModalUploadFiles />
</template>
<script setup>
import ModalFiles from '@/component/modal/ModalFiles.vue'
import SelectPanel from '@/component/modal/SelectPanel.vue'
import CommonModalDataList from '@/component/modal/CommonModalDataList.vue'
import ModalUploadFiles from '@/component/modal/ModalUploadFiles.vue'


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
