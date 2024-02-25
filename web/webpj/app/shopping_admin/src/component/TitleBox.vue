<template>
  <div class="titleBox">{{ props.title}}</div>
</template>
<script setup>


const props = defineProps({
  title: {
    type: String,
    default: " ",
    required: false
  }
});


</script>
<style scoped lang="less">
.titleBox {
  padding: 10px;
  border-bottom: 1px solid #efefef;
  background: #fff;
}
</style>