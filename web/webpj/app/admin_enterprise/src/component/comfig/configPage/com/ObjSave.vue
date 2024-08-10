<template>
  <div class="configBox" v-if="setting">
    <div class="headerBox">
      <div></div>
      <div>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="setting.columns.push({})">添加</Button>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload">添加从表格选择</Button>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeFn">查看代码</Button>
      </div>
    </div>
    <div class="dataContent">
      <ObjRequest :setting="setting" title="保存地址"/>
      <draggable
          ghost-class="ghost"
          chosen-class="chosenClass"
          animation="300"
          :list="setting.columns"
          item-key="id"
      >
        <template #item="{ element, index }">
          <div class="row">
            <div class="rowItem sortBtn">
              <Button size="small" type="error" icon="ios-trash-outline"
                      @click="setting.columns.splice(index, 1)"/>
            </div>
            <div class="rowItem">
              <Checkbox v-model="element.isShowSave"/>
            </div>
            <div class="rowItem">
              <Input v-model="element.title" placeholder="标题" clearable style="width: 130px"/>
            </div>
            <div class="rowItem">
              <Input v-model="element.key" placeholder="key" clearable style="width: 100px"/>
            </div>
            <div class="rowItem">
              输入类型:
              <Select v-model="element.controlType" clearable size="small" style="width:100px">
                <Option value="hidden">隐藏框</Option>
                <Option value="text">文本</Option>
                <Option value="textarea">长文本</Option>
                <Option value="date">日期</Option>
                <Option value="time">时间</Option>
                <Option value="datetime">日期时间</Option>
                <Option value="img">图片</Option>
                <Option value="select">单选</Option>
                <Option value="number">数字</Option>
              </Select>
            </div>
            <div class="rowItem">
              <Switch v-model="element.isRequire">
                <template #open>必需</template>
              </Switch>
            </div>
            <div class="rowItem">
              正则
              <Input v-model="element.regStr" placeholder="key" clearable style="width: 200px"/>
            </div>
            <div class="rowItem">
              <Input v-model="element.dfval" placeholder="默认值" clearable style="width: 200px"/>
            </div>
          </div>
        </template>
      </draggable>
    </div>
  </div>
</template>

<script setup>
import draggable from "vuedraggable";
import ObjRequest from './ObjRequest.vue'
import {watch} from "vue";
import {useWinModal} from "@/store/winModal.js";

const {winIcon, winCode} = useWinModal();


const emits = defineEmits(['update:modelValue']);
const props = defineProps({
  setting: {
    type: Object,
    default: {
      columns: [],
    },
    required: false
  }
});
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

watch(() => props.setting, () => {
  if (!props.setting.columns) {
    props.setting.columns = []
  }
})
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