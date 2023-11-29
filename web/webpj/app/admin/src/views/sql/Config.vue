<template>
  <div class="sqlPage">
    <div class="sqlLeft">
      <!--    搜索    -->
      <div class="searchBox">
        <Input search enter-button placeholder="搜索" @on-search="search"/>
        <Button type="primary" class="searchBtn" @click="addData">添加</Button>
      </div>
      <!--   表格   -->
      <div class="listBox">
        <Table border ref="selection" :columns="tableData.columns" :data="tableData.data">
          <!--   表格操作  -->
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
        <div class="pageBox">
          <Page :total="pageData.total" :page-size="pageData.size" @on-change="changePage" show-elevator/>
        </div>
      </div>
    </div>
    <!--==================    ====================-->
    <Modal
        v-model="modalData.showModal"
        title="保存数据"
        @on-ok="modalData.Save()"
        @on-cancel="modalData.Cancel()"
        :mask-closable="false"
        :loading="true"
        width="80wh"
    >
      <div class="modalBox">
        <div class="sqlContent">
          <div class="row">
            <div class="label">ID</div>
            <div class="content">
              <Input v-model="sqlData.tid" placeholder="ID"/>
            </div>
          </div>
          <div class="row">
            <div class="label">名称</div>
            <div class="content">
              <Input v-model="sqlData.name" placeholder="名称"/>
            </div>
          </div>
          <div class="row">
            <div class="label">类型</div>
            <div class="content">
              <Select v-model="sqlData.type">
                <Option :value="0">查询</Option>
                <Option :value="1">保存</Option>
              </Select>
            </div>
          </div>
          <div v-if="sqlData.type == 0">
            <div class="row">
              <div class="label">查询语句</div>
              <div class="content">
                <Input v-model="sqlData.contentSql" placeholder="sql" type="textarea" :rows="8"/>
              </div>
            </div>
            <div class="row">
              <div class="label">统计数量</div>
              <div class="content">
                <Input v-model="sqlData.countSql" placeholder="sql" type="textarea" :rows="8"/>
              </div>
            </div>
          </div>
          <div v-if="sqlData.type == 1">
            <div class="row">
              <div class="label">主表</div>
              <div class="content">
                <Input v-model="sqlData.mainTable" placeholder="主表"/>
              </div>
            </div>
            <div class="row">
              <div class="label">主表ID</div>
              <div class="content">
                <Input v-model="sqlData.mainId" placeholder="主表ID"/>
              </div>
            </div>
            <div class="row">
              <div class="label">插入语句</div>
              <div class="content">
                <Input v-model="sqlData.insetSql" placeholder="sql" type="textarea" :rows="8"/>
              </div>
            </div>
            <div class="row">
              <div class="label">更新语句</div>
              <div class="content">
                <Input v-model="sqlData.updateSql" placeholder="sql" type="textarea" :rows="8"/>
              </div>
            </div>
          </div>
        </div>
        <div class="sqlNote">
          <p>[] ---&gt; =</p>
          <p>[@] ---&gt; in</p>
          <p>[!@] ----&gt; not in</p>
          <p>[%] ----&gt; like</p>
          <p>[L%] ----&gt; like</p>
          <p>[R%] ----&gt; like</p>
          <p>[!%] ----&gt; not like</p>
          <p>[:=] ----&gt; 别名 [cc:=sss]</p>
          <p>[!!&lt;=] ----&gt; 大于等于</p>
          <p>[!!&lt;=] ----&gt; 小于等于</p>
        </div>
      </div>

    </Modal>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {getSqlList, saveSql} from '@/api/api.js'
import {Modal} from 'view-ui-plus'


const pageData = ref({
  page: 1,
  total: 0,
  size: 30
});

const changePage = (page) => {
  pageData.value.page = page;
  loadTableData();
}

// ====================================

let sqlData = ref({
  tid: '',
  name: '',
  contentSql: '',
  countSql: '',
  type: 0
});

// =====================================  搜索
const search = () => {
  pageData.value.page = 1;
  loadTableData();
}

const addData = () => {
  sqlData.value = {};
  modalData.value.showModal = true;
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
      title: '名称',
      key: 'name'
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
  modalData.value.showModal = true;
  modalData.value.editor = editor;
  console.log("sssssss")
  if (!row.type) {
    row.type = 0;
  }
  sqlData.value = row;
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
      Modal.remove();
    },
  });

}
// =====================================

const Save = () => {
  saveSql(sqlData.value).then((rest) => {
    console.log(rest);
    sqlData.value = rest.data;
    loadTableData();
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
  Save,
  Cancel
})
//======================================


</script>

<style scoped lang="less">
.sqlPage {
  .sqlLeft {
    padding: 10px;

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

.modalBox {
  display: flex;

  .sqlContent {
    flex: 1;

    .row {
      display: flex;
      padding: 10px;
      margin-bottom: 10px;
      background: #f0f0f0;

      .label {
        width: 100px;
        padding: 0 10px;
        display: flex;
        flex-direction: row-reverse;
        align-items: center;
        justify-items: right;
      }

      .content {
        flex: 1;
      }
    }
  }

  .sqlNote {
    width: 220px;
    margin: 0 10px;
    padding: 10px;
  }
}
</style>
