package com.zero2ipo.cfj.invitationCode.bo;

import com.zero2ipo.module.entity.invitationCode.InvitationCodeEntity;

   /**
    * cfjInvitationCode 实体类
    * Tue Dec 30 15:13:29 GMT+08:00 2014 zhengyunfei
    */ 


public class InvitationCodeBo{
	private String id;
	private String userId;//邀请码关联的会员ID
	private String value;//邀请码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public static InvitationCodeEntity boToEntity(InvitationCodeBo bo){
	InvitationCodeEntity entity=new InvitationCodeEntity();
	entity.setId(bo.getId());
	entity.setUserId(bo.getUserId());
	entity.setValue(bo.getValue());
	return entity;
	}
	public static InvitationCodeBo entityToBo(InvitationCodeEntity bo){
	InvitationCodeBo entity=new InvitationCodeBo();
	entity.setId(bo.getId());
	entity.setUserId(bo.getUserId());
	entity.setValue(bo.getValue());
	return entity;
	}
}

