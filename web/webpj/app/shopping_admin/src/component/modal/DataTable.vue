<template>
  <div class="commonTable">

    <!-- ========================================   搜索   ======================================== -->
    <div class="searchBox" v-if="state.searchObj.show">
      <template v-for="(item,index) in state.searchObj.columns" :key="index">
        <template v-if="item.show">
          <template v-if="item.name == '搜索'">
            <div class="searchRow" v-for="(pt,inx) in item.parameter" :key="inx">
              <div class="inputLabel">{{ pt.title }}:</div>
              <Input v-model="pt.val" :placeholder="pt.javaWhere" clearable style="width: auto"/>
            </div>
          </template>
          <div class="btnBox">
            <Button class="btn" :type="item.type" :icon="item.icon" @click="searchBoxEven(item, index)">
              {{ item.name }}
            </Button>
          </div>
        </template>
      </template>
    </div>

    <!--  ===========================  表格 ========================================  -->
    <div class="tableBox">
      <div class="tableColumnsBtn">
        <Button type="primary" icon="md-list" @click="state.tableObj.showColumns = !state.tableObj.showColumns"/>
        <Drawer title="字段" :closable="true" v-model="state.tableObj.showColumns">
          <div style="margin: 6px 0" v-for=" (item,inx) in state.tableObj.columns" :key="inx">
            <Switch v-model="item.isShowColumn" @on-change="changeColumnsList"/>
            {{ item.title }}
          </div>
        </Drawer>
      </div>
      <!--   ============   -->
      <Table ref="selection"
             border
             highlight-row
             stripe
             size="small"
             :columns="state.showTableObj.columns"
             :data="state.tableObj.data"
             :loading="state.tableObj.loading"
             @on-row-click="selectData"
             @on-selection-change="selectDataChange"
      >
      </Table>
    </div>
    <!-- ===== 分页 ===== -->
    <div class="pageBox">
      <Page :total="state.pageData.total"
            :page-size="state.pageData.size"
            @on-change="changePage"
            show-elevator/>
    </div>
  </div>
  <!--==================  保存数据  ====================-->
  <ModalDataList
      v-model="state.saveObj.show"
      :modalSetting="state.saveObj"
      @event="saveEventFn"
  />
</template>

<script setup>
import {defineEmits, inject, reactive, ref, resolveComponent, watch} from "vue"
import {commonRequest} from "@/api/api";
import {Message, Modal} from "view-ui-plus";
import ModalDataList from './ModalDataList.vue'
import {loginInfo} from "@/store/loginInfo.js";

const loginInfoSt = loginInfo();

const emits = defineEmits(['event']);
const commonTableSearchData = inject('commonTableSearchData', {})

const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  },
  modelValue: {
    type: Boolean,
    default: true
  },
});

const state = reactive({
  // ===============  搜索
  searchObj: {},
  // ===============  表格
  tableObj: {
    queryRequest: {}
  },
  showTableObj: {},
  // ===============  保存
  saveObj: {
    show: false,
    loading: true,
    editor: false,
    data: {},
    columns: [],
    url: ''
  },
  showSaveObj: {},

  pageData: {
    total: 0,
    size: 10
  }
});

const saveData = ref({
  url: '',
  columns: [],
  data: {},
  show: false
});

// ==========================================
const searchBoxEven = (item, index) => {
  console.log(item, index)
  if (item.even === 'search') {
    search(item.parameter);
  } else if (item.even === 'save') {
    state.saveObj.show = true;
    state.saveObj.data = {};
  } else if (item.even === 'del') {
    // console.log(item)
  } else {
    emits('event', {
      even: item.even,
      data: item
    });
  }
}

const search = (columns = []) => {
  state.pageData.page = 1;
  let searchData = {}
  for (let i = 0; i < columns.length; i++) {
    const it = columns[i]
    if (it.val) {
      searchData[it.javaWhere + "_" + it.key] = it.val
    }
  }
  state.tableObj.queryRequest.pm = searchData;
  loadData();
}

/**
 * 加载数据
 */
const loadData = () => {
  if (state.tableObj.queryRequest.url) {
    state.tableObj.loading = true;
    commonRequest(
        loginInfoSt.reLoadUrl(state.tableObj.queryRequest.url),
        {
          ...state.tableObj.queryRequest.parameter,
          ...state.tableObj.queryRequest.pm,
          ...commonTableSearchData.value,
          ...state.pageData
        }
    ).then((res) => {
      if (res.data && res.data.length !== undefined) {
        state.tableObj.data = res.data;
      } else {
        console.error("返回的数据格式有误, data: ", res.data);
      }
      state.pageData.total = res.result.total;
    }).catch(err => {
      state.tableObj.loading = false;
      state.tableObj.data = [];
      console.log(err);
    }).finally(() => {
      state.tableObj.loading = false;
    })
  }
}


/**
 * 初始化表格
 */
const initFn = () => {
  // 表格初始化
  initTable();
  // 初始化保存列表
  initSave();
  // 加载数据
  loadData()
}

/**
 * 表格初始化
 */
const initTable = () => {
  const {columns, operation} = state.tableObj;
  const temp = [];
  for (let i = 0; i < columns.length; i++) {
    const itemObj = columns[i];
    if (itemObj.isShowColumn) {
      if (itemObj.type == 'img' || itemObj.type == 'filters') {
        itemObj.render = (h, params) => {
          return createH(itemObj, h, params);
        }
      }
      temp.push(itemObj);
    }
  }
  if (operation && operation.show) {
    operation.render = (h, params) => {
      return createH(operation, h, params);
    }
    temp.push(operation)
  }

  state.showTableObj.columns = temp;
}
const changeColumnsList = () => {
  initTable();
}

const initSave = () => {
  state.showSaveObj.columns = state.saveObj.columns;
}


const selectTableData = (row, index, editor) => {
  state.saveObj.show = true;
  state.saveObj.data = row;
}

// ==========================================


const objConfig = ref({
  columns: [],
  data: [],
  loading: false,
  isShowColumns: false,
  columnsList: [],
  selectData: []
});


// ======================================================
const selectData = (row, index) => {
  const dataArr = objConfig.value.data;
  for (let i = 0; i < dataArr.length; i++) {
    if (i != index) {
      dataArr[i]._checked = false
    } else {
      dataArr[i]._checked = true
      selectDataChange([dataArr[i]]);
    }
  }
  emits('event', row);
}

const selectDataChange = (rows) => {
  console.log(rows)
  objConfig.value.selectData = rows;
  const eventObj = {
    even: 'table_select',
    data: rows
  }
  emits('event', eventObj);
}

// ======================================================

// ======================================================


/**
 * 点击删除
 */
const delSelect = () => {
  const arr = [];
  const selectData = objConfig.value.selectData;
  for (let i = 0; i < selectData.length; i++) {
    arr.push(selectData[i].id)
  }
  delTableDataFn(arr)
}
/**
 * 删除单条数据
 */
const delTableData = (row, index) => {
  delTableDataFn([row.id])
}
/**
 * 执行删除
 */
const delTableDataFn = (idArr = []) => {
  Modal.confirm({
    title: '是否删除?',
    okText: '删除',
    loading: true,
    onOk: () => {
      const url = loginInfoSt.reLoadUrl(state.tableObj.delRequest.url);
      commonRequest(url, idArr, 'post').then((rest) => {
        Message.success({
          content: `${rest.data ? rest.data : rest.msg}`,
          onClose: () => {
            Modal.remove();
            loadData();
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


const changePage = (page) => {
  state.pageData.page = page
  loadData();
}

// ===================================================
const saveEventFn = (ev, itemData) => {
  if ('ok' === ev) {
    Save(itemData)
  } else if ('cancel' === ev) {
    Cancel(itemData);
  }
}
const Save = (itemData) => {
  let url = state.saveObj.url;
  let parameter = itemData;
  if (!url || url === '/admin/common/save') {
    url = "/admin/common/save";
    parameter = {
      ...state.saveObj.parameter,
      data: [itemData]
    }
  }
  commonRequest(loginInfoSt.reLoadUrl(url), parameter, 'post').then((rest) => {
    if (rest.code === 2000) {
      state.saveObj.data = rest.data;
      Message.success({
        content: `${rest.msg}`,
        onClose: () => {
          state.saveObj.show = false;
          loadData();
        }
      })
    }
  }).catch(err => {
    Message.error({
      content: `${err}`
    })
  }).finally(() => {
    state.saveObj.loading = false;
    setTimeout(() => {
      state.saveObj.loading = true;
    })
  })
}
const Cancel = (itemData) => {
  console.log('cancel', itemData)
}


// ==============================================
watch(() => props.setting, () => {
  const setting = props.setting;
  console.log("=================  变化  ===================", setting)
  if (setting) {
    if (setting.searchObj) {
      state.searchObj = setting.searchObj;
    }
    if (setting.tableObj) {
      state.tableObj = setting.tableObj;
    }
    if (setting.saveObj) {
      state.saveObj = setting.saveObj;
    }
    initFn();
  } else {
    console.log("=========== 未设置数据 =======", setting)
  }
}, {deep: false, immediate: false})

watch(() => commonTableSearchData.value, () => {
  search()
}, {deep: false, immediate: false})

watch(() => props.modelValue, () => {
  console.log('设置数据未设置数据未设置数据未设置数据ss')
})

//==============================


const createH = (columnsItem, h, params) => {
  const hArr = [];
  const {row, index} = params;
  if (columnsItem.key === 'operation') {
    for (const operationKey in columnsItem.operationArr) {
      const opObj = columnsItem.operationArr[operationKey];
      let type = 'info';
      if (opObj.type) {
        type = opObj.type;
      } else if (opObj.label === '查看') {
        type = 'info';
      } else if (opObj.label === '修改') {
        type = 'warning';
      } else if (opObj.label === '删除') {
        type = 'error';
      }
      hArr.push(h(resolveComponent('Button'), {
        type: type,
        size: 'small',
        style: {
          marginRight: '5px'
        },
        onClick: (e) => {
          e.stopPropagation()
          if (!opObj.even) {
            if (opObj.label === '查看') {
              selectTableData(row, index, false)
            } else if (opObj.label === '修改') {
              selectTableData(row, index, true)
            } else if (opObj.label === '删除') {
              delTableData(row, index);
            } else {
              emits('event', {
                even: opObj.even,
                data: row
              });
            }
          } else {
            emits('event', {
              even: opObj.even,
              data: row
            });
          }
        }
      }, {
        default() {
          return opObj.label
        }
      }))
    }
  } else if (columnsItem.type == 'img') {
    const face = row[columnsItem.key];
    if (face) {
      hArr.push(h('img', {
        src: face,
        style: {
          maxHeight: '100%',
          maxWidth: '100%'
        }
      }))
    }
    return h('div', {
      style: {
        height: '40px'
      }
    }, hArr);
  } else if (columnsItem.type == 'filters') {
    let strData = ''
    const objData = row[columnsItem.key];
    if (columnsItem.filters && columnsItem.filters.length > 0) {
      for (let i = 0; i < columnsItem.filters.length; i++) {
        if (columnsItem.filters[i].value == objData) {
          strData = columnsItem.filters[i].label;
          break;
        }
      }
    }
    hArr.push(h('span', {
      style: {},
    }, {
      default() {
        return strData
      }
    }))
  }
  return h('div', hArr);
}


</script>

<style scoped lang="less">
.commonTable {
  padding: 10px;

  .searchBox {
    display: flex;
    padding: 10px 10px 20px;

    .searchRow {
      display: flex;
      align-items: center;

      .inputLabel {
        margin-left: 6px;
        margin-right: 4px;
      }
    }

    .btnBox {
      .btn {
        margin: 0 6px;
      }
    }
  }

  .tableBox {
    position: relative;

    .tableColumnsBtn {
      position: absolute;
      top: -40px;
      right: 0;
      z-index: 99;
    }
  }

  .pageBox {
    display: flex;
    align-items: center;
    margin: 30px;
    justify-content: center;

    .pageText {
      margin: 0 10px;
      color: #999;
    }
  }
}


</style>

