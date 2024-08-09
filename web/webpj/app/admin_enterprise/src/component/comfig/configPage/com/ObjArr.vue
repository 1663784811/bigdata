<template>
  <div>
    <div>
      {{ title }}：
      <Button type="success" icon="md-add-circle" @click="setting.push({label:'', value:''});changeArr()" size="small"/>
    </div>
    <div v-for="(item, index) in setting" :key="index">
      <Input style="width: 200px" v-model="item.label" @on-change="changeArr" placeholder="label"/>:
      <Input style="width: 200px" v-model="item.value" @on-change="changeArr" placeholder="value"/>
      <Button type="error" icon="md-trash" @click="setting.splice(index, 1);changeArr()" size="small"/>
    </div>
  </div>
</template>

<script setup>
const emits = defineEmits(['event']);
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

const changeArr = () => {
  emits('event', props.setting)
}

</script>

<style scoped>

</style>