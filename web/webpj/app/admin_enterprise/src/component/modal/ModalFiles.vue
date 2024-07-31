<!--       图列表弹出层        -->
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
    <!-- ========================================   搜索   ======================================== -->
    <div class="searchBox">
      <div class="searchRow">
        <div class="inputLabel">图片:</div>
        <Input placeholder="搜索" style="width: auto"/>
      </div>
      <div class="btnBox">
        <Button class="btn" type="success" icon="ios-search">搜索</Button>
      </div>
      <Upload multiple :format="['jpg','jpeg','png']" action="http://192.168.0.130:8080/admin/file/upload">
        <Button icon="ios-cloud-upload-outline">上传图片</Button>
      </Upload>
    </div>
    <!-- ========================================   图片   ======================================== -->
    <div class="modalBox">
      <div class="imageBox">
        <div class="imageItem" v-for="(item, index) in state.photoObj.data" :key="index">
          <div class="closeImg">
            <Icon type="md-close-circle"/>
          </div>
          <img :src="'http://192.168.0.130:8080/admin/file/download?code='+item.tid" alt="">
        </div>
      </div>
      <div class="pageBox">
        <Page :total="state.photoObj.pageData.total" :page-size="state.photoObj.pageData.size" @on-change="changePage" show-elevator/>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import { ref, watch, provide, onMounted, reactive} from "vue";
import {findPageWebImage} from '@/api/api.js'

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

const state = reactive({
  photoObj: {
    data: [],
    pageData: {
      page: 1,
      total: 0,
      size: 30
    }
  }
})

onMounted(() => {
  console.log("sssssssssssssssss")
  // loaData();
})

/**
 * 加载数据
 */
const loaData = () => {
  findPageWebImage().then((rest) => {
    const {data} = rest;
    state.photoObj.data = data;
  })
}

const changePage = () => {


}

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


</script>

<style scoped lang="less">
.searchBox {
  display: flex;
  padding: 10px 10px 20px;
  border-bottom: 1px solid #e9e9e9;

  .searchRow {
    display: flex;
    align-items: center;
    margin-right: 10px;

    .inputLabel {
      margin-right: 4px;
    }
  }

  .btnBox {
    margin-left: 10px;

    .btn {
      margin: 0 6px;
    }
  }
}

.modalBox {
  .imageBox {
    display: flex;
    flex-wrap: wrap;
    min-height: 30vh;

    .imageItem {
      height: 70px;
      width: 70px;
      padding: 4px;
      border-radius: 2px;
      border: 1px solid #ddd;
      margin: 6px;
      background: #f3f1f1;
      position: relative;

      .closeImg {
        position: absolute;
        top: -8px;
        right: -8px;
        border-radius: 50%;
        justify-content: center;
        align-content: center;
        cursor: pointer;
      }

      &:hover {
        cursor: pointer;
        background: #efefef;
      }

      img {
        max-height: 100%;
        max-width: 100%;
      }
    }
  }

  .pageBox {
    border-top: 1px solid #ededed;
    padding-top: 10px;
  }
}
</style>