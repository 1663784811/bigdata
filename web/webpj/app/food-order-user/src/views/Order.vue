<template>
  <div class="categray">
    ss
  </div>
  <navBar/>
</template>

<script setup>
import {reactive, onMounted, ref} from 'vue'
import {useRouter, useRoute} from 'vue-router'
import navBar from '@/components/NavBar.vue'
import NavBar from "@/components/NavBar.vue";

const router = useRouter()
const route = useRoute()

const state = reactive({
  categoryData: [],
  currentIndex: 0
})

onMounted(async () => {



})
</script>
<style lang="less" scoped>

</style>