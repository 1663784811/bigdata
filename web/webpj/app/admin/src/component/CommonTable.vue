<template>
  <div class="commonTable">
    <!--  搜索  -->
    <div class="searchBox">

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
    <!--  表格  -->
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

    <!-- 分页 -->
    <div class="pageBox">
      <Page :total="tableConfig.pageData.total" :page-size="tableConfig.pageData.size" @on-change="changePage"
            show-elevator/>
    </div>
  </div>
</template>

<script setup>

const tableConfig = {
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
      key: 'operation',
      slot: 'operation',
      width: 200,
    }
  ],
  data: [],
  pageData: {}
}

const selectTableData = (row, index) => {

}

const delTableData = (row, index) => {

}

const changePage = () => {

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

