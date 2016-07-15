package com.zero2ipo.plugins.area.bo;

/**
    * cfjProvCityAreaStreet 实体类
    * Mon Jan 26 11:42:23 GMT+08:00 2015 zhengyunfei
    */ 
public class AreaBo{
	private String id;
	private String code;
	private String parentId;
	private String text;
	private byte level;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return this.code;
	}
	public void setCode(String code){
	this.code=code;
	}
	public String getCode(){
		return code;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setLevel(byte level){
	this.level=level;
	}
	public byte getLevel(){
		return level;
	}

}

