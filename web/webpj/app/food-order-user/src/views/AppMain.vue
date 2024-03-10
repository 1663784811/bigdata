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
  const {appid, storeId} = route.params;
  // 查询门店
  const {data} = await commonQuery({
    code: 'select_e_store_by_code',
    storeId: storeId
  }, appid)
  if (data && data.length === 1) {
    // 保存app信息
    loginInfoSt.variable.appid = appid;
    // 查询当前餐台状态
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
    alert("您的浏览aasssdddssdssddsssddddddsss器不支持socket")
  } else {
    const {code} = route.params;
    // 实例化socket
    socket.value = new WebSocket(`ws://192.168.0.103:8080/app/appId/food/websocket/${code}/user`)
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
  console.log("接收的数:" + msg.data)
  const routeName = route.name;
  const json = JSON.parse(msg.data);
  if (json.code === 1) {
    boardStatus(route.params.code);
  }
}

const boardStatus = (tid) => {
  console.log('查询当前餐台状态:' + tid)
  commonQuery({
    code: 'select_food_board_by_tid',
    tid
  }).then(res => {
    if (res.data.length > 0) {
      const obj = res.data[0];
      if (obj.status === 1) {
        loginInfoSt.token = 'sssss'
        router.replace({
          name: 'home',
          params: route.params,
          query: route.query
        });
      }
    } else {
    }
  })
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
