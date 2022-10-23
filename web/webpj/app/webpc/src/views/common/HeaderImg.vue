<template>
  <div class="headerImg">
    <div class="overlay-contact"></div>
    <div class="container">
      <div class="row text-center d-flex justify-content-center align-items-center row-height">
        <div class="col-12">
          <h1>关于我们</h1>
          <p>不断创新、追求卓越</p>
          <p>不断推陈出新，为客户创造更多的品牌价值</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderImg"
}
</script>

<style lang="scss" scoped>
.headerImg {
  position: relative;
  background-image: url("../../assets/about-header.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center -305px;

  .overlay-contact {
    display: block;
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    top: 0;
    background-color: rgba(0, 0, 0, 0.70);
  }

  .row-height {
    height: 50vh;
    display: flex;
    align-items: center;

    .col-12 {
      -ms-flex: 0 0 100%;
      flex: 0 0 100%;
      max-width: 100%;
      position: relative;
      width: 100%;
      padding-right: 15px;
      padding-left: 15px;
      text-align: center;
      color: white;

      .main-heading {
        font-size: 46px;
        font-weight: 500;
      }
      h1{
        margin: 20px 0;
        font-weight: normal;
      }
      p{
        margin: 12px 0;
        color: #cdcdcd;
      }
    }
  }
}
</style>
