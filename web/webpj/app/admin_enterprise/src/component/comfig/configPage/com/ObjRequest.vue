<template>
  <div class="requestBox" v-if="setting">
    {{ title }}:
    <div class="urlBox">
      <Checkbox v-model="setting.isCommonUrl"/>
      <Input v-model="setting.url" placeholder="url" clearable/>
    </div>
    <div>
      <div>
        参数：
        <Button type="success" icon="md-add-circle" @click="state.parameterArr.push({key:'', val:''})" size="small"/>
      </div>
      <div v-for="(item, index) in state.parameterArr" :key="index">
        <Input style="width: 200px" v-model="item.key" @on-change="changeArr" placeholder="key"/>:
        <Input style="width: 200px" v-model="item.val" @on-change="changeArr" placeholder="val"/>
        <Button type="error" icon="md-trash" @click="state.parameterArr.splice(index, 1)" size="small"/>
      </div>
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


const state = reactive({
  parameterArr: [],
  init: false
})


const changeArr = () => {
  const arr = state.parameterArr;
  let obj = {}
  for (let i = 0; i < arr.length; i++) {
    const arrObj = arr[i];
    if(arrObj.key){
      obj[arrObj.key] = arrObj.val;
    }
  }
  props.setting.parameter = obj;
}

watch(() => state.parameterArr, () => {
  changeArr()
})
watch(() => props.setting.parameter, () => {
  const {parameter} = props.setting;
  if (parameter && !state.init) {
    state.init = true;
    state.parameterArr = [];
    for (let key in parameter) {
      const val = parameter[key];
      state.parameterArr.push({key: key, val: val})
    }
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