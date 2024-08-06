<template>
  <div>
    <div class="infoBoxRow">
      <div class="infoBox">
        <div class="infoHead">CPU</div>
        <div class="rowBox">
          <div class="label">核心数:</div>
          <div class="infVal">{{ state.cpu.cpuNum }}</div>
        </div>
        <div class="rowBox">
          <div class="label">线程:</div>
          <div class="infVal">{{ state.cpu.threadNum }}</div>
        </div>
        <div class="rowBox">
          <div class="label">总的使用率:</div>
          <div class="infVal">{{ state.cpu.total }}</div>
        </div>
        <div class="rowBox">
          <div class="label">系统使用率:</div>
          <div class="infVal">{{ state.cpu.sys }}</div>
        </div>
        <div class="rowBox">
          <div class="label">用户使用率:</div>
          <div class="infVal">{{ state.cpu.used }}</div>
        </div>
        <div class="rowBox">
          <div class="label">等待率:</div>
          <div class="infVal">{{ state.cpu.wait }}</div>
        </div>
        <div class="rowBox">
          <div class="label">空闲率:</div>
          <div class="infVal">{{ state.cpu.free }}</div>
        </div>
      </div>

      <div class="infoBox">
        <div class="infoHead">内存</div>
        <div class="rowBox">
          <div class="label">内存总量(GB):</div>
          <div class="infVal">{{ state.mem.total }}</div>
        </div>
        <div class="rowBox">
          <div class="label">已用内存(GB):</div>
          <div class="infVal">{{ state.mem.used }}</div>
        </div>
        <div class="rowBox">
          <div class="label">剩余内存(GB):</div>
          <div class="infVal">{{ state.mem.free }}</div>
        </div>
      </div>

    </div>
    <div class="infoBoxRow">

      <div class="infoBox">
        <div class="infoHead">java虚拟机信息</div>
        <div class="rowBox">
          <div class="label">当前JVM占用的内存总数(M):</div>
          <div class="infVal">{{ state.jvm.total }}</div>
        </div>
        <div class="rowBox">
          <div class="label">JVM最大可用内存总数(M):</div>
          <div class="infVal">{{ state.jvm.max }}</div>
        </div>
        <div class="rowBox">
          <div class="label">JVM空闲内存(M):</div>
          <div class="infVal">{{ state.jvm.free }}</div>
        </div>
        <div class="rowBox">
          <div class="label">JDK版本:</div>
          <div class="infVal">{{ state.jvm.version }}</div>
        </div>
        <div class="rowBox">
          <div class="label">JDK路径:</div>
          <div class="infVal">{{ state.jvm.home }}</div>
        </div>
      </div>

      <div class="infoBox">
        <div class="infoHead">系统</div>
        <div class="rowBox">
          <div class="label">服务器名称:</div>
          <div class="infVal">{{ state.sys.computerName }}</div>
        </div>
        <div class="rowBox">
          <div class="label">服务器Ip:</div>
          <div class="infVal">{{ state.sys.computerIp }}</div>
        </div>
        <div class="rowBox">
          <div class="label">项目路径:</div>
          <div class="infVal">{{ state.sys.userDir }}</div>
        </div>
        <div class="rowBox">
          <div class="label">操作系统:</div>
          <div class="infVal">{{ state.sys.osName }}</div>
        </div>
        <div class="rowBox">
          <div class="label">系统架构:</div>
          <div class="infVal">{{ state.sys.osArch }}</div>
        </div>
      </div>
    </div>


    <div class="infoBoxRow">
      <div class="infoBox">
        <div class="infoHead">文件系统</div>
        <div class="rowBox" v-for="(item, index) in state.sysFiles" :key="index">
          <div class="label">{{ item.typeName }}:</div>
          <div class="infVal"> 类型: {{ item.sysTypeName }} , 总容量: {{ item.total }} , 剩余空间:{{ item.free }}</div>
        </div>
      </div>
    </div>

  </div>
</template>
<script setup>
import {serverNodeInfo} from '@/api/api.js'
import {onMounted, reactive} from "vue";


const state = reactive({
  cpu: {},
  jvm: {},
  mem: {},
  sys: {},
  sysFiles: []
})


onMounted(() => {

  serverNodeInfo().then((rest) => {
    const {data} = rest;
    if (data) {
      state.cpu = data.cpu;
      state.jvm = data.jvm;
      state.mem = data.mem;
      state.sys = data.sys;
      state.sysFiles = data.sysFiles;
    }
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