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
      <Divider orientation="left">搜索</Divider>
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
      <div style="text-align: right">
        <Page :total="100" show-elevator />
      </div>
    </div>
  </Modal>
</template>

<script setup>

import {ref} from "vue";
import {commonRequest} from "@/api/api.js";

const selectPanel = ref({
  show: false,
  loading: false,
  columns: [
    {
      title:'标题'
    },
    {
      title:'标题2'
    }
  ],
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