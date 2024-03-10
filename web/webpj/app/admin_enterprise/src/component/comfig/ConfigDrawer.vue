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
          </div>
        </div>
      </div>
    </div>
    <div class="commonTableBox" v-if="configModule.configPage.select === 'commonTable'">
      <ConfigCommonTable :setting="configModule.configPage.data"/>
    </div>
    <div class="newTable" v-if="configModule.configPage.select === 'newTable'">
      <ConfigNewTable :setting="configModule.configPage.data"/>
    </div>
    <div class="selectData" v-if="configModule.configPage.select === 'selectData'">
      <ConfigSelectData :setting="configModule.configPage.data"/>
    </div>
    <div class="dataTree" v-if="configModule.configPage.select === 'dataTree'">
      <ConfigDataTree :setting="configModule.configPage.data"/>
    </div>
  </Drawer>
  <div class="configOperation" v-show="configModule.configPage.showOperation">
    <div @click="configModule.configPage.showOperation = false">xxxx</div>
    <div class="pageContent">
      <div class="pageTitle">
        <div>页面</div>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" size="small" @click="addPage">添加页面</Button>
      </div>
      <div class="pageList">
        <div class="pageItem" v-for="(item, index) in state.pageList" :key="index">
          <div @click="selectPage(item)">{{ item.name +"\t"+ item.pageCode }}</div>
          <div class="rightBtn">
            <Button class="dataBtn" type="warning" icon="md-create" size="small" @click="updatePage(item)"/>
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
import ConfigCommonTable from './ConfigCommonTable.vue'
import ConfigNewTable from './ConfigNewTable.vue'
import ConfigSelectData from './ConfigSelectData.vue'
import ConfigDataTree from './ConfigDataTree.vue'


import {onMounted, reactive} from "vue";
import {pageConfig} from "@/store/pageConfig.js";
import {useConfigModule} from "@/store/configModule.js";
import {pageSetting, findIdCPageComponents, commonRequest} from "@/api/api.js";
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
  commonRequest("/admin/{eCode}/common/query", {
    code: 'select_c_page',
    size: 1000
  }).then((rest) => {
    const {data} = rest;
    console.log('eeeeeeeeeeeeeeeeeeeeeee', data)
    state.pageList = data;
  })
}

const initPage = () => {

  winModal.winData.columns = [{
    "width": 60,
    "key": "id",
    "title": "id",
    "type": "selection",
    "length": 10,
    "controltype": "hidden",
    "max": "",
    "min": "",
    "isShowColumn": true,
    "javawhere": "equals",
    "javatype": "integer",
    "isShowSave": false
  }, {
    "key": "pageCode",
    "title": "pageCode",
    "length": 32,
    "controltype": "input",
    "isShowColumn": true,
    "iswhere": true,
    "javawhere": "like",
    "javatype": "string",
    "width": "160",
    "isShowSearch": true,
    "javaWhere": "lk"
  }, {
    "key": "pageIcon",
    "title": "图标",
    "length": 65535,
    "controltype": "textarea",
    "isShowColumn": true,
    "iswhere": true,
    "javawhere": "like",
    "javatype": "string",
    "tooltip": false,
    "width": "100"
  }, {
    "key": "name",
    "title": "名称",
    "length": 32,
    "controltype": "input",
    "isShowColumn": true,
    "iswhere": true,
    "javawhere": "like",
    "javatype": "string",
    "isShowSearch": true,
    "javaWhere": "lk"
  }, {
    "key": "createTime",
    "title": "创建时间",
    "length": 19,
    "controltype": "datetime",
    "isShowColumn": true,
    "iswhere": true,
    "javawhere": "equals",
    "javatype": "date",
    "width": "160",
    "isShowSave": false
  }, {
    "key": "note",
    "title": "备注",
    "length": 255,
    "controltype": "input",
    "isShowColumn": true,
    "iswhere": true,
    "javawhere": "like",
    "javatype": "string",
    "controlType": "textarea"
  }, {
    "key": "tid",
    "title": "tid",
    "controlType": "text",
    "isShowColumn": false,
    "isWhere": true,
    "javaWhere": "like",
    "javaType": "string",
    "type": "text",
    "tooltip": true,
    "width": "150",
    "isShowSave": false
  }]

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

const selectPage = (row) => {
  configModule.configPage.pageId = row.tid;
  loadData(row.pageCode)
}

const clickSqlConfigFn = () => {
  configModule.sqlConfig.show = true;
}

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
        tid: row.tid,
        code: 'del_c_page_components'
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
      "controlType": "select",
      "isShowColumn": true,
      "isWhere": true,
      "javaType": "string",
      "isShowSave": true,
      "filters": [
        {
          "value": "commonTable",
          "label": "公共表格"
        },
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
        }
      ],
    },
    {
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
    margin: 16px 0;

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
  left: 50%;
  top: 10%;
  z-index: 99;
  background: #fff;
  min-width: 50px;
  border: 1px solid #ccc;
  padding: 6px;
  border-radius: 4px;
  min-height: 100px;
  width: 450px;
  display: flex;

  .pageContent {
    width: 300px;
    background: #f5f5f5;

    .pageTitle {
      display: flex;
      justify-content: space-between;
    }

    .pageList {
      .pageItem {
        cursor: pointer;
        display: flex;
        justify-content: space-between;

        &:hover {
          background: #ccc;
        }

        .rightBtn {
          display: flex;
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
