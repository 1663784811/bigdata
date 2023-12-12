<template>
  <Drawer
      placement="right"
      title="部门"
      v-model="state.config.showDrawer"
      width="1200"
      :mask-closable="false"
  >
    <div class="dataDrawerBox">
      <div class="selectEdBox">
        <Divider orientation="left">已选择</Divider>
        <div class="selectEd" v-for="(item, index) in state.config.selectData" :key="index">
          <div>{{ item.name }}</div>
          <Button class="btn" type="error" size="small" icon="ios-trash-outline" @click="delData(item)"></Button>
        </div>
      </div>

      <div class="tempSelectBox">
        <Divider orientation="left">选择</Divider>
        <Button
            class="saveBtn"
            type="warning"
            size="small"
            icon="md-cloud-upload"
            @click="saveDataFn">
          保存
        </Button>

        <div class="select" v-for="(item, index) in state.config.tempData" :key="index">
          <div>{{ item.name }}</div>
          <Button class="btn"
                  type="error"
                  size="small"
                  icon="ios-trash-outline"
                  @click="state.config.tempData.splice(index, 1)">
          </Button>

        </div>
      </div>

      <div class="dataBox">
        <Divider orientation="left">数据</Divider>
        <CommonTable :table-setting="state.tableSetting" @event="eventFn"/>
      </div>
    </div>
  </Drawer>
</template>


<script setup>
import CommonTable from "@/component/CommonTable.vue";
import {reactive, inject, watch} from "vue";
import {commonRequest} from "@/api/api.js";
import {loginInfo} from "@/store/loginInfo.js";
import {Message, Modal} from "view-ui-plus";

const loginInfoSt = loginInfo();

const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  tableSetting: {},
  config: {
    queryData: {
      url: '/admin/${eCode}/common/query',
      parameter: {
        code: 'select_t_role'
      }
    },
    delRequest: {
      url: '/admin/${eCode}/common/query',
      parameter: {
        code: 'select_t_role'
      }
    },
    showDrawer: inject("showDrawer", false),
    selectData: [],
    tempData: [],
  },
  pageStatus: {
    loading: false
  }
})


// 初始化
const initFn = () => {
  console.log('11111111111111111111111111111111111111111')
  loadData();
}
const loadData = () => {
  state.pageStatus.loading = true;
  commonRequest(
      loginInfoSt.reLoadUrl(state.config.queryData.url),
      {
        ...state.config.queryData.parameter
      }
  ).then((rest) => {
    console.log(rest)
    const {data} = rest;
    state.config.selectData = data
  }).catch(err => {
    state.pageStatus.loading = false;
    console.log(err);
  })
}

const delData = (item) => {

  delDataFn();
}
const delDataFn = (idArr = []) => {
  Modal.confirm({
    title: '是否删除?',
    okText: '删除',
    loading: true,
    onOk: () => {
      const url = loginInfoSt.reLoadUrl(state.config.delRequest.url);
      console.log(url);
      commonRequest(url, idArr, 'post').then((rest) => {
        Message.success({
          content: `${rest.data ? rest.data : rest.msg}`,
          onClose: () => {
            Modal.remove();
            loadData();
          }
        })
      }).catch((err) => {
        console.log(err);
        Message.error({
          content: `${err}`,
        })
      })
    },
  });
}

const saveDataFn = () => {

  console.log('保存数据')

}

const eventFn = (eventObj) => {
  if (eventObj.even === 'table_select') {
    const arr = eventObj.data
    for (let i = 0; i < arr.length; i++) {
      const arrItem = arr[i]
      let h = false;
      for (let j = 0; j < state.config.tempData.length; j++) {
        if (state.config.tempData[j]['tid'] === arrItem['tid']) {
          h = true
        }
      }
      if (!h) {
        state.config.tempData.push(arrItem)
      }
    }
  } else {

  }
  console.log(eventObj)
}

// ===============================================
watch(() => props.setting, () => {
  console.log('ssssssssssssssssssssssssssssssss', props.setting)
  const setting = props.setting;
  if (setting) {
    if (setting.columns) {
      // saveData.value.columns = setting.columns;
    }


    initFn()

    state.tableSetting = setting;


  } else {
    console.log("=========== 未设置数据 =======", setting)
  }

})

</script>

<style lang="less" scoped>
.dataDrawerBox {
  .selectEdBox {
    min-height: 160px;
    background: #eceeef;
    padding: 0 10px 10px 10px;
    border-radius: 4px;

    .selectEd {
      display: flex;
      justify-content: space-between;
      background: #ebf9ff;
      margin: 2px 0;
      border-radius: 2px;
      padding: 4px;

      &:hover {
        background: #2b85e4;
        color: #fff;
      }
    }
  }

  .tempSelectBox {
    min-height: 160px;
    position: relative;
    background: #eceeef;
    padding: 0 10px 10px 10px;
    border-radius: 4px;

    .saveBtn {
      position: absolute;
      top: 6px;
      right: 6px;
    }

    .select {
      display: flex;
      justify-content: space-between;
      background: #ebf9ff;
      margin: 2px 0;
      border-radius: 2px;
      padding: 4px;

      &:hover {
        background: #2b85e4;
        color: #fff;
      }
    }
  }

  .dataBox {
    min-height: 160px;
    position: relative;
    background: #eceeef;
    padding: 0 10px 10px 10px;
    border-radius: 4px;


  }

}

</style>