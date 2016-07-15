/**
 * @(#)SysMenuImpl.java	07/04/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-04 
 */
package com.zero2ipo.plugins.menu.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.common.Regexs;
import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.plugins.menu.biz.IsysMenu;
import com.zero2ipo.plugins.menu.bo.SysMenu;
/**
 *  @title : 系统菜单接口实现类
 *  @author zhengYunFei
 *  @date: 2013-7-04
 * */
@Service("menu")
public class SysMenuImpl implements IsysMenu {

	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	
	
	/**
	 * @title : 根据菜单级别查询该级别下的所有菜单
	 * @param：String menuCode 菜单编码
	 * @date 2013-7-04
	 * @author zhengYunFei
	 * @return List<SysMenu> 菜单集合
	 * */
	public List<SysMenu> getSysMenu(String menuCode){
		List<SysMenu> listSysMenu = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			listSysMenu = baseDao.findForList("getSysMenu", menuCode);
		} catch (Exception e) {
			 BaseLog.e(this.getClass(), "getSysMenu 根据菜单级别查询该级别下的所有菜单有误", e);
			 throw new BaseException("根据菜单级别查询该级别下的所有菜单有误!",e);
		}
		return listSysMenu;
	}
	
	/**
	 * @title : 获得所有菜单信息
	 * @param boolean 是否显示无效菜单 (true 显示 false 不显示)
	 * @date 2013-7-08
	 * @author zhengYunFei
	 * @return List<SysMenu> 菜单集合
	 * */
	public List<SysMenu> listSysMenu(boolean flag){
		List<SysMenu> listSysMenu = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			if(flag){
				listSysMenu = baseDao.findForList("listSysMenu", null);
			}else{
				//查询所有(启用)菜单
				listSysMenu = baseDao.findForList("findSysMenu", null);
			}
		} catch (Exception e) {
			 BaseLog.e(this.getClass(), "listSysMenu 获得所有菜单信息有误", e);
			 throw new BaseException("获得所有菜单信息有误!",e);
		}
		return listSysMenu;
	}
	
	/**
	 * @title : 修改菜单
	 * @param  SysMenu menu 菜单对象
	 * @date 2013-7-08
	 * @author zhengYunFei
	 * @return void
	 * */
	public void updSysMenu(SysMenu menu){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updSysMenu", menu);
		} catch (Exception e) {
			 BaseLog.e(this.getClass(), "updSysMenu 修改菜单有误", e);
			 throw new BaseException("修改菜单有误!",e);
		}
	}
	
	/**
	 * @title :修改菜单状态
	 * @param String menuId 系统菜单ID
	 * @param String isActive 状态标识
	 * @date 2013-7-08
	 * @author zhengYunFei
	 * @return void
	 * */
	public void updSysMenuStatus(String menuId,String isActive){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("menuId", menuId);
			map.put("isActive", isActive);
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updMenuStatus", map);
		} catch (Exception e) {
			 BaseLog.e(this.getClass(), "updSysMenuStatus 修改菜单状态有误", e);
			 throw new BaseException("修改菜单状态有误!",e);
		}
	}
	
	/**
	 * @title : 查看系统菜单信息
	 * @param : String menuId 菜单ID
	 * @date ： 2013-7-08
	 * @author ：zhengYunFei
	 * @return : SysMenu 菜单对象
	 * */
	public SysMenu showSysMenu(String menuId){
		SysMenu sm = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			sm = (SysMenu)baseDao.findForObject("showSysMenu", menuId);
		} catch (Exception e) {
			 BaseLog.e(this.getClass(), "showSysMenu 查看系统菜单信息有误", e);
			 throw new BaseException("查看系统菜单信息有误!",e);
		}
		return sm;
	}
	
	/**
	 * @title : 添加系统菜单
	 * @param ： SysMenu menu 菜单对象
	 * @date ： 2013-7-08
	 * @author ：zhengYunFei
	 * @return ： void
	 * */
	public void addSysMenu(SysMenu menu){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			menu.setAddTime(DateUtil.getCurrentDate());
			menu.setMenuId(Long.toString(baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT)));
			menu.setMenuLevel("0");
			baseDao.addObject("addSysMenu", menu);
		} catch (Exception e) {
			e.printStackTrace();
			 BaseLog.e(this.getClass(), "addSysMenu 添加系统菜单有误", e);
			 throw new BaseException("添加系统菜单有误!",e);
		}
	}
	
	/**
	 * @title : 更改系统菜单
	 * @param ： SysMenu menu 菜单对象
	 * @date ： 2013-7-08
	 * @author ：zhengYunFei
	 * @return void
	 * */
	public void updateSysMenu(SysMenu menu){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updateSysMenu", menu);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "updateSysMenu更改系统菜单失败", e);
			 throw new BaseException("更改系统菜单有误!",e);
		}
	}
	
	/**
	 * @title : 添加角色菜单权限
	 * @param : String roleId
	 * @param : String menuId
	 * @date ： 2013-7-08
	 * @author ：zhengYunFei
	 * @return : void
	 * */
	public void addRoleMenu(String roleId,String menuId){
		SqlMapClient client =  baseDao.getSqlMapClient();
		String [] mId = menuId.split(",");
		try {
			client.startTransaction();//开启事物
			client.startBatch();//开启批处理模式
			
			//删除原有数据
			deleteRoleMenu(roleId);
			
			Map<String, Object> map = null;
			for(int i =0;i<mId.length;i++){
				map = new HashMap<String, Object>();
				map.put("roleId", roleId);
				map.put("menuId", mId[i]);
				map.put("id", baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT));
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				client.insert("addRoleMenu", map);
			}
	        client.executeBatch();//执行批处理模式
		} catch (Exception e) {
		    BaseLog.e(this.getClass(), "addRoleMenu:批量添加角色菜单权限有误", e);
		    throw new BaseException("批量添加角色菜单权限有误",e);
		} finally{
            try {
                client.commitTransaction();//提交事物
                client.endTransaction();//结束事物
            } catch (Exception e) {
                BaseLog.e(this.getClass(), "addRoleMenu:提交&结束事物异常!", e);
                throw new BaseException("提交&结束事物异常!",e);
            }
        }
	}
	
	/**
	 * @title : 删除角色菜单权限
	 * @param : String roleId 角色ID
	 * @date ： 2013-7-08
	 * @author ：zhengYunFei
	 * @return void
	 * */
	public void deleteRoleMenu(String roleId){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.delObject("deleteRoleMenu", roleId);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "deleteRoleMenu:删除角色菜单权限异常!", e);
			throw new BaseException("删除角色菜单权限异常!",e);
		}
	}
	/**
	 * @title : 获得角色菜单权限
	 * @param : String roleId 角色ID
	 * @date ： 2013-7-08
	 * @author ：zhengYunFei
	 * @return List<String> 菜单ID列表
	 * */
	public List<String> findRoleMenu(String roleId){
		List<String>  list = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("findRoleMenu", roleId);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findRoleMenu:获得角色菜单权限异常!", e);
			throw new BaseException("获得角色菜单权限异常!",e);
		}
		return list;
	}
	/**
	 * 根据用户userId查询用户菜单权限
	 *@date 2014-9-29
	 *@author ZhengYunfei
	 * @param userId
	 * @return
	 */
	public List<String> findMenuListByUserId(String userId){
		List<String>  list = new ArrayList<String>();
		List<SysMenu>  menuList = new ArrayList<SysMenu>();
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			menuList = baseDao.findForList("findMenuListByUserId", userId);
			for(int index=0;index<menuList.size();index++){
				list.add(Regexs.splitUrl(menuList.get(index).getPathCode()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findMenuListByUserId:获得角色菜单权限异常!", e);
			throw new BaseException("根据用户userId查询用户菜单权限异常!",e);
		}
		return list;
	}
	/**
	 * @title : 获得角色菜单权限
	 * @param : List<String> roleList 角色列表
	 * @date ： 2013-7-08
	 * @author ：zhengYunFei
	 * @return ： List<String> 菜单ID列表
	 * */
	public List<String> findRoleMenu(List<String> roleList){
		List<String> list = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleList", roleList);
			list = baseDao.findForList("findManyRoleMenu", map);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findRoleMenu:获得多角色菜单权限异常!", e);
			throw new BaseException("获得角色菜单权限异常!",e);
		}
		return list;
	}
}
