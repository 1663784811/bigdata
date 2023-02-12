<template>
  <div class="commonTable">
    <!-- ========================================   搜索   ======================================== -->
    <div class="searchBox">
      <div class="searchRow" v-for="(item,index) in searchColumns" :key="index">
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
      <Table border ref="selection" :columns="tableConfig.columns" :data="tableConfig.data">
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
          <div class="label">{{ item.name }}</div>
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
import {getSqlList, saveSql} from "@/api/api";
import {Modal} from "view-ui-plus";

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

const tableConfig = ref({
  columns: [],
  data: [],
  pageData: {}
});

const saveData = ref({
  columns: [],
  data: {}
});

// ======================================================

const loadTableData = () => {
  console.log('=========================================')
  console.log('=========================================')
  console.log('=========================================')
  console.log('=========================================')
  console.log('=========================================')
  tableConfig.value.data = [];
  getSqlList(tableConfig.value.pageData).then((res) => {
    console.log(res)
  })
}
loadTableData();


// ======================================================
const search = () => {
  tableConfig.value.pageData.page = 1;
  console.log("search")
  loadTableData();
}

const addData = () => {
  modalData.value.showModal = true;
}

const delSelect = () => {
  Modal.confirm({
    title: '是否删除,选择的数据?',
    okText: '删除',
    loading: true,
    onOk: () => {
      console.log("onOk", this);
      Modal.remove();
    },
  });
}

const selectTableData = (row, index, editor) => {
  modalData.value.showModal = true;
}

const delTableData = (row, index) => {
  console.log(row)
  Modal.confirm({
    title: '是否删除?',
    okText: '删除',
    loading: true,
    onOk: () => {
      console.log("onOk", this);
      Modal.remove();
    },
  });
}


const changePage = () => {

}

// ===================================================
const Save = () => {
  saveSql(sqlData.value).then((rest) => {
    console.log(rest);
    // sqlData.value = rest.data;
    // loadTableData();
    modalData.value.showModal = false;
  }).catch(err => {
    console.log('dddd');

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
  console.log('dddddddddddd');
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


</style>

