package com.zero2ipo.plugins.friendLink.bo;

import java.io.Serializable;

/**
 * @title :
 * @description :友情链接实体类
 * @author: zhengYunFei
 * @data: 2013-10-11
 */
public class FriendLinkInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; //友情链接主键
	private String typeId;//友情链接分类ID值
	private String fsiteName;//友情链接网站名称
	private String fsiteUrl;//网站的链接地址
	private String logoUrl;//网站LOGO图标地址
	private String validFlag;//友情链接是否有效的标识（01：是    02：否）
	private String addDate;//维护时间
	private String effDate;//结束时间
	private String userId;//操作人
	private String remark;//备注（网站的文字说明）
	//对应的链接分类
    private String typeName;//链接分类名称
    private String userName; //操作人名字
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getFsiteName() {
		return fsiteName;
	}
	public void setFsiteName(String fsiteName) {
		this.fsiteName = fsiteName;
	}
	public String getFsiteUrl() {
		return fsiteUrl;
	}
	public void setFsiteUrl(String fsiteUrl) {
		this.fsiteUrl = fsiteUrl;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
