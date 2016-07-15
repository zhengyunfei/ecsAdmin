package com.zero2ipo.plugins.template.bo;
import com.zero2ipo.module.entity.template.TemplateEntity;

   /**
    * cfjTemplate 实体类
    * Fri Nov 07 10:54:56 GMT+08:00 2014 zhengyunfei
    */ 


public class TemplateBo{
	private int templateId;
	private String templateCode;
	private String templateName;
	private String templateAddress;
	private int templateOrder;
	private String templatePreview;
	private int isValid;
	private String createUserCode;
	private String createTime;
	private String updateUserCode;
	private String updateTime;
	private String templateType;
	private String templateFilter;
	public void setTemplateId(int templateId){
	this.templateId=templateId;
	}
	public int getTemplateId(){
		return templateId;
	}
	public void setTemplateCode(String templateCode){
	this.templateCode=templateCode;
	}
	public String getTemplateCode(){
		return templateCode;
	}
	public void setTemplateName(String templateName){
	this.templateName=templateName;
	}
	public String getTemplateName(){
		return templateName;
	}
	public void setTemplateAddress(String templateAddress){
	this.templateAddress=templateAddress;
	}
	public String getTemplateAddress(){
		return templateAddress;
	}
	public void setTemplateOrder(int templateOrder){
	this.templateOrder=templateOrder;
	}
	public int getTemplateOrder(){
		return templateOrder;
	}
	public void setTemplatePreview(String templatePreview){
	this.templatePreview=templatePreview;
	}
	public String getTemplatePreview(){
		return templatePreview;
	}
	public void setIsValid(int isValid){
	this.isValid=isValid;
	}
	public int getIsValid(){
		return isValid;
	}
	public void setCreateUserCode(String createUserCode){
	this.createUserCode=createUserCode;
	}
	public String getCreateUserCode(){
		return createUserCode;
	}
	
	public void setUpdateUserCode(String updateUserCode){
	this.updateUserCode=updateUserCode;
	}
	public String getUpdateUserCode(){
		return updateUserCode;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setTemplateType(String templateType){
	this.templateType=templateType;
	}
	public String getTemplateType(){
		return templateType;
	}
	public void setTemplateFilter(String templateFilter){
	this.templateFilter=templateFilter;
	}
	public String getTemplateFilter(){
		return templateFilter;
	}
	public static TemplateEntity boToEntity(TemplateBo bo){
		TemplateEntity entity=new TemplateEntity();
		entity.setTemplateId(bo.getTemplateId());
		entity.setTemplateCode(bo.getTemplateCode());
		entity.setTemplateName(bo.getTemplateName());
		entity.setTemplateAddress(bo.getTemplateAddress());
		entity.setTemplateOrder(bo.getTemplateOrder());
		entity.setTemplatePreview(bo.getTemplatePreview());
		entity.setIsValid(bo.getIsValid());
		entity.setCreateUserCode(bo.getCreateUserCode());
		entity.setCreateTime(bo.getCreateTime());
		entity.setUpdateUserCode(bo.getUpdateUserCode());
		entity.setUpdateTime(bo.getUpdateTime());
		entity.setTemplateType(bo.getTemplateType());
		entity.setTemplateFilter(bo.getTemplateFilter());
		return entity;
	}
	public static TemplateBo entityToBo(TemplateEntity bo){
		TemplateBo entity=new TemplateBo();
		entity.setTemplateId(bo.getTemplateId());
		entity.setTemplateCode(bo.getTemplateCode());
		entity.setTemplateName(bo.getTemplateName());
		entity.setTemplateAddress(bo.getTemplateAddress());
		entity.setTemplateOrder(bo.getTemplateOrder());
		entity.setTemplatePreview(bo.getTemplatePreview());
		entity.setIsValid(bo.getIsValid());
		entity.setCreateUserCode(bo.getCreateUserCode());
		entity.setCreateTime(bo.getCreateTime());
		entity.setUpdateUserCode(bo.getUpdateUserCode());
		entity.setUpdateTime(bo.getUpdateTime());
		entity.setTemplateType(bo.getTemplateType());
		entity.setTemplateFilter(bo.getTemplateFilter());
		return entity;
	}
}

