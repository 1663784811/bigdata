<template>
  <div>
    <div class="infoBoxRow">
      <div class="infoBox">
        <div class="infoHead">
          CPU
        </div>
        <div class="rowBox" v-for="i in 10">
          <div class="label">
            Java名称:
          </div>
          <div class="infVal">
            Java HotSpot(TM) 64-Bit Server VM
          </div>
        </div>
      </div>

      <div class="infoBox">
        <div class="infoHead">
          内存
        </div>
        <div class="rowBox" v-for="i in 10">
          <div class="label">
            Java名称:
          </div>
          <div class="infVal">
            Java HotSpot(TM) 64-Bit Server VM
          </div>
        </div>
      </div>
    </div>
    <div class="infoBoxRow">
      <div class="infoBox">
        <div class="infoHead">
          Java虚拟机信息
        </div>
        <div class="rowBox" v-for="i in 10">
          <div class="label">
            Java名称:
          </div>
          <div class="infVal">
            Java HotSpot(TM) 64-Bit Server VM
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {serverNodeInfo} from '@/api/api.js'
import {onMounted} from "vue";


onMounted(() => {

  serverNodeInfo().then((rest) => {
    console.log(rest)
  })

})


</script>
<style scoped lang="less">

.infoBoxRow {
  display: flex;

  .infoBox {
    flex: 1;
    background: #fff;
    border: 1px solid #f1f1f1;
    box-shadow: 0 0 2px #ccc;
    border-radius: 6px;
    margin: 10px;

    .infoHead {
      padding: 10px;
      border-bottom: 1px solid #f1f1f1;
      font-size: 16px;
      font-weight: bold;
    }

    .rowBox {
      display: flex;
      padding: 10px;
      border-bottom: 1px solid #f1f1f1;

      .infVal {
        margin-left: 10px;
        font-weight: bold;
      }
    }
  }
}


</style>