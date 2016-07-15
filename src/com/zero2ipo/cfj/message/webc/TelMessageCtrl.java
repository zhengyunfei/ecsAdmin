package com.zero2ipo.cfj.message.webc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.cfj.message.bizc.ITelMessage;
import com.zero2ipo.cfj.message.bo.TelMessageBo;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
   /**
    * cfjTelMessage 实体类
    * Fri Nov 14 15:16:00 GMT+08:00 2014 zhengyunfei
    */ 
	@Controller
@RequestMapping("telMessage")
	public class TelMessageCtrl extends BaseCtrl{
	@Autowired @Qualifier("telMessage")
	private ITelMessage telMessage;
	@RequestMapping("/forInit.shtml")
	public ModelAndView forInit(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/s9/message/message_init.jsp");
		return mv;
	}
	/**
	*新增
	*@author zhengYunFei
	*@date Fri Nov 14 15:16:00 GMT+08:00 2014
	*/
	@RequestMapping("add.shtml")
	@ResponseBody
	public Map<String,Object>  add(TelMessageBo bo){
	Map<String,Object> map=new HashMap<String, Object>();
	String backInfo = null;
	try{
		backInfo = telMessage.add(bo);
	}catch (Exception e) {
	BaseLog.e(this.getClass(), "新增成功", e);
	}
	map.put("msg",backInfo);
	return map;
	}
	/**
	*修改
	*@author zhengYunFei
	*@date Fri Nov 14 15:16:00 GMT+08:00 2014
	*/
	@RequestMapping("update.shtml")
	@ResponseBody
	public Map<String,Object>  update(TelMessageBo bo){
	Map<String,Object> map=new HashMap<String, Object>();
	String backInfo = null;
	try{
	backInfo = telMessage.update(bo);
	}catch (Exception e) {
	BaseLog.e(this.getClass(), "修改成功", e);
	}
	map.put("msg",backInfo);
	return map;
	}
	/**
	*删除
	*@author zhengYunFei
	*@date Fri Nov 14 15:16:00 GMT+08:00 2014
	*/
	@RequestMapping("delete.shtml")
	@ResponseBody
	public Map<String,Object>  delete(String id){
	Map<String,Object> map=new HashMap<String, Object>();
	String backInfo = null;
	try{
	backInfo = telMessage.delete(id);
	}catch (Exception e) {
	BaseLog.e(this.getClass(), "新增成功", e);
	}
	map.put("msg",backInfo);
	return map;
	}
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Fri Nov 14 15:16:00 GMT+08:00 2014
	*/
	@RequestMapping("findById.shtml")
	@ResponseBody
	public TelMessageBo findById(String id){
	Map<String,Object> map=new HashMap<String, Object>();
	TelMessageBo bo = null;
	try{
	bo = telMessage.findById(id);
	}catch (Exception e) {
	BaseLog.e(this.getClass(), "新增成功", e);
	}
	return bo;
	}
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Fri Nov 14 15:16:00 GMT+08:00 2014
	*/
	@RequestMapping("findAllList.shtml")
	@ResponseBody
	public Map<String,Object>  findAllList(String tel,String curNo, String curSize){
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
	if(!StringUtil.isNullOrEmpty(tel)){
		map.put("tel", tel);
	}
	int total=0;
	total=telMessage.findAllList(map);
	List<TelMessageBo> vipInfo=null;
	if(total>0){
		vipInfo = telMessage.findAllList(map, (skip-1)*max, max);
	}
	jsonMap.put("Rows", vipInfo);
	jsonMap.put("Total", total);
	} catch (Exception e) {
	BaseLog.e(this.getClass(), "queryArticleList:查询有误", e);
	}
	return jsonMap;
	}
}

