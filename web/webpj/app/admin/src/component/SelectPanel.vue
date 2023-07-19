<template>
  <Modal
      v-model="selectPanel.show"
      title="选择面板"
      @on-ok="okFn()"
      @on-cancel="cancel()"
      :mask-closable="false"
      :loading="selectPanel.loading"
      width="80wh"
  >
    <div class="modalBox">
      <div>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
        <Tag color="success" closable>success</Tag>
      </div>
      <div class="tableBox">
        <Table ref="selection"
               border
               highlight-row
               stripe
               size="small"
               :columns="selectPanel.columns"
               :data="selectPanel.data"
               :loading="selectPanel.loading"
        >
        </Table>
      </div>
    </div>
  </Modal>
</template>

<script setup>

import {ref} from "vue";
import {commonRequest} from "@/api/api";

const selectPanel = ref({
  show: true,
  loading: false,
  columns: [],
  data: [],
  pageData: {
    total: 0
  },
  url: '',
  split: 0.5
});

const okFn = () => {

}
const cancel = () => {

}

/**
 * 加载数据
 */
const loadData = () => {
  commonRequest(
      selectPanel.value.url,
      {}
  ).then((res) => {
    selectPanel.value.loading = false;
    selectPanel.value.data = res.data;
    selectPanel.value.pageData.total = res.result.total;
  }).catch(err => {
    selectPanel.value.loading = false;
    selectPanel.value.data = [];
    console.log(err);
  })
}


</script>

<style scoped lang="less">

.tableBox {
  padding: 20px 0;
}
</style>