<template>
  <HeaderImg/>
  <Techn></Techn>
  <LeftRightBox />
</template>

<script>
import HeaderImg from "@/views/common/HeaderImg.vue";
import Techn from "@/views/common/Techn.vue";
import LeftRightBox from "@/views/common/LeftRightBox.vue";

export default {
  name: "AppDevelopment",
  components: {
    HeaderImg,
    Techn,
    LeftRightBox
  }
}
</script>

<style scoped>

</style>
