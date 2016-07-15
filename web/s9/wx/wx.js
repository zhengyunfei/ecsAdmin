 wx.ready(function(){
	 $('#checkJsApi').click(function(){
		   wx.checkJsApi({
				jsApiList: ['getLocation','openLocation'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
				success: function(res) {
					alert(JSON.stringify(res));
					// 以键值对的形式返回，可用的api值true，不可用为false
					// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
				}
			});
	});


	// 2. 分享接口
  // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
	  $('#onMenuShareAppMessage').click(function(){
			wx.onMenuShareAppMessage({
			  title: '分享给朋友',
			  desc: '',
			  link: 'http://wx.gujins.com/wx1.jsp',
			  imgUrl: 'http://wx.gujins.com/wx.jpg',
			  trigger: function (res) {
				// 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
				alert('用户点击发送给朋友');
			  },
			  success: function (res) {
				alert('已分享');
			  },
			  cancel: function (res) {
				alert('已取消');
			  },
			  fail: function (res) {
				alert(JSON.stringify(res));
			  }
			});
			alert('已注册获取“发送给朋友”状态事件');
	  });

  // 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
	 $('#onMenuShareTimeline').click(function(){
			 wx.onMenuShareTimeline({
				  title: '分享到朋友圈',
				  link: 'http://wx.gujins.com/wx1.jsp',
				  imgUrl: 'http://wx.gujins.com/wx.jpg',
				  trigger: function (res) {
					// 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
					alert('用户点击分享到朋友圈');
				  },
				  success: function (res) {
					alert('已分享');
				  },
				  cancel: function (res) {
					alert('已取消');
				  },
				  fail: function (res) {
					alert(JSON.stringify(res));
				  }
			});
		alert('已注册获取“分享到朋友圈”状态事件');
	 });

	  // 2.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
		$('#onMenuShareQQ').click(function(){
			wx.onMenuShareQQ({
			  title: '分享到QQ',
			  desc: '分享到QQ',
			  link: 'http://wx.gujins.com/wx1.jsp',
			  imgUrl: 'http://wx.gujins.com/wx.jpg',
			  trigger: function (res) {
				alert('用户点击分享到QQ');
			  },
			  complete: function (res) {
				alert(JSON.stringify(res));
			  },
			  success: function (res) {
				alert('已分享');
			  },
			  cancel: function (res) {
				alert('已取消');
			  },
			  fail: function (res) {
				alert(JSON.stringify(res));
			  }
			});
			alert('已注册获取“分享到 QQ”状态事件');
		});

		// 3 智能接口
		  var voice = {
			localId: '',
			serverId: ''
		  };
		  // 3.1 识别音频并返回识别结果
		  $('#translateVoice').click(function () {
				if (voice.localId == '') {
				  alert('请先使用 startRecord 接口录制一段声音');
				  return;
				}
				wx.translateVoice({
					  localId: voice.localId,
					  complete: function (res) {
						if (res.hasOwnProperty('translateResult')) {
						  alert('识别结果：' + res.translateResult);
						} else {
						  alert('无法识别');
						}
					  }
				});
		  });


  // 4 音频接口
  // 4.2 开始录音
  $('#startRecord').click(function () {
    wx.startRecord({
      cancel: function () {
        alert('用户拒绝授权录音');
      }
    });
  });

  // 4.3 停止录音
  $('#stopRecord').click(function () {
    wx.stopRecord({
      success: function (res) {
        voice.localId = res.localId;
      },
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });
  });

  // 4.4 监听录音自动停止
  wx.onVoiceRecordEnd({
    complete: function (res) {
      voice.localId = res.localId;
      alert('录音时间已超过一分钟');
    }
  });

  // 4.5 播放音频
  $('#playVoice').click(function () {
    if (voice.localId == '') {
      alert('请先使用 startRecord 接口录制一段声音');
      return;
    }
    wx.playVoice({
      localId: voice.localId
    });
  });

  // 4.6 暂停播放音频
  $('#pauseVoice').click(function () {
    wx.pauseVoice({
      localId: voice.localId
    });
  });

  // 4.7 停止播放音频
   $('#stopVoice').click(function () {
    wx.stopVoice({
      localId: voice.localId
    });
  });

  // 4.8 监听录音播放停止
  wx.onVoicePlayEnd({
    complete: function (res) {
      alert('录音（' + res.localId + '）播放结束');
    }
  });

  // 4.8 上传语音
  $('#uploadVoice').click(function(){
    if (voice.localId == '') {
      alert('请先使用 startRecord 接口录制一段声音');
      return;
    }
    wx.uploadVoice({
      localId: voice.localId,
      success: function (res) {
        alert('上传语音成功，serverId 为' + res.serverId);
        voice.serverId = res.serverId;
      }
    });
  });

  // 4.9 下载语音
	 $('#downloadVoice').click(function(){
		if (voice.serverId == '') {
		  alert('请先使用 uploadVoice 上传声音');
		  return;
		}
		wx.downloadVoice({
		  serverId: voice.serverId,
		  success: function (res) {
			alert('下载语音成功，localId 为' + res.localId);
			voice.localId = res.localId;
		  }
		});
	  });


	
    

	   // *************************地理位置接口
		 $('#getLocation').click(function(){
		   wx.getLocation({
				success: function (res) {
					var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
					var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
					var speed = res.speed; // 速度，以米/每秒计
					var accuracy = res.accuracy; // 位置精度
					
				}
			});
		});		 
		$('#openLocation').click(function(){
		    wx.openLocation({
			  latitude: 116.377823,
			  longitude: 39.949238,
			  name: '北京东站',
			  address: '北京市北京东站',
			  scale: 14,
			  infoUrl: 'http://weixin.qq.com'
			});		
		});
	 //end *************************地理位置接口





	 // 5 图片接口
  // 5.1 拍照、本地选图
  var images = {
    localId: [],
    serverId: []
  };

	$('#chooseImage').click(function(){  
		wx.chooseImage({
		  success: function (res) {
			images.localId = res.localIds;
			alert('已选择 ' + res.localIds.length + ' 张图片');
		  }
		});
    });

  // 5.2 图片预览
  $('#previewImage').click(function(){  
    wx.previewImage({
      current: 'http://wx.gujins.com/wx.jpg',
      urls: [
        'http://wx.gujins.com/wx.jpg',
        'http://wx.gujins.com/wx.jpg',
        'http://wx.gujins.com/wx.jpg'
      ]
    });
  });

  // 5.3 上传图片  
 $('#uploadImage').click(function(){  
    if (images.localId.length == 0) {
      alert('请先使用 chooseImage 接口选择图片');
      return;
    }
    var i = 0, length = images.localId.length;
    images.serverId = [];
    function upload() {
      wx.uploadImage({
        localId: images.localId[i],
        success: function (res) {
          i++;
          alert('已上传：' + i + '/' + length);
          images.serverId.push(res.serverId);
          if (i < length) {
            upload();
          }
        },
        fail: function (res) {
          alert(JSON.stringify(res));
        }
      });
    }
    upload();
  });

  // 5.4 下载图片  
	$('#downloadImage').click(function(){  		
		if (images.serverId.length === 0) {
		  alert('请先使用 uploadImage 上传图片');
		  return;
		}
		var i = 0, length = images.serverId.length;
		images.localId = [];		
		 
		function download() {			 
		  wx.downloadImage({
			serverId: images.serverId[i],
		    isShowProgressTips: 1, 
			success: function (res) {
			  i++;
			  alert('已下载：' + i + '/' + length);
			  images.localId.push(res.localId);
			  if (i < length) {
				download();
			  }
			}
		  });
		}
		download();
	  });



 // 6 设备信息接口
  // 6.1 获取当前网络状态
 $('#getNetworkType').click(function(){  	
	  
		wx.getNetworkType({
		  success: function (res) {
			alert(res.networkType);
		  },
		  fail: function (res) {
			alert(JSON.stringify(res));
		  }
		});
  });




   // 8 界面操作接口
  // 8.1 隐藏右上角菜单
 $('#hideOptionMenu').click(function(){
    wx.hideOptionMenu();
  });

  // 8.2 显示右上角菜单
  $('#showOptionMenu').click(function(){
    wx.showOptionMenu();
  });

  // 8.3 批量隐藏菜单项
 $('#hideMenuItems').click(function(){
    wx.hideMenuItems({
      menuList: [
        'menuItem:readMode', // 阅读模式
        'menuItem:share:timeline', // 分享到朋友圈
        'menuItem:copyUrl' // 复制链接
      ],
      success: function (res) {
        alert('已隐藏“阅读模式”，“分享到朋友圈”，“复制链接”等按钮');
      },
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });
  });

  // 8.4 批量显示菜单项
  $('#showMenuItems').click(function(){
    wx.showMenuItems({
      menuList: [
        'menuItem:readMode', // 阅读模式
        'menuItem:share:timeline', // 分享到朋友圈
        'menuItem:copyUrl' // 复制链接
      ],
      success: function (res) {
        alert('已显示“阅读模式”，“分享到朋友圈”，“复制链接”等按钮');
      },
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });
  });

  // 8.5 隐藏所有非基本菜单项
  $('#hideAllNonBaseMenuItem').click(function(){
    wx.hideAllNonBaseMenuItem({
      success: function () {
        alert('已隐藏所有非基本菜单项');
      }
    });
  });

  // 8.6 显示所有被隐藏的非基本菜单项
  $('#showAllNonBaseMenuItem').click(function(){
    wx.showAllNonBaseMenuItem({
      success: function () {
        alert('已显示所有非基本菜单项');
      }
    });
 });

  // 8.7 关闭当前窗口
  $('#closeWindow').click(function(){
    wx.closeWindow();
  });



  // 9 微信原生接口
  // 9.1.1 扫描二维码并返回结果 
 $('#scanQRCode0').click(function(){
     wx.scanQRCode();
  });
  // 9.1.2 扫描二维码并返回结果
  $('#scanQRCode1').click(function(){
    wx.scanQRCode({
      needResult: 1,
      desc: 'scanQRCode desc',
      success: function (res) {
        alert(JSON.stringify(res));
      }
    });
  });




   });

    