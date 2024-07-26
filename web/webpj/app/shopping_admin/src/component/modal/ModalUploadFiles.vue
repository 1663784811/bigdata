<template>
  <Modal
      v-model="fileStore.uploadFile.show"
      title="图片"
      @on-ok="eventFn('ok')"
      @on-cancel="eventFn('cancel')"
      :mask-closable="false"
      :loading="state.loading"
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
            :output-size="state.cropper.outputSize"
            :output-type="state.cropper.outputType"
            :auto-crop="state.cropper.autoCrop"
            :center-box="state.cropper.centerBox"
            :fixed="state.cropper.fixed"
            :fixedNumber="state.cropper.fixedNumber"
            :fixedBox="state.cropper.fixedBox"
            :info="state.cropper.info"
        ></vue-cropper>
        <div v-else class="addImg">
          <Icon type="md-add"/>
          <input
              type="file"
              id="uploads"
              accept="image/png, image/jpeg, image/gif, image/jpg"
              @change="selectImgFun($event)"
          />
        </div>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {useUploadFileStore} from '@/store/uploadFile.js'
import 'vue-cropper/dist/index.css'
import {Message} from "view-ui-plus";
import {uploadFile} from '@/api/api.js'
import {VueCropper} from "vue-cropper";

const fileStore = useUploadFileStore();

const cropper = ref(null);
const state = reactive({
  cropper: {
    img: '', // 裁剪图片的地址
    outputSize: 1, // 裁剪生成图片的质量(可选0.1 - 1)
    outputType: "png", // 裁剪生成图片的格式（jpeg || png || webp）
    info: true, // 图片大小信息
    canScale: true, // 图片是否允许滚轮缩放
    autoCrop: true, // 是否默认生成截图框
    autoCropWidth: 200, // 默认生成截图框宽度
    autoCropHeight: 200, // 默认生成截图框高度
    fixed: true, // 是否开启截图框宽高固定比例
    fixedNumber: [1, 1], // 截图框的宽高比例
    full: false, // false按原比例裁切图片，不失真
    fixedBox: false, // 固定截图框大小，不允许改变
    canMove: false, // 上传图片是否可以移动
    canMoveBox: true, // 截图框能否拖动
    original: false, // 上传图片按照原始比例渲染
    centerBox: true, // 截图框是否被限制在图片里面
    height: true, // 是否按照设备的dpr 输出等比例图片
    infoTrue: true, // true为展示真实输出图片宽高，false展示看到的截图框宽高
    maxImgSize: 3000, // 限制图片最大宽度和高度
    enlarge: 1, // 图片根据截图框输出比例倍数
    fnIndex: 0
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
  ],
  loading: true
});


onMounted(() => {

})

const clickFixed = (index) => {
  state.cropper.fnIndex = index;
  state.cropper.fixedNumber = state.fixedNumber[index].data;
  cropper.value.refresh();
}

/**
 * 事件
 */
const eventFn = (ev) => {
  if (ev === 'ok') {
    cropper.value.getCropBlob(blob => {
      console.log(blob)
      const formData = new FormData();
      formData.append('file', blob, 'face.jpeg');
      uploadFile(formData, 'aaa').then(rest => {
        console.log(rest)
        fileStore.eventFn({
          ev,
          data: rest.data
        });
      }).finally(() => {
        state.loading = false;
        setTimeout(() => {
          state.loading = true;
        })
      })
    });
  } else {
    fileStore.eventFn({
      ev,
      data: ''
    });
  }
}

const selectImgFun = (e) => {
  const file = e.target.files[0];
  if (!/\.(jpg|jpeg|png|JPG|PNG)$/.test(e.target.value)) {
    Message.error("图片类型要求: jpeg、jpg、png!");
    return false;
  }
  // 转化为blob
  const reader = new FileReader();
  reader.onload = eve => {
    let data;
    if (typeof eve.target.result === "object") {
      data = new Blob([eve.target.result]);
    } else {
      data = eve.target.result;
    }
    state.cropper.img = data;
  };
  // 转化为base64
  reader.readAsDataURL(file);
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

  .addImg {
    width: 400px;
    height: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #e1e1e1;
    cursor: pointer;

    &:hover {
      background: #f5f5f5;
    }
  }

}

</style>