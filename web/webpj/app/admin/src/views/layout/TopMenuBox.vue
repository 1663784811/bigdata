<template>
  <div class="menuBox">
    <div class="menuItem" v-for="(item,index) in menuArr" :key="index" @click="gotoPage(item.routeName)">
      <Icon :type="item.icon"/>
      {{ item.name }}
    </div>

  </div>
</template>

<script>
import {ref} from "vue";
import {useRouter} from 'vue-router'

export default {
  name: "TopMenuBox",
  setup() {
    const $r = useRouter();

    const menuArr = ref([
      {
        name: 'Sql配置',
        icon: 'md-settings',
        routeName: 'sqlConfig'
      },
      {
        name: '爬虫监控',
        icon: 'md-settings',
        routeName: 'spiderMonitor'
      },
      {
        name: '数据处理',
        icon: 'md-settings',
        routeName: 'spiderData'
      }
    ]);

    function gotoPage(routeName) {
      $r.push({name: routeName})
    }

    return {
      menuArr, gotoPage
    }
  }
}
</script>

<style scoped lang="less">
.menuBox {
  flex: 1;
  display: flex;
  align-items: center;

  .menuItem {
    color: #fff;
    padding: 0 10px;

    &:hover {
      color: #ccc;
      cursor: pointer;
    }
  }
}
</style>
