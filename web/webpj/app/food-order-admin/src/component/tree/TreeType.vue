<template>
  <div class="menuBox" :style="style">
    <Tree :data="objConfig.data" @on-contextmenu="handleContextMenu" @on-check-change="selectTreeFn" show-checkbox>
      <template #contextMenu>
        <DropdownItem @click="handleContextMenuSave(false)">添加</DropdownItem>
        <DropdownItem v-if="objConfig.showMainMenu" @click="handleContextMenuSave(true)">编辑</DropdownItem>
        <DropdownItem v-if="objConfig.showMainMenu" @click="handleContextMenuDelete" style="color: #ed4014">删除
        </DropdownItem>
      </template>
    </Tree>
  </div>
  <ModalDataList
      v-model="saveData.show"
      :modalSetting="saveData"
      @event="saveEventFn"
  />
</template>

<script setup>

import {defineEmits, ref, watch} from "vue";
import {commonRequest} from "@/api/api.js"
import ModalDataList from '@/component/modal/ModalDataList.vue'
import {pageConfig} from '@/store/pageConfig.js'
import {getAddColumns} from '@/api/webUtil.js'
import {Message} from "view-ui-plus";

const emits = defineEmits(['event', 'selectChange']);

const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  },
  style: {
    type: Object,
    default: {},
    required: false
  },
  asTitle: {
    type: String,
    default: null,
    required: false
  },
  editer: {
    type: Boolean,
    default: false,
    required: false
  }
});

const searchObj = ref({
  columns: [],
  queryRequest: {
    url: '',
    parameter: {}
  },
  saveRequest: {
    url: '',
    parameter: {}
  },
  delRequest: {
    url: '',
    parameter: {}
  }
});

const saveData = ref({
  url: '',
  columns: [],
  data: {},
  show: false,
  saveType: ''
});


const objConfig = ref(
    {
      showMainMenu: false,
      data: [
        {
          title: '全部',
          expand: true,
          contextmenu: props.editer,
          children: []
        }
      ]
    });
// ======================================================

const initFn = async () => {
  if (props.setting) {
    saveData.value.columns = getAddColumns(props.setting.columns);
  }
}
initFn();


// ======================================================
/**
 * 加载数据
 */
const loadData = () => {
  objConfig.value.loading = true;
  commonRequest(
      searchObj.value.queryRequest.url,
      {
        ...searchObj.value.queryRequest.pm,
        ...searchObj.value.queryRequest.parameter,
      }
  ).then((rest) => {
    const {data} = rest;
    const arr = [];
    if (data && data.root && data.root.length > 0) {
      arr.push(...reTree(data.root));
    } else if (data) {
      arr.push(...reTree(data));
    }
    objConfig.value.data[0].children = arr;
  }).catch(err => {
    objConfig.value.data = [];
  }).finally(() => {
    objConfig.value.loading = false;
  })
}
// ======================================================

const usePageConfig = pageConfig();

const selectData = ref({});


/**
 * 处理树结构
 */
const reTree = (list) => {
  for (let i = 0; i < list.length; i++) {
    let itemObj = list[i];
    itemObj.expand = true;
    itemObj.contextmenu = props.editer;
    if (props.asTitle) {
      itemObj.title = itemObj[props.asTitle]
    }
    if (itemObj.children && itemObj.children.length > 0) {
      itemObj.children = reTree(itemObj.children);
    }
  }
  return list;
}


const handleContextMenu = (data, event, position) => {
  console.log(data, position)
  if (data.nodeKey === 0) {
    objConfig.value.showMainMenu = false;
  } else {
    objConfig.value.showMainMenu = true;
  }
  selectData.value = data;
}
/**
 * 编辑
 */
const handleContextMenuSave = (isEditor) => {
  saveData.value.show = true;
  if (isEditor) {
    saveData.value.saveType = 'editor'
    saveData.value.data = selectData.value.data || {}
  } else {
    saveData.value.saveType = 'add'
    const {data} = selectData.value
    if (data) {
      saveData.value.data = {
        pid: data.tid
      }
    } else {
      saveData.value.data = {}
    }

  }
}

/**
 * 删除
 */
const handleContextMenuDelete = () => {
  const {url} = searchObj.value.delRequest;
  const id = selectData.value.data.id;
  commonRequest(
      url,
      [id],
      'post'
  ).then((res) => {
    console.log(res)
  }).finally(() => {
    loadData();
  })
}

const saveEventFn = (ev, itemData) => {
  if ('ok' === ev) {
    const {url, parameter} = searchObj.value.saveRequest;
    commonRequest(url, {
      ...parameter,
      ...itemData
    }, 'post').then((rest) => {
      saveData.value.data = rest.data;
      Message.success({
        content: `${rest.msg}`,
        onClose: () => {
          saveData.value.show = false;
          loadData();
        }
      })
    }).catch((err) => {
      Message.error({
        content: `${err}`
      })
      saveData.value.loading = false;
      setTimeout(() => {
        saveData.value.loading = true;
      })
    })

  } else if ('cancel' === ev) {

  }
}

const selectTreeFn = (arr, obj) => {
  const tempArr = []
  for (let i = 0; i < arr.length; i++) {
    if (arr[i].title !== '全部') {
      tempArr.push(arr[i].data)
    }
  }
  emits('selectChange', tempArr, obj);
}

// =======================================
watch(() => props.setting, () => {
  const setting = props.setting;
  console.log("=========== props =======")
  if (setting) {
    if (setting.columns) {
      saveData.value.columns = setting.columns;
    }
    if (setting.columns) {
      objConfig.value.columnsList = setting.columns;
      setTimeout(() => {
        initFn();
        loadData();
      })
    }
    searchObj.value.queryRequest = setting.requestObj.queryRequest;
    searchObj.value.saveRequest = setting.requestObj.saveRequest;
    searchObj.value.delRequest = setting.requestObj.delRequest;
  } else {
    console.log("=========== 未设置数据 =======", setting)
  }
}, {deep: false, immediate: false})
// =======================================


</script>

<style lang="less" scoped>
.menuBox {
  padding: 20px;
  width: 400px;
  background: #fff;
}
</style>