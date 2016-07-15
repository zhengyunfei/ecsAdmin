package com.zero2ipo.plugins.template.bizc;
import java.util.List;
import java.util.Map;

import com.zero2ipo.plugins.template.bo.TemplateBo;
   /**
    * Template 实体类
    * Thu Oct 30 15:18:22 GMT+08:00 2014 zhengyunfei
    */ 


public interface ITemplate{
	/**
	*新增
	*@author zhengYunFei
	*@date Thu Oct 30 15:18:22 GMT+08:00 2014
	*/
	public String add(TemplateBo bo);
	/**
	*修改
	*@author zhengYunFei
	*@date Thu Oct 30 15:18:22 GMT+08:00 2014
	*/
	public String update(TemplateBo bo);
	/**
	*删除
	*@author zhengYunFei
	*@date Thu Oct 30 15:18:22 GMT+08:00 2014
	*/
	public String delete(String id);
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Thu Oct 30 15:18:22 GMT+08:00 2014
	*/
	public TemplateBo findById(Map<String, Object> map);
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Thu Oct 30 15:18:22 GMT+08:00 2014
	*/
	public List<TemplateBo> findAllList(Map<String, Object> queryMap, int i, int max);
	
	public List<TemplateBo> findAllList(Map<String, Object> map);
}

