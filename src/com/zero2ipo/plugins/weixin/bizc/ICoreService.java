package com.zero2ipo.plugins.weixin.bizc;

import com.zero2ipo.plugins.weixin.bo.WxTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZhengYunfei on 2015/7/16
 * 用户审核微信消息接口
 */
@Service
public interface ICoreService {
    /**
     * 发送模板消息
     * appId 公众账号的唯一标识
     * appSecret 公众账号的密钥
     * openId 用户标识
     * msgTemplateId 模板ID
     */
    public void send_template_message(String appId, String appSecret, String openId, WxTemplate template);

    /**
     * 获取微信关注者openid
     * @return
     */
    public String getOpenId(HttpServletRequest request, HttpServletResponse response);

}
