<template>
  <div class="sqlPage">
    <div class="sqlLeft">
      <div class="searchBox">
        <n-input size="small" round placeholder="页面配置" />
      </div>
      <div class="listBox">
        <div
          class="listItem"
          v-for="(item, index) in sqlList"
          :key="index"
          @click="selectSqlData(item, index)"
        >
          <div> {{ item.name }}</div>
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
            <n-input v-model:value="sqlData.tid" type="text" placeholder="sqlData" />
          </div>
        </div>
        <div class="row">
          <div class="label">名称</div>
          <div class="content">
            <n-input v-model:value="sqlData.name" type="text" placeholder="sqlData" />
          </div>
        </div>
        <div class="row">
          <div class="label">sql</div>
          <div class="content">
            <n-input v-model:value="sqlData.sqlcontent" type="textarea" placeholder="sqlData" />
          </div>
        </div>
        <div class="row">
          <div class="label">count</div>
          <div class="content">
            <n-input v-model:value="sqlData.countsql" type="textarea" placeholder="countData" />
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
  import { getSqlList, saveSql } from '../../api/api'

  export default {
    name: 'SqlPage',
    data() {
      return {
        sqlList: [],
        sqlData: {},
        countData: '',
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
        getSqlList().then((res) => {
          this.sqlList = res.data
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
        saveSql(this.sqlData).catch((res) => {
          this.sqlData = res.data
        })
      },
      /**
       * 添加数据
       */
      addData() {
        console.log('ssssssss', this.sqlData)
        this.sqlData = {}
      },
    },
  }
</script>

<style lang="scss" scoped>
  .sqlPage {
    display: flex;

    .sqlLeft {
      width: 200px;
      height: 100vh;
      background: #191919;
      padding: 10px;

      .searchBox {
        padding-bottom: 10px;
      }

      .listBox {
        .listItem {
          color: #fff;
          padding: 6px;

          &:hover {
            color: #111;
            background: #fff;
            cursor: pointer;
          }
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
