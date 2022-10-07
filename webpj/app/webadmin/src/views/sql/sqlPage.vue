<template>
  <div class="sqlPage">
    <div class="sqlLeft"> s </div>
    <div class="sqlRight">
      <div class="row">
        <div class="label">ID</div>
        <div class="content">
          <n-input v-model:value="sqlData" type="text" placeholder="sqlData" />
        </div>
      </div>
      <div class="row">
        <div class="label">名称</div>
        <div class="content">
          <n-input v-model:value="sqlData" type="text" placeholder="sqlData" />
        </div>
      </div>
      <div class="row">
        <div class="label">sql</div>
        <div class="content">
          <n-input v-model:value="sqlData" type="textarea" placeholder="sqlData" />
        </div>
      </div>
      <div class="row">
        <div class="label">count</div>
        <div class="content">
          <n-input v-model:value="countData" type="textarea" placeholder="countData" />
        </div>
      </div>
      <div class="row submitBox">
        <n-button type="success">保存</n-button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'SqlPage',
    setup() {
      return {
        sqlData: '',
        countData: '',
      }
    },
  }
</script>

<style lang="scss" scoped>
  .sqlPage {
    display: flex;
    .sqlLeft {
      width: 200px;
      height: 100vh;
      background: #191919;
    }
    .sqlRight {
      flex: 1;
      padding: 10px;
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
      .submitBox {
        padding-left: 100px;
        display: block;
        background: none;
      }
    }
  }
</style>
