/**
 * @(#)ICodeManage.java	07/03/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-03 
 */
package com.zero2ipo.plugins.code.biz.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.code.biz.ICodeManage;
import com.zero2ipo.plugins.code.bo.CodeInfo;
import com.zero2ipo.plugins.code.bo.CodeSort;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: 标准代码维护接口实现类
 * @description: 针对标准代码分类管理、标准代码项的一个维护管理接口的实现
 * @author： zhengYunFei
 * @date： 2013-07-03 10:10
 */
@Service("codeManage")
public class CodeManageImpl implements ICodeManage {
	
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	
	/**
     * @title: 获取代码分类信息树
     * @description: 获取代码分类信息树 
     * @param bo {codeSortType:(代码分类信息，非必须参数),codeSortName:(代码分类名称，非必须参数)}
     * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码分类列表
     */
	public List<CodeSort> findCodeSortTree(CodeSort bo){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			List lt  = baseDao.findForList("selectCodeSortTree", bo);
			return lt;
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "findCodeSortTree 获取代码分类信息树出错！", e);
		}
		return null;
	}

	/**
	 * @title 根据codeSortId获取代码分类信息
	 * @description:根据codeSortId获取代码分类信息
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return bo 代码分类信息实体
	 */
	public CodeSort findCodeSort(String codeType){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("codeType",codeType);
			return (CodeSort)baseDao.findForObject("selectCodeSortDetail",queryMap);
		} catch (BaseException e) {
		    e.printStackTrace();
			BaseLog.e(this.getClass(), "findCodeSort 获取代码分类信息出错！", e);
		}
		return null;
	}
	/**
	 * @title：查询代码分类信息
	 * @description:查询代码分类信息
	 * @param bo {codeSortId：} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码分类信息
	 */
	public List<CodeSort> findCodeSortList(CodeSort bo){

		return null;
	}
	/**
	 * @title： 保存代码分类信息实体
	 * @description:保存代码分类信息实体
	 * @param bo 代码分类信息实体
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public String  addCodeSort(CodeSort bo){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Object obj = baseDao.addObject("addPCodeSort", bo);
			if(null!=obj)
				return obj.toString();
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "addCodeSort 保存代码分类信息出错！", e);
		}
		return null;
	}
	/**
	 * @title 修改代码分类信息实体
	 * @description:修改代码分类信息实体
	 * @param bo 代码分类信息实体
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void updCodeSort(CodeSort bo){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updatePCodeSort", bo);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "updCodeSort 修改代码分类信息出错！", e);
		}
	}
	/**
	 * @title 根据代码分类信息ID删除记录
	             	同时删除子节点（分类信息对应的代码信息）
	 * @description:  删除代码分类信息记录同时删除子节点       	
	 * @param codeSortId
	 */
	public void delCodeSort(String codeSortId){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			SqlMapClient smt = baseDao.getSqlMapClient();
			try {
				/*开启事物 把代码分类信息和代码信息删除放在一个事物中处理，失败回滚*/
				smt.startTransaction();
				smt.delete("deletePCodeSortByCodeSortId", codeSortId);
				smt.delete("deletePCodeSort", codeSortId);
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
			BaseLog.e(this.getClass(), "delCodeSort 删除代码分类信息出错！", e);
		}
	}
	
	/**
	 * @title 根据codeId获取代码信息
	 * @description:根据codeId获取代码项信息
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return bo 代码项信息实体
	 */
	public CodeInfo findCodeInfo(Map<String,Object> map){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return (CodeInfo)baseDao.findForObject("selectPCodeInfoDetail", map);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "findCodeInfo 获取代码信息出错！", e);
		}
		return null;
		
	}
	
	/**
	 * @title 查询代码项信息列表
	 * @description:查询代码项信息列表
	 * @param bo {codeSortId} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码项信息列表
	 */
	public List<CodeInfo> findCodeInfoList(CodeInfo bo,int curNo,int curSize){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return baseDao.findForList("selectPCodesInfo", bo,curNo,curSize);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "findCodeInfoList 查询代码项信息列表出错！", e);
		}
		return null;
	}
	
	/**
	 * @title 查询代码项信息列表
	 * @description:查询代码项信息列表
	 * @param bo {codeSortId} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码项信息列表
	 */
	public List<CodeInfo> findCodeInfoList(CodeInfo bo){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return baseDao.findForList("selectPCodesInfo", bo);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "findCodeInfoList 查询代码项信息列表出错！", e);
		}
		return null;
	}
	
	/**
	 * @title 查询代码信息记录总数
	 * @description:查询代码信息记录总数
	 * @param bo {codeSortId } 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return count  代码项记录数量
	 */
	public int findCodeInfoCount(CodeInfo bo){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return (Integer)baseDao.findForObject("selectPCodesInfoCount", bo);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "findCodeInfoCount 查询代码信息记录总数出错！", e);
		}
		return 0;
	}
	
	/**
	 * @title： 保存代码项信息实体到数据库
	 * @description:保存代码项信息实体到数据库
	 * @param bo 代码项信息实体
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void addCodeInfo(CodeInfo bo){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.addObject("addPCodeInfo", bo);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "addCodeInfo 保存代码项信息出错！", e);
		}
	}
	/**
	 * @title 修改代码项信息实体到数据库
	 * @description:修改代码项信息实体到数据库
	 * @param bo 代码项信息实体
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void updCodeInfo(CodeInfo bo){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updatePCodeInfo", bo);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "updCodeInfo 修改代码项信息出错！", e);
		}
	}
	/**
	 * @title 根据代码项信息ID删除记录
	 * @description:根据代码项信息ID删除记录
	 * @param codeIds 代码信息ID字符串{codeIds = "codeID1,codeID2..."} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void delCodeInfo(String codeIds){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String,Object> map = new  HashMap<String,Object>();
			map.put("contentEditId", codeIds.split(","));
			baseDao.delObject("deletePCodeInfo", map);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "delCodeInfo 删除代码项信息出错！", e);
		}
	}
	/**
     * @title: 根据代码id或者代码名称查询字典
     * @description:根据代码id或者代码名称查询字典
     * @param : codeId  代码标志
     * @param :codeName 代码名称
     * @author: zhengYunFei
     * @date: 2014-09-15 10:20
     * @return: map
     */
	public Map<String,Object> getCodeValue(Map<String,Object> map){
		List<CodeInfo> list=new ArrayList<CodeInfo>();
		 Map<String,Object> returnMap=new HashMap<String, Object>();
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list=baseDao.findForList("getCodeValue", map);
			if(!StringUtil.isNullOrEmpty(list)){
				for (CodeInfo codeInfo : list) {
					returnMap.put(codeInfo.getCodeValue(), codeInfo.getCodeName());
				}
				
			}
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "delCodeInfo 删除代码项信息出错！", e);
		}
		return returnMap;
	}
	 
		/**
	     * @title: 查询分类名字是否已经存在
	     * @description: 查询分类名字是否已经存在
	     * @param : boolean
	     * @author: zhengYunFei
	     * @date: 2014-09-16 10:20
	     */
	    public int findCodeSortNameIsExist(String codeName){
			int count=0;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				 Map<String,Object> queryMap=new HashMap<String, Object>();
				 queryMap.put("codeSortName", codeName);
				 count=(Integer) baseDao.findForObject("findCodeSortNameIsExist", queryMap);
			} catch (BaseException e) {
				BaseLog.e(this.getClass(), "查询分类名字是否已经存在！", e);
			}
			return count;
		}
		 /**
	     *自动生成代码值
	     *@title
	     *@date 2010-9-16
	     *@author ZhengYunfei
	     * @return
	     */
		public String generateCodeValue(String codeSortId){
			int count=0;
			String result="";
			if(null==codeSortId){
				codeSortId="";
			}
			try {
				 baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				 Map<String,Object> map=new HashMap<String, Object>();
				 map.put("codeSortId", codeSortId);
				 count=(Integer) baseDao.findForObject("selectPCodesInfoCount", map);
				 if(count<10){
					 result=codeSortId+"0"+(count+1);
				 }else{
					 result=codeSortId+""+(count+1);
				 }
			} catch (BaseException e) {
				BaseLog.e(this.getClass(), "查询分类名字是否已经存在！", e);
			}
			return result;
		}
		/**
		 * 根据codeValue查询CodeName
		 *@title
		 *@date 2014-9-24
		 *@author ZhengYunfei
		 * @param codeValue
		 * @return
		 */
		public String findCodeNameByValue(String codeValue){
			String codeName="";
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("codeValue",codeValue);
				codeName= (String) baseDao.findForObject("findCodeNameByValue", map);
			} catch (BaseException e) {
				BaseLog.e(this.getClass(), "查询分类名字是否已经存在！", e);
			}
			return codeName;
		}

	@Override
	public CodeSort findCodeSortByMap(Map<String, Object> queryMap) {
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return (CodeSort)baseDao.findForObject("selectCodeSortDetail",queryMap);
		} catch (BaseException e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findCodeSort 获取代码分类信息出错！", e);
		}
		return null;
	}
	@Override
	public void addWxUser(Wxuser wxuser) {
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.addObject("addWx_user", wxuser);
		} catch (BaseException e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "addCodeInfo 保存代码项信息出错！", e);
		}

	}
	@Override
	public void DelWxUser(String userId) {
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String, Object> queryMap=new HashMap<String, Object>();
			if(StringUtil.isNullOrEmpty(userId)){
				queryMap.put("unionid", userId);
			}
			baseDao.delObject("delWxUser", queryMap);
		} catch (BaseException e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "addCodeInfo 保存代码项信息出错！", e);
		}

	}
	@Override
	public int findWxUserCount(Map<String, Object> map) {
		int count=0;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			count=(Integer) baseDao.findForObject("findWxUserCount", map);
		} catch (BaseException e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "查询分类名字是否已经存在！", e);
		}
		return count;
	}

	@Override
	public List<Wxuser> findWxUserList(Map<String, Object> map,
									   int i, int max) {
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return baseDao.findForList("findWx_userList", map,i,max);
		} catch (BaseException e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findCodeInfoList 查询代码项信息列表出错！", e);
		}
		return null;
	}
	@Override
	public List<Wxuser> findWxUserList(Map<String, Object> map) {
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			return baseDao.findForList("findWx_userList", map);
		} catch (BaseException e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findCodeInfoList 查询代码项信息列表出错！", e);
		}
		return null;
	}
}
