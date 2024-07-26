<template>
  <modal-data-list
      v-model="winModal.winData.show"
      :modalSetting="winModal.winData"
      @event="saveEventFn"
  />
</template>

<script setup>
import {useWinModal} from '@/store/winModal.js'
import {commonRequest} from "@/api/api.js";
import {Message} from "view-ui-plus";
import {loginInfo} from "@/store/loginInfo.js";

const winModal = useWinModal();
const loginInfoSt = loginInfo();

const saveEventFn = (ev, itemData) => {
  if ('ok' === ev) {
    save(itemData)
  } else if ('cancel' === ev) {
    // Cancel(itemData);
  } else if (ev == 'change') {
    if (winModal.winData.changeDataFn) {
      winModal.winData.changeDataFn(itemData);
    }
  }
}


const save = (itemData) => {
  let url = winModal.winData.url;
  commonRequest(loginInfoSt.reLoadUrl(url), itemData, 'post').then((rest) => {
    winModal.winData.data = rest.data;
    Message.success({
      content: `${rest.msg}`,
      onClose: () => {
        winModal.winData.show = false;
      }
    })
  }).catch(err => {
    Message.error({
      content: `${err}`
    })
  }).finally(() => {
    winModal.winData.loading = false;
    setTimeout(() => {
      winModal.winData.loading = true;
    })
  })
}


</script>

<style scoped lang="less">

</style>