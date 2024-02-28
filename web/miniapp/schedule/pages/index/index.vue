<template>
	 <gwbq-calendar 
	    :insert="true"
	    :lunar="true" />



  <view>
    <view>08点30分: 起床</view>
    <view>09点30分: 上班</view>
    <view>处理文件</view>
  </view>
		 
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>
	
</style>
