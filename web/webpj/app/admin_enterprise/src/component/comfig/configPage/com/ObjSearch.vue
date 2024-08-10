<template>
  <div class="configBox">
    <div class="headerBox">
      <div></div>
      <div>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看代码
        </Button>
      </div>
    </div>
    <div class="dataContent">
      <div class="row">
        <div class="labelLeft">显示搜索模块:</div>
        <Checkbox border v-model="setting.show"></Checkbox>
        <Button class="dataBtn" type="primary" icon="md-list" @click="setting.columns.push({})">添加</Button>
      </div>
      <template v-for="(item, index) in setting.columns" :key="index">
        <div class="row">
          <div class="sortBtn">
            <Button size="small" type="error" icon="ios-trash-outline" @click="setting.columns.splice(index,1)"/>
            <Button v-if="index>0" size="small" type="primary" icon="md-arrow-up"
                    @click="arrUp(setting.columns,index)"/>
          </div>
          <div>名称:</div>
          <Checkbox border v-model="item.show"></Checkbox>
          <div class="rightInput">
            <Input v-model="item.name" placeholder="名称"/>
          </div>
          <div class="rightInput">
            <Input v-model="item.icon" placeholder="icon" @on-focus="selectIconFn(item, 'icon')"/>
          </div>
          <div class="rightInput">
            <Input v-model="item.type" placeholder="icon"/>
          </div>
          <div class="rightInput">
            <Input v-model="item.even" placeholder="事件" clearable/>
          </div>
          <div class="rightInput">
            <Input v-model="item.power" placeholder="授权码" clearable/>
          </div>
        </div>
        <!-- ================== -->
        <template v-if="item.even === 'search' && item.parameter.length>0">
          <div class="row" style="margin-left: 100px;" v-for="(it,inx) in item.parameter" :key="inx">
            <div class="rowItem sortBtn">
              <Button size="small" type="error" icon="ios-trash-outline" @click="item.parameter.splice(inx, 1)"/>
              <Button v-if="inx>0" size="small" type="primary" icon="md-arrow-up" disabled/>
            </div>
            <div class="rowItem">
              <Checkbox v-model="it.isShowColumn"/>
              <Input v-model="it.title" placeholder="标题" clearable style="width: 130px"/>
            </div>
            <div class="rowItem">
              <Input v-model="it.key" placeholder="key" clearable style="width: 100px"/>
            </div>
            <div class="rowItem">
              搜索条件:
              <Select v-model="it.javaWhere" clearable size="small" style="width:160px">
                <Option value="lk">%模糊查询%</Option>
                <Option value="lkR">模糊查询%</Option>
                <Option value="lkL">%模糊查询</Option>
                <Option value="eq">等于</Option>
                <Option value="neq">不等于</Option>
                <Option value="geq">大于等于</Option>
                <Option value="gt">大于</Option>
                <Option value="leq">小于等于</Option>
                <Option value="lt">小于</Option>
              </Select>
            </div>
            <div class="rowItem">
              {{ it.controlType }}
              <Select v-model="it.javaWhere" clearable size="small" style="width:160px">
                <Option value="lk">%模糊查询%</Option>
                <Option value="lkR">模糊查询%</Option>
                <Option value="lkL">%模糊查询</Option>
                <Option value="eq">等于</Option>
                <Option value="neq">不等于</Option>
                <Option value="geq">大于等于</Option>
                <Option value="gt">大于</Option>
                <Option value="leq">小于等于</Option>
                <Option value="lt">小于</Option>
              </Select>
            </div>
          </div>
        </template>
      </template>
    </div>
  </div>
</template>

<script setup>
import {useWinModal} from "@/store/winModal.js";

const {winIcon, winCode} = useWinModal();

const props = defineProps({
  setting: {
    type: Object,
    default: {
      columns: [],
    },
    required: false
  }
});

const selectIconFn = (obj, key) => {
  winIcon.show = true;
  winIcon.callBack = (iconText) => {
    obj[key] = iconText;
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

const arrUp = (list, index) => {
  if (index > 0) {
    let nowObj = list[index];
    let prObj = list[index - 1];
    list[index] = prObj;
    list[index - 1] = nowObj;
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