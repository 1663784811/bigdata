<template>
  <div class="sqlPage">
    <div class="sqlLeft">
      <div class="searchBox">
        <n-input size="small" round placeholder="小"/>
        <n-button class="searchBtn" type="primary">搜索</n-button>
      </div>
      <div class="listBox">
        <n-data-table
            :columns="columns"
            :data="sqlList"
            :pagination="pagination"
            :bordered="true"
            striped
        />
        <div class="pageBox">
          <n-pagination v-model:page="page" :page-count="100"/>
        </div>
      </div>
    </div>
    <div class="sqlRight">
      <div>
        <n-button type="success" @click="addData">新增</n-button>
      </div>
      <div>
        <div class="row">
          <div class="label">ID</div>
          <div class="content">
            <n-input v-model:value="sqlData.tid" type="text" placeholder="sqlData"/>
          </div>
        </div>
        <div class="row">
          <div class="label">名称</div>
          <div class="content">
            <n-input v-model:value="sqlData.name" type="text" placeholder="sqlData"/>
          </div>
        </div>
        <div class="row">
          <div class="label">sql</div>
          <div class="content">
            <n-input v-model:value="sqlData.contentSql" type="textarea" placeholder="sqlData"/>
          </div>
        </div>
        <div class="row">
          <div class="label">count</div>
          <div class="content">
            <n-input v-model:value="sqlData.countSql" type="textarea" placeholder="countData"/>
          </div>
        </div>
        <div class="row submitBox">
          <n-button type="success" @click="saveData">保存</n-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getSqlList, saveSql} from '../../api/api'
import {h} from 'vue'

import {NButton} from 'naive-ui'


export default {
  name: 'SqlPage',
  data() {
    return {
      sqlList: [],
      sqlData: {},
      countData: '',
      columns: [
        {
          title: 'ID',
          key: 'select',
          type: 'selection',
        },
        {
          title: 'ID',
          key: 'tid'
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
          width: 150,
          render: (rowData) => {
            const check = h(NButton,
                {
                  size: 'small',
                  type: "primary"
                },
                {default: () => '查看'}
            )
            const update = h(NButton,
                {
                  size: 'small',
                  type: "warning"
                },
                {default: () => '修改'}
            )

            return h('div', [check, update]);
          }
        }
      ],
      pagination: false,
      page: 1
    }
  },
  created() {
    this.getDataList()
  },
  methods: {
    /**
     * 获取数据
     */
    getDataList() {
      getSqlList({}).then((res) => {
        this.sqlList = res.data;
      })
    },
    /**
     * 选择数据
     */
    selectSqlData(data) {
      this.sqlData = data
    },

    /**
     * 保存数据
     */
    saveData() {
      saveSql(this.sqlData).then((res) => {
        this.sqlData = res.data;
      })
    },
    /**
     * 添加数据
     */
    addData() {
      this.sqlData = {}
    },
  },
}
</script>

<style lang="scss" scoped>
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
        margin: 30px;
        justify-content: center;
      }
    }
  }

  .sqlRight {
    flex: 1;
    padding: 10px;

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

    .submitBox {
      padding-left: 100px;
      display: block;
      background: none;
    }
  }
}
</style>
