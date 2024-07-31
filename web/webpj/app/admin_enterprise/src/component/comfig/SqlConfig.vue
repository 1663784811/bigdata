<template>
  <Modal
      v-model="sqlConfig.show"
      title="SQL列表数据"
      :mask-closable="false"
      width="80wh"
  >
    <div class="sqlPage">
      <div class="sqlLeft">
        <!--    搜索    -->
        <div class="searchBox">
          <Input v-model="pageData.lk_name" clearable placeholder="名称"/>
          <Input v-model="pageData.lk_tid" clearable search enter-button placeholder="ID" @on-search="search"/>
          <Button type="primary" class="searchBtn" @click="addData">添加</Button>
        </div>
        <!--   表格   -->
        <div class="listBox">
          <Table border ref="selection" :columns="tableData.columns" :data="tableData.data">
            <!--   表格操作  -->
            <template #operation="{ row, index }">
              <Button size="small" type="info" @click="selectTableData(row,index,false)" style="margin-right: 5px">
                查看
              </Button>
              <Button size="small" type="warning" @click="selectTableData(row,index,true)" style="margin-right: 5px">
                修改
              </Button>
              <Button size="small" type="error" @click="delTableData(row,index)">删除</Button>
            </template>
          </Table>
          <div class="pageBox">
            <Page :total="pageData.total" :page-size="pageData.size" @on-change="changePage" show-elevator/>
          </div>
        </div>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {reactive, ref} from "vue";
import {delSql, getSqlList} from '@/api/api.js'
import {Modal} from 'view-ui-plus'
import {useConfigModule} from "@/store/configModule.js";

const {sqlConfig, sqlModal} = useConfigModule();

const state = reactive({
  mainTable: '',
  tableColumn: []
})
const pageData = ref({
  page: 1,
  total: 0,
  size: 10,
  lk_name: '',
  lk_tid: '',
  sort: 'createTime_desc'
});

const changePage = (page) => {
  pageData.value.page = page;
  loadTableData();
}

// =====================================  搜索
const search = () => {
  pageData.value.page = 1;
  loadTableData();
}

const addData = () => {
  sqlModal.editor = true;
  sqlModal.show = true;
  sqlModal.data = {};
  sqlModal.callBack = search;
}


// =====================================  表格
const tableData = ref({
  columns: [
    {
      type: 'selection',
      rowKey: 'id',
      width: 60
    },
    {
      title: 'ID',
      key: 'tid',
      width: 250
    },
    {
      title: '应用类型',
      key: 'appType'
    },
    {
      title: '名称',
      key: 'name'
    },
    {
      title: '状态',
      key: 'status'
    },
    {
      title: '备注',
      key: 'tags'
    },
    {
      title: '操作',
      key: 'operation',
      slot: 'operation',
      width: 200,
    }
  ],
  data: [
    {
      name: 'John Brown',
      age: 18,
      address: 'New York No. 1 Lake Park',
      date: '2016-10-03'
    },
    {
      name: 'Jim Green',
      age: 24,
      address: 'London No. 1 Lake Park',
      date: '2016-10-01'
    },
    {
      name: 'Joe Black',
      age: 30,
      address: 'Sydney No. 1 Lake Park',
      date: '2016-10-02'
    },
    {
      name: 'Jon Snow',
      age: 26,
      address: 'Ottawa No. 2 Lake Park',
      date: '2016-10-04'
    }

  ]
})
const loadTableData = () => {
  tableData.value.data = [];
  getSqlList(pageData.value).then((res) => {
    tableData.value.data = res.data;
    pageData.value = {
      ...pageData.value,
      ...res.result
    };
  })
}
loadTableData();

const selectTableData = (row, index, editor = false) => {
  sqlModal.editor = editor;
  sqlModal.show = true;
  sqlModal.data = row;
  sqlModal.callBack = search;
  if (!row.type) {
    row.type = 0;
  }
}
/**
 * 删除数据
 */
const delTableData = (row, index) => {
  Modal.confirm({
    title: '是否删除?',
    content: `${row.name}`,
    okText: '删除',
    loading: true,
    onOk: () => {
      console.log("onOk", this);
      delSql([row.id]).then(res => {
        Modal.remove();
        loadTableData();
      });
    },
  });

}

</script>

<style scoped lang="less">
.sqlPage {
  .sqlLeft {
    padding: 10px;
    min-height: 60vh;

    .searchBox {
      padding-bottom: 10px;
      max-width: 560px;
      margin: 60px auto;
      display: flex;

      .searchBtn {
        margin-left: 10px;
      }
    }

    .listBox {
      .listItem {
        padding: 6px;

        &:hover {
          color: #111;
          cursor: pointer;
        }

        &.activity {
          color: #111;
          background: #fff;
          cursor: pointer;
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
  }
}

</style>
