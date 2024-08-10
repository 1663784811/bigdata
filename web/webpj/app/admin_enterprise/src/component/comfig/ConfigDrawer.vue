<template>
  <Drawer
      title="配置面板"
      v-model="configModule.configPage.show"
      width="1200"
      :mask-closable="false"
  >
    <div class="pagInfo">
      <div>
        <div>页面ID:{{ configModule.configPage.pageCode }}</div>
      </div>
      <div>选择组件:
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" size="small" @click="addComponent">添加</Button>
      </div>
      <div class="comBox">
        <div class="comItem" v-for="(item, index) in state.pageObj" :key="index" @click="clickComponentItem(item)">
          <div>组件ID:{{ item.id }} 名称:{{ item.name }} 类型:{{ item.type }}
            <Button class="dataBtn" type="warning" icon="md-create" size="small" @click="updateComponent(item)"/>

            <Button class="dataBtn" type="error" icon="md-trash" size="small" @click="delComponent(item)"/>
            <Button class="dataBtn" type="warning" icon="md-create" size="small" @click="commonTableToNewTable(item)"
                    v-if="item.type === 'commonTable'">转新表格
            </Button>
            <Button class="dataBtn" type="success" icon="md-create" size="small" @click="commonTableToNewTable(item)">
              组件日志
            </Button>
          </div>
        </div>
      </div>
    </div>
    <!--  新表格  -->
    <div class="newTable" v-if="configModule.configPage.select === 'newTable'">
      <ConfigNewTable :setting="configModule.configPage.data"/>
    </div>
    <!--  数据选择  -->
    <div class="selectData" v-if="configModule.configPage.select === 'selectData'">
      <ConfigSelectData :setting="configModule.configPage.data"/>
    </div>
    <!--  数据树  -->
    <div class="dataTree" v-if="configModule.configPage.select === 'dataTree'">
      <ConfigDataTree :setting="configModule.configPage.data"/>
    </div>
    <!--  数据树  -->
    <div class="dataTree" v-if="configModule.configPage.select === 'pageForm'">
      <ConfigPageForm :setting="configModule.configPage.data"/>
    </div>
  </Drawer>
  <!-- ======================= -->
  <div class="configOperation" v-show="configModule.configPage.showOperation">
    <div @click="configModule.configPage.showOperation = false">xxxx</div>
    <div class="pageContent" v-for="inx in 3" :key="inx">
      <div class="pageTitle">
        <div v-if="inx === 1">企业页面</div>
        <div v-else-if="inx === 2">APP管理</div>
        <div v-else-if="inx === 3">门店管理</div>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" size="small" @click="addPage">添加页面</Button>
      </div>
      <div class="pageList">
        <div class="pageItem" v-for="(item, index) in state.pageList" :key="index" v-show="item.type === (inx -1)">
          <div @click="selectPage(item)">{{ item.name + "\t" + item.pageCode }}</div>
          <div class="rightBtn">
            <Button class="dataBtn" type="success" icon="md-create" size="small" @click="updatePage(item)"/>
            <Button class="dataBtn" type="warning" icon="ios-copy" size="small" @click="copyPage(item)"/>
            <Button class="dataBtn" type="error" icon="md-trash" size="small" @click="delPage(item)"/>
          </div>
        </div>
      </div>
    </div>
    <div class="componentContent">
      <div class="pageCodeItem"
           v-for="(item, index) in usePageConfig.componentConfig.pageCodeList"
           :key="index"
           @click="loadData(item)"
      >
        {{ item }}
      </div>
      <div class="pageCodeItem" @click="clickSqlConfigFn">SQL配置</div>
    </div>
  </div>
</template>
<script setup>
import ConfigNewTable from './configPage/ConfigNewTable.vue'
import ConfigSelectData from './configPage/ConfigSelectData.vue'
import ConfigDataTree from './configPage/ConfigDataTree.vue'
import ConfigPageForm from './configPage/ConfigPageForm.vue'


import {onMounted, reactive} from "vue";
import {pageConfig} from "@/store/pageConfig.js";
import {useConfigModule} from "@/store/configModule.js";
import {pageSetting, findIdCPageComponents, commonRequest, copyCPageRequest} from "@/api/api.js";
import {useRoute, useRouter} from "vue-router";
import {useWinModal} from "@/store/winModal.js";
import {Message, Modal} from "view-ui-plus";


const winModal = useWinModal();
const usePageConfig = pageConfig();
const router = useRouter();
const route = useRoute();
const configModule = useConfigModule();


const state = reactive({
  pageObj: {},
  pageList: []
})


onMounted(() => {
  setTimeout(() => {
    if (route.query.debugger) {
      configModule.configPage.showOperation = true;
      loadPage();
    }
  }, 1000)
  loadPage();
  if (configModule.configPage.show) {
    loadData(configModule.configPage.pageCode);
  }
})

const loadPage = () => {
  commonRequest("/admin/${eCode}/common/query", {
    code: 'select_c_page',
    size: 10000
  }).then((rest) => {
    const {data} = rest;
    state.pageList = data;
  })
}

const initPage = () => {

  winModal.winData.columns = [
    {
      "key": "pageCode",
      "title": "pageCode",
      "length": 32,
      "controlType": "input",
      "isShowColumn": true,
      "width": "160",
      "isShowSearch": true,
    },
    {
      "key": "pageIcon",
      "title": "图标",
      "length": 65535,
      "controlType": "input",
      "isShowColumn": true,
      "tooltip": false,
      "width": "100"
    },
    {
      "key": "name",
      "title": "名称",
      "length": 32,
      "controlType": "input",
      "isShowColumn": true,
      "isShowSearch": true,
      "javaWhere": "lk"
    },
    {
      "key": "type",
      "title": "类型",
      "length": 32,
      "controlType": "select",
      "isShowColumn": true,
      "isShowSearch": true,
      "javaWhere": "lk",
      "filters": [
        {
          "value": "0",
          "label": "企业页面"
        },
        {
          "value": "1",
          "label": "APP管理"
        },
        {
          "value": "2",
          "label": "门店管理"
        }
      ],
    },
    {
      "key": "note",
      "title": "备注",
      "length": 255,
      "controlType": "textarea",
      "isShowColumn": true,
      "javawhere": "like",
    }
  ]

}

const addPage = () => {
  winModal.winData.show = true;
  winModal.winData.url = "/admin/config/page/saveCPage";
  winModal.winData.data = {};
  winModal.winData.pageCode = ''
  initPage();
}

const updatePage = (row) => {
  winModal.winData.show = true;
  winModal.winData.url = "/admin/config/page/saveCPage";
  winModal.winData.data = row;
  winModal.winData.pageCode = ''
  initPage();
}

const delPage = (row) => {


}

const copyPage = (row) => {
  Modal.confirm({
    title: `您是否要复制【 ${row.name} 】页面?`,
    content: `${row.name}`,
    okText: '',
    loading: true,
    onOk: () => {
      copyCPageRequest(row).then(rest => {
        Modal.remove();
        Message.success({
          content: `${rest.msg}`
        })
        loadPage();
      })
    },
  });

}

const selectPage = (row) => {
  configModule.configPage.pageId = row.tid;
  loadData(row.pageCode)
}

const clickSqlConfigFn = () => {
  configModule.sqlConfig.show = true;
}

/**
 * 加载数据
 */
const loadData = (pageCode) => {
  configModule.configPage.pageCode = pageCode;
  pageSetting({
    pageCode
  }).then((rest) => {
    configModule.configPage.show = true;
    const {data} = rest
    state.pageObj = data;
    for (const dataKey in data) {
      configModule.configPage.pageId = data[dataKey].pageId;
    }
  })
}

const clickComponentItem = (item) => {
  configModule.configPage.select = item.type;
  configModule.configPage.data = item;
}

const updateComponent = (item) => {
  winModal.winData.show = true;
  initSave();
  winModal.winData.changeDataFn = changeDataFn;
  findIdCPageComponents({
    id: item.id
  }).then(rest => {
    winModal.winData.data = rest.data;
  })
}

const delComponent = (row) => {
  Modal.confirm({
    title: '是否删除?',
    okText: '删除',
    loading: true,
    onOk: () => {
      commonRequest("/admin/{eCode}/common/del", {
        data: {
          tid: row.tid,
        },
        code: 'del_c_page_components'
      }, 'post').then((rest) => {
        Message.success({
          content: `${rest.data ? rest.data : rest.msg}`,
          onClose: () => {
            Modal.remove();
            loadPage();
          }
        })
      }).catch((err) => {
        console.log(err);
        Message.error({
          content: `${err}`,
        })
      })
    },
  });
}

const commonTableToNewTable = (row) => {
  Modal.confirm({
    title: '是否转至新表格?',
    okText: '确定',
    loading: true,
    onOk: () => {
      commonRequest("/tx/config/page/commonTableToNewTable", {
        id: row.id
      }).then((rest) => {
        Message.success({
          content: `${rest.data ? rest.data : rest.msg}`,
          onClose: () => {
            Modal.remove();
            loadPage();
          }
        })
      }).catch((err) => {
        console.log(err);
        Message.error({
          content: `${err}`,
        })
      })
    },
  });
}


const addComponent = () => {
  winModal.winData.show = true;
  initSave();
  winModal.winData.data = {
    pageId: configModule.configPage.pageId
  };
  winModal.winData.changeDataFn = changeDataFn;
}

const changeDataFn = (dataObj) => {
  if (dataObj.key === 'type') {
    if (dataObj.allData) {
      if (!dataObj.allData.componentsCode) {
        winModal.winData.data.componentsCode = dataObj.value;
      }
    }
  }
}


const initSave = () => {
  winModal.winData.url = "/admin/config/cpagecomponents/saveCPageComponents";
  winModal.winData.data = {};
  winModal.winData.pageCode = 'sssss'
  winModal.winData.columns = [
    {
      "width": 100,
      "key": "icon",
      "title": "icon图标",
      "length": 255,
      "controlType": "input",
      "isShowColumn": true,
      "isShowSave": true
    }, {
      "key": "name",
      "title": "名称",
      "length": 45,
      "controlType": "input",
      "isShowColumn": true,
      "javaWhere": "lk",
      "isShowSave": true,
      "isShowSearch": true
    }, {
      "key": "componentsCode",
      "title": "组件ID",
      "length": 45,
      "controlType": "input",
      "isShowColumn": true,
      "isShowSave": true
    }, {
      "key": "type",
      "title": "类型",
      "length": 45,
      "controlType": "select",
      "isShowColumn": true,
      "isShowSave": true,
      "filters": [
        {
          "value": "newTable",
          "label": "新表格"
        },
        {
          "value": "selectData",
          "label": "数据选择"
        },
        {
          "value": "dataTree",
          "label": "数据树"
        },
        {
          "value": "pageForm",
          "label": "页面表单"
        }
      ],
    },
    {
      "key": "note",
      "title": "备注",
      "length": 255,
      "isShowColumn": true,
      "isShowSave": true
    }, {
      "key": "pageId",
      "title": "页面ID",
      "length": 45,
      "controlType": "input",
      "isShowColumn": false,
      "isShowSave": true
    }]
}

</script>


<style lang="less">
.pagInfo {
  .comBox {
    background: #f5f5f5;
    padding: 4px;
    margin: 16px 0;

    .comItem {
      cursor: pointer;

      &:hover {
        background: #e5e5e5;
      }

      .dataBtn {
        margin-right: 10px;
      }
    }
  }
}

.configOperation {
  position: fixed;
  right: 100px;
  top: 10%;
  z-index: 99;
  background: #fff;
  min-width: 50px;
  border: 1px solid #ccc;
  padding: 6px;
  border-radius: 4px;
  min-height: 100px;
  width: 1920px;
  display: flex;

  .pageContent {
    flex: 1;
    background: #f5f5f5;
    margin-right: 20px;

    .pageTitle {
      display: flex;
      justify-content: space-between;
    }

    .pageList {
      .pageItem {
        cursor: pointer;
        display: flex;
        justify-content: space-between;
        margin: 2px 0;

        &:hover {
          background: #ccc;
        }

        .rightBtn {
          display: flex;

          .dataBtn {
            margin: 0 4px;
          }
        }
      }
    }
  }

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
