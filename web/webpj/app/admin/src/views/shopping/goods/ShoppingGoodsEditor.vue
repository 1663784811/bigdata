<template>
  <!--  商品基本信息  -->
  <div class="goodsBaseInfo">
    <div>商品基本信息</div>
    <div class="row">
      <div class="label">商品名称:</div>
      <div class="content">
        <Input/>
      </div>
    </div>
    <div class="row">
      <div class="label">品牌Code:</div>
      <div class="content">
        <Input/>
      </div>
    </div>
    <div class="row">
      <div class="label">品类Code:</div>
      <div class="content">
        <Input/>
      </div>
    </div>
    <div class="row">
      <div class="label">备注:</div>
      <div class="content">
        <Input type="textarea"/>
      </div>
    </div>

    <div class="row">
      <div class="label">图片:</div>
      <div class="content">
        <div class="imageBox">
          <div class="closeImg">
            <Icon type="md-close-circle"/>
          </div>
          <img src="" alt="">
        </div>
        <div class="imageBox">
          <div class="closeImg">
            <Icon type="md-close-circle"/>
          </div>
          <img src="" alt="">
        </div>
        <div class="imageBox addImage" @click="addImageFn()">
          <Icon type="md-add-circle"/>
        </div>
      </div>
    </div>

  </div>
  <!--  商品sku信息  -->
  <div class="skuBox">
    <div class="skuTypBox">
      <div class="skuTypOperation">
        <Button type="warning" size="small" icon="md-add"/>
      </div>
      <div class="row" v-for="i in 4">
        <div class="itemOperation">
          <Button type="error" size="small" icon="ios-trash"/>
        </div>
        <div class="attrBox">
          <Input v-model="value" placeholder="属性"/>
        </div>
        <div class="labelBox">
          <div class="labelItem">
            <Input v-model="value" placeholder="值"/>
            <Button type="error" size="small" icon="ios-trash"/>
          </div>
          <div class="labelItem">绿色</div>
        </div>
      </div>


    </div>
    <div class="skuEditor">
      <div class="skuEditorOperation">
        <Button type="warning" size="small" icon="md-add"/>
      </div>

      <div class="row" v-for="i in 4">
        <div class="contentRow">
          <div class="labelBox">sku:</div>
          <div class="content">
            颜色：白色 大小：大 类型：男
          </div>
        </div>
        <div class="contentRow">
          <div class="labelBox">图片:</div>
          <div class="content">
            <div class="imageBox">
              <div class="closeImg">
                <Icon type="md-close-circle"/>
              </div>
              <img src="" alt="">
            </div>
            <div class="imageBox">
              <div class="closeImg">
                <Icon type="md-close-circle"/>
              </div>
              <img src="" alt="">
            </div>
            <div class="imageBox addImage" @click="addImageFn()">
              <Icon type="md-add-circle"/>
            </div>
          </div>
        </div>
        <div class="contentRow">
          <div class="labelBox">文字描述:</div>
          <div class="content">
            <Input type="textarea"/>
          </div>
        </div>
      </div>


    </div>
  </div>
  <!--  商品详情信息  -->


</template>

<script setup>
import EventBus from "@/component/EventBus.js";

const emitter = EventBus();

const initFn = async () => {

}
initFn();

const addImageFn = (dataObj, keyObj) => {
  emitter.emit('showModalFiles', true);
}
</script>

<style scoped lang="less">
.goodsBaseInfo {
  background: #fff;
  margin: 10px 0;
  padding: 10px;

  .row {
    display: flex;
    padding: 10px 0;

    .label {
      width: 150px;
      padding-right: 10px;
      align-items: center;
      display: flex;
      justify-content: right;
    }

    .content {
      flex: 1;
      display: flex;
      align-items: center;

      .imageBox {
        height: 50px;
        border: 1px solid #ccc;
        padding: 4px;
        border-radius: 2px;
        margin: 0 4px;
        position: relative;
        display: flex;
        align-items: center;
        min-width: 50px;
        justify-content: center;

        .closeImg {
          position: absolute;
          top: -8px;
          right: -8px;
          border-radius: 50%;
          justify-content: center;
          align-content: center;
          cursor: pointer;
        }

        img {
          height: 100%;
        }
      }

      .addImage {
        cursor: pointer;
        font-size: 20px;

        &:hover {
          background: #ddd;
        }
      }
    }
  }
}

.skuBox {
  display: flex;

  .skuTypBox {
    background: #fff;
    width: 400px;
    box-shadow: 0 0 4px #bababa;
    border-radius: 6px;

    .skuTypOperation {
      padding: 10px;
    }

    .row {
      margin: 0 10px;
      padding: 10px 10px 10px 32px;
      position: relative;

      .itemOperation {
        position: absolute;
        top: 10px;
        left: 0;
        display: flex;
        flex-direction: column;
      }

      .labelBox {
        padding: 6px 0px 6px 30px;

        .labelItem {
          margin: 10px 0;
          background: #ccc;
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }
    }
  }

  .skuEditor {
    background: #fff;
    margin: 0 0 0 20px;
    flex: 1;
    box-shadow: 0 0 4px #bababa;
    border-radius: 6px;

    .skuEditorOperation {
      padding: 10px;
    }

    .row {
      margin: 10px 0;
      padding: 10px;
      background: #f7f7f7;

      .contentRow {
        flex: 1;
        display: flex;
        align-items: center;
        margin: 10px 0;

        .labelBox {
          width: 80px;
          display: flex;
          justify-content: right;
          margin-right: 10px;
        }

        .content {
          flex: 1;
          display: flex;
          align-items: center;

          .imageBox {
            height: 50px;
            border: 1px solid #ccc;
            padding: 4px;
            border-radius: 2px;
            margin: 0 4px;
            position: relative;
            display: flex;
            align-items: center;
            min-width: 50px;
            justify-content: center;

            .closeImg {
              position: absolute;
              top: -8px;
              right: -8px;
              border-radius: 50%;
              justify-content: center;
              align-content: center;
              cursor: pointer;
            }

            img {
              height: 100%;
            }
          }
        }
      }


    }
  }
}
</style>
