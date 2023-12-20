<template>
  <div class="createCode">
    <div class="tableBox">
      <div v-for="(item,index) in tableList">
        {{ item.note }}{{ item.table }}
      </div>
    </div>
    <div class="codeView">
      <Input :rows="50" disabled show-word-limit type="textarea" placeholder="代码输出"/>
    </div>
    <div class="ctlButtonBox">
      <div class="btnList"><Button type="success" long>Controller</Button></div>
      <div class="btnList"><Button type="success" long>Service</Button></div>
      <div class="btnList"><Button type="success" long>ServiceImpl</Button></div>
      <div class="btnList"><Button type="success" long>Dao</Button></div>
    </div>
  </div>
</template>

<script setup>
import {findSetting, saveComponents, loadTable} from "@/api/api.js";
import {ref} from "vue";

const tableList = ref([]);

const init = () => {

  loadTable({}).then((res) => {
    const {data} = res;
    tableList.value = data;
  })

}
init();

</script>

<style scoped lang="less">
.createCode {
  display: flex;

  .tableBox {
    width: 200px;
    background: #f2f2f2;
    border: 1px solid #ccc;
  }

  .codeView {
    flex: 1;
    padding: 10px;
  }

  .ctlButtonBox {
    width: 160px;
    padding: 10px;
    background: #ccc;
    .btnList{
      margin: 10px 0;
    }
  }
}
</style>