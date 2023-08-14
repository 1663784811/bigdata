<template>
  <Modal
      v-model="modalData.show"
      title="图片"
      @on-ok="eventFn('ok')"
      @on-cancel="eventFn('cancel')"
      :mask-closable="false"
      :loading="modalData.loading"
      width="80wh"
  >
    <div class="modalBox">
      <div class="imageBox">
        <div class="imageItem" v-for="(item, index) in 80" :key="index">
          <img
              src="https://imgcps.jd.com/ling-cubic/ling4/lab/amZzL3QxLzExODM2NC8xNy8zNTAwNS83NzE5My82NDA3MTNmOEY4YjYyN2EzYi8yNzBjNDhhZmUwNWU4ZTAyLnBuZw/5Lqs6YCJ5aW96LSn/5L2g5YC85b6X5oul5pyJ/1635187706996019201/cr/s/q.jpg"
              alt="">

        </div>

      </div>
    </div>
  </Modal>
</template>

<script setup>
import {defineEmits, ref, watch, provide} from "vue";
import EventBus from "@/component/EventBus.js";

const emitter = EventBus();
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


// ============================================    事件总线
emitter.on('showModalFiles', (valueObj) => {
  modalData.value.show = valueObj
});

const clickBtnFn = () => {
  emitter.emit("indexMsg", "sssssssssssssssss");
}


</script>

<style scoped lang="less">
.modalBox {
  .imageBox {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    align-content: center;

    .imageItem {
      height: 70px;
      padding: 4px;
      border-radius: 2px;
      border: 1px solid #ddd;
      margin: 6px;

      &:hover {
        cursor: pointer;
        background: #ccc;
      }

      img {
        height: 100%;
      }
    }

  }
}
</style>