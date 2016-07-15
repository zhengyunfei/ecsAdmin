package com.zero2ipo.cfj.project.bo;
public class ServiceProject{
	private String id;
	private String name;
	private float price;
	private float orignPrice;
	private String remark;
	private String isMain;
	
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
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
	public void setPrice(float price){
	this.price=price;
	}
	public float getPrice(){
		return price;
	}
	public void setOrignPrice(float orignPrice){
	this.orignPrice=orignPrice;
	}
	public float getOrignPrice(){
		return orignPrice;
	}
	public void setRemark(String remark){
	this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	
}

