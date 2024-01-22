<template>
  <!--==============   基础树  =============-->
  <ul v-if="treeArray.length" :class="{ normal: (theme === 'normal')}">
    <li v-for="(treeNode, key) in treeArray" :key="key">

      <!--==============      节点      ================-->
      <div class="node">
        <div v-if="!showSelect"
             class="nodeContent" :class="{
                            haveChild:(treeNode.children && treeLayer===1 && treeNode.children.length > 0),
                            active:(treeNode.isActive && !treeNode.children)
                         }"
             @click.stop="clickNode(treeNode.data ,( treeNode.children && treeNode.children.length>0 ) ? true : false)"
             :style="{paddingLeft :  treeLayer * 10  +'px' }"
        >
          <Icon v-if="treeNode.children" :type="treeNode.icon||'ios-folder-open'"/>
          <Icon v-else="treeNode.children" :type="treeNode.icon||'md-list-box'"/>
          <span class="nodeText">{{ treeNode.title }}</span>
        </div>

        <div v-else
             class="nodeContent"
             :class="{
                            haveChild:(treeNode.children && treeLayer===1 && treeNode.children.length > 0),
                            active:(treeNode.isActive && !treeNode.children)
                         }"
             :style="{paddingLeft :  treeLayer * 10  +'px' }"
        >
          <!--                        <TreeCheckbox-->
          <!--                                :treeNode="treeNode"-->
          <!--                        ></TreeCheckbox>-->

          <Icon v-if="treeNode.children" :type="treeNode.icon||'ios-folder-open'"/>
          <Icon v-else="treeNode.children" :type="treeNode.icon||'md-list-box'"/>
          <span class="nodeText">{{ treeNode.title }}</span>
        </div>
        <span v-if="treeNode.children" class="treeRightIcon"
              @click.stop.prevent="clickIcon(treeNode ,( treeNode.children && treeNode.children.length>0 ) ? true : false )">
                         <Icon :type="treeNode.isOpenTree ? 'md-arrow-dropdown':'md-arrow-dropright'"/>
        </span>
      </div>

      <!--==============     树     ================-->
      <TreeNode ref="treeUlRef"
                v-show="openTree(treeNode.isOpenTree)"
                v-model="treeNode.children"
                :treeLayer="treeLayer+1"
                @clickNode="clickChildren"
                @clickIcon="clickIcon"
                :showSelect="showSelectBoolean"
      >
      </TreeNode>
    </li>
  </ul>
</template>

<script>
// import CollapseTransition from './collapseTransition.js';
// import TreeCheckbox from '@/components/form/TreeCheckbox';

export default {
  name: "TreeNode",
  components: {
    // CollapseTransition,
    // TreeCheckbox
  },
  props: {
    //====树数据
    modelValue: {
      type: Array,
      request: true,
      default() {
        return [];
      }
    },
    //====  层级
    treeLayer: {
      type: Number,
      request: false,
      default() {
        return 1
      }
    },
    //==== 主题
    theme: {
      type: String,
      request: false,
      default() {
        return "default"
      }
    },
    //是否显示选择
    showSelect: {
      type: Boolean,
      request: false,
      default() {
        return false;
      }
    }
  },
  data() {
    return {
      showSelectBoolean: this.showSelect,
      label: true,
      treeArray: this.modelValue
    }
  },
  methods: {


    //==== 传递数据
    clickNode: function (treeNode, isHaveChildren) {
      this.$emit('clickNode', treeNode, isHaveChildren);
    },
    //==== 子节点被点击
    clickChildren: function (treeNode, isHaveChildren) {
      this.$emit('clickNode', treeNode, isHaveChildren);
    },
    //点击icon
    clickIcon: function (treeNode, isHaveChildren) {
      console.log(treeNode)
      treeNode.isOpenTree = !treeNode.isOpenTree;
      this.$emit('clickIcon', treeNode, isHaveChildren);
    },
    openTree: function (isopen = true) {
      return isopen;
    }
  },
  watch: {
    modelValue: function () {
      this.treeArray = this.modelValue;
    }
  }
}
</script>
<style scoped lang="less">
.node {
  cursor: pointer;
  user-select: none;
  color: #eaeaea;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  position: relative;
}


.treeRightIcon {
  position: absolute;
  top: 50%;
  margin-top: -10px;
  right: 4px;
}


.nodeText {
  display: inline-block;
  margin-left: 5px;
}


.nodeContent {
  background: #252a2f;
  padding: 8px 0;

  &:not(.haveChild),
  &:hover {
    background: #606a80;
  }

}


.haveChild {
  //background: #16191d;
}


.active {
  background: #445e98;
}


.normal {
  .node {
    color: #333;
  }

  .nodeContent {
    padding: 8px 0;
    background: none
  }

  &:not(.haveChild), &:hover {
    background: #f1f1f1;
  }

  .haveChild {
    background: #dedfe0
  }

  .active {
    background: #d3e7fb;
  }
}

</style>
