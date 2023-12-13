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
          <div>组件ID:{{ item.id }} 名称:{{ item.name }} 类型:{{ item.type }}</div>
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
import {pageSetting} from "@/api/api.js";
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
    data: {}
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
  })
}

const clickComponentItem = (item) => {
  state.pageStatus.select = item.type;
  state.pageStatus.data = item;
}

const addComponent = () => {
  winModal.winData.show = true;
  winModal.winData.url = "";
  winModal.winData.data = {};
  winModal.winData.pageCode = 'sssss'
  winModal.winData.columns = [
    {
      key: 'id'
    }
  ]
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