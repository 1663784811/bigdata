<template>
  <Menu active-name="1">
    <MenuGroup title="内容管理">
      <MenuItem name="1">
        <Icon type="md-document" />
        文章管理
      </MenuItem>
      <MenuItem name="2">
        <Icon type="md-chatbubbles" />
        评论管理
      </MenuItem>
    </MenuGroup>
    <MenuGroup title="统计分析">
      <MenuItem name="3">
        <Icon type="md-heart" />
        用户留存
      </MenuItem>
      <MenuItem name="4">
        <Icon type="md-leaf" />
        流失用户
      </MenuItem>
    </MenuGroup>
  </Menu>
</template>

<script>
export default {
  name: "LeftMenu"
}
</script>

<style scoped>

</style>
