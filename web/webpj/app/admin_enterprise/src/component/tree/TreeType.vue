<template>
  <div class="menuBox" :style="style">
    <Tree :data="state.objConfig.data" @on-contextmenu="handleContextMenu" @on-check-change="selectTreeFn" show-checkbox>
      <template #contextMenu>
        <DropdownItem @click="handleContextMenuSave(false)">添加</DropdownItem>
        <DropdownItem v-if="state.objConfig.showMainMenu" @click="handleContextMenuSave(true)">编辑</DropdownItem>
        <DropdownItem v-if="state.objConfig.showMainMenu" @click="handleContextMenuDelete" style="color: #ed4014">删除
        </DropdownItem>
      </template>
    </Tree>
  </div>
  <modal-data-list v-model="state.saveObj.show" :modalSetting="state.saveObj" @event="saveEventFn"/>
</template>

<script setup>

import {onMounted, reactive, watch} from "vue";
import {commonRequest} from "@/api/api.js"
import {pageConfig} from '@/store/pageConfig.js'
import {Message} from "view-ui-plus";

const emits = defineEmits(['event', 'selectChange']);

const usePageConfig = pageConfig();

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
  },
  selectData: {}
});

const state = reactive({
  columns: [],
  data: {},
  show: false,
  saveObj: {
    columns: [],
    url: ''
  },
  objConfig: {
    showMainMenu: false,
    data: [
      {
        title: '全部',
        expand: true,
        contextmenu: props.editer,
        children: []
      }
    ]
  }
})
// ======================================================

onMounted(() => {
  initFn();
});
const initFn = async () => {
  loadData();
}


// ======================================================
/**
 * 加载数据
 */
const loadData = () => {
  state.loading = true;
  const {setting} = props;
  if (setting.queryObj) {
    commonRequest(
        setting.queryObj.url,
        {
          ...setting.queryObj.parameter,
          ...setting.queryObj.pm,
        }
    ).then((rest) => {
      const {data} = rest;
      const arr = [];
      if (data && data.root && data.root.length > 0) {
        arr.push(...reTree(data.root));
      } else if (data) {
        arr.push(...reTree(data));
      }
      state.objConfig.data[0].children = arr;
    }).catch(err => {
      state.objConfig.data = [];
    }).finally(() => {
      state.objConfig.loading = false;
    })
  }
}
// ======================================================

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

/**
 * 点击右键菜单
 */
const handleContextMenu = (data, event, position) => {
  if (data.nodeKey === 0) {
    state.objConfig.showMainMenu = false;
  } else {
    state.objConfig.showMainMenu = true;
  }
  state.selectData = data.data;
}
/**
 * 编辑
 */
const handleContextMenuSave = (isEditor) => {
  const {setting} = props;
  if (isEditor) {
    state.saveObj = setting.updateObj;
    state.saveObj.data = state.selectData;
  } else {
    state.saveObj = setting.addObj;
    const selectData = state.selectData;
    if (selectData) {
      state.saveObj.data = {
        pid: selectData.tid
      }
    } else {
      state.saveObj.data = {}
    }
  }
  state.saveObj.show = true;
}

/**
 * 删除
 */
const handleContextMenuDelete = () => {
  const {url} = searchObj.value.delRequest;
  const id = state.selectData.data.id;
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
    const {url, parameter} = state.saveObj;
    commonRequest(url, {
      ...parameter,
      ...itemData
    }, 'post').then((rest) => {
      state.saveObj.data = rest.data;
      Message.success({
        content: `${rest.msg}`,
        onClose: () => {
          state.saveObj.show = false;
          loadData();
        }
      })
    }).catch((err) => {
      Message.error({
        content: `${err}`
      })
      state.saveObj.loading = false;
      setTimeout(() => {
        state.saveObj.loading = true;
      })
    })

  } else if ('cancel' === ev) {

  }
}

/**
 * 选择树
 */
const selectTreeFn = (arr, obj) => {
  const tempArr = []
  for (let i = 0; i < arr.length; i++) {
    if (arr[i].title !== '全部') {
      tempArr.push(arr[i].data)
    }
  }
  emits('selectChange', tempArr, obj);
}

watch(() => props.setting, () => {
  initFn();
})


</script>

<style lang="less" scoped>
.menuBox {
  padding: 20px;
  width: 400px;
  background: #fff;
}
</style>