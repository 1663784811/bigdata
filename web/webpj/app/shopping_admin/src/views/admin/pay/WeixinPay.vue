<template>

  <div class="headTitle">
    微信支付设置
  </div>

  <div class="formBox">
    <div class="rowBox">
      <div class="itemLabel">
        APPID:
      </div>
      <div class="itemValBox">
        <Input placeholder="APPID"/>
      </div>
    </div>
    <div class="rowBox">
      <div class="itemLabel">
        APPID:
      </div>
      <div class="itemValBox">
        abcdefeefsse
      </div>
    </div>
    <div class="rowBox">
      <div class="itemLabel">
        APPID:
      </div>
      <div class="itemValBox">
        abcdefeefsse
      </div>
    </div>
    <div class="rowBox">
      <div class="itemLabel">
        APPID:
      </div>
      <div class="itemValBox">
        abcdefeefsse
      </div>
    </div>
    <div class="rowBox">
      <div class="itemLabel">
        APPID:
      </div>
      <div class="itemValBox">
        abcdefeefsse
      </div>
    </div>
    <div class="rowBox">
      <div class="itemLabel"></div>
      <div>
        <Button type="info">保存</Button>
      </div>
    </div>
  </div>


</template>
<script setup>
import {onMounted} from "vue";
import {commonRequest} from "@/api/api.js"
import {useRoute, useRouter} from "vue-router";

const router = useRouter();
const route = useRoute();

onMounted(() => {

  const {code} = route.params;

  commonRequest("/admin/aaa/common/query", {
    code: 'select_ent_pay_setting',
    pay_type: 1,
    appId: 'ssss'
  }).then(rest => {
    console.log(rest)
  })

})


</script>
<style scoped lang="less">
.headTitle {
  background: #fff;
  padding: 10px 20px;
  font-size: 18px;
}

.formBox {
  background: #fff;

  .rowBox {
    display: flex;
    padding: 10px;

    .itemLabel {
      width: 120px;
      text-align: right;
      margin-right: 10px;
    }

    .itemValBox {

    }
  }
}
</style>