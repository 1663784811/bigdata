<template>
  <div class="powerManageBox">
    <div class="departmentBox">
      <div class="headerBtn">
        <div>部门</div>
        <div>部门</div>
      </div>
      <Tree :data="treeData" class="demo-tree-render"></Tree>
    </div>
    <div class="roleBox">
      <div class="headerBtn">
        <div>角色</div>
        <div>部门</div>
      </div>
      <Tree :data="treeData" class="demo-tree-render"></Tree>
    </div>
    <div class="userBox">
      人员
      sssss
    </div>
    <div class="powerBox">
      权限
      ssss
    </div>

  </div>

</template>

<script setup>
import {ref, resolveComponent} from "vue";

const treeData = ref([
  {
    title: 'parent 1',
    expand: true,
    render: (h, {root, node, data}) => {
      return h('span', {
        style: {
          display: 'inline-block',
          width: '100%'
        }
      }, [
        h('span', [
          h(resolveComponent('Icon'), {
            type: 'ios-folder-outline',
            style: {
              marginRight: '8px'
            }
          }),
          h('span', data.title)
        ]),
        h('span', {
          style: {
            display: 'inline-block',
            float: 'right',
            marginRight: '32px'
          }
        }, [
          h(resolveComponent('Button'), {
            size: 'small',
            icon: 'ios-add',
            type: 'primary',
            style: {
              width: '64px'
            },
            onClick: () => {
              this.append(data)
            }
          })
        ])
      ]);
    },
    children: [
      {
        title: 'child 1-1',
        expand: true,
        children: [
          {
            title: 'leaf 1-1-1',
            expand: true
          },
          {
            title: 'leaf 1-1-2',
            expand: true
          }
        ]
      },
      {
        title: 'child 1-2',
        expand: true,
        children: [
          {
            title: 'leaf 1-2-1',
            expand: true
          },
          {
            title: 'leaf 1-2-1',
            expand: true
          }
        ]
      }
    ]
  }
]);






</script>

<style scoped lang="less">
.powerManageBox {
  display: flex;
  flex-wrap: wrap;
  .headerBtn{
    display: flex;
  }
  .departmentBox{
    width: 400px;
    margin: 10px;
    padding: 10px;
    background: #fff;
  }
  .roleBox{
    width: 400px;
    margin: 10px;
    padding: 10px;
    background: #fff;
  }
  .userBox{
    flex: 1;
    min-width: 400px;
    width: 400px;
    margin: 10px;
    padding: 10px;
    background: #fff;
  }
  .powerBox{
    flex: 1;
    min-width: 400px;
    margin: 10px;
    padding: 10px;
    background: #fff;
  }
}
</style>
<style>
.demo-tree-render .ivu-tree-title {
  width: 100%;
}
</style>