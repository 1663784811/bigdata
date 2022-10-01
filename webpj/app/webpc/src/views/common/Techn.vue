<template>
  <div class="techn">
    <div class="container">
      <div class="nbt tc">
        <p><img src="http://www.seejoys.com/Tpl/SJ_Home/conf/images/Technical-Solutions.png"></p>
        <span></span>
        <h1>全面的技术方案</h1>
        <p>由于每个客户的产品功能、用户需求、项目预算都有不同，我们会根据产品的定位以及用户需求提供有效的的技术解决方案</p></div>
      <div class="tove ml-35">
        <div class="teclist">
          <p class="cgreen">原生型</p>
          <h2>Native APP</h2>
          <p class="mt15">
            原生App在本地运行，可以不依赖于网络。它能够更灵活地访问硬件和底层数据，并可以针对不同的平台做优化设计，从而为用户提供优秀的性能和体验。原生App获得更高性能的代价，是开发与维护成本的提高。开发者需要针对不同的操作系统设立不同的项目。
          </p></div>
        <div class="teclist">
          <p class="cgreen">网页型</p>
          <h2>Web APP</h2>
          <p class="mt15">移动Web App则具有跨平台的特性，开发者只需要设立一个项目，迭代升级相对容易。Web App的发布也更为简单快速，可以避开应用商店复杂冗长的审核过程。但是，移动Web
            App仅能有限度地访问底层数据和硬件，软件性能和用户体验尚不能与原生App相比。
          </p></div>
        <div class="teclist">
          <p class="cgreen">混合型</p>
          <h2>Hybrid APP</h2>
          <p class="mt15"> Hybrid APP从开发层面实现"一次开发，多处运行"的机制，成为真正适合跨平台的开发。目前已经有众多Hybrid
            App开发成功应用，比如百度、网易、街旁等知名移动应用，都是采用Hybrid App开发模式。
          </p></div>
        <div class="teclist">
          <p class="cgreen">微信二次开发型</p>
          <h2>WeChat</h2>
          <p class="mt15"> 微网站即将通过微信入口访问的手机网站，通过微网站展示企业的产品与服务，随时随地可查询到企业的全部信息，让您的公众平台发挥更大的价值。
          </p></div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Techn"
}
</script>

<style lang="scss" scoped>


.techn {

  .nbt {
    text-align: center;
    h1{
      font-weight: normal;
      font-size: 26px;
      color: #333;
    }
    p {
      color: #7b8082;
      font-size: 16px;
    }
  }

  .tove {
    display: flex;
    justify-content: space-between;


    .teclist {
      margin-top: 35px;
      line-height: 25px;
      width: 250px;
      background: #006a93;
      border-radius: 10px;
      padding: 20px;
      color: #ffffff;
      font-size: 14px;

      .cgreen {
        color: #81c40d;
        font-size: 26px;
        margin-top: 15px;
      }

      h2 {
        font-size: 36px;
        margin-top: 15px;
      }
    }
  }
}


</style>
