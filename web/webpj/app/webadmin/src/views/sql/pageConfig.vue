<template>
  <div class="sqlPage">
    <div class="columnItem">
      <div class="searchBox">
        <n-input size="small" round placeholder="页面配置" />
      </div>
      <div class="listBox">
        <div
          class="listItem"
          v-for="(item, index) in dataList"
          :key="index"
          :class="{ activity: selectData.tid === item.tid }"
          @click="clickSelectData(item, index)"
        >
          <div> {{ item.name }}</div>
        </div>
      </div>
    </div>
    <div class="columnItem">
      <div>
        <n-button type="success" @click="addData">新增</n-button>
      </div>
      <div>
        <div class="row">
          <div class="label">ID</div>
          <div class="content">
            <n-input v-model:value="selectData.tid" type="text" placeholder="selectData" />
          </div>
        </div>
        <div class="row">
          <div class="label">页码</div>
          <div class="content">
            <n-input v-model:value="selectData.pageCode" type="text" placeholder="pageCode" />
          </div>
        </div>
        <div class="row">
          <div class="label">名称</div>
          <div class="content">
            <n-input v-model:value="selectData.name" type="text" placeholder="name" />
          </div>
        </div>

        <div class="row submitBox">
          <n-button type="success" @click="saveData">保存</n-button>
        </div>
      </div>
    </div>
    <div class="columnItem">
      <div class="searchBox">组件列表</div>
      <div class="listBox">
        <div
          class="listItem"
          v-for="(item, index) in componentsList"
          :key="index"
          :class="{ activity: componentsData.tid === item.tid }"
          @click="selectComponentsData(item, index)"
        >
          <div> {{ item.name }}</div>
        </div>
      </div>
    </div>
    <div class="columnItem">
      <div>组件数据</div>
      <div>
        <div class="row">
          <div class="label">类型</div>
          <div class="content">
            <n-select v-model:value="componentsData.components_code" :options="options" />
          </div>
        </div>
        <div class="row">
          <div class="label">ID</div>
          <div class="content">
            <n-input v-model:value="selectData.tid" type="text" placeholder="selectData" />
          </div>
        </div>
        <div class="row">
          <div class="label">ID</div>
          <div class="content">
            <n-input v-model:value="selectData.tid" type="text" placeholder="selectData" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { getPageConfig, saveSql } from '../../api/api'

  export default {
    name: 'SqlPage',
    data() {
      return {
        dataList: [],
        selectData: {},
        componentsList: {},
        componentsData: {},
        countData: '',
        options: [
          {
            label: '主表格',
            value: 'mainTable',
          },
          {
            label: '左边树组件',
            value: 'leftTree',
          },
        ],
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
        getPageConfig().then((res) => {
          this.dataList = res.data
        })
      },
      /**
       * 选择数据
       */
      clickSelectData(data) {
        this.selectData = data
        this.componentsList = this.selectData.data
      },
      /**
       * 选择数据
       */
      selectComponentsData(data) {
        this.componentsData = data
      },

      /**
       * 保存数据
       */
      saveData() {
        saveSql(this.selectData).catch((res) => {
          this.selectData = res.data
        })
      },
      /**
       * 添加数据
       */
      addData() {
        console.log('ssssssss', this.selectData)
        this.selectData = {}
      },
    },
  }
</script>

<style lang="scss" scoped>
  .sqlPage {
    display: flex;

    .columnItem {
      width: 200px;
      height: 100vh;
      background: #d3d3d3;
      padding: 10px;
      margin-right: 1px;

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
          &.activity {
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
