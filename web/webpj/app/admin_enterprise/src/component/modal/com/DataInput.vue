<template>
  <template v-for="(item,index) in settings.columns" :key="index">
    <div v-if="item.isShowSave !== false" class="row" :style="{display: item.controlType === 'hidden'?'none':''}">
      <div class="label">{{ item.title }}:</div>
      <!--     ==========      长文本     ===========       -->
      <div class="content" v-if="item.controlType === 'textarea'">
        <Input v-model="settings.data[item.key]" type="textarea" :rows="10" :placeholder="item.node"
               :disabled="!settings.editor"/>
      </div>
      <!--     ==========      日期时间     ===========       -->
      <div class="content" v-else-if="item.controlType === 'datetime'">
        <DatePicker style="width: 100%" v-model="settings.data[item.key]" type="datetime"
                    format="yyyy-MM-dd HH:mm" :placeholder="item.node" :disabled="!settings.editor"/>
      </div>
      <!--     ==========      选择     ===========       -->
      <div class="content" v-else-if="item.controlType === 'select'">
        <Select v-model="settings.data[item.key]" clearable :disabled="!settings.editor"
                @on-change="onChangeData(item.key,settings.data[item.key])" label-in-value>
          <Option v-for="(it, inx) in item.filters" :value="it.value" :key="inx">{{ it.label }}</Option>
        </Select>
      </div>
      <!--     ==========      图片     ===========       -->
      <div class="content imgContent" v-else-if="item.controlType === 'img'">
        <div class="imageBox" v-if="settings.data[item.key]">
          <div class="closeImg">
            <Icon type="md-close-circle" @click="delete settings.data[item.key]"/>
          </div>
          <img :src="settings.data[item.key]" alt="">
        </div>
        <div v-else class="imageBox addImage" @click="updateImage(item.key)">
          <Icon type="md-add-circle"/>
        </div>
      </div>
      <!--     ==========      数字     ===========       -->
      <div class="content" v-else-if="item.controlType === 'number'">
        <Input v-model="settings.data[item.key]" :placeholder="item.node" type="number"
               :disabled="!settings.editor"/>
      </div>
      <!--     ==========      文本     ===========       -->
      <div class="content" :class="{'ivu-form-item-error': !!state.verifyData[item.key]}" v-else>
        <Input v-model="settings.data[item.key]" :placeholder="item.node" :disabled="!settings.editor"
               @on-blur="changDataFn(item,settings.data[item.key])"
               @on-focus="inputFocusFn(item,settings.data[item.key])" clearable/>
        <div v-show="!!state.verifyData[item.key]">{{ item.message }}</div>
      </div>
    </div>
  </template>


</template>

<script setup>

import {useUploadFileStore} from "@/store/uploadFile";
import {reactive} from "vue";

const fileStore = useUploadFileStore();
const emits = defineEmits(['event', 'update:modelValue']);
const props = defineProps({
  settings: {
    type: Object,
    default: {
      data: {},
      editor: true,
      columns: []
    },
    required: false
  }
});

const state = reactive({
  verifyData: {}
});


const onChangeData = (key, val) => {
  emits('event', 'change', {
    key: key,
    value: val,
    allData: props.settings.data
  });
}

/**
 * 上传文件
 */
const updateImage = (key) => {
  fileStore.uploadFile.show = true;
  fileStore.uploadFile.callBack = (data) => {
    props.settings.data[key] = data.data;
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


</script>

<style scoped lang="less">

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
</style>