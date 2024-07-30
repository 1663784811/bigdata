<!--       图列表弹出层        -->
<template>
  <Modal
      v-model="modalData.show"
      title="Mqtt 连接 "
      @on-ok="eventFn('ok')"
      @on-cancel="eventFn('cancel')"
      :mask-closable="false"
      :loading="modalData.loading"
      width="900px"
  >
    <template #header>
      <div>
        Mqtt 连接 向设备【 xxxx 】 发送数据
      </div>
    </template>
    <div class="msgContent">
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
      <div class="msgRight">
        <span class="msgText">sssssssssssssssssss</span>
      </div>
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
      <div class="msgRight">
        <span class="msgText">sssssssssssssssssss</span>
      </div>
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
      <div class="msgRight">
        <span class="msgText">sssssssssssssssssss</span>
      </div>
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
      <div class="msgRight">
        <span class="msgText">sssssssssssssssssss</span>
      </div>
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
      <div class="msgRight">
        <span class="msgText">sssssssssssssssssss</span>
      </div>
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
      <div class="msgRight">
        <span class="msgText">sssssssssssssssssss</span>
      </div>
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
      <div class="msgRight">
        <span class="msgText">sssssssssssssssssss</span>
      </div>
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
      <div class="msgRight">
        <span class="msgText">sssssssssssssssssss</span>
      </div>
      <div class="msgLeft">
        <span class="msgText">sssssssssssssssssssssssssssssssssssssssssssssssssssss</span>
      </div>
    </div>
    <template #footer>
      <div class="chatFooter">
        <div>
          <Input placeholder="请输入要发送的数据" :rows="4" type="textarea"/>
        </div>
        <div class="btnBox">
          <div>状态: 已连接服务器</div>
          <Button type="success">发送</Button>
        </div>
      </div>
    </template>
  </Modal>
</template>

<script setup>
import mqtt from "mqtt";
import {defineEmits, onMounted, reactive, ref, watch} from "vue";

const emits = defineEmits(['event', 'update:modelValue']);

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

const state = reactive({})
const modalData = ref({
  loading: true,
  show: true,
  editor: false,
  data: {},
  columns: []
})
onMounted(() => {
  console.log("sssssssssssssssss")
  initMqtt();
})

const initMqtt = () => {
  const client = mqtt.connect("ws://192.168.0.158:15675/ws", {
    clientId: 'my_unique_client_id', // 客户端ID
    username: 'admin', // 用户名
    password: '123456', // 密码
    keepalive: 60, // 保持连接的时间间隔
    clean: true, // 是否清理会话
  });

  client.on('connect', () => console.log('Connected to MQTT Broker.'));
  client.on('error', (err) => console.error('An error occurred: ', err));
  client.on('offline', () => console.log('We are offline.'));
  client.on('reconnect', () => console.log('Reconnecting...'));

  // publish a message
  client.publish('mqtt/test', 'Hello MQTT');

  // subscribe to a topic
  client.subscribe('mqtt/test');
  // handle messages
  client.on('message', (topic, message) => {
    console.log(`Received message on ${topic}: ${message}`);
  });
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