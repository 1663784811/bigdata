 <template>
  <div class="about">
    <s-header :name="'关于我们'"></s-header>
    <div class="about-body">
      xxx科技有限公司
    </div>
  </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
</script>

<style lang="less" scoped>
  .about {
    box-sizing: border-box;
    padding: 20px;
    .intro-img {
      width: 100%;
    }
    .about-body {
      font-size: 16px;
      a {
        color: #007fff;
      }
    }
  }
</style>
