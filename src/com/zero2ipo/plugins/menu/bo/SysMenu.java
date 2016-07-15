/**
 * @(#)SysMenu.java	07/05/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-05 
 */
package com.zero2ipo.plugins.menu.bo;

import java.io.Serializable;
import java.util.List;

import com.zero2ipo.framework.util.StringUtil;

/**
 *  @title : 系统菜单实体类
 *  @param : 菜单相关操作的一些基本信息，对应数据库中的S9_SYS_MENU表。
 *  @author : zhengYunFei
 *  @date : 2013-7-04
 * */
public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String menuId;   		//本实体记录的唯一标识
	private String menuCode; 		//菜单编码
	private String menuName;		//菜单名称
	private String menuTitle;		//菜单标题
	private String pmenuCode;		//直接上级菜单的菜单标识
	private String pMenuName;       //直接上级菜单的菜单名称
	private String menuFolderFlag;	//是否菜单夹，是：菜单夹；否：菜单项
	private String handleSort;		//菜单对应的资源的类别。01 组件 02 HTML 03 报表
	private String handleRepresent;	//记录菜单链接资源的名称
	private String sortNo;			//在同级中的排列顺序的序号，用自然数标识，如，1、2、3
	private String menuImg;			//菜单图标
	private String menuLevel;		//菜单级别
	private String pathCode;		//菜单路径
	private String isActive;		//是否可用标识 0：无效 1：有效
	private String addUser;			//菜单添加人员
	private String addTime;			//菜单添加时间
	private String remark;			//菜单的备注
	private List<SysMenu> children; //二级菜单
	
	public String getMenuId() {
		return menuId.trim();
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuCode() {
		return menuCode.trim();
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getPmenuCode() {
		return pmenuCode;
	}
	public void setPmenuCode(String pmenuCode) {
		this.pmenuCode = pmenuCode;
	}
	public String getpMenuName() {
		return pMenuName;
	}
	public void setpMenuName(String pMenuName) {
		this.pMenuName = pMenuName;
	}
	public String getMenuFolderFlag() {
		return menuFolderFlag;
	}
	public void setMenuFolderFlag(String menuFolderFlag) {
		this.menuFolderFlag = menuFolderFlag;
	}
	public String getHandleSort() {
		return handleSort;
	}
	public void setHandleSort(String handleSort) {
		this.handleSort = handleSort;
	}
	public String getHandleRepresent() {
		return handleRepresent;
	}
	public void setHandleRepresent(String handleRepresent) {
		this.handleRepresent = handleRepresent;
	}
	public String getSortNo() {
		if(StringUtil.isNullOrEmpty(sortNo)){
			sortNo="1";
		}
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getPathCode() {
		return pathCode;
	}
	public void setPathCode(String pathCode) {
		this.pathCode = pathCode;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<SysMenu> getChildren() {
		return children;
	}
	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}
	public String getMenuImg() {
		return menuImg;
	}
	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}
}
