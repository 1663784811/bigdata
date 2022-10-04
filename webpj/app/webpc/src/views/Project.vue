<template>
  <HeaderImg/>


  <div class="container">
    <div class="titleBox">
      <p class="rowTitle">Special Offers & Discounts</p>
      <p class="rowText">Far far away, behind the word mountains, far from the countries Vokalia and</p>
      <p class="rowText">right at the coast of the Semantics, a large language ocean.</p>
    </div>
    <div class="row">
      <div v-for="(item,index) in 4" class="goodsItem">
        <div class="media-1">
          <img src="../assets/hero-item.jpg" class="img-fluid">
          <div class="">
            <h3 class="itemTitle">免啦APP</h3>
            <p class="itemNote">免啦APP是一款“新体验+场景互动式电商服务平台”包含“吃、喝、玩、乐等”</p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div style="margin-top: 120px"></div>
</template>

<script>
import HeaderImg from "@/views/common/HeaderImg.vue";

export default {
  name: "Project",
  components: {
    HeaderImg
  }
}
</script>

<style lang="scss" scoped>
.titleBox {
  margin-top: 50px;
  padding: 50px 0;
  text-align: center;

  .rowTitle {
    font-weight: normal;
    font-size: 26px;
    color: #333;
    margin: 20px 0;
  }

  .rowText {
    margin: 16px;
    color: #7b8082;
    font-size: 16px;
  }
}

.row {
  display: flex;
  flex-wrap: wrap;
  margin: 15px 0;
  padding: 0 30px;

  .goodsItem {
    flex: 0 0 25%;
    max-width: 25%;
    position: relative;
    padding: 20px 15px;

    .img-fluid {
      max-width: 100%;
      height: auto;
      border-radius: 20px;
    }
    .itemTitle{
      font-weight: normal;
      padding: 10px 0;
      color: #333333;
    }
    .itemNote{
      font-size: 14px;
      color: #646666;
    }
  }
}

</style>
