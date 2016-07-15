package com.zero2ipo.cfj.user.bo;

import com.zero2ipo.module.entity.user.UserInfoEntity;

   /**
    * cfjUserInfo 实体类
    * Sat Oct 11 15:46:41 GMT+08:00 2014 郑云飞
    */ 


public class UserInfoBo{
	private String userInfoId;
	private String userId;
	private String userName;
	private String sex;
	private String maile;
	private String position;
	private String company;
	private String likePro;
	private String column9;
	private String userCardUrl;
	private String idCardUrl;
	private String idCard;
	private String invitationCode;
	private String assest;
	private String arpm;
	private String codeArea;
	private String codeAreaName;
	private String remark;
	private String userPhoto;
	
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getRemark() {
		return remark==null?"":remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInvitationCode() {
		return invitationCode==null?"":invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public String getCodeAreaName() {
		return codeAreaName==null?"":codeAreaName;
	}
	public void setCodeAreaName(String codeAreaName) {
		this.codeAreaName = codeAreaName;
	}

	public String getCodeArea() {
		return codeArea;
	}
	public void setCodeArea(String codeArea) {
		this.codeArea = codeArea;
	}
	public String getAssest() {
		return assest==null?"":assest;
	}
	public void setAssest(String assest) {
		this.assest = assest;
	}
	public String getArpm() {
		return arpm==null?"":arpm;
	}
	public void setArpm(String arpm) {
		this.arpm = arpm;
	}
	public String getIdCard() {
		return idCard==null?"":idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getIdCardUrl() {
		return idCardUrl==null?"":idCardUrl;
	}
	public void setIdCardUrl(String idCardUrl) {
		this.idCardUrl = idCardUrl;
	}
	public void setUserInfoId(String userInfoId){
	this.userInfoId=userInfoId;
	}
	public String getUserInfoId(){
		return userInfoId;
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
	public void setSex(String sex){
	this.sex=sex;
	}
	public String getSex(){
		return sex;
	}
	public void setMaile(String maile){
	this.maile=maile;
	}
	public String getMaile(){
		return maile;
	}
	public void setPosition(String position){
	this.position=position;
	}
	public String getPosition(){
		return position;
	}
	public void setCompany(String company){
	this.company=company;
	}
	public String getCompany(){
		return company;
	}
	public void setLikePro(String likePro){
	this.likePro=likePro;
	}
	public String getLikePro(){
		return likePro==null?"":likePro;
	}
	public void setColumn9(String column9){
	this.column9=column9;
	}
	public String getColumn9(){
		return column9;
	}
	
	public String getUserCardUrl() {
		return userCardUrl==null?"":userCardUrl;
	}
	public void setUserCardUrl(String userCardUrl) {
		this.userCardUrl = userCardUrl;
	}
	public static UserInfoEntity boToEntity(UserInfoBo bo){
	UserInfoEntity entity=new UserInfoEntity();
	entity.setUserInfoId(bo.getUserInfoId());
	entity.setUserId(bo.getUserId());
	entity.setUserName(bo.getUserName());
	entity.setSex(bo.getSex());
	entity.setMaile(bo.getMaile());
	entity.setPosition(bo.getPosition());
	entity.setCompany(bo.getCompany());
	entity.setLikePro(bo.getLikePro());
	entity.setUserCardUrl(bo.getUserCardUrl());
	entity.setColumn9(bo.getColumn9());
	entity.setIdCardUrl(bo.getIdCardUrl());
	entity.setIdCard(bo.getIdCard());
	entity.setAssest(bo.getAssest());
	entity.setArpm(bo.getArpm());
	entity.setCodeArea(bo.getCodeArea());
	entity.setInvitationCode(bo.getInvitationCode());
	entity.setRemark(bo.getRemark());
	entity.setUserPhoto(bo.getUserPhoto());
	return entity;
	}
	public static UserInfoBo entityToBo(UserInfoEntity bo){
	UserInfoBo entity=new UserInfoBo();
	entity.setUserInfoId(bo.getUserInfoId());
	entity.setUserId(bo.getUserId());
	entity.setUserName(bo.getUserName());
	entity.setSex(bo.getSex());
	entity.setMaile(bo.getMaile());
	entity.setPosition(bo.getPosition());
	entity.setCompany(bo.getCompany());
	entity.setLikePro(bo.getLikePro());
	entity.setColumn9(bo.getColumn9());
	entity.setUserCardUrl(bo.getUserCardUrl());
	entity.setIdCardUrl(bo.getIdCardUrl());
	entity.setIdCard(bo.getIdCard());
	entity.setAssest(bo.getAssest());
	entity.setArpm(bo.getArpm());
	entity.setCodeArea(bo.getCodeArea());
	entity.setInvitationCode(bo.getInvitationCode());
	entity.setRemark(bo.getRemark());
	entity.setUserPhoto(bo.getUserPhoto());
	return entity;
	}
}

