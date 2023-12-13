<template>
  <Drawer
      placement="left"
      title="配置面板"
      v-model="usePageConfig.componentConfig.show"
      width="1200"
      :mask-closable="false"
  >
    <div class="pagInfo">
      <div>选择组件:
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="addComponent">添加</Button>
      </div>
      <div class="comBox">
        <div class="comItem" v-for="(item, index) in state.pageObj" :key="index" @click="clickComponentItem(item)">
          <div>组件ID:{{ item.id }} 名称:{{ item.name }} 类型:{{ item.type }}
            <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="updateComponent(item)">修改</Button>
          </div>
        </div>
      </div>
    </div>
    <div class="commonTableBox" v-if="state.pageStatus.select === 'commonTable'">
      <ConfigCommonTable :setting="state.pageStatus.data"/>
    </div>
    <div class="newTable" v-if="state.pageStatus.select === 'newTable'">
      <ConfigNewTable :setting="state.pageStatus.data"/>
    </div>
    <div class="selectDataDrawer" v-if="state.pageStatus.select === 'selectDataDrawer'">
      <ConfigSelectData :setting="state.pageStatus.data"/>
    </div>
  </Drawer>
  <div class="configOperation" v-show="state.pageStatus.showOperation">
    <div class="pageCodeItem"
         v-for="(item, index) in usePageConfig.componentConfig.pageCodeList"
         :key="index"
         @click="loadData(item)"
    >
      {{ item }}
    </div>
  </div>
</template>
<script setup>
import ConfigCommonTable from './ConfigCommonTable.vue'
import ConfigNewTable from './ConfigNewTable.vue'
import ConfigSelectData from './ConfigSelectData.vue'
import {onMounted, reactive} from "vue";
import {pageConfig} from "@/store/pageConfig.js";
import {pageSetting, findIdCPageComponents} from "@/api/api.js";
import {useRoute, useRouter} from "vue-router";
import {useWinModal} from "@/store/winModal.js";


const winModal = useWinModal();
const usePageConfig = pageConfig();
const router = useRouter();
const route = useRoute();


const state = reactive({
  pageStatus: {
    showOperation: false,
    select: '',
    data: {},
    pageId: ''
  },
  pageObj: {}
})


onMounted(() => {
  setTimeout(() => {
    if (route.query.debugger) {
      state.pageStatus.showOperation = true;
    }
  }, 1000)
})

const loadData = (pageCode) => {
  pageSetting({
    pageCode
  }).then((rest) => {
    usePageConfig.componentConfig.show = true;
    const {data} = rest
    state.pageObj = data;
    for (const dataKey in data) {
      state.pageStatus.pageId = data[dataKey].pageId;
    }
  })
}

const clickComponentItem = (item) => {
  state.pageStatus.select = item.type;
  state.pageStatus.data = item;
}

const updateComponent = (item) => {
  winModal.winData.show = true;
  initSave();

  findIdCPageComponents({
    id: item.id
  }).then(rest => {
    console.log('ssssssssssss', rest)
    winModal.winData.data = rest.data;
  })

}

const addComponent = () => {
  winModal.winData.show = true;
  initSave();
  winModal.winData.data = {
    pageId: state.pageStatus.pageId
  };
}


const initSave = () => {
  winModal.winData.url = "/admin/config/cpagecomponents/saveCPageComponents";
  winModal.winData.data = {};
  winModal.winData.pageCode = 'sssss'
  winModal.winData.columns = [{
    "width": 60,
    "key": "id",
    "title": "id",
    "type": "selection",
    "length": 10,
    "controlType": "input",
    "isShowColumn": true,
    "javaWhere": "equals",
    "javaType": "integer",
    "isShowSave": true
  }, {
    "width": 100,
    "key": "icon",
    "title": "icon图标",
    "length": 255,
    "controlType": "input",
    "isShowColumn": true,
    "isWhere": true,
    "javaType": "string",
    "isShowSave": true
  }, {
    "key": "name",
    "title": "名称",
    "length": 45,
    "controlType": "input",
    "isShowColumn": true,
    "isWhere": true,
    "javaWhere": "lk",
    "javaType": "string",
    "isShowSave": true,
    "isShowSearch": true
  }, {
    "key": "componentsCode",
    "title": "组件ID",
    "length": 45,
    "controlType": "input",
    "isShowColumn": true,
    "isWhere": true,
    "javaType": "string",
    "isShowSave": true
  }, {
    "key": "type",
    "title": "类型",
    "length": 45,
    "controlType": "input",
    "isShowColumn": true,
    "isWhere": true,
    "javaType": "string",
    "isShowSave": true
  }, {
    "key": "note",
    "title": "备注",
    "length": 255,
    "isShowColumn": true,
    "isWhere": true,
    "javaWhere": "like",
    "javaType": "string",
    "isShowSave": true
  }, {
    "key": "pageId",
    "title": "页面ID",
    "length": 45,
    "controlType": "input",
    "isShowColumn": false,
    "isWhere": true,
    "javaWhere": "like",
    "javaType": "string",
    "isShowSave": true
  }]
}

</script>


<style lang="less">
.pagInfo {
  .comBox {
    background: #f5f5f5;
    padding: 4px;

    .comItem {
      cursor: pointer;

      &:hover {
        background: #e5e5e5;
      }
    }
  }
}

.configOperation {
  position: fixed;
  right: 10px;
  top: 300px;
  z-index: 99;
  background: #fff;
  min-width: 50px;
  border: 1px solid #ccc;
  padding: 6px;
  border-radius: 4px;
  min-height: 100px;

  .pageCodeItem {
    display: flex;
    justify-content: center;
    align-items: center;
    background: #ccc;
    margin: 2px 0;
    cursor: pointer;
  }
}
</style>