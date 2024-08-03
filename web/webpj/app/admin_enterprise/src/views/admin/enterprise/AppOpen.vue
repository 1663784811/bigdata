<template>
  <div class="container formBox">
    <form-input :settings="state.settings">
      <template #footer="{data}" >
        <div style="margin: 50px 0">
          <Button type="success" long shape="circle" @click="openAppFn(data)">开通</Button>
        </div>
      </template>
    </form-input>

  </div>

</template>
<script setup>
import {onMounted, reactive} from "vue";
import {loginInfo} from "@/store/loginInfo.js";
import {openApp} from '@/api/api.js'

const loginInfoSt = loginInfo();


const state = reactive({
  settings: {
    columns: [
      {
        "width": 60,
        "key": "id",
        "title": "id",
        "type": "selection",
        "length": 10,
        "isRequire": false,
        "regStr": "",
        "message": "id",
        "controlType": "hidden",
        "format": "",
        "isWhere": false,
        "isShowColumn": true,
        "javaWhere": "eq",
        "javaType": "integer",
        "isShowSave": true
      },
      {
        "key": "nickName",
        "title": "昵称",
        "type": "html",
        "length": 32,
        "isRequire": false,
        "regStr": "",
        "message": "昵称",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "account",
        "title": "账号",
        "type": "html",
        "length": 32,
        "isRequire": false,
        "regStr": "",
        "message": "账号",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "email",
        "title": "邮箱",
        "type": "html",
        "length": 255,
        "isRequire": false,
        "regStr": "",
        "message": "邮箱",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "balance",
        "title": "余额",
        "type": "html",
        "length": 18,
        "isRequire": false,
        "regStr": "",
        "message": "余额",
        "controlType": "number",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "",
        "javaType": "bigdecimal",
        "isShowSave": true
      },
      {
        "key": "face",
        "title": "用户头像",
        "type": "html",
        "length": 65535,
        "isRequire": false,
        "regStr": "",
        "message": "用户头像",
        "controlType": "img",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "ip",
        "title": "最后登录IP",
        "type": "html",
        "length": 60,
        "isRequire": false,
        "regStr": "",
        "message": "最后登录IP",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "lastLoginTime",
        "title": "最后登录时间",
        "type": "html",
        "length": 19,
        "isRequire": false,
        "regStr": "",
        "message": "最后登录时间",
        "controlType": "datetime",
        "format": "yyyy-MM-dd HH:mm:ss",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "eq",
        "javaType": "date",
        "isShowSave": true
      },
      {
        "width": 160,
        "key": "createTime",
        "title": "创建时间",
        "type": "html",
        "length": 19,
        "isRequire": false,
        "regStr": "",
        "message": "创建时间",
        "controlType": "hidden",
        "format": "yyyy-MM-dd HH:mm:ss",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "eq",
        "javaType": "date",
        "isShowSave": true
      },
      {
        "key": "note",
        "title": "备注",
        "type": "html",
        "length": 255,
        "isRequire": false,
        "regStr": "",
        "message": "备注",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "canLoginTime",
        "title": "可登录时间",
        "type": "html",
        "length": 19,
        "isRequire": false,
        "regStr": "",
        "message": "可登录时间",
        "controlType": "datetime",
        "format": "yyyy-MM-dd HH:mm:ss",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "eq",
        "javaType": "date",
        "isShowSave": true
      },
      {
        "key": "openId",
        "title": "微信openid",
        "type": "html",
        "length": 64,
        "isRequire": false,
        "regStr": "",
        "message": "微信openid",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "password",
        "title": "密码",
        "type": "html",
        "length": 32,
        "isRequire": false,
        "regStr": "",
        "message": "密码",
        "controlType": "input",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "phone",
        "title": "手机号",
        "type": "html",
        "length": 15,
        "isRequire": false,
        "regStr": "",
        "message": "手机号",
        "controlType": "input",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "salt",
        "title": "加密盐",
        "type": "html",
        "length": 32,
        "isRequire": false,
        "regStr": "",
        "message": "加密盐",
        "controlType": "input",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "sex",
        "title": "性别",
        "type": "html",
        "length": 5,
        "isRequire": false,
        "regStr": "",
        "message": "性别",
        "controlType": "input",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "status",
        "title": "状态",
        "type": "html",
        "length": 10,
        "isRequire": false,
        "regStr": "",
        "message": "状态{0:正常,1:暂时锁定,2:永久锁定}",
        "controlType": "select",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "eq",
        "javaType": "integer",
        "filters": [
          {
            "value": "0",
            "label": "正常"
          },
          {
            "value": "1",
            "label": "暂时锁定"
          },
          {
            "value": "2",
            "label": "永久锁定"
          }
        ],
        "isShowSave": true
      },
      {
        "key": "trueName",
        "title": "真实姓名",
        "type": "html",
        "length": 32,
        "isRequire": false,
        "regStr": "",
        "message": "真实姓名",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true,
        "isShowSearch": true
      },
      {
        "key": "type",
        "title": "会员类型",
        "type": "html",
        "length": 10,
        "isRequire": false,
        "regStr": "",
        "message": "会员类型{0:普通会员,1:客服}",
        "controlType": "select",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "eq",
        "javaType": "integer",
        "filters": [
          {
            "value": "0",
            "label": "普通会员"
          },
          {
            "value": "1",
            "label": "客服"
          }
        ],
        "isShowSave": true
      },
      {
        "key": "unionId",
        "title": "微信unionid",
        "type": "html",
        "length": 64,
        "isRequire": false,
        "regStr": "",
        "message": "微信unionid",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      },
      {
        "key": "introduceSign",
        "title": "个性签名",
        "type": "html",
        "length": 255,
        "isRequire": false,
        "regStr": "",
        "message": "个性签名",
        "controlType": "input",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true,
        "isShowSearch": true
      },
      {
        "key": "appId",
        "title": "应用ID",
        "type": "html",
        "length": 32,
        "isRequire": false,
        "regStr": "",
        "message": "应用ID",
        "controlType": "input",
        "format": "",
        "isWhere": true,
        "isShowColumn": true,
        "javaWhere": "lk",
        "javaType": "string",
        "isShowSave": true
      }
    ]
  }
})


onMounted(() => {

})


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