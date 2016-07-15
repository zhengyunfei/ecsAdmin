package com.zero2ipo.plugins.template.bizc.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.module.entity.template.TemplateEntity;
import com.zero2ipo.plugins.template.bizc.ITemplate;
import com.zero2ipo.plugins.template.bo.TemplateBo;
   /**
    * Template 实体类
    * Thu Oct 30 15:20:34 GMT+08:00 2014 zhengyunfei
    */ 
	@Service("template")
	public class TemplateImpl implements ITemplate{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	/**
	*新增
	*@author zhengYunFei
	*@date Thu Oct 30 15:20:34 GMT+08:00 2014
	*/
	public String add(TemplateBo bo){
	String backInfo="保存成功";
	try{
		TemplateEntity entity=TemplateBo.boToEntity(bo);
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		baseDao.addObject("addTemplate", entity);
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
	*@date Thu Oct 30 15:20:34 GMT+08:00 2014
	*/
	public String update(TemplateBo bo){
	String backInfo="修改成功";
	try{
		TemplateEntity entity=TemplateBo.boToEntity(bo);
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		baseDao.updObject("updateTemplate", entity);
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
	*@date Thu Oct 30 15:20:34 GMT+08:00 2014
	*/
	public String delete(String id){
	String backInfo="删除成功";
	try{
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		Map<String,Object> map=new HashMap<String, Object>();
		 map.put("id",id.split(","));
		baseDao.delObject("deleteTemplate", id);
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
	*@date Thu Oct 30 15:20:34 GMT+08:00 2014
	*/
	public TemplateBo findById(Map<String,Object> map){
		TemplateEntity entity=null;
		TemplateBo bo=null;
	try{
		    baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		    entity=(TemplateEntity) baseDao.findForObject("findByIdTemplate", map);
		    if(!StringUtil.isNullOrEmpty(entity)){
		    	bo=TemplateBo.entityToBo(entity);
		    }
		}catch(Exception e){
		 BaseLog.e(this.getClass(), "findById 查询失败", e);
		 throw new BaseException("查询！",e);
	}
	return bo;
}
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Thu Oct 30 15:20:34 GMT+08:00 2014
	*/
	public List<TemplateBo> findAllList(Map<String,Object> map,int skip, int max){
		List<TemplateBo> boList=new ArrayList<TemplateBo>();
		List<TemplateEntity> entityList=new ArrayList<TemplateEntity>();
	    try{
	      baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
	      entityList=baseDao.findForList("findTemplateList", map);
		for(int i=0;i<entityList.size();i++){
			TemplateEntity entity=entityList.get(i);
			TemplateBo bo=TemplateBo.entityToBo(entity);
			boList.add(bo);
	      }
		}catch(Exception e){
			BaseLog.e(this.getClass(), "findById 查询失败", e);
		 throw new BaseException("查询！",e);
	}
	return boList;
}
public List<TemplateBo> findAllList(Map<String, Object> map){
	List<TemplateBo> boList=new ArrayList<TemplateBo>();
	List<TemplateEntity> entityList=new ArrayList<TemplateEntity>();
    try{
      baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
      entityList=baseDao.findForList("findAllTemplateList", map);
	for(int i=0;i<entityList.size();i++){
		TemplateEntity entity=entityList.get(i);
		TemplateBo bo=TemplateBo.entityToBo(entity);
		boList.add(bo);
      }
	}catch(Exception e){
		e.printStackTrace();
	 BaseLog.e(this.getClass(), "findById 查询失败", e);
	 throw new BaseException("查询！",e);
}
return boList;
}
}

