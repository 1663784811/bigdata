<template>
  <div class="requestBox" v-if="setting">
    {{ title }}:
    <div class="urlBox">
      <Checkbox v-model="setting.isCommonUrl" @on-change="changeIsCommonUrl"/>
      <Input v-model="setting.url" placeholder="url" clearable/>
    </div>
    <obj-json title="参数" :setting="setting.parameter"/>
  </div>
</template>

<script setup>

import ObjJson from './ObjJson.vue'
import {watch} from "vue";

const props = defineProps({
  title: {
    type: String,
    default: '',
    required: false
  },
  setting: {
    type: Object,
    default: {
      url: '',
      isCommonUrl: true,
      parameter: {
        code: ""
      }
    },
    required: false
  }
});
const changeIsCommonUrl = () => {
  if (props.setting.isCommonUrl && !props.setting.url) {
    props.setting.url = '/admin/${eCode}/common/query'
  }
}

watch(() => props.setting, () => {
  if (!props.setting.parameter) {
    props.setting.parameter = {}
  }
})

</script>

<style scoped lang="less">
.requestBox {
  background: #ccc;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 4px;

  .urlBox {
    display: flex;
    align-items: center;
  }
}
</style>