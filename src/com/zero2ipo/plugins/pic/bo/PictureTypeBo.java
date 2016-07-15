package com.zero2ipo.plugins.pic.bo;

import com.zero2ipo.module.entity.picture.PictureTypeEntity;

   /**
    * cfjPicture 实体类
    * Mon Oct 13 11:44:45 GMT+08:00 2014 郑云飞
    */ 


public class PictureTypeBo{
	private String id;
	private String name;
	private String remark;
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
	
	public void setRemark(String remark){
	this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public static PictureTypeEntity boToEntity(PictureTypeBo bo){
		PictureTypeEntity entity=new PictureTypeEntity();
		entity.setId(bo.getId());
		entity.setName(bo.getName());
		entity.setRemark(bo.getRemark());
		return entity;
	}
	public static PictureTypeBo entityToBo(PictureTypeEntity bo){
		PictureTypeBo entity=new PictureTypeBo();
		entity.setId(bo.getId());
		entity.setName(bo.getName());
		entity.setRemark(bo.getRemark());
		return entity;
	}
}

