<template>
  <div>
    <div>
      {{ title }}：
      <Button type="success" icon="md-add-circle" @click="setting.push({label:'', value:''})" size="small"/>
    </div>
    <div v-for="(item, index) in setting" :key="index">
      <Input style="width: 200px" v-model="item.label" placeholder="label"/>:
      <Input style="width: 200px" v-model="item.value" placeholder="value"/>
      <Button type="error" icon="md-trash" @click="setting.splice(index, 1);" size="small"/>
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
    default: [],
    required: false
  }
});
</script>

<style scoped>

</style>