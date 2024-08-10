<template>
  <Modal v-model="modalData.show" :title="title" @on-ok="eventFn('ok')" @on-cancel="eventFn('cancel')"
         :mask-closable="false" :loading="modalData.loading" width="auto"
         style="max-width: 1000px; margin-bottom: 300px">
    <div class="modalBox">
      <div>
        <template v-for="(item,index) in modalData.columns" :key="index">
          <div v-if="item.isShowSave !== false" class="row" :style="{display: item.controlType === 'hidden'?'none':''}">
            <div class="label">{{ item.title }}:</div>
            <!--     ==========      长文本     ===========       -->
            <div class="content" v-if="item.controlType === 'textarea'">
              <Input v-model="modalData.data[item.key]" type="textarea" :rows="10" :placeholder="item.node"
                     :disabled="!modalData.editor"/>
            </div>
            <!--     ==========      日期时间     ===========       -->
            <div class="content" v-else-if="item.controlType === 'datetime'">
              <DatePicker style="width: 100%" v-model="modalData.data[item.key]" type="datetime"
                          format="yyyy-MM-dd HH:mm" :placeholder="item.node" :disabled="!modalData.editor"/>
            </div>
            <!--     ==========      选择     ===========       -->
            <div class="content" v-else-if="item.controlType === 'select'">
              <Select v-model="modalData.data[item.key]" clearable :disabled="!modalData.editor"
                      @on-change="onChangeData(item.key,modalData.data[item.key])" label-in-value>
                <Option v-for="(it, inx) in item.filters" :value="it.value" :key="inx">{{ it.label }}</Option>
              </Select>
            </div>
            <!--     ==========      图片     ===========       -->
            <div class="content imgContent" v-else-if="item.controlType === 'img'">
              <div class="imageBox" v-if="modalData.data[item.key]">
                <div class="closeImg">
                  <Icon type="md-close-circle" @click="delete modalData.data[item.key]"/>
                </div>
                <img :src="modalData.data[item.key]" alt="">
              </div>
              <div v-else class="imageBox addImage" @click="updateImage(item.key)">
                <Icon type="md-add-circle"/>
              </div>
            </div>
            <!--     ==========      数字     ===========       -->
            <div class="content" v-else-if="item.controlType === 'number'">
              <Input v-model="modalData.data[item.key]" :placeholder="item.node" type="number"
                     :disabled="!modalData.editor"/>
            </div>
            <!--     ==========      文本     ===========       -->
            <div class="content" :class="{'ivu-form-item-error': !!state.verifyData[item.key]}" v-else>
              <Input v-model="modalData.data[item.key]" :placeholder="item.node" :disabled="!modalData.editor"
                     @on-blur="changDataFn(item,modalData.data[item.key])"
                     @on-focus="inputFocusFn(item,modalData.data[item.key])"
                     clearable/>
              <div v-show="!!state.verifyData[item.key]">{{ item.message }}</div>
            </div>
          </div>
        </template>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {reactive, ref, watch} from "vue";
import {useUploadFileStore} from "@/store/uploadFile.js";

const fileStore = useUploadFileStore();
const emits = defineEmits(['event', 'update:modelValue']);

const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false
  },
  title: {
    type: String,
    default: '数据',
  },
  modalSetting: {
    type: Object,
    default: {
      loading: true,
      show: false,
      editor: true,
      data: {},
      columns: []
    },
    required: false
  }
});

const modalData = ref({
  loading: true,
  show: false,
  editor: true,
  data: {},
  columns: []
})

const state = reactive({
  verifyData: {}
});

watch(() => props.modalSetting, () => {
  const {columns, data} = props.modalSetting;
  for (let i = 0; i < columns.length; i++) {
    const {dfval, key} = columns[i];
    if (dfval && !data[key]) {
      data[key] = dfval;
    }
  }
  modalData.value = {
    ...modalData.value,
    ...props.modalSetting
  }
}, {deep: true, immediate: true})

watch(() => props.modelValue, () => {
  modalData.value.show = props.modelValue;
  state.verifyData = {}
})

watch(() => modalData.value.show, () => {
  emits('update:modelValue', modalData.value.show);
})


/**
 * 事件
 */
const eventFn = (ev) => {
  const {data, columns} = modalData.value;
  if (ev === 'ok' && modalData.value.editor) {
    let h = true;
    // 验证数据
    for (let i = 0; i < columns.length; i++) {
      if (!verifyDataFn(columns[i], data[columns[i].key])) {
        state.verifyData[columns[i].key] = true;
        h = false;
      }
    }
    if (h) {
      emits('event', ev, data);
    } else {
      modalData.value.loading = false;
      setTimeout(() => {
        modalData.value.loading = true;
      });
    }
  } else {
    emits('event', ev, data);
  }
}
/**
 * 数据改变 ( 获取焦点时 )
 */
const inputFocusFn = (column, value) => {
  if (state.verifyData[column.key] != undefined) {
    changDataFn(column, value);
  }
}

/**
 * 数据改变
 */
const changDataFn = (column, value) => {
  if (verifyDataFn(column, value)) {
    state.verifyData[column.key] = false;
  } else {
    state.verifyData[column.key] = true;
  }
}

/**
 * 验证数据
 */
const verifyDataFn = (column, value) => {
  if (!column.isRequire) {
    // 不是必需
    if (column.regStr && (value + "").trim().length > 0) {
      // 验证正则
      return verifyReg(column.regStr, value);
    } else {
      return true;
    }
  } else if (value && (value + "").trim().length > 0) {
    // 必需,验证正则
    return verifyReg(column.regStr, value);
  }
  return false;
}

/**
 * 验证正则
 */
const verifyReg = (regStr, value) => {
  if (regStr && regStr.length > 0) {
    return new RegExp(regStr).test(value);
  }
  return true;
}


/**
 * 上传文件
 */
const updateImage = (key) => {
  fileStore.uploadFile.show = true;
  fileStore.uploadFile.callBack = (data) => {
    modalData.value.data[key] = data.data;
  }
}

const onChangeData = (key, val) => {
  emits('event', 'change', {
    key: key,
    value: val,
    allData: modalData.value.data
  });
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
      display: flex;
      justify-content: right;
    }

    .content {
      flex: 1;

      &.imgContent {
        display: flex;
        align-items: center;

        .imageBox {
          height: 50px;
          border: 1px solid #ccc;
          padding: 4px;
          border-radius: 2px;
          margin: 0 4px;
          position: relative;
          display: flex;
          align-items: center;
          min-width: 50px;
          justify-content: center;

          .closeImg {
            position: absolute;
            top: -8px;
            right: -8px;
            border-radius: 50%;
            justify-content: center;
            align-content: center;
            cursor: pointer;
          }

          img {
            height: 100%;
          }
        }

        .addImage {
          cursor: pointer;
          font-size: 20px;

          &:hover {
            background: #ddd;
          }
        }
      }
    }
  }
}
</style>