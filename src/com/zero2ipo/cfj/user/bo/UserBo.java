package com.zero2ipo.cfj.user.bo;
import com.zero2ipo.module.entity.user.UserEntity;

   /**
    * cfjUser 实体类
    * Sat Oct 04 16:23:43 GMT+08:00 2014 郑云飞
    */ 


public class UserBo{
	private String userId;
	private String userName;
	private String userPassword;
	private String mobile;
	private String userStatus;
	private String userStatusName;
	private String userGroup;
	private String userGroupName;
	private String userInputtime;
	private int userLoginnum;
	private String userLasttime;
	private String remark;//管理备注
	private String areaName;//管理员录入地区
	private String jrMoney;//管理员录入金融资产量
	private UserInfoBo userInfo;
	private OrganizationUserInfoBo orgInfoBo;
	private String userIrId;
	private String userIrMobile;
	private String userIrName;
	private String openId;
    private String source;

	   public String getSource() {
		   return source;
	   }

	   public void setSource(String source) {
		   this.source = source;
	   }

	   public String getUserIrMobile() {
		   return userIrMobile;
	   }

	   public void setUserIrMobile(String userIrMobile) {
		   this.userIrMobile = userIrMobile;
	   }

	   public String getUserIrName() {
		   return userIrName;
	   }

	   public void setUserIrName(String userIrName) {
		   this.userIrName = userIrName;
	   }

	   public String getAreaName() {
		   return areaName;
	   }

	   public void setAreaName(String areaName) {
		   this.areaName = areaName;
	   }

	   public String getJrMoney() {
		   return jrMoney;
	   }

	   public void setJrMoney(String jrMoney) {
		   this.jrMoney = jrMoney;
	   }

	   public OrganizationUserInfoBo getOrgInfoBo() {
		return orgInfoBo;
	}
	public void setOrgInfoBo(OrganizationUserInfoBo orgInfoBo) {
		this.orgInfoBo = orgInfoBo;
	}
	public void setUserId(String userId){
	this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserName(String userName){
	this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setUserPassword(String userPassword){
	this.userPassword=userPassword;
	}
	public String getUserPassword(){
		return userPassword;
	}
	public void setMobile(String mobile){
	this.mobile=mobile;
	}
	public String getMobile(){
		return mobile;
	}
	public void setUserStatus(String userStatus){
	this.userStatus=userStatus;
	}
	public String getUserStatus(){
		return userStatus;
	}
	public void setUserGroup(String userGroup){
	this.userGroup=userGroup;
	}
	public String getUserGroup(){
		return userGroup;
	}
	public void setUserInputtime(String userInputtime){
	this.userInputtime=userInputtime;
	}
	public String getUserInputtime(){
		return userInputtime;
	}
	public void setUserLoginnum(int userLoginnum){
	this.userLoginnum=userLoginnum;
	}
	public int getUserLoginnum(){
		return userLoginnum;
	}
	public void setUserLasttime(String userLasttime){
	this.userLasttime=userLasttime;
	}
	public String getUserLasttime(){
		return userLasttime;
	}
	public void setRemark(String remark){
	this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	
	public String getUserStatusName() {
		return userStatusName;
	}
	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}
	
	public UserInfoBo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoBo userInfo) {
		this.userInfo = userInfo;
	}
	
	public String getUserIrId() {
		return userIrId;
	}
	public void setUserIrId(String userIrId) {
		this.userIrId = userIrId;
	}

	   public String getOpenId() {
		   return openId;
	   }

	   public void setOpenId(String openId) {
		   this.openId = openId;
	   }

	   public static UserEntity boToEntity(UserBo bo){
		UserEntity entity=new UserEntity();
		entity.setUserId(bo.getUserId());
		entity.setUserName(bo.getUserName());
		entity.setUserPassword(bo.getUserPassword());
		entity.setMobile(bo.getMobile());
		entity.setUserStatus(bo.getUserStatus());
		entity.setUserGroup(bo.getUserGroup());
		entity.setUserInputtime(bo.getUserInputtime());
		entity.setUserLoginnum(bo.getUserLoginnum());
		entity.setUserLasttime(bo.getUserLasttime());
		entity.setRemark(bo.getRemark());entity.setUserIrId(bo.getUserIrId());
		entity.setOpenId(bo.getOpenId());
		entity.setAreaName(bo.getAreaName());
		entity.setJrMoney(bo.getJrMoney());
		entity.setSource(bo.getSource());
		return entity;
	}
	public static UserBo entityToBo(UserEntity bo){
		UserBo entity=new UserBo();
		entity.setUserId(bo.getUserId());
		entity.setUserName(bo.getUserName());
		entity.setUserPassword(bo.getUserPassword());
		entity.setMobile(bo.getMobile());
		entity.setUserStatus(bo.getUserStatus());
		entity.setUserGroup(bo.getUserGroup());
		entity.setUserInputtime(bo.getUserInputtime());
		entity.setUserLoginnum(bo.getUserLoginnum());
		entity.setUserLasttime(bo.getUserLasttime());
		entity.setRemark(bo.getRemark());
		entity.setUserIrId(bo.getUserIrId());
		entity.setOpenId(bo.getOpenId());
		entity.setAreaName(bo.getAreaName());
		entity.setJrMoney(bo.getJrMoney());
		entity.setSource(bo.getSource());
		return entity;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	
}

