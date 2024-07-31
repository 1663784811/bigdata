<template>
  <router-view/>
  <modal-files/>
  <select-panel/>
  <config-drawer/>
  <common-modal-data-list/>
  <sql-config/>
  <modal-upload-files/>
  <modal-mqtt-msg/>
  <modal-icon/>
  <sql-config-modal/>
</template>
<script setup>
// 文件管理
import ModalFiles from '@/component/modal/ModalFiles.vue'
// 选择面板
import SelectPanel from '@/component/modal/SelectPanel.vue'

import ConfigDrawer from '@/component/comfig/ConfigDrawer.vue'
// sql配置
import SqlConfig from '@/component/comfig/SqlConfig.vue'
import SqlConfigModal from '@/component/comfig/SqlConfigModal.vue'


import CommonModalDataList from '@/component/modal/CommonModalDataList.vue'
// 上传文件
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