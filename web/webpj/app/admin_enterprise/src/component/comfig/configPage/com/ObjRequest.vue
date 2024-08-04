<template>
  <div class="requestBox" v-if="setting">
    {{ title }}:
    <div class="urlBox">
      <Checkbox v-model="setting.isCommonUrl"/>
      <Input v-model="setting.url" placeholder="url" clearable/>
    </div>
    <div>
      参数：

    </div>

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
      isCommonUrl: true,
      parameter: {
        code: ""
      }
    },
    required: false
  }
});
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