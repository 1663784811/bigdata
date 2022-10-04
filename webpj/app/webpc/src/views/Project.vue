<template>
  <HeaderImg/>


  <div class="container">
    <div class="row">
      <div v-for="(item,index) in 4" class="goodsItem">
        <div class="media-1">
          <a href="#" class="d-block mb-3">
            <img src="../assets/hero-item.jpg"  class="img-fluid">
          </a>
          <span class="d-flex align-items-center loc mb-2"><span class="icon-room mr-3"></span><span>Italy</span></span>
          <div class="d-flex align-items-center">
            <div>
              <h3><a href="#">Rialto Mountains</a></h3>
              <div class="price ml-auto">
                <span>$520.00</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
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
.row{
  display: flex;
  flex-wrap: wrap;
  margin-right: -15px;
  margin-left: -15px;
  .goodsItem{
    flex: 0 0 25%;
    max-width: 25%;
    position: relative;
    padding-right: 15px;
    padding-left: 15px;
    .img-fluid{
      max-width: 100%;
      height: auto;
      border-radius: 20px;
    }
  }
}

</style>
