package com.zero2ipo.plugins.pic.bo;

import com.zero2ipo.module.entity.picture.PictureEntity;

   /**
    * cfjPicture 实体类
    * Mon Oct 13 11:44:45 GMT+08:00 2014 郑云飞
    */ 


public class PictureBo{
	private String pid;
	private String id;
	private String name;
	private String url;
	private String attachmentUrl;
	private String remark;
	private String tum;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setUrl(String url){
	this.url=url;
	}
	public String getUrl(){
		return url;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	
	public String getTum() {
		return tum;
	}
	public void setTum(String tum) {
		this.tum = tum;
	}
	public void setRemark(String remark){
	this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public static PictureEntity boToEntity(PictureBo bo){
		PictureEntity entity=new PictureEntity();
		entity.setId(bo.getId());
		entity.setPid(bo.getPid());
		entity.setName(bo.getName());
		entity.setUrl(bo.getUrl());
		entity.setAttachmentUrl(bo.getAttachmentUrl());
		entity.setRemark(bo.getRemark());
		entity.setTum(bo.getTum());
		return entity;
	}
	public static PictureBo entityToBo(PictureEntity bo){
		PictureBo entity=new PictureBo();
		entity.setId(bo.getId());
		entity.setName(bo.getName());
		entity.setPid(bo.getPid());
		entity.setUrl(bo.getUrl());
		entity.setAttachmentUrl(bo.getAttachmentUrl());
		entity.setRemark(bo.getRemark());
		entity.setTum(bo.getTum());
		return entity;
	}
}

