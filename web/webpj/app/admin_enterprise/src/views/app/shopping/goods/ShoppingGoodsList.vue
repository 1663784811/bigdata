<template>
  <div>
    <CommonTable :table-setting="commonTable" @event="tableEvent"/>
  </div>
</template>

<script setup>
import CommonTable from '@/component/CommonTable.vue'
import {pageConfig} from "@/store/pageConfig.js";
import {ref} from "vue";
import {useRouter} from "vue-router";

const route = useRouter();


const usePageConfig = pageConfig();
const commonTable = ref(null);
const initFn = async () => {
  const role = await usePageConfig.getPageConfig("goodsList");
  commonTable.value = role.commonTable;
}
initFn();

const tableEvent = (eData) => {
  console.log(eData)
  if (eData.even && eData.even === "showEvent") {
    const {tid} = eData.data;
    route.push({
      name: 'shoppingGoodsEditor',
      query: {
        goodsId: tid,
        show: true
      }
    })
  } else if (eData.even && eData.even === "updateEvent") {
    const {tid} = eData.data;
    route.push({
      name: 'shoppingGoodsEditor',
      query: {
        goodsId: tid,
        show: false
      }
    })
  }


}

</script>

<style scoped lang="less">

</style>
