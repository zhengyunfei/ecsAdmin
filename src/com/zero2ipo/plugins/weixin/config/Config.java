package com.zero2ipo.plugins.weixin.config;


/**
 * 配置文件
 * @author zhengyunfei
 * @date 2015年7月9日
 * @version 1.0
 */
public interface Config {

	// 赋权类型 
	 String grant_type = "client_credential";
	
	// 测试开发者申请的appid
	//String APPID = "wxba458b8ccfd615ef";
	//正式
	String APPID = "wx22816edffffsdfdea0";

	// 开发者申请的secret密钥
	//String SECRET = "453a2cc14bfa6b4f3fc4b27bbc6e7c5e";
	String SECRET = "fc84efad40d3";
	//页面回调连接
	String CALL_BACK_LINK="http://www.pestreet.cn/mobile/oauth/do.html";
	//微信授权连接
	String OAUTH_LINK="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+CALL_BACK_LINK+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
}
