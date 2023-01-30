<template>
  <div class="sqlPage">
    <div class="sqlLeft">
      <!--    搜索    -->
      <div class="searchBox">
        <Input search enter-button placeholder="搜索"/>
        <Button type="primary" class="searchBtn" @click="addData">添加</Button>
      </div>
      <!--   表格   -->
      <div class="listBox">
        <Table border ref="selection" :columns="tableData.columns" :data="tableData.data"></Table>
        <div class="pageBox">
          <Page :total="pageData.total" show-elevator/>
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
        width="80wh"
    >
      <div class="modalBox">
        <div>
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
            <div class="label">sql</div>
            <div class="content">
              <Input v-model="sqlData.contentSql" placeholder="sql" type="textarea" :rows="8"/>
            </div>
          </div>
          <div class="row">
            <div class="label">count</div>
            <div class="content">
              <Input v-model="sqlData.countSql" placeholder="sql" type="textarea" :rows="8"/>
            </div>
          </div>

        </div>
      </div>
    </Modal>
  </div>
</template>

<script setup>
import {ref} from "vue";

const pageData = ref({
  page: 0,
  total: 0,
  size: 0
});

let sqlData = ref({
  tid: '',
  name: '',
  contentSql: '',
  countSql: '',
});

// =====================================  搜索


const addData = () => {
  sqlData = ref({});
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
      title: '分类',
      key: 'type'
    },
    {
      title: '备注',
      key: 'tags'
    },
    {
      title: '操作',
      key: 'actions',
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

// =====================================

const Save = () => {
  console.log('dddd')
}
const Cancel = () => {
  console.log('dddd')
}

const modalData = ref({
  showModal: false,
  Save,
  Cancel
})
//======================================


</script>

<style scoped lang="less">
.sqlPage {
  .sqlLeft {
    padding: 10px;
    height: 100vh;

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
</style>
