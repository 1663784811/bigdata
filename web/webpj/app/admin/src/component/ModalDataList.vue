<template>
  <Modal
      v-model="modalData.show"
      title="数据"
      @on-ok="eventFn('ok')"
      @on-cancel="eventFn('cancel')"
      :mask-closable="false"
      :loading="modalData.loading"
      width="80wh"
  >
    <div class="modalBox">
      <div>
        <template v-for="(item,index) in modalData.columns" :key="index">
          <div v-if="item.isShowSave !== false" class="row" :style="{display: item.controlType == 'hidden'?'none':''}">
            <div class="label">{{ item.title }}:</div>
            <div class="content" v-if="item.controlType == 'textarea'">
              <Input v-model="modalData.data[item.key]" type="textarea" :rows="10" :placeholder="item.node"/>
            </div>
            <div class="content" v-else-if="item.controlType == 'datetime'">
              <DatePicker v-model="modalData.data[item.key]"
                          type="datetime"
                          format="yyyy-MM-dd HH:mm"
                          :placeholder="item.node"/>
            </div>
            <div class="content" v-else>
              <Input v-model="modalData.data[item.key]" :placeholder="item.node"/>
            </div>
          </div>
        </template>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {defineEmits, ref, watch} from "vue";

const emits = defineEmits(['event', 'update:modelValue']);

const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false
  },
  modalSetting: {
    type: Object,
    default: {},
    required: false
  }
});

const modalData = ref({
  loading: true,
  show: false,
  editor: false,
  data: {},
  columns: []
})

watch(() => props.modalSetting, () => {
  modalData.value = {
    ...modalData.value,
    ...props.modalSetting
  }
}, {deep: true, immediate: true})

watch(() => props.modelValue, () => {
  modalData.value.show = props.modelValue;
})

watch(() => modalData.value.show, () => {
  emits('update:modelValue', modalData.value.show);
})


/**
 * 事件
 */
const eventFn = (ev) => {
  const {data} = modalData.value;
  emits('event', ev, data);
}


</script>

<style scoped lang="less">
.modalBox {
  .row {
    display: flex;
    padding: 10px 0;

    .label {
      width: 150px;
      padding-right: 10px;
      align-items: center;
      display: flex;
      justify-content: right;
    }

    .content {
      flex: 1;
    }
  }
}
</style>