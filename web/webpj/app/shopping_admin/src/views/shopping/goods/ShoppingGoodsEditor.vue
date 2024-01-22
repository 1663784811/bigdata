<template>
  <!-- =============================== 商品基本信息 =============================== -->
  <div class="goodsBaseInfo">
    <div>商品基本信息</div>
    <div class="row">
      <div class="label">商品名称:</div>
      <div class="content">
        <Input v-model="goodsObj.name"/>
      </div>
    </div>
    <div class="row">
      <div class="label">品牌Code:</div>
      <div class="content">
        <Input v-model="goodsObj.brandCode"/>
      </div>
    </div>
    <div class="row">
      <div class="label">品类Code:</div>
      <div class="content">
        <Input v-model="goodsObj.typeCode"/>
      </div>
    </div>
    <div class="row">
      <div class="label">备注:</div>
      <div class="content">
        <Input type="textarea" v-model="goodsObj.note"/>
      </div>
    </div>
    <div class="row">
      <div class="label">主图:</div>
      <div class="content">
        <div class="imageBox">
          <div class="closeImg">
            <Icon type="md-close-circle"/>
          </div>
          <img :src="goodsObj.photo" alt="">
        </div>
        <div class="imageBox">
          <div class="closeImg">
            <Icon type="md-close-circle"/>
          </div>
          <img :src="goodsObj.photo" alt="">
        </div>
        <div class="imageBox addImage" @click="addImageFn()">
          <Icon type="md-add-circle"/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="label"></div>
      <div class="content">
        <Button type="success" @click="saveGoods">保存</Button>
      </div>
    </div>
  </div>
  <div class="goodsBaseInfo">
    <div>商品图片</div>
    <div class="row">
      <div class="label">图片:</div>
      <div class="content">
        <div class="imageBox" v-for="(item,index) in goodsPhotoList" :key="index">
          <div class="closeImg">
            <Icon type="md-close-circle"/>
          </div>
          <img :src="item.photo" alt="">
        </div>
        <div class="imageBox addImage" @click="addImageFn()">
          <Icon type="md-add-circle"/>
        </div>
      </div>
    </div>
  </div>
  <!-- =============================== 商品sku信息 =============================== -->
  <div class="skuBox">
    <div class="skuTypBox">
      <div class="skuTypOperation">
        <Button type="warning" size="small" icon="md-add" @click="addAttr"/>
      </div>
      <div class="row" v-for="(item, index) in skuAttr" :key="index">
        <div class="itemOperation">
          <Button type="error" size="small" icon="ios-trash" @click="delAttr(item, index)"/>
        </div>
        <div class="attrBox">
          <Input v-model="item.label" placeholder="属性"/>
        </div>
        <div class="labelBox">
          <div class="labelItem" v-for="(v,ix) in item.value" :key="ix">
            <Input v-model="v.value" placeholder="值"/>
            <Button type="error" size="small" icon="ios-trash" @click="delValue(item.value, ix)"/>
          </div>
          <div class="labelItem">
            <Button type="success" long size="small" icon="md-add-circle" @click="addValue(item.value)"/>
          </div>
        </div>
      </div>
    </div>
    <div class="skuEditor">
      <div class="skuEditorOperation">
        <Button type="warning" size="small" icon="md-add" @click="addSku"/>
        <Button type="success" @click="saveSku">保存</Button>
      </div>
      <div class="row" v-for="(item,index) in skuList" :key="index">
        <div class="operationSku">
          <Button type="error" size="small" icon="ios-trash" @click="delSku(skuList,index)"/>
          <Button type="success" @click="saveSku(item)">保存</Button>
        </div>
        <div class="contentRow">
          <div class="labelBox">图片:</div>
          <div class="content">
            <div class="imageBox" v-for="(item,index) in goodsPhotoList" :key="index">
              <div class="closeImg">
                <Icon type="md-close-circle"/>
              </div>
              <img :src="item.photo" alt="">
            </div>
            <div class="imageBox addImage" @click="addImageFn()">
              <Icon type="md-add-circle"/>
            </div>
          </div>
        </div>
        <div class="contentRow">
          <div class="labelBox">属性:</div>
          <div class="content">
            <div v-for="(items, ix) in skuAttr" :key="ix">
              <div>{{ items.label }}:</div>
              <div>
                <Select v-model="item.skuAttr[items.label]" size="small">
                  <Option v-for="selectOption in items.value" :value="selectOption.value" :key="selectOption">
                    {{ selectOption.value }}
                  </Option>
                </Select>
              </div>
            </div>
          </div>
        </div>
        <div class="contentRow">
          <div class="labelBox">文字描述:</div>
          <div class="content">
            <Input v-model="item.note" type="textarea"/>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--  商品详情信息  -->


</template>

<script setup>
import EventBus from "@/component/EventBus.js";
import {useRoute, useRouter} from "vue-router";
import {findIdGGoods, goodsPhoto, findGoodsSku, saveGGoods, saveGStoreGoodsSku} from '@/api/api.js'
import {onMounted, ref} from "vue";
import {loginInfo} from '@/store/loginInfo.js'

const loginInfoStore = loginInfo();
const emitter = EventBus();
const router = useRouter();
const route = useRoute();

const goodsObj = ref({});
const goodsPhotoList = ref([]);
const skuList = ref([]);
const skuAttr = ref([])


onMounted(() => {
  console.log(router)
  console.log(route.query.goodsId)

  const tid = route.query.goodsId;

  initFn(tid);


})

const initFn = async (tid) => {
  if (tid) {
    const {data} = await findIdGGoods({
      tid
    })
    goodsObj.value = data;

    goodsPhoto({
      goodsId: goodsObj.value.tid
    }).then(rest => {
      const {data} = rest;
      goodsPhotoList.value = data;
      console.log(goodsPhotoList.value)
    })
    findGoodsSku({
      goodsId: goodsObj.value.tid
    }).then(rest => {
      const {data} = rest;
      for (let i = 0; i < data.gstoreGoodsSkuList.length; i++) {
        const {attr} = data.gstoreGoodsSkuList[i]
        if (attr) {
          data.gstoreGoodsSkuList[i].skuAttr = JSON.parse(attr)
        } else {
          data.gstoreGoodsSkuList[i].skuAttr = {}
        }
      }
      skuList.value = data.gstoreGoodsSkuList;
      skuAttrHandler(data.skuAttr);
    })
  }
}

/**
 * 属性处理
 */
const skuAttrHandler = (skuAttrData) => {
  const skuArr = [];
  for (const it in skuAttrData) {
    const arr = skuAttrData[it];
    const value = [];
    for (let i = 0; i < arr.length; i++) {
      value.push({
        label: arr[i],
        value: arr[i]
      })
    }
    skuArr.push({
      label: it,
      value
    })
  }
  skuAttr.value = skuArr;
}

const addAttr = () => {
  skuAttr.value.push({
    label: '规格',
    value: [{
      label: '',
      value: ''
    }]
  });
}
const delAttr = (item, index) => {
  console.log(item, index)
  skuAttr.value.splice(index, 1);
}

const addSku = () => {
  skuList.value.push({
    skuAttr: {}
  })
}

const addImageFn = (dataObj, keyObj) => {
  emitter.emit('showModalFiles', true);
}

const addValue = (node) => {
  node.push({
    label: '',
    value: ''
  })
}

const delValue = (node, index) => {
  node.splice(index, 1);
}

const delSku = (node, index) => {
  node.splice(index, 1);
}

const saveGoods = () => {
  saveGGoods({
    ...goodsObj.value,
    storeId: loginInfoStore.storeInfo.tid
  }).then(rest => {
    const {data} = rest;
    goodsObj.value = data;
    router.replace({
      name: 'shoppingGoodsEditor',
      query: {
        goodsId: data.tid
      }
    })
  });
}

const saveSku = (rowData) => {
  const goodsId = goodsObj.value.tid;
  console.log()
  saveGStoreGoodsSku({
    ...rowData,
    attr: skuAttrToAttr(rowData.skuAttr),
    goodsId,
    storeId: goodsObj.value.storeId
  }).then(() => {
    router.replace({
      name: 'shoppingGoodsEditor',
      query: {
        goodsId
      }
    })
    initFn(goodsId)
  })

}

const skuAttrToAttr = (data) => {
  const attr = skuAttr.value;
  const json = {};
  for (let i = 0; i < attr.length; i++) {
    json[attr[i].label] = data[attr[i].label]
  }
  return JSON.stringify(json);
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
          background: #f9f9f9;
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
      position: relative;

      .operationSku {
        position: absolute;
        top: 10px;
        left: 10px;
      }

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
