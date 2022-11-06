<template>
  <div class="FormBox">
    <n-form ref="formRef" label-placement="left" :model="formValue">
      <template v-for="(item, index) in itemList" :key="index">
        <template v-if="item.type === 'input'">
          <n-form-item :label="item.title">
            <n-input v-model:value="formValue[item.key]" :placeholder="item.title" />
          </n-form-item>
        </template>
        <template v-else-if="item.type === 'select'">
          <n-form-item :label="item.title">
            <n-select
              v-model:value="formValue[item.key]"
              :placeholder="item.title"
              :options="item.labelArr"
            />
          </n-form-item>
        </template>
      </template>
    </n-form>
  </div>
</template>

<script>
  export default {
    name: 'FormBox',
    props: {
      formBoxField: {
        type: Array,
        require: true,
        default() {
          return []
        },
      },
    },
    data() {
      return {
        formValue: {},
        itemList: [
          {
            key: 'sdf',
            type: 'input',
            title: '姓名',
          },
          {
            key: 'sdf',
            type: 'input',
            title: '姓名',
          },
          {
            key: 'sdf',
            type: 'input',
            title: '姓名',
          },
          {
            key: 'aa',
            type: 'select',
            title: '姓名',
            labelArr: [
              {
                label: 'label',
                value: 'se1',
              },
              {
                label: 'label',
                value: 'se2',
              },
              {
                label: 'label',
                value: 'se3',
              },
            ],
          },
        ],
      }
    },
    watch: {
      formBoxField() {
        console.log(this.formBoxField)
      },
    },
    methods: {
      getFormValue() {
        return this.formValue
      },
    },
  }
</script>

<style>
  .FormBox .n-form-item-label.n-form-item-label--right-mark {
    min-width: 100px;
  }
</style>
