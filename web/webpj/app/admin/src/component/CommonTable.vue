<template>
  <div class="commonTable">
    <!-- ========================================   搜索   ======================================== -->
    <div class="searchBox">
      <div class="searchRow" v-for="(item,index) in searchObj.columns" :key="index">
        <div class="inputLabel">{{ item.title }}:</div>
        <Input v-model="item.searchVal" :placeholder="item.javaWhere" style="width: auto"/>
      </div>
      <div class="btnBox">
        <Button class="btn" type="success" icon="ios-search" @click="search">搜索</Button>
        <Button class="btn" type="warning" icon="ios-search" @click="addData">添加</Button>
        <Button class="btn" type="error" icon="ios-search" @click="delSelect">删除</Button>
      </div>
    </div>
    <!--  ===========================  表格 ========================================  -->
    <div class="tableBox">

      <div class="tableColumnsBtn">
        <Button type="primary" icon="md-list" @click="tableConfig.isShowColumns = !tableConfig.isShowColumns"/>
        <Drawer title="字段" :closable="true" v-model="tableConfig.isShowColumns">
          <div style="margin: 6px 0" v-for=" (columnsItem,inx) in tableConfig.columnsList" :key="inx">
            <Switch v-model="columnsItem.isShowColumn"
                    @on-change="changeColumnsList"/>
            {{ columnsItem.title }}
          </div>
        </Drawer>
      </div>
      <!--   ============   -->
      <Table ref="selection"
             border
             highlight-row
             stripe
             size="small"
             :columns="tableConfig.columns"
             :data="tableConfig.data"
             :loading="tableConfig.loading"
             @on-row-click="selectData"
             @on-selection-change="selectDataChange"
      >
        <template #operation="{ row, index }">
          <Button size="small" v-if="operationObj.show" type="info"
                  @click="selectTableData(row,index,true)"
                  style="margin-right: 5px">
            查看
          </Button>
          <Button size="small" v-if="operationObj.update" type="warning"
                  @click="selectTableData(row,index,true)"
                  style="margin-right: 5px">
            修改
          </Button>
          <Button size="small" v-if="operationObj.del" type="error"
                  @click="delTableData(row,index)">
            删除
          </Button>
        </template>
      </Table>
    </div>
    <!-- ========================================  分页  ======================================== -->
    <div class="pageBox">
      <Page :total="tableConfig.pageData.total"
            :page-size="tableConfig.pageData.size"
            @on-change="changePage"
            show-elevator/>
    </div>
  </div>
  <!--==================    ====================-->
  <Modal
      v-model="modalData.showModal"
      title="数据"
      @on-ok="modalData.Save()"
      @on-cancel="modalData.Cancel()"
      :mask-closable="false"
      :loading="modalData.loading"
      width="80wh"
  >
    <div class="modalBox">
      <div>
        <template v-for="(item,index) in saveData.columns" :key="index">

          <div v-if="item.isShowSave !== false" class="row" :style="{display: item.controlType == 'hidden'?'none':''}">
            <div class="label">{{ item.title }}:</div>
            <div class="content" v-if="item.controlType == 'textarea'">
              <Input v-model="saveData.data[item.key]" type="textarea" :rows="10" :placeholder="item.node"/>
            </div>
            <div class="content" v-else-if="item.controlType == 'datetime'">
              <DatePicker v-model="saveData.data[item.key]"
                          type="datetime"
                          format="yyyy-MM-dd HH:mm"
                          :placeholder="item.node"/>
            </div>
            <div class="content" v-else>
              <Input v-model="saveData.data[item.key]" :placeholder="item.node"/>
            </div>
          </div>

        </template>

      </div>
    </div>
  </Modal>
  <SelectPanel/>
</template>

<script setup>
import {defineEmits, ref, watch} from "vue"
import {commonRequest} from "@/api/api";
import {Message, Modal} from "view-ui-plus";
import SelectPanel from './SelectPanel.vue'

const emits = defineEmits(['event']);

const props = defineProps({
  tableSetting: {},
  searchColumns: {
    type: Array,
    default: [],
    required: false
  },
  tableColumns: {
    type: Array,
    default: [],
    required: false
  },
  tableData: {
    type: Array,
    default: [],
    required: false
  },
  operation: {
    type: Object,
    default: undefined,
    required: false
  },
  saveColumns: {
    type: Array,
    default: [],
    required: false
  },
  requestObj: {
    type: Object,
    default: {
      queryRequest: {
        url: ''
      },
      saveRequest: {
        url: '',
      },
      delRequest: {
        url: ''
      }
    },
    required: false
  }
});


const selectPanelData = ref({});

const operationObj = ref({});

const operationColumns = ref({
  title: '操作',
  slot: 'operation',
  width: 200,
});

const selectionColumns = ref({
  type: 'selection',
  rowKey: 'id',
  width: 60
})

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

const tableConfig = ref({
  columns: [],
  data: [],
  pageData: {
    total: 0,
    size: 10
  },
  loading: false,
  isShowColumns: false,
  columnsList: [],
  selectData: []
});

const saveData = ref({
  url: '',
  columns: [],
  data: {}
});

// ======================================================
const selectData = (row, index) => {
  const dataArr = tableConfig.value.data;
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
  tableConfig.value.selectData = rows;
}

// ======================================================
/**
 * 加载数据
 */
const loadTableData = () => {
  tableConfig.value.loading = true;
  commonRequest(
      searchObj.value.queryRequest.url,
      {
        ...searchObj.value.queryRequest.pm,
        ...searchObj.value.queryRequest.parameter,
        ...tableConfig.value.pageData
      }
  ).then((res) => {
    tableConfig.value.loading = false;
    tableConfig.value.data = res.data;
    tableConfig.value.pageData.total = res.result.total;
  }).catch(err => {
    tableConfig.value.loading = false;
    tableConfig.value.data = [];
    console.log(err);
  })
}
setTimeout(() => {
  loadTableData();
}, 1000)

// ======================================================
const search = () => {
  tableConfig.value.pageData.page = 1;
  let columns = searchObj.value.columns;
  let searchData = {}
  for (let i = 0; i < columns.length; i++) {
    const it = columns[i]
    if (it.searchVal) {
      searchData[it.javaWhere + "_" + it.key] = it.searchVal
    }
  }
  searchObj.value.queryRequest.pm = searchData;
  loadTableData();
}

const addData = () => {
  saveData.value.data = {};
  modalData.value.showModal = true;
}

const selectTableData = (row, index, editor) => {
  modalData.value.showModal = true;
  saveData.value.data = row;
}

/**
 * 点击删除
 */
const delSelect = () => {
  const arr = [];
  const selectData = tableConfig.value.selectData;
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
      const url = searchObj.value.delRequest.url;
      console.log(url);
      commonRequest(url, idArr, 'post').then((rest) => {
        Message.success({
          content: `${rest.data ? rest.data : rest.msg}`,
          onClose: () => {
            Modal.remove();
            loadTableData();
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
  tableConfig.value.pageData.page = page
  loadTableData();
}

// ===================================================
const Save = () => {
  let url = searchObj.value.saveRequest.url;
  let parameter = saveData.value.data;
  if (!url || url === '/admin/common/save') {
    url = "/admin/common/save";
    parameter = {
      ...searchObj.value.saveRequest.parameter,
      data: [saveData.value.data]
    }
  }
  commonRequest(url, parameter, 'post').then((rest) => {
    saveData.value.data = rest.data;
    Message.success({
      content: `${rest.msg}`,
      onClose: () => {
        modalData.value.showModal = false;
        loadTableData();
      }
    })

  }).catch(err => {
    modalData.value.loading = false;
    console.log('错误:', err);
    Message.error({
      content: `${err}`
    })
  })
  return false;
}
const Cancel = () => {
  console.log('dddd')
}

const modalData = ref({
  loading: false,
  showModal: false,
  editor: false,
  data: {},
  Save,
  Cancel
})

// ===================================================
watch(() => props.tableColumns, () => {
  tableConfig.value.columns = props.tableColumns
}, {deep: true, immediate: true})

watch(() => props.tableData, () => {
  tableConfig.value.data = props.tableData
}, {deep: true, immediate: true})

watch(() => props.operation, () => {
  if (props.operation) {
    const columns = tableConfig.value.columns;
    let h = false;
    for (let i = 0; i < columns.length; i++) {
      const obj = columns[i];
      if (obj && obj.slot === 'operation') {
        h = true;
        break;
      }
    }
    if (!h) {
      tableConfig.value.columns.push(operationColumns.value);
    }
  }
}, {deep: true, immediate: true})

watch(() => props.saveColumns, () => {
  saveData.value.columns = props.saveColumns
}, {deep: true, immediate: true})

watch(() => props.searchColumns, () => {
  searchObj.value.columns = props.searchColumns
}, {deep: true, immediate: true})

watch(() => props.requestObj, () => {
  searchObj.value.queryRequest = props.requestObj.queryRequest;
  searchObj.value.saveRequest = props.requestObj.saveRequest;
  searchObj.value.delRequest = props.requestObj.delRequest;
}, {deep: true, immediate: true})

watch(() => props.tableSetting, () => {
  const setting = props.tableSetting;
  if (setting) {
    if (setting.columns) {
      saveData.value.columns = setting.columns;
    }
    operationObj.value = setting.operation
    if (setting.columns) {
      tableConfig.value.columnsList = setting.columns;
      setTimeout(() => {
        initTable()
      }, 50)
    }
    searchObj.value.queryRequest = setting.requestObj.queryRequest;
    searchObj.value.saveRequest = setting.requestObj.saveRequest;
    searchObj.value.delRequest = setting.requestObj.delRequest;
  } else {
    console.log("=========== 未设置数据 =======", setting)
  }

}, {deep: false, immediate: false})


//==============================
const changeColumnsList = () => {

  initTable();

}

/**
 * 初始化表格
 */
const initTable = () => {
  const arr = tableConfig.value.columnsList;
  const temp = [];
  const searchTemp = [];
  for (let i = 0; i < arr.length; i++) {
    if (arr[i].isShowColumn) {
      temp.push(arr[i]);
    }
    if (arr[i].isShowSearch) {
      searchTemp.push(arr[i])
    }
  }
  const setting = props.tableSetting;
  if (setting.operation) {
    for (const operationKey in setting.operation) {
      if (setting.operation[operationKey]) {
        temp.push(operationColumns.value)
        break;
      }
    }
  }
  tableConfig.value.columns = temp;
  searchObj.value.columns = searchTemp;
  console.log(searchTemp)
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

.modalBox {
  .row {
    display: flex;
    padding: 10px 0;

    .label {
      width: 150px;
      padding-right: 10px;
      align-items: center;
      display: flex;
      justify-content: right;
    }

    .content {
      flex: 1;
    }
  }
}

</style>

