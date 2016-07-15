package com.zero2ipo.plugins.area.bizc.impl;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.plugins.area.bizc.IArea;
import com.zero2ipo.plugins.area.bo.AreaBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
   /**
    * cfjProvCityAreaStreet 实体类
    * Mon Jan 26 11:54:24 GMT+08:00 2015 zhengyunfei
    */ 
	@Service("area")
	public class AreaImpl implements IArea{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	/**
	*新增
	*@author zhengYunFei
	*@date Mon Jan 26 11:54:24 GMT+08:00 2015
	*/
	public String add(AreaBo bo){
	String backInfo="保存成功";
	try{

		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		baseDao.addObject("addcfjProvCityAreaStreet", bo);
		}catch(Exception e){
		backInfo="保存失败";
		 BaseLog.e(this.getClass(), "add 添加失败", e);
		 throw new BaseException("添加！",e);
	}
	return backInfo;
}
	/**
	*修改
	*@author zhengYunFei
	*@date Mon Jan 26 11:54:24 GMT+08:00 2015
	*/
	public String update(AreaBo bo){
	String backInfo="修改成功";
	try{

		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		baseDao.updObject("updatecfjProvCityAreaStreet", bo);
		}catch(Exception e){
		backInfo="保存失败";
		 BaseLog.e(this.getClass(), "update更新失败", e);
		 throw new BaseException("更新！",e);
	}
	return backInfo;
}
	/**
	*删除
	*@author zhengYunFei
	*@date Mon Jan 26 11:54:24 GMT+08:00 2015
	*/
	public String delete(String id){
	String backInfo="删除成功";
	try{
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		Map<String,Object> map=new HashMap<String, Object>();
		 map.put("id",id.split(","));
		baseDao.delObject("deletecfjProvCityAreaStreet", id);
		}catch(Exception e){
		backInfo="保存失败";
		 BaseLog.e(this.getClass(), "add 添加失败", e);
		 throw new BaseException("添加！",e);
	}
	return backInfo;
}
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Mon Jan 26 11:54:24 GMT+08:00 2015
	*/
	public AreaBo findById(String id){

		AreaBo bo=null;
	try{
		    baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		    Map<String,Object> map=new HashMap<String, Object>();
		    map.put("id",id);
		    bo=(AreaBo)baseDao.findForObject("findByIdcfjProvCityAreaStreet", id);

		}catch(Exception e){
		BaseLog.e(this.getClass(), "findById 查询失败", e);
		 throw new BaseException("查询！",e);
	}
	return bo;
}
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Mon Jan 26 11:54:24 GMT+08:00 2015
	*/
	public List<AreaBo> findAllList(Map<String,Object> map,int curNo, int curSize){
		List<AreaBo> boList=new ArrayList<AreaBo>();

	try{
	    baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		boList=baseDao.findForList("findAreaList", map);
		for(int i=0;i<boList.size();i++){
			AreaBo bo=boList.get(i);
			boList.add(bo);
	}
		}catch(Exception e){
		BaseLog.e(this.getClass(), "findById 查询失败", e);
		 throw new BaseException("查询！",e);
	}
	return boList;
}
}

