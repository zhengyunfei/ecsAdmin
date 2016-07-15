package com.zero2ipo.plugins.weixin.bizc.impl;

import com.zero2ipo.plugins.weixin.bizc.ICoreService;
import com.zero2ipo.plugins.weixin.bo.Token;
import com.zero2ipo.plugins.weixin.bo.WxTemplate;
import com.zero2ipo.plugins.weixin.utils.CommonUtil;
import com.zero2ipo.plugins.weixin.utils.MessageUtil;
import com.zero2ipo.plugins.weixin.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 发送消息核心服务类
 */
@Component("coreService")
public class CoreServiceImpl implements ICoreService {
	/**
	 * 发送模板消息
	 * appId 公众账号的唯一标识
	 * appSecret 公众账号的密钥
	 * openId 用户标识
	 */
	public void send_template_message(String appId, String appSecret, String openId,WxTemplate template) {
		Token token=CommonUtil.getToken(appId,appSecret);
		String access_token = token.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		String jsonString = JSONObject.fromObject(template).toString();
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonString);
		int result = 0;
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
			}
		}
	}
	/**
	 * 获取微信关注者openid
	 * @return
	 */
	public String getOpenId(HttpServletRequest request, HttpServletResponse response){
		String openId="";
		Map<String, String> requestMap = null;
		try {
			requestMap = MessageUtil.parseXml(request);
			openId = requestMap.get("ToUserName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openId;
	}
}
