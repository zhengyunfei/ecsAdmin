package com.zero2ipo.cfj.user.bo;

/**
    * users 实体类
	* 和app公众号公众一个bo
    * Sat Dec 19 18:37:01 GMT+08:00 2015 郑云飞
    */ 


public class Users{
	private long id;
	private String userId;
	private String phoneNum;
	private String account;
	private String password;
	private String createTime;
	private String updateTime;
	private short level;
	private String openId;

	   public String getOpenId() {
		   return openId;
	   }

	   public void setOpenId(String openId) {
		   this.openId = openId;
	   }

	   public void setId(long id){
	this.id=id;
	}
	public long getId(){
		return id;
	}
	public void setUserId(String userId){
	this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	public void setPhoneNum(String phoneNum){
	this.phoneNum=phoneNum;
	}
	public String getPhoneNum(){
		return phoneNum;
	}
	public void setAccount(String account){
	this.account=account;
	}
	public String getAccount(){
		return account;
	}
	public void setPassword(String password){
	this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setCreateTime(String createTime){
	this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setUpdateTime(String updateTime){
	this.updateTime=updateTime;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setLevel(short level){
	this.level=level;
	}
	public short getLevel(){
		return level;
	}

}

