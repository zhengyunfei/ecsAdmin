package com.zero2ipo.cfj.message.bo;
import java.util.Date;

import com.zero2ipo.module.entity.telMessage.TelMessageEntity;

   /**
    * cfjTelMessage 实体类
    * Fri Nov 14 15:17:57 GMT+08:00 2014 zhengyunfei
    */ 


public class TelMessageBo{
	private String msgId;
	private String tel;
	private String msgTime;
	private String content;
	private String typy;
	private String returnCode;
	public void setMsgId(String msgId){
	this.msgId=msgId;
	}
	public String getMsgId(){
		return msgId;
	}
	public void setTel(String tel){
	this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	public void setMsgTime(String msgTime){
	this.msgTime=msgTime;
	}
	public String getMsgTime(){
		return msgTime;
	}
	public void setContent(String content){
	this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setTypy(String typy){
	this.typy=typy;
	}
	public String getTypy(){
		return typy;
	}
	public void setReturnCode(String returnCode){
	this.returnCode=returnCode;
	}
	public String getReturnCode(){
		return returnCode;
	}
	public static TelMessageEntity boToEntity(TelMessageBo bo){
	TelMessageEntity entity=new TelMessageEntity();
	entity.setMsgId(bo.getMsgId());
	entity.setTel(bo.getTel());
	entity.setMsgTime(bo.getMsgTime());
	entity.setContent(bo.getContent());
	entity.setTypy(bo.getTypy());
	entity.setReturnCode(bo.getReturnCode());
	return entity;
	}
	public static TelMessageBo entityToBo(TelMessageEntity bo){
	TelMessageBo entity=new TelMessageBo();
	entity.setMsgId(bo.getMsgId());
	entity.setTel(bo.getTel());
	entity.setMsgTime(bo.getMsgTime());
	entity.setContent(bo.getContent());
	entity.setTypy(bo.getTypy());
	entity.setReturnCode(bo.getReturnCode());
	return entity;
	}
}

