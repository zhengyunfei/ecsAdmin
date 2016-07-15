/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-6-1 
 */
package com.zero2ipo.plugins.conf.webc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.code.bo.CodeSort;
import com.zero2ipo.plugins.conf.bizc.IConfManage;
import com.zero2ipo.plugins.conf.bo.ConfSort;
import com.zero2ipo.plugins.conf.bo.ConfValue;

/**
 * @title： 项目配置部署控制类
 * @description: 项目配置部署控制类。
 * @author： zhengYunFei
 * @date： 2013-07-15 10:10
 */
@Controller
@RequestMapping("/confManage")
public class ConfManageCtrl extends BaseCtrl{
	@Autowired @Qualifier("confManage")
	private IConfManage confManage;
	/**
     * @title: 项目配置部署初始化管理页面 
     * @description:项目配置部署初始化管理页面 
     * @param : void
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: ModelAndView /s9/conf/confManage_init.jsp
     */
    @RequestMapping("/forInit.shtml")
	public String forInit(){
		return "/s9/conf/confManage_init.jsp";
    	//return "/s9/conf/confSortList.jsp";
	}
	/**
     * @title: 初始化项目配置分类树
     * @description:初始化项目配置分类树
     * @param : void
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: ModelAndView /s9/conf/confManage_init.jsp
     */
    @RequestMapping("/initConfSortTree.shtml")
    @ResponseBody
	public Map<String,Object> initConfSortTree(){
    	Map<String,Object> jsonMap = new HashMap<String, Object>();
        try{
        	CodeSort bo = new CodeSort();
        	/* 查询条件开始 */
        	Map<String, String> map = new HashMap<String,String>();
        	/* 查询条件结束*/
           List<ConfSort> list = confManage.findConfSort(map);
           jsonMap.put("dataList", list);
        }catch (BaseException e) {
            BaseLog.e(this.getClass(), "初始化项目配置树，出错 initConfSortTree! ", e);
        }
        return jsonMap;
	}
    
	/**
     * @title: 初始化项目配置分类->项(参数) 信息列表页面 
     * @description: 初始化项目配置项信息列表页面 _项目配置分类信息初始化
     * @param : sortCode  项目配置分类ID
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: ModelAndView  /s9/conf/confSortAndItem_init.jsp
     */
	@RequestMapping("/initConfSortAndItem.shtml")
    public ModelAndView initConfSortAndItem(String sortCode){
		ModelAndView mv = new ModelAndView("/s9/conf/confSortAndItem_init.jsp");
		try {
			if(!StringUtil.isNullOrEmpty(sortCode)){
				ConfSort bo = confManage.findConfSort(sortCode);
				mv.addObject("confSortObj", bo);
				mv.addObject("sortCode", sortCode);
			}
		} catch (BaseException e) {
			 BaseLog.e(this.getClass(), "初始化项目配置分类->项(参数)信息列表页面 initConfSortAndItem! ", e);
		}
    	return mv;
    }  
	/**
     * @title:  初始化项目配置分类信息添加页面
     * @description: 初始化项目配置分类信息添加页面 
     * @param : void
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: ModelAndView /s9/conf/confSort_add.jsp
     */
	@RequestMapping("/forInitSaveConfSort.shtml")
    public ModelAndView forInitSaveConfSort(){
		ModelAndView mv = new ModelAndView("/s9/conf/confSort_add.jsp");
		//Map<String,String> proCodeMap = GlobalConstant.getProCodeMap();//项目配置部署中的项目分类编码
		//Map<String,String> typeMap = GlobalConstant.getProTypeMap();//项目配置部署中的项目分类  
		//mv.addObject("proCodeMap", proCodeMap);
		//mv.addObject("typeMap", typeMap);
    	return mv;
    }
	/**
     * @title:  保存项目配置分类信息
     * @description: 保存项目配置分类信息
     * @param : bo 项目配置分类信息bean
     * @author: zhengYunFei
     * @date: 2013-07-03 10:20
     * @return: ModelAndView  /s9/conf/confSort_add.jsp
     */
	@RequestMapping("/addConfSort.shtml")
    public ModelAndView addConfSort(ConfSort bo){
		ModelAndView mv = new ModelAndView();
    	try {
			if(!StringUtil.isNullOrEmpty(bo)){
				String sortCode = confManage.addSortConf(bo);
				bo.setSortCode(sortCode);
				mv.addObject("msg", "sucess");
				mv.addObject("confSortObj", bo);
			}
		} catch (BaseException e) {
			mv.addObject("msg", "error");
			 BaseLog.e(this.getClass(), "添加代码分类信息出错! addConfSort! ", e);
		}
		mv.setViewName("/s9/conf/confSort_add.jsp");
		//Map<String,String> proCodeMap = GlobalConstant.getProCodeMap();//项目配置部署中的项目分类编码
		//Map<String,String> typeMap = GlobalConstant.getProTypeMap();//项目配置部署中的项目分类  
		//mv.addObject("proCodeMap", proCodeMap);
	//	mv.addObject("typeMap", typeMap);
    	return mv;
    }
	
	/**
     * @title:  初始化项目配置分类信息修改页面
     * @description: 初始化项目配置分类信息修改页面 
     * @param : codeSortId项目配置分类信息ID
     * @author: zhengYunFei
     * @date: 2013-07-03 10:20 
     * @return: ModelAndView  /s9/conf/confSort_upd.jsp
     */
	@RequestMapping("/forInitUpdateConfSort.shtml")
    public ModelAndView forInitUpdateConfSort(String sortCode){
    	ModelAndView mv = new ModelAndView("/s9/conf/confSort_upd.jsp");
		try {
			ConfSort bo = null;
			if(!StringUtil.isNullOrEmpty(sortCode)){
				bo = confManage.findConfSort(sortCode);
			}
			mv.addObject("confSortObj", bo);
		} catch (BaseException e) {
			 BaseLog.e(this.getClass(), "初始化项目配置分类信息修改页面出错! forInit.shtmlUpdateCodeSort! ", e);
		}
		
		//Map<String,String> proCodeMap = GlobalConstant.getProCodeMap();//项目配置部署中的项目分类编码
		//Map<String,String> typeMap = GlobalConstant.getProTypeMap();//项目配置部署中的项目分类  
		//mv.addObject("proCodeMap", proCodeMap);
		//mv.addObject("typeMap", typeMap);
		
    	return mv;
    }
	
	/**
     * @title:  修改项目配置分类信息
     * @description: 修改项目配置分类信息
     * @param : bo 项目配置分类信息bean
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: ModelAndView  /s9/conf/confSort_upd.jsp
     */
	@RequestMapping("/updConfSort.shtml")
    public ModelAndView updConfSort(ConfSort bo){
		ModelAndView mv = new ModelAndView("/s9/conf/confSort_upd.jsp");
    	try {
			if(!StringUtil.isNullOrEmpty(bo)){
				confManage.updConfSort(bo);
				mv.addObject("msg", "sucess");
				mv.addObject("confSortObj", bo);
			}
		} catch (BaseException e) {
			 mv.addObject("msg", "error");
			 BaseLog.e(this.getClass(), "修改项目配置分类信息出错! updConfSort! ", e);
		}
    	return mv;
    }
	/**
     * @title:  删除项目配置分类信息
     * @description: 删除项目配置分类信息_代码项信息
     * @param : sortCode 项目配置分类信息ID
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: map
     */
	@RequestMapping("/delConfSort.shtml")
    @ResponseBody
    public Map<String,Object> delConfSort(String sortCode){
    	Map<String,Object> jsonMap = new HashMap<String, Object>();
    	try {
			if(!StringUtil.isNullOrEmpty(sortCode)){
				confManage.delConfSort(sortCode);
				jsonMap.put("msg", "sucess");
			}
		} catch (BaseException e) {
			jsonMap.put("msg", "error");
			 BaseLog.e(this.getClass(), "删除代码分类信息出错! delCodeSort! ", e);
		}
    	return jsonMap;
    }
	
    /**
     * @title: 根据项目配置分类编码查询配置参数key-value信息
     * @description:根据项目配置分类编码查询配置参数key-value信息
     * @param : confSort  配置分类
     * @param : curNo  分页-页码
     * @param : curSize  分页-页面加载记录条数
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: map
     */
    @RequestMapping("/findConfValues.shtml")
    @ResponseBody
    public Map<String,Object> findConfValues(String curNo,String curSize,String curPgn,ConfSort confSort){
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
 			List<ConfValue> confValues=new ArrayList<ConfValue>();
 			Map<String, String> map=new HashMap<String, String>();
 			if(confSort.getSortCode()!=null){
 				map.put("sortCode", confSort.getSortCode());
 			}
 			confValues=confManage.findConfValue(map, (skip - 1) * max, max);
 			int allSize=0;
 			allSize=confManage.findConfValueCount(map);
    		 
        	 jsonMap.put("Rows", confValues);
 			 jsonMap.put("Total", allSize);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "根据项目配置分类编码查询配置参数列表出错! findCodeInfoList! ", e);
		}
    	 return jsonMap;
    }
     
	/**
     * @title:  初始化项目配置参数信息添加页面
     * @description: 初始化项目配置参数信息添加页面 
     * @param : void
     * @author: zhengYunFei
     * @date: 2013-07-03 10:20
     * @return: ModelAndView /s9/conf/confValue_add.jsp
     */
	@RequestMapping("/forInitSaveConfValue.shtml")
    public ModelAndView forInitSaveConfValue(String sortCode){
		ModelAndView mv = new ModelAndView("/s9/conf/confValue_add.jsp");
		mv.addObject("sortCode", sortCode);
    	return mv;
    }
	
	/**
     * @title:  保存项目配置参数信息
     * @description: 保存项目配置参数信息
     * @param : bo 项目配置信息bean
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: ModelAndView  /s9/conf/confValue_add.jsp
     */
	@RequestMapping("/addConfValue.shtml")
    public ModelAndView addSortValue(ConfValue confValue){
		ModelAndView mv = new ModelAndView();
    	try {
			if(!StringUtil.isNullOrEmpty(confValue)){
				confManage.addConfValue(confValue);
				mv.addObject("msg", "sucess");
				mv.addObject("valueObj", confValue);
			}
		} catch (BaseException e) {
			mv.addObject("msg", "error");
			 BaseLog.e(this.getClass(), "保存参数信息   ，出错! addSortValue! ", e);
		}
		mv.setViewName("/s9/conf/confValue_add.jsp");
    	return mv;
    }
	
	/**
     * @title: 删除参数项信息修改页面
     * @description:删除参数项信息添加页面
     * @param : valueIds  {参数项ID，参数项ID……}
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: map
     */
	@RequestMapping("/delConfValue.shtml")
    @ResponseBody
    public Map<String,Object> delConfValue(String valueIds){
    	Map<String,Object> jsonMap = new HashMap<String, Object>();
    	try {
			if(!StringUtil.isNullOrEmpty(valueIds)){
				confManage.delConfValue(valueIds);
				jsonMap.put("msg", "sucess");
			}
		} catch (BaseException e) {
			 BaseLog.e(this.getClass(), "delCodeInfo 删除代码项信息出错! ", e);
		}
    	return jsonMap;
    }
	
	/**
     * @title:  初始化项目配置参数信息修改页面
     * @description: 初始化项目配置参数信息修改页面 
     * @param : codeSortId项目配置参数信息ID
     * @author: zhengYunFei
     * @date: 2013-07-03 10:20 
     * @return: ModelAndView  /s9/conf/confSort_upd.jsp
     */
	@RequestMapping("/forInitUpdateConfValue.shtml")
    public ModelAndView forInitUpdateConfValue(String valueId){
    	ModelAndView mv = new ModelAndView("/s9/conf/confValue_upd.jsp");
		try {
			ConfValue bo = null;
			if(!StringUtil.isNullOrEmpty(valueId)){
				bo = confManage.findConfValue(valueId);
			}
			mv.addObject("valueObj", bo);
		} catch (BaseException e) {
			 BaseLog.e(this.getClass(), "初始化项目配置参数信息出错! forInit.shtmlUpdateConfValue! ", e);
		}
    	return mv;
    }
	
	/**
     * @title:  修改项目配置参数信息
     * @description: 修改项目配置参数信息
     * @param : bo 项目配置参数信息bean
     * @author: zhengYunFei
     * @date: 2013-07-15 10:20
     * @return: ModelAndView  /s9/conf/confValue_upd.jsp
     */
	@RequestMapping("/updConfValue.shtml")
    public ModelAndView updConfValue(ConfValue bo){
		ModelAndView mv = new ModelAndView("/s9/conf/confSort_upd.jsp");
    	try {
			if(!StringUtil.isNullOrEmpty(bo)){
				confManage.updConfValue(bo);
				mv.addObject("msg", "sucess");
				mv.addObject("valueObj", bo);
			}
		} catch (BaseException e) {
			 mv.addObject("msg", "error");
			 BaseLog.e(this.getClass(), "修改项目配置参数信息出错! updConfSort! ", e);
		}
    	return mv;
    }
	
    
}
