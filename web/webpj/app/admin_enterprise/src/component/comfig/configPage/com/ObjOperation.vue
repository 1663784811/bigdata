<template>
  <div class="configBox">
    <div class="headerBox">
      <div></div>
      <div>
        <Button class="dataBtn" type="primary" icon="md-list" @click="loadDefaultFn">加载默认</Button>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看代码</Button>
      </div>
    </div>
    <div class="dataContent" v-if="setting">
      <div class="row">
        <div class="labelLeft">操作标题:</div>
        <Checkbox border v-model="setting.show"></Checkbox>
        <div class="rightInput">
          <Input v-model="setting.title" placeholder="标题"/>
        </div>
        <div>宽度:</div>
        <div class="rightInput">
          <Input v-model="setting.width" placeholder="宽" clearable type="number"/>
        </div>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload"
                @click="setting.operationArr.push({label: '',even: '',show: true})">添加
        </Button>
      </div>
      <div class="row" v-for="(item, index) in setting.operationArr" :key="index">
        <div class="sortBtn">
          <Button size="small" type="error" icon="ios-trash-outline" @click="setting.operationArr.splice(index, 1)"/>
          <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up"/>
        </div>
        <div>名称:</div>
        <Checkbox border v-model="item.show"></Checkbox>
        <div class="rightInput">
          <Input v-model="item.label" placeholder="名称"/>
        </div>
        <div>事件:</div>
        <div class="rightInput">
          <Input v-model="item.even" placeholder="事件" clearable/>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import {useWinModal} from "@/store/winModal.js";

const {winIcon, winCode} = useWinModal();

const props = defineProps({
  setting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  operation: {
    show: true,
    title: '操作',
    key: 'operation',
    width: 200,
    operationArr: [
      {
        label: "查看",
        even: '',
        show: true,
      },
      {
        label: "修改",
        even: '',
        show: true,
      },
      {
        label: "删除",
        even: '',
        show: true,
      }
    ]
  }
})

const loadDefaultFn = () => {
  for (const setKey in props.setting) {
    delete props.setting[setKey]
  }
  for (const operationKey in state.operation) {
    const temp = state.operation[operationKey];
    props.setting[operationKey] = JSON.parse(JSON.stringify(temp));
  }
}

const showCodeFn = () => {
  winCode.show = true;
  winCode.data = JSON.stringify(props.setting, null, "  ");
  winCode.callBack = (data) => {
    for (const dataKey in props.setting) {
      delete props.setting[dataKey]
    }
    const obj = JSON.parse(data);
    for (const objKey in obj) {
      props.setting[objKey] = obj[objKey]
    }
  }
}

</script>

<style scoped lang="less">
.configBox {
  .headerBox {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
  }

  .dataContent {
    min-height: 800px;

    .row {
      display: flex;
      align-items: center;
      padding: 12px 0;
      border-top: 1px solid #f1f1f1;

      &:hover {
        background: #f1f1f1;
      }

      .labelLeft {
        width: 120px;
        text-align: right;
        margin-right: 10px;
      }

      .rightInput {
        flex: 1;
        margin-right: 10px;
      }

      .rowItem {
        margin: 0 4px;
      }

      .sortBtn {
      }

      .saveTitle {
        width: 120px;
        text-align: right;
      }
    }
  }
}
</style>