package com.zero2ipo.cfj.invitationCode.bo;

import com.zero2ipo.module.entity.invitationCode.InvitationCodeUserEntity;


   /**
    * cfjInvitationCode 实体类
    * Tue Dec 30 15:13:29 GMT+08:00 2014 zhengyunfei
    */ 


public class InvitationCodeUserBo{
	private String id;//主键
	private String value;//邀请码
	private String useId;//使用者id
	private String useName;//使用者姓名
	private String useMobile;//使用人手机号码
	private String useTime;//使用时间
	private String owerName;//拥有者姓名
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	public String getUseName() {
		return useName;
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getOwerName() {
		return owerName;
	}
	public void setOwerName(String owerName) {
		this.owerName = owerName;
	}
	
	public String getUseMobile() {
		return useMobile;
	}
	public void setUseMobile(String useMobile) {
		this.useMobile = useMobile;
	}
	public static InvitationCodeUserBo entityToBo(InvitationCodeUserEntity entity){
		InvitationCodeUserBo bo=new InvitationCodeUserBo();
		bo.setId(entity.getId());
		bo.setUseId(entity.getUseId());
		bo.setUseTime(entity.getUseTime());
		bo.setValue(entity.getValue());
		return bo;
	}
	public static InvitationCodeUserEntity entityToBo(InvitationCodeUserBo entity){
		InvitationCodeUserEntity bo=new InvitationCodeUserEntity();
		bo.setId(entity.getId());
		bo.setUseId(entity.getUseId());
		bo.setUseTime(entity.getUseTime());
		bo.setValue(entity.getValue());
		return bo;
	}
}

