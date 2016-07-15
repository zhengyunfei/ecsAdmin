package com.zero2ipo.cfj.newstation.bizc.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zero2ipo.cfj.newstation.bizc.INewsTation;
import com.zero2ipo.cfj.newstation.bo.NewsTationBo;
import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.module.entity.newstation.NewsTationEntity;
   /**
    * cfjNewsTation 实体类
    * Thu Dec 11 10:50:15 GMT+08:00 2014 郑云飞
    */ 
	@Service("newsTation")
	public class NewsTationImpl implements INewsTation{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	/**
	*新增
	*@author zhengYunFei
	*@date Thu Dec 11 10:50:15 GMT+08:00 2014
	*/
	public String add(NewsTationBo bo){
	String backInfo="保存成功";	
	try{
		if(!StringUtil.isNullOrEmpty(bo)){
			String userIds[]=bo.getUserId().split(",");
			for(int i=0;i<userIds.length;i++){
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				String id=String.valueOf(baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT));
				bo.setId(id);
				bo.setUserId(userIds[i]);
				bo.setStatus("0");
				NewsTationEntity entity=NewsTationBo.boToEntity(bo);
				baseDao.addObject("addNewsTation", entity);
			}
		}
	}catch(Exception e){
		backInfo="保存失败";
		 BaseLog.e(this.getClass(), "add 添加失败", e);
		 throw new BaseException("添加！",e);
	}
	return backInfo;
}
}

