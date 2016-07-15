package com.zero2ipo.cfj.message.bizc.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zero2ipo.cfj.message.bizc.ITelMessage;
import com.zero2ipo.cfj.message.bo.TelMessageBo;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.module.entity.telMessage.TelMessageEntity;
   /**
    * cfjTelMessage 实体类
    * Fri Nov 14 15:18:31 GMT+08:00 2014 zhengyunfei
    */ 
	@Service("telMessage")
	public class TelMessageImpl implements ITelMessage{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	/**
	*新增
	*@author zhengYunFei
	*@date Fri Nov 14 15:18:31 GMT+08:00 2014
	*/
	public String add(TelMessageBo bo){
	String backInfo="保存成功";
	try{
		TelMessageEntity entity=TelMessageBo.boToEntity(bo);
		UUID uid = UUID.randomUUID();
		entity.setMsgId(uid.toString());
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		baseDao.addObject("addTelMessage", entity);
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
	*@date Fri Nov 14 15:18:31 GMT+08:00 2014
	*/
	public String update(TelMessageBo bo){
	String backInfo="修改成功";
	try{
		TelMessageEntity entity=TelMessageBo.boToEntity(bo);
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		baseDao.updObject("updatecfjTelMessage", entity);
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
	*@date Fri Nov 14 15:18:31 GMT+08:00 2014
	*/
	public String delete(String id){
	String backInfo="删除成功";
	try{
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		Map<String,Object> map=new HashMap<String, Object>();
		 map.put("id",id.split(","));
		baseDao.delObject("deletecfjTelMessage", id);
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
	*@date Fri Nov 14 15:18:31 GMT+08:00 2014
	*/
	public TelMessageBo findById(String id){
		TelMessageEntity entity=null;
		TelMessageBo bo=null;
	try{
		    baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		    Map<String,Object> map=new HashMap<String, Object>();
		    map.put("id",id);
		    entity=(TelMessageEntity)baseDao.findForObject("findByIdcfjTelMessage", id);
		  bo=TelMessageBo.entityToBo(entity);
		}catch(Exception e){
		 BaseLog.e(this.getClass(), "findById 查询失败", e);
		 throw new BaseException("查询！",e);
	}
	return bo;
}
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Fri Nov 14 15:18:31 GMT+08:00 2014
	*/
	public List<TelMessageBo> findAllList(Map<String,Object> map,int skip, int max){
		List<TelMessageBo> boList=new ArrayList<TelMessageBo>();
		List<TelMessageEntity> entityList=new ArrayList<TelMessageEntity>();
	try{
	    baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
	    entityList=baseDao.findForList("findAllMessageList", map,skip,max);
		for(int i=0;i<entityList.size();i++){
		TelMessageEntity entity=entityList.get(i);
		TelMessageBo bo=TelMessageBo.entityToBo(entity);
		boList.add(bo);
	}
		}catch(Exception e){
		 BaseLog.e(this.getClass(), "findById 查询失败", e);
		 throw new BaseException("查询！",e);
	}
	return boList;
}
	public int findAllList(Map<String, Object> map){
		int count=0;
		 baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		 count=(Integer)baseDao.findForObject("findAllMessageListCount", map);
		 return count;
	}
}

