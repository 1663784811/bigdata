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
          <div>状态: 已连接服务器</div>
          <Button type="success" :loading="state.loading" @click="publishMsg">发送</Button>
        </div>
      </div>
    </template>
  </Modal>
</template>

<script setup>
import mqtt from "mqtt";
import {defineEmits, onMounted, reactive, ref, watch} from "vue";

import {useWinModal} from "@/store/winModal.js";


const winMqtt = useWinModal().winMqtt;

const emits = defineEmits(['event', 'update:modelValue']);

let client;
const clientId = 'client.web.web3';

const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false
  },
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
  modalData: {}
})
const modalData = ref({
  editor: false,
  data: {},
  columns: []
})
onMounted(() => {
  initMqtt();
})

const initMqtt = () => {
  client = mqtt.connect("ws://192.168.0.158:15675/ws", {
    clientId: clientId, // 客户端ID
    username: 'admin', // 用户名
    password: '123456', // 密码
    keepalive: 6, // 保持连接的时间间隔
    clean: false, // 是否清理会话
  });

  client.on('connect', () => console.log('Connected to MQTT Broker.'));
  client.on('error', (err) => console.error('An error occurred: ', err));
  client.on('offline', () => console.log('We are offline.'));
  client.on('reconnect', () => console.log('Reconnecting...'));

  client.on('message', (topic, message) => {
    console.log(`接收：主题 ${topic}: 内容 ${message}`);
    state.logDataArr.push({
      send: false,
      data: message
    });
  });
  client.subscribe(clientId);
}

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


watch(() => props.modalSetting, () => {
  modalData.value = {
    ...modalData.value,
    ...props.modalSetting
  }
}, {deep: true, immediate: true})

watch(() => props.modelValue, () => {
  modalData.value.show = props.modelValue;
})

watch(() => modalData.value.show, () => {
  emits('update:modelValue', modalData.value.show);
})


/**
 * 事件
 */
const eventFn = (ev) => {
  const {data} = modalData.value;
  emits('event', ev, data);
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