<template>
  <obj-arr :title="title" :setting="state.parameterArr"  @event="changeArr"></obj-arr>
</template>

<script setup>
import {reactive, watch} from "vue";
import ObjArr from './ObjArr.vue'

const props = defineProps({
  title: {
    type: String,
    default: '',
    required: false
  },
  setting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  parameterArr: []
})

const changeArr = () => {
  const {parameterArr} = state;
  let obj = {}
  for (let i = 0; i < parameterArr.length; i++) {
    const arrObj = parameterArr[i];
    if (arrObj.label) {
      obj[arrObj.label] = arrObj.value;
    }
  }
  for (const settinglabel in props.setting) {
    delete props.setting[settinglabel]
  }
  for (const objlabel in obj) {
    props.setting[objlabel] = obj[objlabel]
  }
}


watch(() => props.setting, () => {
  const setting = props.setting;
  if (setting) {
    state.parameterArr = [];
    for (let label in setting) {
      const value = setting[label];
      state.parameterArr.push({label: label, value: value})
    }
  }
})
</script>

<style scoped>

</style>