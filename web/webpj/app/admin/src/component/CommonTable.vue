<template>
  <div class="commonTable">
    <!-- ========================================   搜索   ======================================== -->
    <div class="searchBox">
      <div class="searchRow" v-for="(item,index) in searchObj.columns" :key="index">
        <div class="inputLabel">{{ item.name }}:</div>
        <Input :placeholder="item.note" style="width: auto"/>
      </div>
      <div class="btnBox">
        <Button class="btn" type="success" icon="ios-search" @click="search">搜索</Button>
        <Button class="btn" type="warning" icon="ios-search" @click="addData">添加</Button>
        <Button class="btn" type="error" icon="ios-search" @click="delSelect">删除</Button>
      </div>
    </div>
    <!--  ===========================  表格 ========================================  -->
    <div class="tableBox">
      <Table border ref="selection" :columns="tableConfig.columns" :data="tableConfig.data"
             :loading="tableConfig.loading">
        <template #operation="{ row, index }">
          <Button size="small" v-if="operation.show" type="info"
                  @click="selectTableData(row,index,true)"
                  style="margin-right: 5px">
            查看
          </Button>
          <Button size="small" v-if="operation.update" type="warning"
                  @click="selectTableData(row,index,true)"
                  style="margin-right: 5px">
            修改
          </Button>
          <Button size="small" v-if="operation.del" type="error"
                  @click="delTableData(row,index)">
            删除
          </Button>
        </template>
      </Table>
    </div>
    <!-- ========================================  分页  ======================================== -->
    <div class="pageBox">
      <Page :total="tableConfig.pageData.total" :page-size="tableConfig.pageData.size" @on-change="changePage"
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
      :loading="true"
      width="80wh"
  >
    <div class="modalBox">
      <div>
        <div class="row" v-for="(item,index) in saveData.columns" :key="index">
          <div class="label">{{ item.name }}:</div>
          <div class="content">
            <Input v-model="saveData.data[item.key]" :placeholder="item.node"/>
          </div>
        </div>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {ref, watch} from "vue"
import {commonRequest} from "@/api/api";
import {Message, Modal} from "view-ui-plus";

const props = defineProps({
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
  pageData: {},
  loading: false
});

const saveData = ref({
  url: '',
  columns: [],
  data: {}
});

// ======================================================
/**
 * 加载数据
 */
const loadTableData = () => {
  tableConfig.value.loading = true;
  commonRequest(
      searchObj.value.queryRequest.url,
      {
        ...searchObj.value.queryRequest.parameter,
        ...tableConfig.value.pageData
      }
  ).then((res) => {
    tableConfig.value.loading = false;
    tableConfig.value.data = res.data;
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
  console.log("search")
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

const delSelect = () => {
  delTableDataFn([])
}

const delTableData = (row, index) => {
  delTableDataFn([row.id])
}

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


const changePage = () => {

}

// ===================================================
const Save = () => {
  const url = searchObj.value.saveRequest.url;
  commonRequest(url, saveData.value.data, 'post').then((rest) => {
    saveData.value.data = rest.data;
    Message.success({
      content: `${rest.msg}`,
      onClose: () => {
        modalData.value.showModal = false;
        loadTableData();
      }
    })

  }).catch(err => {
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
    const isInclude = tableConfig.value.columns.indexOf(operationColumns.value);
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

