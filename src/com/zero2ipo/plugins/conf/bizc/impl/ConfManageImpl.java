/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-6-1 
 */
package com.zero2ipo.plugins.conf.bizc.impl;

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
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.plugins.conf.bizc.IConfManage;
import com.zero2ipo.plugins.conf.bo.ConfSort;
import com.zero2ipo.plugins.conf.bo.ConfValue;

/**
 * @title :项目配置部署
 * @description :项目配置部署
 * @author: zhengYunFei
 * @data: 2013-7-11
 */
@Service("confManage")
public class ConfManageImpl implements IConfManage{
	
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;

	/**
	 * @title 查询所有项目配置部署
	 * @description:查询所有项目配置部署 
	 * @param map 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return list <项目配置分类实体>
	 */
	public List<ConfSort> findConfSort(Map<String, String> map) {
		List<ConfSort> list = new ArrayList<ConfSort>();
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("querySysConfSortList",map);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findCacheList ", e);
			throw new BaseException("查询项目配置信息列表出错！");
		}
		return list;
	}
	/**
	 * @title 根据sortCode获取项目配置分类信息
	 * @description:根据sortCode获取项目配置分类信息
	 * @param sortCode 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return bo 项目配置分类实体
	 */
	public ConfSort findConfSort(String sortCode){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return (ConfSort)baseDao.findForObject("queryConfSort", sortCode);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "findCodeSort 获取代码分类信息出错！", e);
		}
		return null;
	}
	/**
	 * @description: 根据项目配置参数编码查询key-value信息
	 * @param map  查询条件
	 * @param max 
	 * @param skip 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return List<ConfValue> 项目配置参数实体List
	 */
	public List<ConfValue> findConfValue(Map<String, String> map,int skip,int max) {
		List<ConfValue> list = new ArrayList<ConfValue>();
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("querySysConfValueList", map,skip,max);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "querySysConfValueList ", e);
			throw new BaseException("查询项目配置信息列表出错！");
		}
		return list;
	}
	/**
	 * @title 保存配置部署信息
	 * @description:保存配置部署信息
	 * @param bo 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return sortCode
	 */
	public String addSortConf(ConfSort bo) {
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Object obj  = baseDao.addObject("addConfSort", bo);
			if(obj!=null && !"".equals(obj)){
				return obj.toString();
			}
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "addSortConf ", e);
			throw new BaseException("保存配置部署信息出错！");
		}
		return null;
	}

	/**
	 * @title 修改项目配置部署信息
	 * @description:修改项目配置部署信息
	 * @param bo 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return void
	 */
	public void updConfSort(ConfSort bo){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updateConfSort", bo);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "addSortValue ", e);
			throw new BaseException("保存项目配置部署明细信息出错！");
		}
	}

	/**
	 * @title 保存项目配置部署明细信息
	 * @description:保存项目配置部署明细信息
	 * @param bo 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return void
	 */
	public void addConfValue(ConfValue bo) {
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.addObject("addConfValue", bo);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "addSortValue ", e);
			throw new BaseException("保存项目配置部署明细信息出错！");
		}
	}
	
	/**
	 * @title 修改项目配置部署明细信息
	 * @description:修改项目配置部署明细信息
	 * @param bo 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return void
	 */
	public void updConfValue(ConfValue bo) {
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updateConfValue", bo);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "updateConfValue ", e);
			throw new BaseException("保存项目配置部署明细信息出错！");
		}
	}
	/**
	 * @title 根据valueID获取项目配置参数信息
	 * @description:根据valueID获取项目配置参数信息
	 * @param valueID 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return bo 项目配置参数信息分类实体
	 */
	public ConfValue findConfValue(String valueId){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String, Object> queryMap=new HashMap<String, Object>();
			queryMap.put("value", valueId);
			return (ConfValue)baseDao.findForObject("queryConfValue", queryMap);
		} catch (BaseException e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findConfValue 项目配置参数信息出错！", e);
		}
		return null;
	}
	
	/**
	 * @description: 根据项目配置参数编码查询配置部署信息明细总记录数
	 * @param map 查询条件
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return：int
	 */
	public int findConfValueCount(Map<String, String> map) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("querySysConfValueCount", map);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "querySysConfValueCount ", e);
			throw new BaseException("查询项目配置信息列表出错！");
		}
		return list.get(0);
	}

	/**
	 * @description: 根据自定义编码查询项目配置部署信息总记录数
	 * @param map 查询条件
	 * @return：
	 */
	public int findConfSortCount(Map<String, String> map) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("querySysConfSortCount", map);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "querySysConfSortCount ", e);
			throw new BaseException("查询项目配置信息列表出错！");
		}
		return list.get(0);
	}

	/**
	 * @title 根据项目配置分类信息ID删除记录
	             	同时删除子节点（分类信息对应的代码信息）
	 * @description:  删除项目配置分类信息记录同时删除子节点       	
	 * @param sortCode
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 */
	public void delConfSort(String sortCode){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			SqlMapClient smt = baseDao.getSqlMapClient();
			try {
				/*开启事物 把项目配置分类信息和参数信息删除放在一个事物中处理，失败回滚*/
				smt.startTransaction();
				smt.delete("deleteConfValueByConfSortCode", sortCode);
				smt.delete("deleteConfSort", sortCode);
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
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "delConfSort 删除项目配置分类信息出错！", e);
		}
	}
	/**
	 * @title 根据参数项信息ID删除记录
	 * @description:根据参数项信息ID删除记录
	 * @param valueIds 参数信息ID字符串{valueIds = "valueId,valueId..."} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void delConfValue(String valueIds){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String,Object> map = new  HashMap<String,Object>();
			map.put("contentEditId", valueIds.split(","));
			baseDao.delObject("deleteConfValue", map);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "delConfValue 删除参数项信息出错！", e);
		}
	}
	public ConfValue findConfValueByMap(Map<String, Object> m) {
		
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return (ConfValue)baseDao.findForObject("queryConfValue", m);
		} catch (BaseException e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findConfValue 项目配置参数信息出错！", e);
		}
		return null;
	}

}
