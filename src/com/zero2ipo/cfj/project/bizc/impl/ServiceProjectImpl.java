package com.zero2ipo.cfj.project.bizc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.cfj.project.bizc.IServiceProject;
import com.zero2ipo.cfj.project.bo.ServiceProject;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
@Service("serviceProject")
public class ServiceProjectImpl implements IServiceProject {
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	@Override
	public String add(ServiceProject bo) {
		String backInfo= "1";
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("addServiceProject", bo);
			backInfo= "1";
		}catch(Exception e){
			e.printStackTrace();
			backInfo= "0";
		    BaseLog.e(this.getClass(), "addOrder 添加联系人", e);
		    throw new BaseException("添加联系人出错！",e);
		}
		return backInfo;
	}

	@Override
	public String delete(String ids) {
		String backInfo = "0";
		try{
			Map map = new HashMap();
			map.put("id",ids.split(","));
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.delObject("delServiceProject", map);
			//删除成功
			backInfo = "1";
		}catch(Exception e){
			backInfo = "0";	//删除失败
		    BaseLog.e(this.getClass(), "delOrderById 删除联系人", e);
		    throw new BaseException("删除联系人出错！",e);
		}
		return backInfo;
	}

	@Override
	public List<ServiceProject> findAllList(Map<String, Object> queryMap, int skip,
			int max) {
		List<ServiceProject> orderInfoList = new ArrayList<ServiceProject>();
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		orderInfoList = (List<ServiceProject>)baseDao.findForList("findServiceProjectList", queryMap,skip,max);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "queryOrderInfoList 查询列表失败",e);
		}
		return orderInfoList;
	}

	@Override
	public ServiceProject findById(String id) {
		ServiceProject bo = null;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			 bo = (ServiceProject)baseDao.findForObject("findServiceProject", map);
		}catch(Exception e){
		    BaseLog.e(this.getClass(), "updInatitutionsInit 修改联系人前查询", e);
		    throw new BaseException("修改联系人前查询出错！",e);
		}
		return bo;
	}

	@Override
	public String update(ServiceProject bo) {
		SqlMapClient client =  baseDao.getSqlMapClient();
		String backInfo = "0";
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		
			client.update("updServiceProject", bo);
			backInfo = "1";
		}catch(Exception e){
			e.getStackTrace();
		    BaseLog.e(this.getClass(), "updOrder 修改联系人", e);
		    throw new BaseException("修改联系人出错！",e);
		}
		return backInfo;
	}

	@Override
	public int findAllListCount(Map<String, Object> map) {
		int count=0;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.findForObject("findServiceProjectCount", map);
			count=1;
		}catch(Exception e){
			e.getStackTrace();
		    BaseLog.e(this.getClass(), "updOrder 修改联系人", e);
		    throw new BaseException("修改联系人出错！",e);
		}
		return count;
	}

}
