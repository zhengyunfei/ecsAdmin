package com.zero2ipo.plugins.weixin.bizc;

import com.zero2ipo.module.entity.user.UserEntity;
import com.zero2ipo.plugins.weixin.bo.WxTemplate;

/**
 * 活动预约成功发送微信消息接口
 * @author zhengyunfei
 */
public interface IUserWeiXinMsgService {
	public boolean sendWeixin_sh_success(UserEntity user);
	public boolean sendWeixin_sh_error(UserEntity user);
	/**
	 * 获取模板数据
	 * @return
	 */
	public WxTemplate getWxTemplate(String openId, String msgTemplateId, UserEntity user);
}
