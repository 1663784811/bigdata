<template>
  <Modal
      v-model="sqlModal.show"
      title="保存SQL数据"
      @on-ok="Save"
      @on-cancel="Cancel"
      :mask-closable="false"
      :loading="true"
      width="80wh"
  >
    <div class="modalBox">
      <div class="sqlContent">
        <div class="row">
          <div class="label">ID</div>
          <div class="content">
            <Input v-model="state.sqlData.tid" placeholder="ID"/>
          </div>
        </div>
        <div class="row">
          <div class="label">名称</div>
          <div class="content">
            <Input v-model="state.sqlData.name" placeholder="名称"/>
          </div>
        </div>
        <div class="row">
          <div class="label">类型</div>
          <div class="content">
            <Select v-model="state.sqlData.type">
              <Option :value="0">查询</Option>
              <Option :value="1">保存</Option>
              <Option :value="2">删除</Option>
            </Select>
          </div>
        </div>

        <div class="row">
          <div class="label">是否需要登录</div>
          <div class="content">
            <Select v-model="state.sqlData.login">
              <Option :value="0">不需要</Option>
              <Option :value="1">需要</Option>
            </Select>
          </div>
        </div>
        <div class="row">
          <div class="label">状态</div>
          <div class="content">
            <Select v-model="state.sqlData.status">
              <Option :value="0">启用</Option>
              <Option :value="1">停用</Option>
            </Select>
          </div>
        </div>
        <div v-if="state.sqlData.type === 0">
          <div class="row">
            <div class="label">查询语句</div>
            <div class="content">
              <Input v-model="state.sqlData.contentSql" placeholder="sql" type="textarea" :rows="8"/>
            </div>
          </div>
          <div>
            <Button size="small" type="success" icon="md-add"/>
            <div class="mountFieldsBox">
              <div>外挂字段</div>
              <div>
                <Input type="text" placeholder="字段"/>
              </div>
              <div>
                <Input type="textarea" placeholder="sql"/>
              </div>
              <div>
                <Input type="textarea" placeholder="关联"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="label">统计数量</div>
            <div class="content">
              <Input v-model="state.sqlData.countSql" placeholder="sql" type="textarea" :rows="8"/>
            </div>
          </div>
        </div>
        <div v-else-if="state.sqlData.type === 1">
          <div class="row">
            <div class="label">主表</div>
            <div class="content">
              <Input v-model="state.sqlData.mainTable" placeholder="主表"/>
            </div>
          </div>
          <div class="row">
            <div class="label">主表ID</div>
            <div class="content">
              <Input v-model="state.sqlData.mainId" placeholder="主表ID"/>
            </div>
          </div>
          <div class="row">
            <div class="label">插入语句</div>
            <div class="content">
              <Input v-model="state.sqlData.insetSql" placeholder="sql" type="textarea" :rows="8"/>
            </div>
          </div>
          <div class="row">
            <div class="label">更新语句</div>
            <div class="content">
              <Input v-model="state.sqlData.updateSql" placeholder="sql" type="textarea" :rows="8"/>
            </div>
          </div>
        </div>
        <div v-else-if="state.sqlData.type === 2">
          <div class="row">
            <div class="label">主表</div>
            <div class="content">
              <Input v-model="state.sqlData.mainTable" placeholder="主表"/>
            </div>
          </div>
          <div class="row">
            <div class="label">主表ID</div>
            <div class="content">
              <Input v-model="state.sqlData.mainId" placeholder="主表ID"/>
            </div>
          </div>
          <div class="row">
            <div class="label">删除语句:</div>
            <div class="content">
              <Input v-model="state.sqlData.delSql" placeholder="delSql" type="textarea" :rows="8"/>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="label">备注</div>
          <div class="content">
            <Input v-model="state.sqlData.note" placeholder="备注" type="textarea" :rows="8"/>
          </div>
        </div>

      </div>
      <div class="sqlNote">
        <p>[&] ---&gt; 必传参</p>
        <p>[:=] ----&gt; 别名 [别名:=数据库字段]</p>
        <p>[] ---&gt; =</p>
        <p>[@] ---&gt; in</p>
        <p>[!@] ----&gt; not in</p>
        <p>[%] ----&gt; like</p>
        <p>[L%] ----&gt; like</p>
        <p>[R%] ----&gt; like</p>
        <p>[!%] ----&gt; not like</p>
        <p>[!!&lt;=] ----&gt; 大于等于</p>
        <p>[!!&lt;=] ----&gt; 小于等于</p>
        <div>----------------------------------</div>
        <div>----------------------------------</div>
        <div>内置变量</div>
        <div>__user_eCode:登录所在的企业</div>
        <div>__user_appId:登录所在的app</div>
        <div>__user_uId:用户ID</div>
        <div>__user_account:用户账号</div>
        <div>__user_name:用户名</div>

      </div>
      <div style="width: 500px">
        <div style="display: flex">
          <Input placeholder="主表" v-model="state.mainTable"/>
          <Button size="small" type="warning" style="margin-right: 5px" @click="loadTableFn">加载表字段</Button>
        </div>
        <div>
          <p v-for="(it, index) in state.tableColumn" :key="index">{{ it }}</p>
        </div>
        <div>-------------------------</div>
        <div>
          <span v-for="(it, index) in state.tableColumn" :key="index">{{ it }}</span>
        </div>
        <div>-----------save--------------</div>
        <div>
          <span>{{ state.saveStr }}</span>
        </div>
        <div>-----------upate--------------</div>
        <div>
          <span>{{ state.updateStr }}</span>
        </div>
        <div>-----------delete--------------</div>
        <div>
          <span>{{ state.deleteStr }}</span>
        </div>
      </div>
    </div>
  </Modal>

</template>

<script setup>
import {reactive, watch} from "vue";
import {loadColumn, saveSql} from '@/api/api.js'
import {useConfigModule} from "@/store/configModule.js";


const {sqlModal} = useConfigModule();


const state = reactive({
  mainTable: '',
  tableColumn: [],
  saveStr: '',
  updateStr: '',
  deleteStr: '',
  sqlData: {
    tid: '',
    name: '',
    contentSql: '',
    countSql: '',
    type: 0
  }
})

// =====================================

const Save = () => {
  saveSql(state.sqlData).then((rest) => {
    console.log(rest);
    state.sqlData.value = rest.data;
    if (sqlModal.callBack) {
      sqlModal.callBack();
    }
    sqlModal.show = false;
  }).catch(err => {
    console.log(err);
  })
  return false;
}
const Cancel = () => {
  console.log('dddd')
  if (sqlModal.callBack) {
    sqlModal.callBack();
  }
}

//======================================
const loadTableFn = () => {
  loadColumn({
    table: state.mainTable
  }).then(res => {
    state.tableColumn = res.data.query;
    state.saveStr = res.data.save;
    state.updateStr = res.data.update;
    state.deleteStr = res.data.del;
  })
}


watch(() => sqlModal.show, (val) => {
  if (val) {
    state.sqlData = sqlModal.data;
  } else {
    sqlModal.callBack = null;
  }
})

</script>
<style scoped lang="less">

.modalBox {
  display: flex;
  min-height: 50vh;

  .sqlContent {
    flex: 1;

    .row {
      display: flex;
      padding: 10px;
      margin-bottom: 10px;
      background: #f0f0f0;

      .label {
        width: 100px;
        padding: 0 10px;
        display: flex;
        flex-direction: row-reverse;
        align-items: center;
        justify-items: right;
      }

      .content {
        flex: 1;
      }
    }

    .mountFieldsBox {
      background: #ccc;
    }
  }

  .sqlNote {
    width: 220px;
    margin: 0 10px;
    padding: 10px;
  }
}
</style>