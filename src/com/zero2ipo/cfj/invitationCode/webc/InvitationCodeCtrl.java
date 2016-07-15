package com.zero2ipo.cfj.invitationCode.webc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.cfj.invitationCode.bizc.IInvitationCode;
import com.zero2ipo.cfj.invitationCode.bo.InvitationCodeBo;
import com.zero2ipo.cfj.invitationCode.bo.InvitationCodeUserBo;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
   /**
    * cfjInvitationCode 实体类
    * Tue Dec 30 15:11:03 GMT+08:00 2014 zhengyunfei
    */ 
	@Controller
	@RequestMapping("invitationCode")
	public class InvitationCodeCtrl extends BaseCtrl{
	@Autowired @Qualifier("invitationCode")
	private IInvitationCode invitationCode;
	@RequestMapping("/init.shtml")
	public ModelAndView initPage(){
		ModelAndView mv=new ModelAndView("/s9/invitationCode/invitationCode_init.jsp");
		return mv;
	}
	@RequestMapping("/initAddPage.shtml")
	public ModelAndView addPage(String userId,String mobile){
		ModelAndView mv=new ModelAndView("/s9/invitationCode/invitationCode_add.jsp");
		mv.addObject("userId",userId);
		mv.addObject("mobile",mobile);
		return mv;
	}
	/**
	*新增
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:03 GMT+08:00 2014
	*/
	@RequestMapping("/add.shtml")
	@ResponseBody
	public Map<String,Object>  add(InvitationCodeBo bo){
	Map<String,Object> map=new HashMap<String, Object>();
	boolean flag = true;
	try{
	 invitationCode.add(bo);
	}catch (Exception e) {
		flag=false;
		BaseLog.e(this.getClass(), "新增成功", e);
	}
	map.put("result",flag);
	return map;
	}
	/**
	*修改
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:03 GMT+08:00 2014
	*/
	@RequestMapping("/update.shtml")
	@ResponseBody
	public Map<String,Object>  update(InvitationCodeBo bo){
	Map<String,Object> map=new HashMap<String, Object>();
	try{
		invitationCode.update(bo);
		map.put("message",1);
	}catch (Exception e) {
		map.put("message",0);
		BaseLog.e(this.getClass(), "修改成功", e);
	}
	return map;
	}
	/**
	*删除
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:03 GMT+08:00 2014
	*/
	@RequestMapping("/delete.shtml")
	@ResponseBody
	public Map<String,Object>  delete(String id){
	Map<String,Object> map=new HashMap<String, Object>();
	String backInfo = null;
	try{
		backInfo = invitationCode.delete(id);
		map.put("message",1);
	}catch (Exception e) {
		map.put("message",0);
	BaseLog.e(this.getClass(), "新增成功", e);
	}
	map.put("msg",backInfo);
	return map;
	}
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:03 GMT+08:00 2014
	*/
	@RequestMapping("/findById.shtml")
	@ResponseBody
	public 	InvitationCodeBo findById(String id){
	InvitationCodeBo bo = null;
	try{
		bo = invitationCode.findById(id);
	}catch (Exception e) {
		BaseLog.e(this.getClass(), "新增成功", e);
	}
	return bo;
	}
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:03 GMT+08:00 2014
	*/
	@RequestMapping("/initAjax.shtml")
	@ResponseBody
	public Map<String,Object> findAllList(Map<String,Object> queryMap,String curNo, String curSize,String userName){
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
		if(!StringUtil.isNullOrEmpty(userName)){
			map.put("useName", userName);
			//map.put("userId", 1);//确保map里面存放userId
		}
		
		List<InvitationCodeUserBo> vipInfo = invitationCode.findAllList(map, (skip-1)*max, max);
		jsonMap.put("Rows", vipInfo);
		jsonMap.put("Total", vipInfo.size());
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "queryArticleList:查询有误", e);
		}
		return jsonMap;
	}
}

