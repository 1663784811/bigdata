<template>
  <div class="menuBox">
    <Tree :data="menuArr" @on-contextmenu="handleContextMenu" show-checkbox>
      <template #contextMenu>
        <DropdownItem @click="handleContextMenuSave(false)">添加</DropdownItem>
        <DropdownItem @click="handleContextMenuSave(true)">编辑</DropdownItem>
        <DropdownItem @click="handleContextMenuDelete" style="color: #ed4014">删除</DropdownItem>
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

import {defineEmits, onMounted, ref, watch} from "vue";
import {queryMenu, delMenu, saveMenu, commonRequest} from "@/api/api.js"
import ModalDataList from '@/component/ModalDataList.vue'
import {pageConfig} from '@/store/pageConfig.js'
import {getAddColumns} from '@/api/webUtil.js'
import {Message} from "view-ui-plus";

const emits = defineEmits(['event']);

const props = defineProps({
  treeSetting: {
    type: Object,
    default: {},
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
      data: [
        {
          title: '全部',
          expand: true,
          contextmenu: true,
          children: []
        }
      ]

    });


// ======================================================

const initFn = async () => {
  if (props.treeSetting) {
    saveData.value.columns = getAddColumns(props.treeSetting.columns);

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
  ).then((res) => {
    objConfig.value.data = res.data.root;
  }).catch(err => {
    objConfig.value.data = [];
  }).finally(() => {
    objConfig.value.loading = false;
  })
}
// ======================================================

const usePageConfig = pageConfig();


const menuArr = ref([
  {
    title: '全部',
    expand: true,
    contextmenu: true,
    children: []
  }
]);


const selectData = ref({});


const mountedInitFn = () => {
  queryMenu({}).then((res) => {
    const arr = [];
    if (res.data && res.data.root) {
      const rootArr = res.data.root;
      arr.push(...reTree(rootArr));
    }
    menuArr.value[0].children = arr;
  })
}

/**
 * 处理树结构
 */
const reTree = (list) => {
  for (let i = 0; i < list.length; i++) {
    let itemObj = list[i];
    itemObj.expand = true;
    itemObj.contextmenu = true;
    if (itemObj.children && itemObj.children.length > 0) {
      itemObj.children = reTree(itemObj.children);
    }
  }
  return list;
}


const handleContextMenu = (data, event, position) => {
  console.log(data)
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
    saveData.value.data = {
      pid: saveData.value.data.tid
    }
  }
}

/**
 * 删除
 */
const handleContextMenuDelete = () => {
  delMenu(selectData.value.data).then((res) => {
    console.log(res)
  }).finally(() => {
    mountedInitFn()
  })
}

const saveEventFn = (ev, itemData) => {
  if ('ok' === ev) {
    saveMenu(itemData).then((rest) => {
      saveData.value.data = rest.data;
      Message.success({
        content: `${rest.msg}`,
        onClose: () => {
          saveData.value.show = false;
          mountedInitFn();
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

// =======================================
watch(() => props.treeSetting, () => {
  const setting = props.treeSetting;
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