/**
 * @(#)SysMenuCtrl.java	07/05/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-05 
 */
package com.zero2ipo.plugins.menu.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.menu.biz.IsysMenu;
import com.zero2ipo.plugins.menu.bo.SysMenu;
import com.zero2ipo.plugins.user.bo.User;

/**
 * @title : 系统菜单
 * @author : zhengYunFei
 * @date : 2013-7-05
 * */
@Controller
@RequestMapping("/menu")
public class SysMenuCtrl extends BaseCtrl{

	@Autowired @Qualifier("menu")
	private IsysMenu menu;
	@RequestMapping("init.shtml")
	public ModelAndView init(){
		ModelAndView mv=new ModelAndView("/s9/menu/menu.html");
		return mv;
	}
	/**
	 * @title : 根据父节点查询对应菜单
	 * @param : String pid 父节点
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return Map<String, Object>
	 * */
	@RequestMapping("getSysMenu.shtml")
	@ResponseBody
	public Map<String, Object> getSysMenu(String sid){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			if("".equals(sid)|| sid==null){
				sid = GlobalConstant.TOP_SYSMENU_CODE;
			}
			List<SysMenu> listSysMenu = menu.getSysMenu(sid);
			jsonMap.put("sysMenu", listSysMenu);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "getSysMenu 根据父节点查询对应菜单有误", e);
		}
		return jsonMap;
	}
	
	/**
	 * @title : 显示系统所有菜单信息
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return Map<String, Object>
	 * */
	@RequestMapping("listSysMenu.shtml")
	@ResponseBody
	public Map<String ,Object> listSysMenu(){
		Map<String ,Object> jsonMap = new HashMap<String, Object>();
		List<SysMenu> topMenu = new ArrayList<SysMenu>();
		List<SysMenu> twoMenu = new ArrayList<SysMenu>();
		try {
			//获得系统所有菜单
			List<SysMenu> listSysMenu = menu.listSysMenu(true);
			if(listSysMenu!=null && listSysMenu.size()!=0){
				for(SysMenu menu:listSysMenu){
					if(GlobalConstant.TOP_SYSMENU_CODE.equals(menu.getPmenuCode())){
						//获得顶级菜单
						topMenu.add(menu);
					}else{
						//获得二级菜单
						twoMenu.add(menu);
					}
				}
				List<SysMenu> twoList = null;
				boolean flag;
				for(SysMenu topM:topMenu){
					twoList = new ArrayList<SysMenu>();
					flag = false;
					for(SysMenu twoM:twoMenu){
						if(topM.getMenuCode().equals(twoM.getPmenuCode())){
							twoList.add(twoM);
							flag = true;
						}
					}
					if(flag){
						topM.setChildren(twoList);
					}
				}
			}
			jsonMap.put("Rows", topMenu);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "listSysMenu 显示系统所有菜单信息有误", e);
		}
		return jsonMap;
	}
	
	/**
	 * @title : 更改系统菜单
	 * @param SysMenu menu 菜单信息
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return Map<String, Object>
	 * */
	@RequestMapping("updSysMenu.shtml")
	@ResponseBody
	public Map<String ,Object> updSysMenu(SysMenu bo){
		Map<String ,Object> jsonMap = new HashMap<String, Object>();
		try {
			menu.updSysMenu(bo);
			jsonMap.put("message", "success");
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "updSysMenu 更改系统菜单有误", e);
			jsonMap.put("message", "error");
		}
		return jsonMap;
	}
	
	/**
	 * @title : 修改系统菜单状态
	 * @param : String menuId 菜单ID
	 * @param : String isActive 菜单状态
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return Map<String, Object>
	 * */
	@RequestMapping("updSysMenuStatus.shtml")
	@ResponseBody
	public Map<String, Object> updSysMenuStatus(String menuId,String isActive){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			menu.updSysMenuStatus(menuId, isActive);
			jsonMap.put("message", "success");
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "updSysMenuStatus 修改系统菜单状态有误", e);
			jsonMap.put("message", "error");
		}
		return jsonMap;
	}
	
	/**
	 * @title : 查看系统菜单信息
	 * @param : String menuId 菜单ID
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return ModelAndView
	 * */
	@RequestMapping("showSysMenu.shtml")
	public ModelAndView showSysMenu(String menuId){
		ModelAndView mv = new ModelAndView();
		try {
			SysMenu sm = menu.showSysMenu(menuId);
			mv.addObject("sysMenu", sm);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "showSysMenu 查看系统菜单信息有误", e);
		}
		mv.setViewName("/s9/menu/menu_find.jsp");
		return mv;
	}
	/**
	 * @title : 新增菜单页面初始化
	 * @param : String menuId 菜单ID
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return ModelAndView
	 * */
	@RequestMapping("addSysMenuInit.shtml")
	public ModelAndView addSysMenuInit(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/s9/menu/menu_add.jsp");
		return mv;
	}
	/**
	 * @title : 新增菜单页面初始化
	 * @param : String menuId 菜单ID
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return ModelAndView
	 * */
	@RequestMapping("sysMenuImg.shtml")
	public ModelAndView sysMenuImg(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/s9/menu/menu_img.html");
		return mv;
	}
	/**
	 * @title : 保存系统菜单
	 * @param ： SysMenu menu 菜单对象
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return ModelAndView
	 * */
	@RequestMapping("saveSysMenu.shtml")
	public ModelAndView saveSysMenu(SysMenu bo){
		ModelAndView mv = new ModelAndView();
		try {
			if(bo.getMenuId()==null || "".equals(bo.getMenuId())){
				
			}else{
				menu.updateSysMenu(bo);
			}
			mv.addObject("message", "success");
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "saveSysMenu 保存系统菜单有误", e);
			mv.addObject("message", "error");
		}
		mv.setViewName("/s9/menu/menu_find.jsp");
		return mv;
	}
	
	/**
	 * @title : 添加系统菜单
	 * @param : SysMenu menu 菜单对象
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return ModelAndView
	 * */
	@RequestMapping("addSysMenu.shtml")
	public ModelAndView addSysMenu(SysMenu bo){
		ModelAndView mv = new ModelAndView();
		try {
			User user = (User)session.getAttribute("user");
			if(bo.getPmenuCode().equals(GlobalConstant.TOP_SYSMENU_CODE)){
				bo.setMenuFolderFlag(GlobalConstant.menu_Folder_Flag); 	//菜单夹
			}else{
				bo.setMenuFolderFlag(GlobalConstant.menu_Option_Flag); 	//菜单项
			}
			if(StringUtil.isNullOrEmpty(user)){
				bo.setAddUser(user.getUserName());   //添加用户
			}
			menu.addSysMenu(bo);
			mv.addObject("message", "success");
		} catch (Exception e) {
			mv.addObject("message", "error");
			BaseLog.e(this.getClass(), "addSysMenu 添加系统菜单有误", e);
		}
		mv.setViewName("/s9/menu/menu_add.jsp");
		return mv;
	}                  
	
	/**
	 * @title : 角色菜单管理
	 * @param ： String roleId  角色ID
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return Map<String, Object>
	 * */
	@RequestMapping("findRoleMenu.shtml")
	@ResponseBody
	public Map<String, Object> findRoleMenu(String roleId){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<SysMenu> topMenu = new ArrayList<SysMenu>();
		List<SysMenu> twoMenu = new ArrayList<SysMenu>();
		try {
			//获得系统所有菜单
			List<SysMenu> listSysMenu = menu.listSysMenu(false);
			if(listSysMenu!=null && listSysMenu.size()!=0){
				for(SysMenu menu:listSysMenu){
					if(GlobalConstant.TOP_SYSMENU_CODE.equals(menu.getPmenuCode()) && (GlobalConstant.menu_Folder_Flag).equals(menu.getMenuFolderFlag())){
						//获得顶级菜单
						topMenu.add(menu);
					}else{
						//获得二级菜单
						twoMenu.add(menu);
					}
				}
			}
			//获得当前角色已存在的菜单权限
			List<String> list = menu.findRoleMenu(roleId);
			jsonMap.put("topMenu", topMenu);
			jsonMap.put("twoMenu", twoMenu);
			jsonMap.put("roleMenu", list);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findRoleMenu 角色菜单管理有误", e);
		}
		return jsonMap;
	}
	
	/**
	 * @title : 添加角色权限
	 * @param String roleId 角色ID
	 * @param String menuId 菜单ID
	 * @author : zhengYunFei
	 * @date : 2013-08-07
	 * @return Map<String, Object>
	 * */
	@RequestMapping("addRoleMenu.shtml")
	@ResponseBody
	public Map<String, Object> addRoleMenu(String roleId,String menuId){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			menu.addRoleMenu(roleId, menuId);
			jsonMap.put("message", "success");
		} catch (Exception e) {
			jsonMap.put("message", "error");
			BaseLog.e(this.getClass(), "addRoleMenu 添加角色权限有误", e);
		}
		return jsonMap;
	}
}
