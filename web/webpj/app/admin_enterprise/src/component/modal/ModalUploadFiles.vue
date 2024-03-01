<template>
  <Modal
      v-model="fileStore.uploadFile.show"
      title="图片"
      @on-ok="eventFn('ok')"
      @on-cancel="eventFn('cancel')"
      :mask-closable="false"
      :loading="fileStore.uploadFile.loading"
      width="600px"
  >
    <div class="showImageBox">
      <div class="sizeBox">
        <div class="sizeItem"
             v-for="(item, index) in state.fixedNumber"
             :key="index"
             :class="{'active': index === state.cropper.fnIndex}"
             @click="clickFixed(index)"
        >
          {{ item.text }}
        </div>
      </div>
      <div class="imgBox">
        <vue-cropper
            v-if="state.cropper.img"
            class="cropperImg"
            ref="cropper"
            :img="state.cropper.img"
            :outputSize="state.cropper.size"
            :outputType="state.cropper.outputType"
            autoCrop
            centerBox
            :fixedNumber="state.cropper.fixedNumber"
        ></vue-cropper>
        <div v-else class="addImg">
          <Icon type="md-add" />
        </div>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {defineEmits, ref, watch, provide, onMounted, reactive} from "vue";
import {findPageWebImage} from '@/api/api.js'
import {useUploadFileStore} from '@/store/uploadFile.js'
import 'vue-cropper/dist/index.css'
import {VueCropper} from "vue-cropper";


const fileStore = useUploadFileStore();

const state = reactive({
  cropper: {
    img: '',
    size: null,
    outputType: null,
    fnIndex: 0,
    fixedNumber: [1, 1]
  },
  fixedNumber: [
    {
      text: '1:1',
      data: [1, 1]
    },
    {
      text: '3:4',
      data: [4, 3]
    },
    {
      text: '16:9',
      data: [16, 9]
    }
  ]
});


onMounted(() => {

})

const clickFixed = (index) => {
  state.cropper.fnIndex = index;
  state.cropper.fixedNumber = state.fixedNumber[index].data;
}

/**
 * 事件
 */
const eventFn = (ev) => {
  fileStore.eventFn({
    ev,
    data: 'ssss'
  });
}

</script>

<style scoped lang="less">
.showImageBox {
  display: flex;

  .sizeBox {
    width: 140px;

    .sizeItem {
      margin-bottom: 10px;
      width: 100px;
      text-align: center;
      padding: 10px;
      border-radius: 4px;
      border: 1px solid #e7e7e7;
      cursor: pointer;

      &.active {
        background: #17233d;
        color: #fff;
      }
    }
  }

  .cropperImg {
    width: 400px;
    height: 400px;
    border: 1px solid #ccc;
  }
  .addImg{
    width: 400px;
    height: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #e1e1e1;
    cursor: pointer;
    &:hover{
      background: #f5f5f5;
    }
  }

}

</style>