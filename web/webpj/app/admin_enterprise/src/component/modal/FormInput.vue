<template>
  <div class="formInputContainer">
    <div class="title">开通点餐系统</div>
    <template v-for="(item,index) in state.columns" :key="index">
      <div v-if="item.isShowSave !== false" class="row" :style="{display: item.controlType === 'hidden'?'none':''}">
        <div class="label">{{ item.title }}:</div>
        <!--     ==========      长文本     ===========       -->
        <div class="content" v-if="item.controlType === 'textarea'">
          <Input v-model="state.data[item.key]" type="textarea" :rows="10" :placeholder="item.node"
                 :disabled="!state.editor"/>
        </div>
        <!--     ==========      日期时间     ===========       -->
        <div class="content" v-else-if="item.controlType === 'datetime'">
          <DatePicker style="width: 100%" v-model="state.data[item.key]" type="datetime"
                      format="yyyy-MM-dd HH:mm" :placeholder="item.node" :disabled="!state.editor"/>
        </div>
        <!--     ==========      选择     ===========       -->
        <div class="content" v-else-if="item.controlType === 'select'">
          <Select v-model="state.data[item.key]" clearable :disabled="!state.editor"
                  @on-change="onChangeData(item.key,state.data[item.key])" label-in-value>
            <Option v-for="(it, inx) in item.filters" :value="it.value" :key="inx">{{ it.label }}</Option>
          </Select>
        </div>
        <!--     ==========      图片     ===========       -->
        <div class="content imgContent" v-else-if="item.controlType === 'img'">
          <div class="imageBox" v-if="state.data[item.key]">
            <div class="closeImg">
              <Icon type="md-close-circle" @click="delete state.data[item.key]"/>
            </div>
            <img :src="state.data[item.key]" alt="">
          </div>
          <div v-else class="imageBox addImage" @click="updateImage(item.key)">
            <Icon type="md-add-circle"/>
          </div>
        </div>
        <!--     ==========      数字     ===========       -->
        <div class="content" v-else-if="item.controlType === 'number'">
          <Input v-model="state.data[item.key]" :placeholder="item.node" type="number"
                 :disabled="!state.editor"/>
        </div>
        <!--     ==========      文本     ===========       -->
        <div class="content" :class="{'ivu-form-item-error': !!state.verifyData[item.key]}" v-else>
          <Input v-model="state.data[item.key]" :placeholder="item.node" :disabled="!state.editor"
                 @on-blur="changDataFn(item,state.data[item.key])"
                 @on-focus="inputFocusFn(item,state.data[item.key])"
                 clearable/>
          <div v-show="!!state.verifyData[item.key]">{{ item.message }}</div>
        </div>
      </div>
    </template>
    <div>
      <slot name="footer" :data="state.data"/>
    </div>
  </div>
</template>

<script setup>
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

/**
 * 数据改变 ( 获取焦点时 )
 */
const inputFocusFn = (column, value) => {
  // if (state.verifyData[column.key] != undefined) {
  //   changDataFn(column, value);
  // }
}


/**
 * 数据改变
 */
const changDataFn = (column, value) => {
  // if (verifyDataFn(column, value)) {
  //   state.verifyData[column.key] = false;
  // } else {
  //   state.verifyData[column.key] = true;
  // }
}

/**
 * 上传文件
 */
const updateImage = (key) => {
  // fileStore.uploadFile.show = true;
  // fileStore.uploadFile.callBack = (data) => {
  //   modalData.value.data[key] = data.data;
  // }
}


const onChangeData = (key, val) => {
  emits('event', 'change', {
    key: key,
    value: val,
    allData: state.data
  });
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