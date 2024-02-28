<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="less">
	page,
	view,
	scroll-view,
	text,
	image,
	textarea,
	button,
	input {
		box-sizing: border-box;
	}
	/*每个页面公共css */
	.content{
		min-height: 100vh;
		background: #eee;
	}
	.content:before,.content:after{
    content: ' ';
    height: 0.01rpx;
    display: block;
    clear: both;
  }

</style>
