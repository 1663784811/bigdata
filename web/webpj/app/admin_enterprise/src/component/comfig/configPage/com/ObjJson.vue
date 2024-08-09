<template>
  <div>
    <div>
      {{ title }}：
      <Button type="success" icon="md-add-circle" @click="state.parameterArr.push({key:'', val:''})" size="small"/>
    </div>
    <div v-for="(item, index) in state.parameterArr" :key="index">
      <Input style="width: 200px" v-model="item.key" @on-change="changeArr" placeholder="key"/>:
      <Input style="width: 200px" v-model="item.val" @on-change="changeArr" placeholder="val"/>
      <Button type="error" icon="md-trash" @click="state.parameterArr.splice(index, 1);changeArr()" size="small"/>
    </div>
  </div>
</template>

<script setup>
import {reactive, watch} from "vue";

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
    if (arrObj.key) {
      obj[arrObj.key] = arrObj.val;
    }
  }
  for (const settingKey in props.setting) {
    delete props.setting[settingKey]
  }
  for (const objKey in obj) {
    props.setting[objKey] = obj[objKey]
  }
}


watch(() => props.setting, () => {
  const setting = props.setting;
  if (setting) {
    state.parameterArr = [];
    for (let key in setting) {
      const val = setting[key];
      state.parameterArr.push({key: key, val: val})
    }
  }
})
</script>

<style scoped>

</style>