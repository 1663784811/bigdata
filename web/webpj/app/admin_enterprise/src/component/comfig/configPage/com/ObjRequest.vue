<template>
  <div v-if="setting">
    {{ title }}: <Input v-model="setting.url" placeholder="url" clearable/>
  </div>
</template>

<script setup>

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
      parameter: {
        code: ""
      }
    },
    required: false
  }
});
</script>

<style scoped lang="less">

</style>