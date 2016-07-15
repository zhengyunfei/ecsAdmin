package com.zero2ipo.plugins.friendLink.bo;

import java.io.Serializable;
import java.util.List;



/**
 * @title :
 * @description :友情链接分类信息
 * @author: zhengYunFei
 * @data: 2013-10-11
 */
public class FriendLink implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String typeId; //分类主键
	private String typeName;//友情链接分类名称
	private String typeDesc;//类型描述
	private List<FriendLinkInfo> friendLinkInfoList;//友情链接集合
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public List<FriendLinkInfo> getFriendLinkInfoList() {
		return friendLinkInfoList;
	}
	public void setFriendLinkInfoList(List<FriendLinkInfo> friendLinkInfoList) {
		this.friendLinkInfoList = friendLinkInfoList;
	}
}
