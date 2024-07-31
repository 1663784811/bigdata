<!--       图列表弹出层        -->
<template>
  <Modal
      v-model="winIcon.show"
      title="Icon选择"
      @on-ok="eventFn('ok')"
      @on-cancel="eventFn('cancel')"
      :mask-closable="false"
      width="80vw"
  >
    <div class="iconContainer">
      <div class="item" v-for="(item, index) in state.iconArr" :key="index"
           @click="state.selectIcon = item"
           :class="{active: item === state.selectIcon}">
        <Icon :type="item"/>
        <div style="font-size: 12px">{{ item }}</div>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import {useWinModal} from "@/store/winModal.js";
import iconArr from '@/api/icon.js'


const {winIcon} = useWinModal();
const emits = defineEmits(['event', 'update:modelValue']);

const state = reactive({
  iconArr: iconArr,
  selectIcon: ''
})

onMounted(() => {

})


/**
 * 事件
 */
const eventFn = (ev) => {
  if (ev === 'ok' && winIcon.callBack) {
    winIcon.callBack(state.selectIcon);
    winIcon.callBack = null;
  }
}


</script>

<style scoped lang="less">
.iconContainer {
  display: flex;
  flex-wrap: wrap;
  height: 70vh;
  overflow: auto;

  .item {
    padding: 4px 10px;
    font-size: 20px;
    text-align: center;
    cursor: pointer;

    &.active {
      background: #ccc;
    }

    &:hover {
      background: #ccc;
    }
  }


}
</style>