<template>
  <div>
    <n-grid :x-gap="10">
      <n-grid-item :span="5">
        <n-card
          class="h-full"
          :content-style="{ padding: '5px' }"
          :header-style="{ padding: '5px' }"
          :segmented="true"
        >
          <template #header>
            <div class="flex items-center">
              <n-input class="mr-2" v-model:value="pattern" placeholder="搜索" size="small" />
              <n-switch size="small" v-model:value="expandAllFlag" />
            </div>
          </template>
          <n-tree
            :expanded-keys="getExpandedKeys"
            block-line
            :pattern="pattern"
            :data="departmentData"
            selectable
            :on-update:expanded-keys="onUpdateExpandedKeys"
            :on-update:selected-keys="onCheckedKeys"
          />
        </n-card>
      </n-grid-item>
      <!--   表格   -->
      <n-grid-item :span="19">
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
        <DataForm ref="dataForm" :options="itemFormOptions" />
      </template>
    </ModalDialog>
  </div>
</template>

<script lang="ts">
  import { getCompany } from '@/api/api'
  import { usePagination, useRowKey, useTable, useTableHeight } from '@/hooks/table'
  import { useDialog, useMessage } from 'naive-ui'
  import { defineComponent, h, onMounted, Ref, ref, shallowReactive, unref, watch } from 'vue'
  import { companyPageSetting } from '@/api/pageSettingApi'
  import { sortColumns, transformTreeSelect, getPageConfig } from '@/utils'
  import { DataFormType, FormItem, ModalDialogType, TablePropsType } from '@/types/components'
  import { findRouteByUrl } from '@/store/help'
  import usePermissionStore from '@/store/modules/permission'
  import { renderInput, renderSwitch, renderTreeSelect } from '@/hooks/form'
  import IconSelector from '@/components/common/IconSelector.vue'
  export default defineComponent({
    name: 'Company',
    setup() {
      /**
       * 获取页面配置
       */
      const pageConfigJson = getPageConfig('tag') as any
      const componentsJson = pageConfigJson['data']
      // 表格配置
      const table = useTable(componentsJson['mainTable'])
      const aa = componentsJson['department']
      console.log(aa)
      //
      let actionModel = 'add'
      let tempItem: { menuUrl: string } | null = null
      const modalDialog = ref<ModalDialogType | null>(null)

      const rowKey = useRowKey('id')
      const naiveDialog = useDialog()
      const message = useMessage()
      const permissionStore = usePermissionStore()
      const pagination = usePagination(doRefresh)
      const checkedRowKeys = [] as Array<any>
      const pageSetting = companyPageSetting()
      const departmentData = pageSetting.components.department.column
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
        if (actionModel === 'add') {
          if (dataForm.value?.validator()) {
            message.success(
              '模拟创建菜单成功, 参数为:' + JSON.stringify(dataForm.value?.generatorParams())
            )
          }
        } else {
          if (dataForm.value?.validator()) {
            const params = dataForm.value?.generatorParams()
            if (tempItem) {
              const tempRoute = findRouteByUrl(
                permissionStore.getPermissionSideBar,
                tempItem.menuUrl
              )
              if (tempRoute && tempRoute.meta && tempRoute.meta.badge) {
                ;(tempRoute.meta as any).badge = (params as any).badge || ''
              }
            }
            message.success('模拟修改菜单成功, 参数为:' + JSON.stringify(params))
          }
        }
      }
      // 添加页面
      function onAddItem() {
        actionModel = 'add'
        console.log('sssssssss', modalDialog.value)
        modalDialog.value?.show().then(() => {
          dataForm.value?.reset()
        })
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
          newVal
            ? getExpandedKeys.push(...departmentData.map((it) => it.key))
            : (getExpandedKeys.length = 0)
        }
      )
      onMounted(async () => {
        table.tableHeight.value = await useTableHeight()
        doRefresh()
      })

      const itemFormOptions = [
        {
          label: '上级菜单',
          key: 'parentPath',
          value: ref(null),
          validator: (formItem, message) => {
            if (!formItem.value.value) {
              message.error('请选择上级菜单')
              return false
            }
            return true
          },
          render: (formItem) =>
            renderTreeSelect(
              formItem.value,
              transformTreeSelect(unref(table.dataList)!, 'menuName', 'menuUrl'),
              {
                showPath: true,
              }
            ),
        },
        {
          label: '菜单名称',
          key: 'menuName',
          required: true,
          value: ref(null),
          render: (formItem) =>
            renderInput(formItem.value, {
              placeholder: '请输入菜单名称',
            }),
        },
        {
          label: '菜单地址',
          key: 'menuUrl',
          required: true,
          value: ref(null),
          disabled: ref(false),
          render: (formItem) =>
            renderInput(formItem.value, {
              placeholder: '请输入菜单地址',
              disabled: (formItem.disabled as Ref<boolean>).value,
            }),
          reset: (formItem) => {
            formItem.value.value = null
            ;(formItem.disabled as Ref<boolean>).value = false
          },
        },
        {
          label: '外链地址',
          key: 'outLink',
          value: ref(null),
          render: (formItem) =>
            renderInput(formItem.value, {
              placeholder: '请输入外链地址',
            }),
        },
        {
          label: '菜单图标',
          key: 'icon',
          value: ref(null),
          render: (formItem) => {
            return h(IconSelector, {
              defaultIcon: formItem.value.value,
              onUpdateIcon: (newIcon: any) => {
                formItem.value.value = newIcon.name
              },
            })
          },
        },
        {
          label: '是否缓存',
          key: 'cacheable',
          value: ref(false),
          render: (formItem) => renderSwitch(formItem.value),
        },
        {
          label: '是否隐藏',
          key: 'hidden',
          value: ref(false),
          render: (formItem) => renderSwitch(formItem.value),
        },
        {
          label: '是否固定',
          key: 'affix',
          value: ref(true),
          render: (formItem) => renderSwitch(formItem.value),
        },
      ] as Array<FormItem>

      return {
        ...table,
        itemFormOptions,
        onAddItem,
        onUpdateTable,
        rowKey,
        pattern: ref(''),
        expandAllFlag,
        departmentData,
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
