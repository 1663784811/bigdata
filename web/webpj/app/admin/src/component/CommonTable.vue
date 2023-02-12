<template>
  <div class="commonTable">
    <!-- ========================================   搜索   ======================================== -->
    <div class="searchBox">
      {{ form }}
      <div class="searchRow">
        <div class="inputLabel">名称:</div>
        <Input placeholder="aad" style="width: auto"/>
      </div>

      <div class="searchRow">
        <div class="inputLabel">名称:</div>
        <Input placeholder="aad" style="width: auto"/>
      </div>
      <div class="searchRow">
        <div class="inputLabel">名称:</div>
        <Input placeholder="aad" style="width: auto"/>
      </div>
      <div class="searchRow">
        <div class="inputLabel">名称:</div>
        <Input placeholder="aad" style="width: auto"/>
      </div>
      <div class="searchBtn">
        <Button type="primary" icon="ios-search">搜索</Button>
      </div>
    </div>
    <!--  ===========================  表格 ========================================  -->
    <div class="tableBox">
      <Table border ref="selection" :columns="tableConfig.columns" :data="tableConfig.data">
        <template #operation="{ row, index }">
          <Button size="small" type="info" @click="selectTableData(row,index,true)" style="margin-right: 5px">
            查看
          </Button>
          <Button size="small" type="warning" @click="selectTableData(row,index,true)" style="margin-right: 5px">
            修改
          </Button>
          <Button size="small" type="error" @click="delTableData(row,index)">删除</Button>
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
        <div class="row">
          <div class="label">ID</div>
          <div class="content">
            <Input v-model="modalData.data.tid" placeholder="ID"/>
          </div>
        </div>
        <div class="row">
          <div class="label">名称</div>
          <div class="content">
            <Input v-model="modalData.data.name" placeholder="名称"/>
          </div>
        </div>
        <div class="row">
          <div class="label">sql</div>
          <div class="content">
            <Input v-model="modalData.data.contentSql" placeholder="sql" type="textarea" :rows="8"/>
          </div>
        </div>
        <div class="row">
          <div class="label">count</div>
          <div class="content">
            <Input v-model="modalData.data.countSql" placeholder="sql" type="textarea" :rows="8"/>
          </div>
        </div>

      </div>
    </div>
  </Modal>
</template>

<script setup>
import {watch, ref} from "vue"
import {saveSql} from "@/api/api";

const props = defineProps({
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

});


const tableConfig = ref({
  columns: [],
  data: [],
  pageData: {}
});

const selectTableData = (row, index) => {
  modalData.value.showModal = true;
}

const delTableData = (row, index) => {

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
  data:{},
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

    .searchBtn {
      margin-left: 10px;
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

