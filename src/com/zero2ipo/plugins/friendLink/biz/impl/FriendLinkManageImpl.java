package com.zero2ipo.plugins.friendLink.biz.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.plugins.friendLink.biz.IFriendLinkManage;
import com.zero2ipo.plugins.friendLink.bo.FriendLink;
import com.zero2ipo.plugins.friendLink.bo.FriendLinkInfo;

/**
 * @title :
 * @description :友情链接管理实现类
 * @author: zhengYunFei
 * @date: 2013-10-15 16:53
 */
@Service("friendLinkManage")
public class FriendLinkManageImpl implements IFriendLinkManage {
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	/**
	 * @description: 查询友情链接列表
	 * @param friendLinkType：
	 * @param  skip：页码
	 * @param  max ：最大值
	 * @return：友情链接列表
	 */
	public List<FriendLink> queryFriendLinkGroupList(String typeName, int skip, int max){
		List<FriendLink> friendLinkList = new ArrayList<FriendLink>();
		try{
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		friendLinkList = (List<FriendLink>)baseDao.findForList("queryFriendLinkGroupList", typeName,skip,max);
		}catch(Exception e){
			BaseLog.e(this.getClass(), "queryFriendLinkTypeList 查询链接分类集合失败",e);
		}
		return friendLinkList;
	}
    /**
	 * @description:根据链接分类名称和链接分类id查询友情链接个数
	 * @param map：该参数包括友情链接类型的typeid和友情类型的name值
	 */
	public Integer queryFriendLinkGroupCount(String typeName){
		Integer friendLinkGroupCount = 0;
		try{
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		friendLinkGroupCount = (Integer)baseDao.findForObject("queryFriendLinkGroupCount", typeName);
		}catch(Exception e){
			BaseLog.e(this.getClass(), "queryWFriendLinkTypeNameCount 根据链接分类名称和链接分类id查询链接分类名称条数失败", e);
		}
		return friendLinkGroupCount;
	}
	/**
	 * @description: 添加友情链接分组信息
	 * @param friendLink
	 * @return：flag
	 */
	public Boolean addFriendLinkGroup(FriendLink friendLink){
		Boolean flag = true;
		try {
			String serialNo = String.valueOf(baseDao.getSerialNo("SEQ_SYS_SUPPORT"));
			friendLink.setTypeId(serialNo);
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		baseDao.addObject("saveFriendLinkGroup", friendLink);
		} catch (Exception e) {
			flag = false;
			BaseLog.e(this.getClass(), "addFriendLinkInfo 保存友情链接信息失败", e);
		}
		return flag;
	}
	/**
	 * @description: 更新友情链接分组信息
	 * @param friendLink
	 * @return：flag
	 */
	public Boolean updFriendLinkGroup(FriendLink friendLink){
		Boolean flag = true;
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
    		baseDao.updObject("updFriendLinkGroup", friendLink);
		} catch (Exception e) {
			flag = false;
			BaseLog.e(this.getClass(), "addFriendLinkInfo 保存友情链接信息失败", e);
		}
		return flag;
	}
	/**
	 * @description: 删除友情链接分组信息
	 * @param typeId
	 * @return：flag
	 */
	public Boolean delFriendLinkGroup(String typeIds){
		Boolean flag = true;
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String,Object> map = new  HashMap<String,Object>();
			map.put("typeId", typeIds.split(","));
			SqlMapClient smt = baseDao.getSqlMapClient();
			try {
				/*开启事物 把代码分类信息和代码信息删除放在一个事物中处理，失败回滚*/
				smt.startTransaction();
				baseDao.delObject("deleteFriendLinkGroup", map);
	    		baseDao.delObject("deleteFriendLinkInfoByTypeId", map);
				/*提交事物*/
				smt.commitTransaction();
			} catch (SQLException e) {
				 e.printStackTrace();
			}finally{
				try {
					smt.endTransaction();
				} catch (SQLException e) {
				}
			}
//    		baseDao.delObject("deleteFriendLinkGroup", typeIds);
		} catch (Exception e) {
			flag = false;
			BaseLog.e(this.getClass(), "addFriendLinkInfo 保存友情链接信息失败", e);
		}
		return flag;
	}
	/**
	 * @description: 查询友情链接分组列表
	 * @param friendLinkType：
	 * @param  skip：页码
	 * @param  max ：最大值
	 * @return：友情链接列表
	 */
	public List<FriendLinkInfo> queryFriendLinkInfoList(String typeId, int skip, int max){
		List<FriendLinkInfo> friendLinkInfoList = new ArrayList<FriendLinkInfo>();
		try{
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		friendLinkInfoList = (List<FriendLinkInfo>)baseDao.findForList("queryFriendLinkInfoList", typeId,skip,max);
		}catch(Exception e){
			BaseLog.e(this.getClass(), "queryFriendLinkTypeList 查询链接分类集合失败",e);
		}
		return friendLinkInfoList;
	}
    /**
	 * @description:根据链接分类名称和链接分类id查询友情链接个数
	 * @param map：该参数包括友情链接类型的typeid和友情类型的name值
	 */
	public Integer queryFriendLinkInfoCount(String typeId){
		Integer friendLinkInfoCount = 0;
		try{
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		friendLinkInfoCount = (Integer)baseDao.findForObject("queryFriendLinkInfoCount", typeId);
		}catch(Exception e){
			BaseLog.e(this.getClass(), "queryWFriendLinkTypeNameCount 根据链接分类名称和链接分类id查询链接分类名称条数失败", e);
		}
		return friendLinkInfoCount;
	}
	/**
	 * @description: 查询友情链接信息
	 * @param typeId
	 * @return：friendLink
	 */
	public FriendLinkInfo queryFriendLinkInfo(String id){
		FriendLinkInfo friendLinkInfo = new FriendLinkInfo();
		try{
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		friendLinkInfo = (FriendLinkInfo)baseDao.findForObject("queryFriendLinkInfo", id);
		}catch(Exception e){
			BaseLog.e(this.getClass(), "queryFriendLinkTypeList 查询链接分类集合失败",e);
		}
		return friendLinkInfo;
	}
	/**
	 * @description: 添加友情链接信息
	 * @param friendLinkInfo
	 * @return：flag
	 */
	public Boolean addFriendLinkInfo(FriendLinkInfo friendLinkInfo){
		Boolean flag = true;
		try {
			String serialNo = String.valueOf(baseDao.getSerialNo("SEQ_SYS_SUPPORT"));
			friendLinkInfo.setId(serialNo);
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		baseDao.addObject("saveFriendLinkInfo", friendLinkInfo);
		} catch (Exception e) {
			flag = false;
			BaseLog.e(this.getClass(), "addFriendLinkInfo 保存友情链接信息失败", e);
		}
		return flag;
	}
	/**
	 * @description: 更新友情链接信息
	 * @param friendLinkInfo
	 * @return：flag
	 */
	public Boolean updFriendLinkInfo(FriendLinkInfo friendLinkInfo){
		Boolean flag = true;
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
    		baseDao.updObject("updFriendLinkInfo", friendLinkInfo);
		} catch (Exception e) {
			flag = false;
			BaseLog.e(this.getClass(), "addFriendLinkInfo 保存友情链接信息失败", e);
		}
		return flag;
	}
	/**
	 * @description: 删除友情链接信息
	 * @param id
	 * @return：flag
	 */
	public Boolean delFriendLinkInfo(String ids){
		Boolean flag = true;
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
			Map<String,Object> map = new  HashMap<String,Object>();
			map.put("id", ids.split(","));
    		baseDao.delObject("deleteFriendLinkInfo", map);
		} catch (Exception e) {
			flag = false;
			BaseLog.e(this.getClass(), "addFriendLinkInfo 保存友情链接信息失败", e);
		}
		return flag;
	}
}