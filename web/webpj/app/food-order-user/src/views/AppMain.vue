<template>
  <router-view/>
</template>
<script setup>

import {onMounted, ref} from "vue";
import {commonQuery} from "@/service/api";
import {useRoute, useRouter} from "vue-router";
import {useUserStore} from "@/stores/user";

const route = useRoute();
const router = useRouter();
const loginInfoSt = useUserStore();

const socket = ref(null);

onMounted(async () => {
  const {appid} = route.params;
  const {data} = await commonQuery({
    code: 'select_e_application_by_appid',
    appid
  })
  if (data && data.length === 1) {
    // 保存app信息
    loginInfoSt.variable.appid = appid;
    // 查询当前餐台状态
    console.log('查询当前餐台状态')
    initWebSocket();
  } else {
    console.log(data)
    delete loginInfoSt.variable.appid;
    await router.replace({name: 'welcomePage'});
  }


})

// 使用websocket 工具


const initWebSocket = () => {
  if (typeof (WebSocket) === "undefined") {
    alert("您的浏览器不支持socket")
  } else {
    // 实例化socket
    socket.value = new WebSocket("ws://192.168.0.103:8080/app/appId/food/websocket/111/user")
    // 监听socket连接
    socket.value.onopen = openWebSocket
    // 监听socket错误信息
    socket.value.onerror = errorWebSocket
    // 监听socket消息
    socket.value.onmessage = msgWebSocket
    socket.value.onclose = close
  }
}

const openWebSocket = () => {

}

const errorWebSocket = () => {

}
const msgWebSocket = (msg) => {
  console.log(msg.data)
  const routeName = route.name;
  const json = JSON.parse(msg.data);
  if (json.code === 1) {
    loginInfoSt.token = 'sssss'
    router.replace({name: 'home'});
    // if (json.data.status === 0) {
    //
    // } else if (json.data.status === 1) {
    //   if (routeName === 'selectNumber') {
    //     router.replace({name: 'home'});
    //   }
    // }



  }
}

const send = (params) => {
  socket.value.send(params)
}
const close = () => {
  console.log("socket已经关闭")
  // 重连

}


/**
 * 需要接收的消息
 * 1.选择人数, 开始点菜
 * 2.添加菜品消息,减少菜品消息
 * 3.提交菜品消息
 * 4.支付消息
 */


</script>

<style scoped lang="less">

</style>