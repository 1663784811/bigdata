<!--       图列表弹出层        -->
<template>
  <Modal
      v-model="winMqtt.show"
      title="Mqtt 连接 "
      @on-ok="eventFn('ok')"
      @on-cancel="eventFn('cancel')"
      :mask-closable="false"
      width="900px"
  >
    <template #header>
      <div>
        Mqtt连接: 向设备【 {{ winMqtt.name }} : {{ winMqtt.code }} 】 发送数据
      </div>
    </template>
    <div class="msgContent">
      <div v-for="(item, index) in state.logDataArr" :key="index" :class="{msgRight:item.send, msgLeft:!item.send}">
        <span class="msgText">{{ item.data }}</span>
      </div>
    </div>
    <template #footer>
      <div class="chatFooter">
        <div>
          <Input v-model="state.inputData" placeholder="请输入要发送的数据" :rows="4" type="textarea"/>
        </div>
        <div class="btnBox">
          <div>状态: {{ state.statusText }}</div>
          <Button type="success" :loading="state.loading" @click="publishMsg">发送</Button>
        </div>
      </div>
    </template>
  </Modal>
</template>

<script setup>
import mqtt from "mqtt";
import {defineEmits, onMounted, reactive, watch} from "vue";

import {useWinModal} from "@/store/winModal.js";


const winMqtt = useWinModal().winMqtt;

const emits = defineEmits(['event', 'update:modelValue']);

let client;
const clientId = 'client.web.web3';

const props = defineProps({
  modalSetting: {
    type: Object,
    default: {},
    required: false
  }
});

const state = reactive({
  loading: false,
  logDataArr: [],
  inputData: '',
  modalData: {},
  statusText: '未初化',
  errorNum: 0
})

onMounted(() => {
  if (winMqtt.show) {
    initMqtt();
  }
})

const initMqtt = () => {
  state.statusText = '正在初始化....';
  client = mqtt.connect("ws://192.168.0.158:15675/ws", {
    clientId: clientId, // 客户端ID
    username: 'admin', // 用户名
    password: '123456', // 密码
    keepalive: 60, // 保持连接的时间间隔
    clean: false, // 是否清理会话
  });
  client.on('connect', () => {
    state.statusText = '连接服务器成功';
  });
  client.on('error', (err) => {
    console.error('mqtt错误: ', err)
    state.statusText = '连接服务错误';
  });
  client.on('offline', () => {
    state.errorNum += 1;
    if (state.errorNum > 5) {
      client.end();
      state.statusText = '连接服务offline';
    }
  });
  client.on('reconnect', () => {
    state.statusText = '正在重新连接';
  });
  client.on('close', () => {
    state.statusText = '连接已关闭ss';
  });
  client.on('message', (topic, message) => {
    console.log(`接收：主题 ${topic}: 内容 ${message}`);
    state.logDataArr.push({
      send: false,
      data: message
    });
  });
  client.subscribe(clientId);
}

/**
 * 发布主题
 */
const publishMsg = () => {
  state.loading = true;
  const inputData = state.inputData;
  if (inputData && inputData.length > 0) {
    client.publish(`mqtt_service.${winMqtt.code}`, `${inputData}`);
    state.inputData = ''
    state.logDataArr.push({
      send: true,
      data: inputData
    });
  }
  state.loading = false;
}


watch(() => winMqtt.show, (val) => {
  if (val) {
    if (!client || !client.connected) {
      initMqtt();
    }
  } else {

  }
})

/**
 * 事件
 */
const eventFn = (ev) => {
  console.log(ev)
}


</script>

<style scoped lang="less">

.msgContent {
  min-height: 50vh;
  max-height: 50vh;
  overflow: auto;

  .msgLeft {
    margin-bottom: 5px;

    .msgText {
      background: #2b85e4;
      padding: 5px 10px;
      border-radius: 4px;
      display: inline-block;
      color: #fff;
    }
  }

  .msgRight {
    text-align: right;
    margin-bottom: 5px;

    .msgText {
      background: #515a6e;
      padding: 5px 10px;
      border-radius: 4px;
      display: inline-block;
      color: #fff;
    }
  }
}

.chatFooter {
  .btnBox {
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
  }
}
</style>