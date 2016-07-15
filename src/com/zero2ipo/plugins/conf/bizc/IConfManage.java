/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-6-1 
 */
package com.zero2ipo.plugins.conf.bizc;

import java.util.List;
import java.util.Map;

import com.zero2ipo.plugins.conf.bo.ConfSort;
import com.zero2ipo.plugins.conf.bo.ConfValue;

/**
 * @title :项目配置部署
 * @description :项目配置部署interface类
 * @author: zhengYunFei
 * @data: 2013-7-11
 */
public interface IConfManage{
	
	/**
	 * @title 查询所有项目配置部署
	 * @description:查询所有项目配置部署 
	 * @param map 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return list <项目配置分类实体>
	 */
	public List<ConfSort> findConfSort(Map<String, String> map);
	
	/**
	 * @description: 查询项目配置部署信息总记录数
	 * @param map 查询条件
	 * @return：
	 */
	public int findConfSortCount(Map<String, String> map);
	
	/**
	 * @title 根据sortCode获取项目配置分类信息
	 * @description:根据sortCode获取项目配置分类信息
	 * @param sortCode 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return bo 项目配置分类实体
	 */
	public ConfSort findConfSort(String sortCode);
	
	/**
	 * @title 保存配置部署信息
	 * @description:保存配置部署信息
	 * @param bo 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return sortCode
	 */
	public String addSortConf(ConfSort bo);
	
	/**
	 * @title 修改项目配置部署信息
	 * @description:修改项目配置部署信息
	 * @param bo 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return void
	 */
	public void updConfSort(ConfSort bo);
	
	/**
	 * @title 保存项目配置部署明细信息
	 * @description:保存项目配置部署明细信息
	 * @param bo 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return void
	 */
	public void addConfValue(ConfValue bo);

	/**
	 * @title 修改项目配置部署明细信息
	 * @description:修改项目配置部署明细信息
	 * @param bo 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return void
	 */
	public void updConfValue(ConfValue bo);
	
	/**
	 * @description: 根据项目配置参数编码查询key-value信息
	 * @param map 查询条件
	 * @param max 
	 * @param skip 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return List<ConfValue> 项目配置参数实体List
	 */
	public List<ConfValue> findConfValue(Map<String, String> map, int skip, int max);
	
	/**
	 * @description: 根据项目配置参数编码查询配置部署信息明细总记录数
	 * @param map 查询条件
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return：int
	 */
	public int findConfValueCount(Map<String, String> map);
	
	/**
	 * @title 根据项目配置分类信息ID删除记录
	             	同时删除子节点（分类信息对应的代码信息）
	 * @description:  删除项目配置分类信息记录同时删除子节点       	
	 * @param sortCode
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 */
	public void delConfSort(String sortCode);
	/**
	 * @title 根据参数项信息ID删除记录
	 * @description:根据参数项信息ID删除记录
	 * @param valueIds 参数信息ID字符串{valueIds = "valueId,valueId..."} 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 */
	public void delConfValue(String valueIds);
	/**
	 * @title 根据valueID获取项目配置参数信息
	 * @description:根据valueID获取项目配置参数信息
	 * @param valueID 
	 * @author: zhengYunFei
     * @date: 2013-07-15 10:20
	 * @return bo 项目配置参数信息分类实体
	 */
	public ConfValue findConfValue(String valueId);

	public ConfValue findConfValueByMap(Map<String, Object> m);

}
