<template>
  <div class="timeMain">

    <Timeline>
      <TimelineItem>
        <div class="time">1976年</div>
        <div class="content">Apple I 问世</div>
      </TimelineItem>
      <TimelineItem>
        <p class="time">1984年</p>
        <p class="content">发布 Macintosh</p>
      </TimelineItem>
      <TimelineItem>
        <p class="time">2007年</p>
        <p class="content">发布 iPhone</p>
      </TimelineItem>
      <TimelineItem>
        <p class="time">2010年</p>
        <p class="content">发布 iPad</p>
      </TimelineItem>
      <TimelineItem>
        <p class="time">2011年10月5日</p>
        <p class="content">史蒂夫·乔布斯去世</p>
      </TimelineItem>
    </Timeline>
  </div>
</template>

<script setup>

import {onMounted, reactive, ref} from "vue";
import {pageConfig} from '@/store/pageConfig.js'
import {useRoute, useRouter} from "vue-router";

const router = useRouter();
const route = useRoute();
const usePageConfig = pageConfig();

const state = reactive({})

onMounted(() => {

})

const initFn = async (pageCode) => {

}

</script>

<style scoped lang="less">
.timeMain {
  background: #fff;
  padding: 10px;
  border-radius: 10px;
}
</style>
