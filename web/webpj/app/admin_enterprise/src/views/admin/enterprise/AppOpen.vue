<template>
  <div class="container formBox">
    <form-input :settings="state.pageForm">z
      <template #footer="{data}">
        <div style="margin: 50px 0">
          <Button type="success" long shape="circle" @click="openAppFn(data)">开通</Button>
        </div>
      </template>
    </form-input>

  </div>

</template>
<script setup>
import {onMounted, reactive} from "vue";
import {openApp} from '@/api/api.js'
import {pageConfig} from "@/store/pageConfig";


const usePageConfig = pageConfig();

const state = reactive({
  pageForm: {}
})

onMounted(() => {
  initFn();
})

const initFn = async () => {
  const pageCode = 'ent_open_app'
  const pageData = await usePageConfig.getPageConfig(pageCode);
  state.pageForm = pageData.pageForm;
}

const openAppFn = (data) => {


  openApp().then((rest) => {


    console.log(rest)
  })

}


</script>

<style scoped lang="less">
.formBox {
  padding-bottom: 150px;
}
</style>