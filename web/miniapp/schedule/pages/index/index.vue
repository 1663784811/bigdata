<template>
  <view class="content">

    <view class="headBox">
      <view class="menuBox">
		  <view class="menuItem">
		    <view class="icon">功</view>
		    <view>计划</view>
		  </view>
		  <view class="menuItem">
		    <view class="icon">功</view>
		    <view>日历</view>
		  </view>
		  <view class="menuItem">
		    <view class="icon">功</view>
		    <view>习惯</view>
		  </view>
		  <view class="menuItem">
		    <view class="icon">功</view>
		    <view>通信录</view>
		  </view>
	  </view>
	  <view class="operationBox">
	    <view class="lastWeek">上一周</view>
	    <view class="titleTxt">我的日程</view>
	    <view class="nextWeek">下一周</view>
	  </view>
    </view>

    <gwbq-calendar v-if="true"
                   :insert="true"
                   :lunar="true"/>

    <view class="calendarBox">

      <view class="calendarItem" v-for="(item, index) in state.dateList" :key="index">
        <view class="itemLeft">
          <view>{{ item.week }}</view>
          <view>{{ item.date }}</view>
        </view>
        <view class="itemRight">
          <view class="eventItem" v-for="(ev, inx) in 5" :key="inx">
            <radio class="eventRadio" checked="false" style="transform:scale(0.6)"/>
            <view class="eventNoteBox">
              <view class="eventText">1.起床</view>
              <view class="eventNote">起床</view>
            </view>
          </view>
        </view>
      </view>
    </view>


  </view>
</template>

<script setup>
import {
  reactive
} from 'vue'

const state = reactive({
  dateList: [
    {
      week: '日',
      date: '03-08'
    },
    {
      week: '一',
      date: '03-08'
    },
    {
      week: '二',
      date: '03-08'
    },
    {
      week: '三',
      date: '03-08'
    },
    {
      week: '四',
      date: '03-08'
    },
    {
      week: '五',
      date: '03-08'
    },
    {
      week: '六',
      date: '03-08'
    }
  ]
})
</script>

<style lang="less">
.headBox{
	position: fixed;
	background: #ccc;
	top: 0;
	left: 0;
	right: 0;
	z-index: 9;
	.menuBox{
		display: flex;
		justify-content: space-around;
		.menuItem{
			text-align: center;
		}
	}
	.operationBox {
	  display: flex;
	  justify-items: center;
	  justify-content: center;
	  align-items: center;
	  padding: 18rpx 30rpx;
	  .lastWeek {
	
	  }
	
	  .titleTxt {
	    flex: 1;
	    text-align: center;
	  }
	
	  .nextWeek {
	
	  }
	}
}
.calendarBox {


  .calendarItem {
    display: flex;
    justify-items: center;
    margin-bottom: 1px;

    .itemLeft {
      text-align: center;
      background: red;
      height: 100%;
    }
  }

  .calendarItem:nth-child(2n) {
    background: #ddd;
  }

  .itemRight {
    flex: 1;

    .eventItem {
      display: flex;

      .eventRadio {
      }

      .eventNoteBox {
        flex: 1;

        .eventText {
          font-size: 24rpx;
        }

        .eventNote {
          font-size: 18rpx;
        }
      }
    }

    .eventItem:nth-child(2n) {
      background: #ccc;
    }
  }
}
</style>