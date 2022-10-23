<template>
  <div class="container">
    <div class="rowBox">
      <div class="rowItem">
        <div class="imgBox">
          <img src="../../assets/slider-1.jpg" alt="">
        </div>
      </div>
      <div class="rowItem">
        <h1>关于我们</h1>
        <p>珠海程式科技有限公司成立于2017年8月1日，坐落于中国经济特区之一的海滨城市珠海。是一家“与时俱进、开拓创新”的成长型互联网公司，集产品研发、运营为一体。</p>
        <p>始终坚持不断创新、卓越追求的“程式精神”培养“程式人”精益求精的优良作风。 以技术为服务基础，以客户需求为服务核心，用最专业的服务打造出让客户满意的产品。</p>
      </div>
    </div>
    <div class="rowBox">
      <div class="rowItem">
        <h1>产品介绍</h1>
        <p>免啦APP是一款“新体验+场景互动式电商服务平台”包含“吃、喝、玩、乐等”，主要为用户在消费时提供全新的新鲜感、刺激感、体验感。</p>
        <p>为商家提供精准推广“可视化”窗口。免啦APP—致力于在“解决购买需求的同时，又能满足购买快感”而生。</p>
        <p>具有“互动性”、互娱性”的消费新体验！</p>
      </div>
      <div class="rowItem">
        <div class="imgBox">
          <img src="../../assets/slider-1.jpg" alt="">
        </div>
      </div>
    </div>
    <div class="rowBox">
      <div class="rowItem">
        <div class="imgBox">
          <img src="../../assets/slider-1.jpg" alt="">
        </div>
      </div>
      <div class="rowItem">
        <h1>联系我们</h1>
        <p>电话：18926993229</p>
        <p>邮箱：any9877@aliyun.com</p>
        <p>地址：广东省珠海市香洲区拱北迎宾广场八座1102</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LeftRightBox"
}
</script>

<style lang="scss" scoped>
.rowBox {
  display: flex;
  margin: 200px 100px;

  .rowItem {
    width: 50%;

    .imgBox {
      padding: 0 60px;

      img {
        width: 100%;
      }
    }

    h1 {
      font-weight: normal;
      font-size: 26px;
      color: #333;
      text-align: center;
      padding: 10px 0 30px 0;
    }

    p {
      color: #7b8082;
      font-size: 16px;
      margin: 20px 0;
    }
  }
}
</style>
