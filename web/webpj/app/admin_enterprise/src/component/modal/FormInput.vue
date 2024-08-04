<template>
  <div class="formInputContainer">
    <div class="title">开通点餐系统</div>
    <DataInput :settings="settings.addObj"/>
    <div>
      <slot name="footer" :data="state.data"/>
    </div>
  </div>
</template>

<script setup>
import DataInput from './com/DataInput.vue'
import {onMounted, reactive, watch} from "vue";

const props = defineProps({
  settings: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  columns: [],
  data: {},
  editor: true,
  verifyData: {}
})


onMounted(() => {
  initFn();
})


const initFn = () => {
  const {columns} = props.settings
  if (columns && columns.length > 0) {
    state.columns = columns;
  } else {
    state.columns = [];
  }
  console.log("ssssssssssssssssssssss", state.columns)
}


watch(() => props.settings, () => {
  initFn();
})

</script>

<style scoped lang="less">
.formInputContainer {
  background: #fff;
  padding: 10px 30px;
  border-radius: 10px;

  .title {
    font-size: 24px;
    padding: 40px 60px;
  }
}
</style>