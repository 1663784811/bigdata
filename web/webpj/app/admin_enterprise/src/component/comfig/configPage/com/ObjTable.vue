<template>
  <div class="configBox">
    <div class="headerBox">
      <div></div>
      <div>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="showCodeTableFn('table')">
          查看代码
        </Button>
        <Button class="dataBtn" type="primary" icon="md-cloud-upload" @click="setting.columns.push({})">
          添加字段
        </Button>
      </div>
    </div>
    <div class="dataContent">
      <div>操作对象</div>
      <div>
        显示
        <ObjRequest :setting="setting.queryRequest" title="查询地址"/>
        <ObjRequest :setting="setting.delRequest" title="删除地址"/>
      </div>
      <draggable
          ghost-class="ghost"
          chosen-class="chosenClass"
          animation="300"
          :list="setting.columns"
          item-key="id"
      >
        <template #item="{ element, index }">
          <div class="columnsRow">
            <div class="row">
              <div class="rowItem sortBtn">
                <Button size="small" type="error" icon="ios-trash-outline" @click="setting.columns.splice(index, 1)"/>
              </div>
              <div class="rowItem">
                <Checkbox v-model="element.isShowColumn"/>
              </div>
              <div class="rowItem">
                <Input v-model="element.title" placeholder="标题" clearable style="width: 130px"/>
              </div>
              <div class="rowItem">
                <Input v-model="element.key" placeholder="key" clearable style="width: 100px"/>
              </div>
              <div class="rowItem">
                <Input v-model="element.width" placeholder="宽" clearable type="number" style="width: 80px"/>
              </div>
              <div class="rowItem">
                <Select v-model="element.type" size="small" clearable style="width:110px"
                        @on-change="changeType(element)">
                  <Option value="text">文本</Option>
                  <Option value="selection">选择框</Option>
                  <Option value="img">图片</Option>
                  <Option value="filters">过滤</Option>
                  <Option value="filtersss">开关</Option>
                </Select>
              </div>
              <div class="rowItem">
                <Checkbox v-model="element.tooltip">越长不换行</Checkbox>
              </div>
              <div class="rowItem">
                <Checkbox v-model="element.sortable">排序</Checkbox>
              </div>
              <div class="rowItem">
                <Input v-model="element.event" placeholder="事件" clearable style="width: 100px"/>
              </div>
              <div class="rowItem">
                <Input v-model="element.event" placeholder="表达式" clearable style="width: 100px"/>
              </div>
            </div>
            <div v-if="element.type==='filters'" class="filtersBox">
              <obj-arr title="过滤字段" :setting="element.filters"/>
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
import ObjArr from './ObjArr.vue'

const emits = defineEmits(['showCode', 'update:modelValue']);
const props = defineProps({
  setting: {
    type: Object,
    default: {
      columns: [],
    },
    required: false
  }
});

const showCodeTableFn = (modal) => {
  emits('showCode');
}

const changeType = (item) => {
  if (item.type === 'filters' && !item.filters) {
    item.filters = []
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

    .columnsRow {
      &:hover {
        background: #f1f1f1;
      }

      .row {
        display: flex;
        align-items: center;
        padding: 12px 0;
        border-top: 1px solid #f1f1f1;


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

      .filtersBox {
        padding-left: 70px;
      }
    }
  }
}

</style>