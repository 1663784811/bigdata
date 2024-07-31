<template>
  <div class="welcomeBox">
    <div class="header">
      <div class="headerContainer">
        <div class="container">
          <div class="menuLeft">
            <div class="menuItem logo">
              <img class="logoImg" src="~@/assets/logo_64.png" alt="">
              <span class="logText">听心智能</span>
            </div>
            <div class="menuItem">点餐系统</div>
            <div class="menuItem">即时聊天</div>
            <div class="menuItem">物联网</div>
          </div>
          <div class="menuRight">
            <div class="menuItem">关于我们</div>
            <div class="menuItem">联系电话: 12345678901</div>
          </div>
        </div>


      </div>
    </div>

    <div class="product">
      <div class="container">
        <div class="title">
          我们的产品
        </div>
        <div class="subtitle">
          <div>小程序+APP+代运营+精准投放一站式解决方案</div>
          <div>针对行业特征及细分业务场景深度定制，打造一站式解决方案</div>
        </div>
        <div class="productBox">
          <div class="productItem">
            <div class="productHeader">点餐系统</div>
            <div class="productBody">
              <div class="imgBox">
                <img src="~@/assets/test.jpg" alt="">
              </div>
              <div>以十余年公有云服务运营经验，提供安全可靠、弹性伸缩的云服务，帮助企业有效降低 IT 成本，满足业务创新需求，加速数字化转型</div>
            </div>
          </div>
          <div class="productItem">
            <div class="productHeader">即时聊天</div>
            <div class="productBody">
              <div class="imgBox">
                <img src="~@/assets/test.jpg" alt="">
              </div>
              <div>以 KubeSphere 容器平台为核心，提供完整的云原生产品、方案，以及云原生实践指导服务，陪伴企业走好云原生的每一步</div>
            </div>
          </div>
          <div class="productItem">
            <div class="productHeader">物联网</div>
            <div class="productBody">
              <div class="imgBox">
                <img src="~@/assets/test.jpg" alt="">
              </div>
              <div>以业界领先的软件定义技术为核心，采用超融合技术满足企业数字化转型对于 IT 基础架构设施高性能、高可靠、可扩展、简单易用的需求</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="product">
      <div class="container">
        <div class="title">企业列表</div>
        <div class="subtitle">基于多年自主研发积累和规模化公有云运营验证，提供可靠、成熟、先进的全栈云平台，致力于成为多行业企业数字化的算力基石</div>
        <div>
          <div>sssssssss</div>
        </div>
      </div>
    </div>


    <div class="container">
      <div class="eventBox">
        <div class="eventItem" v-for="i in 10">
          <div class="timeBar">2024-01-05</div>
          <div class="eventContent">
            时间轴
          </div>
        </div>
      </div>
    </div>

    <div class="footer">
      <div class="container">
        <div class="info">
        </div>
        <div class="infoItem">
          Copyright © 2024 xxxxx
          京ICP备xxxxx号-xxx
          京ICP证xxxxxx号
        </div>
      </div>
    </div>
  </div>


</template>

<script setup>

import {useRouter} from "vue-router";

const router = useRouter();


const goFn = (name) => {
  router.push({
    name: name
  })
}
</script>

<style scoped lang="less">
.welcomeBox {
  height: 100vh;
  overflow: auto;
  background: #f2f4f7;

  .header {
    padding-bottom: 49px;

    .headerContainer {
      background: rgba(0, 0, 0, .85);
      color: #fff;
      position: fixed;
      top: 0;
      right: 0;
      left: 0;
      z-index: 99;

      .container {
        display: flex;
        height: 49px;
        align-items: center;
        justify-content: space-between;

        .menuLeft {
          display: flex;

          .menuItem {
            flex-shrink: 0;
            flex-grow: 0;
            margin: 0 20px;
            font-size: 14px;
            cursor: pointer;
            display: flex;
            justify-content: center;
            justify-items: center;
            align-items: center;
          }

          .logo {
            .logoImg {
              width: 30px;
            }

            .logText {
              font-size: 18px;
              margin-left: 6px;
              color: #00a870;
            }
          }
        }

        .menuRight {
          display: flex;

          .menuItem {
            flex-shrink: 0;
            flex-grow: 0;
            margin: 0 10px;
            font-size: 14px;
            cursor: pointer;
            display: flex;
            justify-content: center;
            justify-items: center;
            align-items: center;
          }
        }
      }


    }
  }

  .product {
    padding: 100px 24px;

    .title {
      margin-bottom: 20px;
      font-weight: 600;
      font-size: 32px;
      line-height: 45px;
      color: #0F0D15;
      text-align: center;
    }

    .subtitle {
      font-size: 16px;
      line-height: 22px;
      color: #615E69;
      text-align: center;
      margin-bottom: 50px;
    }

    .productBox {
      display: flex;
      flex-wrap: wrap;

      .productItem {
        flex: none;
        width: 33.33333%;
        background: #fff;
        margin-top: 1px;

        .productHeader {
          padding: 20px 30px;
          display: flex;
          flex-direction: row;
          align-items: center;
          border-top: 1px solid #E8E8EA;
          border-right: 1px solid #E8E8EA;
          border-bottom: 1px solid #E8E8EA;
          color: #00a870;
          font-weight: 500;
          font-size: 24px;
          line-height: 32px;
        }

        .productBody {
          padding: 40px;
          height: 300px;
          border-left: 1px solid #E8E8EA;

          .imgBox {
            height: 100px;

            img {
              max-height: 100%;
            }
          }
        }
      }
    }
  }

  .eventBox {
    .eventItem {
      position: relative;
      clear: both;
      padding-left: 100px;


      .timeBar {
        position: absolute;
        left: 10px;
        top: 10px;
      }

      .eventContent {
        background: #fff;
        padding: 10px;
        margin: 4px;
        border-radius: 8px;
        min-height: 100px;
      }

      &:before, &:after {
        content: "";
        height: 0;
        clear: both;
        display: block;
      }
    }
  }
}

.footer {
  margin-top: 100px;
  width: 100%;
  background-color: #202329;
  padding: 40px 0 16px;
  color: hsla(0, 0%, 100%, .5);
  min-height: 300px;

  .infoItem {
    color: #fff;

    text-align: center;
  }
}
</style>