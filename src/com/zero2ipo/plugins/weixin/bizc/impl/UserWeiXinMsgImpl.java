package com.zero2ipo.plugins.weixin.bizc.impl;

import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.module.entity.user.UserEntity;
import com.zero2ipo.plugins.weixin.bizc.ICoreService;
import com.zero2ipo.plugins.weixin.bizc.IUserWeiXinMsgService;
import com.zero2ipo.plugins.weixin.bo.TemplateData;
import com.zero2ipo.plugins.weixin.bo.WxTemplate;
import com.zero2ipo.plugins.weixin.config.Config;
import com.zero2ipo.plugins.weixin.contants.TemplateMessageContants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户审核-微信消息接口
 * @author zhengyunfei
 *
 */
@Component("userWeiXinMsgService")
public class UserWeiXinMsgImpl implements IUserWeiXinMsgService {

	@Resource(name = "coreService")
	private ICoreService coreService;

	/**
	 * 审核通过 发送消息
	 * @param user
	 * @return
	 */
	public boolean sendWeixin_sh_error(UserEntity user)  {
		String opid=user.getOpenId();
		if(!StringUtil.isNullOrEmpty(opid)){
			/**通过网页版活动预约成功，发送微信消息**/
			WxTemplate wxTemplate=getWxTemplate(opid, TemplateMessageContants.REG_SUCCESS_TEMPLATE_MSGID,user);
			wxTemplate.getData().get("first").setValue("您的会员认证资料审核不通过");
			wxTemplate.getData().get("keyword3").setValue("审核不通过");
			wxTemplate.getData().get("keyword4").setValue("审核不通过");
			coreService.send_template_message(Config.APPID, Config.SECRET, opid,wxTemplate );
		}
		return true;
	}

	/**
	 * 审核未通过,发送微信消息
	 * @param user
	 * @return
	 */
	public boolean sendWeixin_sh_success(UserEntity user)  {
		String opid=user.getOpenId();
		if(!StringUtil.isNullOrEmpty(opid)){
			/**通过网页版活动预约成功，发送微信消息**/
			WxTemplate wxTemplate=getWxTemplate(opid, TemplateMessageContants.REG_SUCCESS_TEMPLATE_MSGID,user);
			coreService.send_template_message(Config.APPID, Config.SECRET,opid,wxTemplate);
		}
		return true;
	}
	/**
	 * 获取模板数据
	 * @return
	 */
	public WxTemplate getWxTemplate(String openId,String msgTemplateId,UserEntity user){
		WxTemplate temp = new WxTemplate();
		temp.setTouser(openId);
		temp.setTemplate_id(msgTemplateId);
		temp.setUrl("http://www.pestreet.cn/mobile/articlelist/jshyhd/1/71.html");
		temp.setTopcolor("#000000");
		/**根据会员id查询该会员预约的活动**/
		String userId=user.getUserId();
		if(!StringUtil.isNullOrEmpty(userId)){
			Map<String,TemplateData> paramMap=new HashMap<String, TemplateData>();
			TemplateData data0=new TemplateData();
			data0.setValue("您的会员认证资料已审核通过\n");
			data0.setColor("#040188");
			paramMap.put("first",data0);
			TemplateData userName=new TemplateData();
			userName.setValue(user.getUserName());
			userName.setColor("#040188");
			paramMap.put("keyword1",userName);
			TemplateData mobile=new TemplateData();
			mobile.setValue(user.getMobile());
			mobile.setColor("#040188");
			paramMap.put("keyword2",mobile);
			TemplateData status=new TemplateData();
			status.setValue("审核通过");
			status.setColor("#040188");
			paramMap.put("keyword3",status);
			temp.setData(paramMap);
			TemplateData description=new TemplateData();
			description.setValue("精英会员\n\n");
			description.setColor("#040188");
			paramMap.put("keyword4",description);
			TemplateData remark=new TemplateData();
			remark.setValue("通过财富街会员认证审核的用户将得到财富街精英会员权益");
			remark.setColor("#000000");
			paramMap.put("remark",remark);
			temp.setData(paramMap);
		}
		return temp;
	}
}
