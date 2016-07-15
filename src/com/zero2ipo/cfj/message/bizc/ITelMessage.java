package com.zero2ipo.cfj.message.bizc;
import java.util.List;
import java.util.Map;

import com.zero2ipo.cfj.message.bo.TelMessageBo;
   /**
    * cfjTelMessage 实体类
    * Fri Nov 14 15:17:44 GMT+08:00 2014 zhengyunfei
    */ 


public interface ITelMessage{
	/**
	*新增
	*@author zhengYunFei
	*@date Fri Nov 14 15:17:44 GMT+08:00 2014
	*/
	public String add(TelMessageBo bo);
	/**
	*修改
	*@author zhengYunFei
	*@date Fri Nov 14 15:17:44 GMT+08:00 2014
	*/
	public String update(TelMessageBo bo);
	/**
	*删除
	*@author zhengYunFei
	*@date Fri Nov 14 15:17:44 GMT+08:00 2014
	*/
	public String delete(String id);
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Fri Nov 14 15:17:44 GMT+08:00 2014
	*/
	public TelMessageBo findById(String id);
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Fri Nov 14 15:17:44 GMT+08:00 2014
	*/
	public List<TelMessageBo> findAllList(Map<String, Object> map, int i, int max);
	public int findAllList(Map<String, Object> map);
}

