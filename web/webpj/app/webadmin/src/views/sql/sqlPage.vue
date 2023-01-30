<template>
  <div class="sqlPage">
    <div class="sqlLeft">
      <div class="searchBox">
        <n-input size="small" round placeholder="小"/>
        <n-button class="searchBtn" type="primary">搜索</n-button>
        <n-button class="searchBtn" type="warning" @click="addData">添加</n-button>
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
          <n-pagination
              v-model:page="pageData.page"
              :page-count="Math.ceil(pageData.total/(pageData.size>0?pageData.size:1))"
              :on-update:page="updatePage"
          />
          <div class="pageText">共{{ pageData.total }}条数据</div>
        </div>
      </div>
    </div>
    <!--==================    ====================-->
    <n-modal
        v-model:show="showModal"
        :mask-closable=false
    >
      <div class="sqlRight">
        <div>
          <div class="row">
            <div class="label">ID</div>
            <div class="content">
              <n-input v-model:value="sqlData.tid" :disabled="editor" type="text" placeholder="sqlData"/>
            </div>
          </div>
          <div class="row">
            <div class="label">名称</div>
            <div class="content">
              <n-input v-model:value="sqlData.name" :disabled="editor" type="text" placeholder="sqlData"/>
            </div>
          </div>
          <div class="row">
            <div class="label">sql</div>
            <div class="content">
              <n-input v-model:value="sqlData.contentSql" :disabled="editor" type="textarea" :rows="8"
                       placeholder="sqlData"/>
            </div>
          </div>
          <div class="row">
            <div class="label">count</div>
            <div class="content">
              <n-input v-model:value="sqlData.countSql" :disabled="editor" type="textarea" :rows="8"
                       placeholder="countData"/>
            </div>
          </div>
          <div class="row submitBox">
            <n-button class="submitBtn" type="warning" @click="showModal=false">取消</n-button>
            <n-button class="submitBtn" type="success" @click="saveData" v-show="!editor">保存</n-button>
          </div>
        </div>
      </div>
    </n-modal>
  </div>
</template>

<script>
import {getSqlList, saveSql} from '../../api/api'
import {h, defineComponent} from 'vue'

import {NButton } from 'naive-ui'

export default defineComponent({
  name: 'SqlPage',
  data() {
    return {
      sqlList: [],
      sqlData: {},
      countData: '',
      showModal: false,
      editor: false,
      columns: [
        {
          type: 'selection',
          rowKey: 'id',
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
          render: (rowData, rowKey) => {
            console.log(rowKey)
            const check = h(NButton,
                {
                  size: 'small',
                  type: "primary",
                  onClick: () => {
                    this.selectSqlData(rowData, true)
                  }
                },
                {default: () => '查看'}
            )
            const update = h(NButton,
                {
                  size: 'small',
                  type: "warning",
                  onClick: () => {
                    this.selectSqlData(rowData, false)
                  }
                },
                {default: () => '修改'}
            )
            const del = h(NButton,
                {
                  size: 'small',
                  type: "error",
                  onClick: () => {
                    this.warning(rowData)
                  }
                },
                {default: () => '删除'}
            )
            return h('div', [check, update, del]);
          }
        }
      ],
      pagination: false,
      pageData: {
        page: 0,
        pageTotal: 0,
        total: 0,
        size: 0
      }
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
      this.sqlList = [];
      getSqlList(this.pageData).then((res) => {
        this.sqlList = res.data;
        this.pageData = res.result;
      })
    },
    /**
     * 选择数据
     */
    selectSqlData(data, editor = false) {
      this.showModal = true;
      this.editor = editor;

      this.sqlData = data
    },

    /**
     * 保存数据
     */
    saveData() {
      saveSql(this.sqlData).then((res) => {
        this.sqlData = res.data;
        this.getDataList();
      })
    },
    /**
     * 添加数据
     */
    addData() {
      this.sqlData = {}
      this.showModal = true;
      this.editor = false;
    },
    updatePage(page) {
      this.pageData.page = page
      this.getDataList();
    },
    delData(rowData) {
      console.log('删除数据', rowData);
      console.log(this);

      this.dialog.warning({
        title: '警告',
        content: '你确定？',
        positiveText: '确定',
        negativeText: '不确定',
        onPositiveClick: () => {
          message.success('确定')
        },
        onNegativeClick: () => {
          message.error('不确定')
        }
      })
    }
  },
})
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

.sqlRight {
  padding: 40px 20px 0 20px;
  background: #ffffff;
  width: 80vw;
  border-radius: 8px;

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
    display: flex;
    justify-content: right;
    background: none;

    .submitBtn {
      margin: 0 10px;
    }
  }
}
</style>
