package com.zero2ipo.cfj.newstation.bo;

import com.zero2ipo.module.entity.newstation.NewsTationEntity;

   /**
    * cfjNewsTation 实体类
    * Thu Dec 11 10:46:19 GMT+08:00 2014 郑云飞
    */ 


public class NewsTationBo{
	private String id;
	private String title;
	private String content;
	private String sendTime;
	private String userId;
	private String createUser;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static NewsTationEntity boToEntity(NewsTationBo bo){
	NewsTationEntity entity=new NewsTationEntity();
	entity.setId(bo.getId());
	entity.setTitle(bo.getTitle());
	entity.setContent(bo.getContent());
	entity.setSendTime(bo.getSendTime());
	entity.setUserId(bo.getUserId());
	entity.setCreateUser(bo.getCreateUser());
	entity.setStatus(bo.getStatus());
	return entity;
	}
	public static NewsTationBo entityToBo(NewsTationEntity bo){
	NewsTationBo entity=new NewsTationBo();
	entity.setId(bo.getId());
	entity.setTitle(bo.getTitle());
	entity.setContent(bo.getContent());
	entity.setSendTime(bo.getSendTime());
	entity.setUserId(bo.getUserId());
	entity.setCreateUser(bo.getCreateUser());
	entity.setStatus(bo.getStatus());
	return entity;
	}
}

