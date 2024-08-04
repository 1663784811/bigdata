<template>
  <Drawer
      placement="right"
      title="部门"
      v-model="pageStatus.show"
      width="1200"
      :mask-closable="false"
  >
    <div class="dataDrawerBox">
      <div class="selectEdBox">
        <Divider orientation="left">已选择</Divider>
        <div class="selectEd" v-for="(item, index) in state.selectData" :key="index">
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

        <div class="select" v-for="(item, index) in state.tempData" :key="index">
          <div>{{ item.name }}</div>
          <Button class="btn"
                  type="error"
                  size="small"
                  icon="ios-trash-outline"
                  @click="state.tempData.splice(index, 1)">
          </Button>

        </div>
      </div>

      <div class="dataBox">
        <Divider orientation="left">数据</Divider>
        <DataTable :setting="state.tableSetting" @event="eventFn"/>
      </div>
    </div>
  </Drawer>
</template>


<script setup>
import DataTable from "@/component/modal/DataTable.vue";
import {reactive, inject, watch, ref} from "vue";
import {commonRequest} from "@/api/api.js";
import {loginInfo} from "@/store/loginInfo.js";
import {Message, Modal} from "view-ui-plus";

const loginInfoSt = loginInfo();
const emits = defineEmits(['event', 'update:modelValue']);


const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  },
  modelValue: {
    type: Boolean,
    default: false
  },
});

const pageStatus = ref({
  show: false,
  loading: false,
  data: {}
});

const state = reactive({
  selectData: [],
  tempData: [],
  // =================
  selectObj: {
    queryRequest: {
      url: '',
      parameter: {
        code: ''
      }
    },
    delRequest: {
      url: '',
      parameter: {
        code: ''
      }
    },
  },
  // =================
  saveObj: {
    saveRequest: {
      url: '',
      parameter: {
        code: ''
      }
    },
  },
  tableSetting: {
    tableObj: {}
  },
  pageStatus: {
    show: false,
    loading: false,
    data: {}
  }
})


// 初始化
const initFn = () => {
  // 初始化页面
  initPage();
  // 初始化选择
  initSelectFn()

  // 初始化表格
  initTable();

}

const initPage = () => {
  const {setting} = props;
  state.pageStatus.data = setting.data;
  state.selectObj = setting.selectObj;
  state.saveObj = setting.saveObj;
  state.tableSetting = {
    tableObj: setting.tableObj
  };
}
const initSelectFn = () => {
  commonRequest(
      loginInfoSt.reLoadUrl(state.selectObj.queryRequest.url),
      {
        ...dataMapping(state.pageStatus.data, state.selectObj.queryRequest.mapping),
        ...state.selectObj.queryRequest.parameter
      }
  ).then((rest) => {
    const {data, code} = rest;
    if (code === 2000) {
      state.selectData = data
    } else {
      state.selectData = [];
    }
  }).catch(err => {
    console.log(err);
  }).finally(() => {
  })
}

const dataMapping = (data, mapping) => {
  const rest = {};
  if (mapping && data && Object.keys(data).length > 0) {
    for (const dataKey in data) {
      if (mapping[dataKey]) {
        if (mapping[dataKey] !== '_del') {
          rest[mapping[dataKey]] = data[dataKey];
        }
      } else {
        rest[dataKey] = data[dataKey];
      }
    }
  } else {
    return data;
  }
  return rest;
}

const initTable = () => {

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
  delDataFn([item]);
}
const delDataFn = (objArr = []) => {
  Modal.confirm({
    title: '是否删除?',
    okText: '删除',
    loading: true,
    onOk: async () => {
      const {parameter, isCommonUrl} = state.selectObj.delRequest;

      for (let i = 0; i < objArr.length; i++) {
        const url = loginInfoSt.reLoadUrl(state.selectObj.delRequest.url);
        let pm = {};
        if (isCommonUrl) {
          pm = {
            ...parameter,
            data: {
              ...objArr[i]
            }
          }
        } else {
          pm = {
            ...objArr[i],
            ...state.selectObj.delRequest.parameter
          }
        }
        await commonRequest(url, pm, 'post').then((rest) => {
          Message.success(`${rest.msg}`)
        }).catch((err) => {
          console.log(err);
          Message.error({
            content: `${err}`,
          })
        })
      }
      Modal.remove();
      initSelectFn();
    },
  });
}

const saveDataFn = async () => {
  const {tempData, pageStatus} = state;
  const oldData = pageStatus.data;
  for (let i = 0; i < tempData.length; i++) {
    const tempObj = tempData[i];
    let {url, parameter, mapping, mapping1} = state.saveObj.saveRequest;
    const sendData = {};
    for (const mappingKey in mapping) {
      sendData[mapping[mappingKey]] = oldData[mappingKey]
    }
    for (const mapping1Key in mapping1) {
      sendData[mapping1[mapping1Key]] = tempObj[mapping1Key]
    }
    let pm = {};
    if (!url || url === '/admin/${eCode}/common/save') {
      url = "/admin/${eCode}/common/save";
      pm = {
        ...parameter,
        data: sendData
      }
    } else {
      pm = sendData
    }
    await commonRequest(loginInfoSt.reLoadUrl(url), pm, 'post').then((rest) => {
      console.log(rest)
    }).catch(err => {
      Message.error({
        content: `${err}`
      })
    })
  }
  state.tempData = [];
  initFn()
}

const eventFn = (eventObj) => {
  console.log(eventObj)
  if (eventObj.even === 'table_select') {
    const arr = eventObj.data
    for (let i = 0; i < arr.length; i++) {
      const arrItem = arr[i]
      let h = false;
      for (let j = 0; j < state.tempData.length; j++) {
        if (state.tempData[j]['tid'] === arrItem['tid']) {
          h = true
        }
      }
      if (!h) {
        state.tempData.push(arrItem)
      }
    }
  }
}

// ===============================================
watch(() => props.modelValue, () => {
  const {setting} = props;
  pageStatus.value.show = props.modelValue
  state.pageStatus.show = props.modelValue;
  if (setting) {
    if (setting.show) {
      initFn()
    }
  } else {
    console.log("=========== 未设置数据 =======", setting)
  }
}, {deep: false, immediate: false})

watch(() => pageStatus.value.show, () => {
  emits("update:modelValue", pageStatus.value.show)
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