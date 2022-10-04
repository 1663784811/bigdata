<template>
  <div class="bannerBox">
    <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item>
        <img style="width: 100%" src="http://www.seejoys.com/pics/banner/banner_s_1461295181.jpg" alt="">
      </van-swipe-item>
      <van-swipe-item>
        <img style="width: 100%" src="http://www.seejoys.com/pics/banner/banner_s_1450842050.png" alt="">
      </van-swipe-item>
      <van-swipe-item>
        <img style="width: 100%" src="http://www.seejoys.com/pics/banner/banner_s_1461307907.png" alt="">
      </van-swipe-item>
    </van-swipe>
  </div>

  <Techn></Techn>

  <IntroduceBox></IntroduceBox>

  <div class="submitBox">
    <div class="container submitRow">
      <div class="rowLeft">

      </div>
      <div class="rowRight">
        <h3>请把您的需求,告诉我们!</h3>
        <div class="fromBox">
          <div class="inputRow">
            <div class="inputLabel">姓名：</div>
            <div class="inputBox">
              <input type="text" placeholder="请输入您的姓名">
            </div>
          </div>
          <div class="inputRow">
            <div class="inputLabel">电话：</div>
            <div class="inputBox">
              <input type="text" placeholder="请输入您的电话号码">
            </div>
          </div>
          <div class="inputRow">
            <div class="inputLabel">描述：</div>
            <div class="inputBox">
              <textarea placeholder="请描述您的需求"/>
            </div>
          </div>
          <div class="submitBtn">提交</div>
        </div>
      </div>
    </div>
  </div>


</template>

<script>

import IntroduceBox from "@/views/common/IntroduceBox.vue";
import Techn from "@/views/common/Techn.vue"

export default {
  name: "Home.vue",
  components: {
    IntroduceBox,
    Techn
  },
  data() {
    return {}
  },
  setup() {

  },
  methods: {
    reload() {
    }
  }
}
</script>

<style lang="scss" scoped>
.bannerBox {
}

.submitBox {
  background: #096589;

  .submitRow {
    background: #096589;
    display: flex;
    padding: 80px 40px;

    .rowLeft {
      width: 50%;
    }

    .rowRight {
      width: 500px;
      background: #f9f9f9;
      padding: 20px 50px 50px 50px;
      border-radius: 10px;
      box-shadow: 0 0 10px #404040;

      h3 {
        font-weight: normal;
        text-align: center;
        margin: 10px 0;
      }

      .inputRow {
        border-bottom: 1px solid #ccc;
        display: flex;
        padding: 20px 0;

        .inputLabel {
          width: 50px;
        }

        .inputBox {
          flex: 1;
          display: flex;
          align-items: center;

          input, textarea {
            width: 100%;
          }
        }
      }

      .submitBtn {
        margin: 50px 0 20px;
        text-align: center;
        background: #20852c;
        padding: 16px 0;
        border-radius: 6px;
        color: #fff;
        cursor: pointer;
      }
    }
  }
}

</style>
