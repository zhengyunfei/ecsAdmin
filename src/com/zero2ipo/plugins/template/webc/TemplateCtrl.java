package com.zero2ipo.plugins.template.webc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.template.bizc.ITemplate;
import com.zero2ipo.plugins.template.bo.TemplateBo;
   /**
    * cfjTemplate 实体类
    * Fri Nov 07 11:36:24 GMT+08:00 2014 zhengyunfei
    */ 
	@Controller
   @RequestMapping("template")
	public class TemplateCtrl extends BaseCtrl{
	@Autowired @Qualifier("template")
	private ITemplate template;
	/**
	*新增
	*@author zhengYunFei
	*@date Fri Nov 07 11:36:24 GMT+08:00 2014
	*/
	@RequestMapping("add.shtml")
	@ResponseBody
	public Map<String,Object> add(TemplateBo bo){
	Map<String,Object> map=new HashMap<String, Object>();
	String backInfo = null;
	try{
	backInfo = template.add(bo);
	}catch (Exception e) {
	BaseLog.e(this.getClass(), "新增成功", e);
	}
	map.put("msg",backInfo);
	return map;
	}
	/**
	*修改
	*@author zhengYunFei
	*@date Fri Nov 07 11:36:24 GMT+08:00 2014
	*/
	@RequestMapping("update.shtml")
	@ResponseBody
	public Map<String,Object>  update(TemplateBo bo){
	Map<String,Object> map=new HashMap<String, Object>();
	String backInfo = null;
	try{
	backInfo = template.update(bo);
	}catch (Exception e) {
	BaseLog.e(this.getClass(), "修改成功", e);
	}
	map.put("msg",backInfo);
	return map;
	}
	/**
	*删除
	*@author zhengYunFei
	*@date Fri Nov 07 11:36:24 GMT+08:00 2014
	*/
	@RequestMapping("delete.shtml")
	@ResponseBody
	public Map<String,Object>  delete(String id){
	Map<String,Object> map=new HashMap<String, Object>();
	String backInfo = null;
	try{
	backInfo = template.delete(id);
	}catch (Exception e) {
	BaseLog.e(this.getClass(), "新增成功", e);
	}
	map.put("msg",backInfo);
	return map;
	}
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Fri Nov 07 11:36:24 GMT+08:00 2014
	*/
	@RequestMapping("findById.shtml")
	@ResponseBody
	public Map<String,Object>  findById(String id){
	Map<String,Object> map=new HashMap<String, Object>();
	TemplateBo bo = null;
	try{
		map.put("templateId", id);
		bo = template.findById(map);
	}catch (Exception e) {
	BaseLog.e(this.getClass(), "新增成功", e);
	}
	map.put("msg",bo);
	return map;
	}
	/**
	*分页查询所有信息
	*@author zhengYunFei
	*@date Fri Nov 07 11:36:24 GMT+08:00 2014
	*/
	@RequestMapping("queryArticleList.shtml")
	@ResponseBody
    public Map<String,Object>  queryArticleList(String curNo, String curSize, String articleType,String articleTitle,String articleId,String keyword){
	Map<String,Object> jsonMap = new HashMap<String, Object>();
	try {
			/************* 分页处理 ************/
	int skip;
	int max;
	if (StringUtil.isNullOrEmpty(curNo))
	curNo = "0";
	if (StringUtil.isNullOrEmpty(curSize))
	curSize = "20";
	skip = Integer.parseInt(curNo);
	max = Integer.parseInt(curSize);
			/************  分页处理结束 ***********/
	Map<String, Object> map = new HashMap<String, Object>();
	List<TemplateBo> vipInfo = template.findAllList(map, (skip-1)*max, max);
	jsonMap.put("Rows", vipInfo);
	jsonMap.put("Total", vipInfo.size());
	} catch (Exception e) {
	BaseLog.e(this.getClass(), "queryArticleList:查询有误", e);
    }
	return jsonMap;
	}
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Fri Nov 07 11:36:24 GMT+08:00 2014
	*/
	@RequestMapping("findAllList.shtml")
	@ResponseBody
    public Map<String,Object>  findAllList(){
	Map<String,Object> jsonMap = new HashMap<String, Object>();
	try {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TemplateBo> vipInfo = template.findAllList(map);
		jsonMap.put("result", vipInfo);
	} catch (Exception e) {
	BaseLog.e(this.getClass(), "queryArticleList:查询有误", e);
    }
	return jsonMap;
	}
}

