<template>
  <div class="address-box">
    <s-header :name="'地址管理'" :back="state.from == 'create-order' ? '' : '/user'"/>
    <div class="address-item">
      <van-address-list
          v-if="state.from != 'mine'"
          v-model="state.chosenAddressId"
          :list="state.list"
          default-tag-text="默认"
          @add="onAdd"
          @edit="onEdit"
          @select="select"
      />
      <van-address-list
          v-else
          v-model="state.chosenAddressId"
          :list="state.list"
          default-tag-text="默认"
          @add="onAdd"
          @edit="onEdit"
      />
    </div>
  </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import {reactive, onMounted} from 'vue'
import {getAddressList} from '@/service/address'
import {useRoute, useRouter} from 'vue-router'

const route = useRoute()
const router = useRouter()
const state = reactive({
  chosenAddressId: '1',
  list: [],
  from: route?.query?.from ?? ''
})

onMounted(() => {
  init()
})

const init = async () => {
  const {data} = await getAddressList()
  if (!data) {
    state.list = []
    return
  }
  state.list = data.map(item => {
    return {
      id: item.tid,
      name: item.name,
      tel: item.phone,
      address: `${item.province} ${item.city} ${item.district} ${item.address}`,
      isDefault: item.defaultIs === 1
    }
  })
}

// 前往添加地址页面
const onAdd = () => {
  router.push({path: 'address-edit', query: {type: 'add', from: state.from}})
}
// 前往地址编辑页面
const onEdit = (item) => {
  router.push({path: 'address-edit', query: {type: 'edit', addressId: item.id, from: state.from}})
}
// 选择某个地址后，跳回订单生成页面
const select = (item, index) => {
  router.push({path: 'create-order', query: {addressId: item.id, from: state.from}})
}
</script>

<style lang="less">
@import '../common/style/mixin';

.address-box {
  .van-radio__icon {
    display: none;
  }

  .address-item {
    .van-button {
      background: @primary;
      border-color: @primary;
    }
  }
}
</style>
