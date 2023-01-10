<template>
  <div>
    <n-grid :x-gap="10">
      <!--   表格   -->
      <n-grid-item :span="24">
        <div>
          <TableBody>
            <template #header>
              <TableHeader ref="tableHeaderRef" :show-filter="true">
                <template #top-right>
                  <AddButton @click="onAddItem" />
                  <DeleteButton @delete="onDeleteItems" />
                  <SortableTable class="ml-4" :columns="tableColumns" @update="onUpdateTable" />
                </template>
              </TableHeader>
            </template>
            <template #default>
              <n-data-table
                :loading="tableLoading"
                :data="dataList"
                :row-key="rowKey"
                :columns="tableColumns"
                :scroll-x="1000"
                :style="{ height: `${tableHeight}px` }"
                :flex-height="true"
                @update:checked-row-keys="onRowCheck"
              />
            </template>
            <template #footer>
              <TableFooter ref="tableFooterRef" :pagination="pagination" />
            </template>
          </TableBody>
        </div>
      </n-grid-item>
    </n-grid>
    <ModalDialog ref="modalDialog" @confirm="onConfirm" content-height="50vh">
      <template #content>
        <FormBox ref="formBox" :formBoxField="formBoxField" />
      </template>
    </ModalDialog>
  </div>
</template>

<script lang="ts">
  import { getCompany } from '@/api/api'
  import { usePagination, useRowKey, useTable, useTableHeight } from '@/hooks/table'
  import { useDialog, useMessage } from 'naive-ui'
  import { defineComponent, onMounted, ref, shallowReactive, watch } from 'vue'
  import { sortColumns, getPageConfig, getAddField } from '@/utils'
  import { DataFormType, ModalDialogType, TablePropsType } from '@/types/components'
  import { findRouteByUrl } from '@/store/help'
  import usePermissionStore from '@/store/modules/permission'
  export default defineComponent({
    name: 'Company',
    setup() {
      /**
       * 获取页面配置
       */
      const pageConfigJson = getPageConfig('tagLink') as any
      const componentsJson = pageConfigJson['data'] ? pageConfigJson['data'] : {}
      const mainTable = componentsJson['mainTable']
      const table = useTable(mainTable)
      const addField = getAddField(mainTable)
      console.log(addField)
      const leftTree = componentsJson['leftTree'] ? componentsJson['leftTree'] : {}

      const departmentData = leftTree['data'] ? leftTree['data'] : {}
      //================
      let tempItem: { menuUrl: string } | null = null
      const modalDialog = ref<ModalDialogType | null>(null)

      const rowKey = useRowKey('id')
      const naiveDialog = useDialog()
      const message = useMessage()
      const permissionStore = usePermissionStore()
      const pagination = usePagination(doRefresh)
      const checkedRowKeys = [] as Array<any>
      const dataForm = ref<DataFormType | null>(null)
      const expandAllFlag = ref(false)

      /**
       * 刷新
       */
      function doRefresh() {
        // 清空数据
        table.dataList = [] as any
        // 获取数据
        getCompany({
          page: pagination.page,
          size: pagination.pageSize,
          code: 'a',
        })
          .then((res) => {
            table.dataList = res.data
            table.handleSuccess(res)
            pagination.setTotalSize((res as any).total)
          })
          .catch((err) => {
            console.log('获取数失败：', err)
          })
      }
      // 更新表格
      function onUpdateTable(newColumns: Array<TablePropsType>) {
        sortColumns([], newColumns)
      }
      // 删除数据
      function onDeleteItems() {
        naiveDialog.warning({
          title: '提示',
          content: '确定要删除此数据吗？',
          positiveText: '确定',
          onPositiveClick: () => {
            message.success('数据模拟删除成功，参数为：' + JSON.stringify(checkedRowKeys))
          },
        })
      }
      // 删除数据
      function onDeleteItem(item: any) {
        naiveDialog.warning({
          title: '提示',
          content: '确定要删除此数据吗？',
          positiveText: '确定',
          onPositiveClick: () => {
            table.dataList.value!.splice(table.dataList.value!.indexOf(item), 1)
          },
        })
      }
      //
      function onConfirm() {
        if (dataForm.value?.validator()) {
          const params = dataForm.value?.generatorParams()
          if (tempItem) {
            const tempRoute = findRouteByUrl(permissionStore.getPermissionSideBar, tempItem.menuUrl)
            if (tempRoute && tempRoute.meta && tempRoute.meta.badge) {
              ;(tempRoute.meta as any).badge = (params as any).badge || ''
            }
          }
          message.success('模拟修改菜单成功, 参数为:' + JSON.stringify(params))
        }
      }

      function onRowCheck(rowKeys: Array<any>) {
        checkedRowKeys.length = 0
        checkedRowKeys.push(...rowKeys)
      }
      function onUpdateExpandedKeys(keys: any) {
        getExpandedKeys.length = 0
        getExpandedKeys.push(...keys)
      }
      function onCheckedKeys(keys: any) {
        message.success('选中的值为--->' + JSON.stringify(keys))
      }
      const getExpandedKeys = shallowReactive([] as Array<number>)
      watch(
        () => expandAllFlag.value,
        (newVal) => {
          // newVal
          //   ? getExpandedKeys.push(...departmentData.map((it) => it.key))
          //   : (getExpandedKeys.length = 0)
        }
      )
      onMounted(async () => {
        table.tableHeight.value = await useTableHeight()
        doRefresh()
      })

      return {
        ...table,
        addField,
        onUpdateTable,
        rowKey,
        pattern: ref(''),
        expandAllFlag,
        modalDialog,
        pagination,
        onDeleteItem,
        onDeleteItems,
        onRowCheck,
        getExpandedKeys,
        onUpdateExpandedKeys,
        onCheckedKeys,
        onConfirm,
      }
    },
    data() {
      return {
        formBoxField: [],
      }
    },
    methods: {
      // 添加页面
      onAddItem: function () {
        // this.formBoxField = this.addField
        console.log(this.formBoxField)
        // console.log('sssssssss', this.modalDialog.value)
        // this.modalDialog.value?.show().then(() => {
        //   this.modalDialog.value?.reset()
        // })
      },
    },
  })
</script>

<style lang="scss" scoped>
  .avatar-container {
    position: relative;
    width: 30px;
    margin: 0 auto;
    vertical-align: middle;
    .avatar {
      width: 100%;
      border-radius: 50%;
    }
    .avatar-vip {
      border: 2px solid #cece1e;
    }
    .vip {
      position: absolute;
      top: 0;
      right: -9px;
      width: 15px;
      transform: rotate(60deg);
    }
  }
  .gender-container {
    .gender-icon {
      width: 20px;
    }
  }
</style>
