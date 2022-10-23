<template>
  <div class="orderBox">
    <div class="orderItem" v-for="(item,index) in orderList">
      <div class="storeInfo">
        {{ item.storeInfo.name }}
      </div>
      <div class="goodsBox">
        <div class="goodsItem" v-for="(goodsItem,gindex) in item.goodsList">
          {{ goodsItem.name }}
        </div>
      </div>
      <div class="countBox">
        <div class="">共{{ item.countInfo.count }}件</div>
        <div class="">实付：{{ item.countInfo.pay }}元</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Order",
  data() {
    return {
      orderList: [
        {
          storeInfo: {
            name: '听心一号店'
          },
          goodsList: [
            {
              name: '玉米'
            },
            {
              name: '玉米'
            },
            {
              name: '玉米'
            }
          ],
          countInfo: {
            pay: 10.00,
            count: 10
          }
        },
        {
          storeInfo: {
            name: '听心一号店'
          },
          goodsList: [
            {
              name: '玉米'
            },
            {
              name: '玉米'
            },
            {
              name: '玉米'
            }
          ],
          countInfo: {
            pay: 10.00,
            count: 10
          }
        },
        {
          storeInfo: {
            name: '听心一号店'
          },
          goodsList: [
            {
              name: '玉米'
            },
            {
              name: '玉米'
            },
            {
              name: '玉米'
            }
          ],
          countInfo: {
            pay: 10.00,
            count: 10
          }
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.orderBox {
  .orderItem {
    background: #fff;
    margin: 10px 0;
    padding: 10px;
  }
}

</style>
