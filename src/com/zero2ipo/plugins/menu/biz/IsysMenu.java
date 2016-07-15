/**
 * @(#)IsysMenu.java	07/04/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-04 
 */
package com.zero2ipo.plugins.menu.biz;

import java.util.List;

import com.zero2ipo.plugins.menu.bo.SysMenu;

/**
 * @title : 系统菜单接口
 * @author ： zhengYunFei
 * @date 2013-7-04
 * */
public interface IsysMenu {
 

	/**
	 * @title : 根据菜单级别查询该级别下的所有菜单
	 * @param：String menuCode
	 * @return ：List<SysMenu> 菜单集合
	 * @date 2013-7-04
	 * */
	public List<SysMenu> getSysMenu(String menuCode);
	
	/**
	 * @title : 获得所有菜单信息
	 * @param ： boolean flag 是否查询出已停用系统菜单
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return List<SysMenu> 菜单集合
	 * */
	public List<SysMenu> listSysMenu(boolean flag);
	
	/**
	 * @title : 修改菜单
	 * @param  SysMenu menu 菜单对象
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return void
	 * */
	public void updSysMenu(SysMenu menu);
	
	/**
	 * @title :修改菜单状态
	 * @param String menuId 系统菜单ID
	 * @param String isActive 状态标识
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return void
	 * */
	public void updSysMenuStatus(String menuId, String isActive);
	
	/**
	 * @title : 查看系统菜单信息
	 * @param : String menuId 菜单ID
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return : SysMenu 菜单对象
	 * */
	public SysMenu showSysMenu(String menuId);
	
	/**
	 * @title : 添加系统菜单
	 * @param ： SysMenu menu 菜单对象
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return void
	 * */
	public void addSysMenu(SysMenu menu);
	
	/**
	 * @title : 更改系统菜单
	 * @param ： SysMenu menu 菜单对象
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return void
	 * */
	public void updateSysMenu(SysMenu menu);
	
	
	/**
	 * @title : 添加角色菜单权限
	 * @param : String roleId 角色ID
	 * @param : String menuId 菜单ID(多条 xx,xx,xx)
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return : void
	 * */
	public void addRoleMenu(String roleId, String menuId);
	
	/**
	 * @title : 获得角色菜单权限
	 * @param : String roleId 角色ID
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return List<String> 菜单ID列表
	 * */
	public List<String> findRoleMenu(String roleId);
	
	/**
	 * @title : 获得角色菜单权限
	 * @param : List<String> roleList 角色列表
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return List<String> 菜单ID列表
	 * */
	public List<String> findRoleMenu(List<String> roleList);
	
	/**
	 * @title : 删除角色菜单权限
	 * @param : String roleId 角色ID
	 * @date ：2013-7-08
	 * @author ： zhengYunFei
	 * @return void
	 * */
	public void deleteRoleMenu(String roleId);
	/**
	 * 根据用户userId查询用户菜单权限
	 *@date 2014-9-29
	 *@author ZhengYunfei
	 * @param userId
	 * @return
	 */
	public List<String> findMenuListByUserId(String userId);
}
