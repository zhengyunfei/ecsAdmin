/**
 * @(#)CodeManageCtrl.java	07/03/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-03
 */
package com.zero2ipo.plugins.wxmenu.web;

import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.CodeCommon;
import com.zero2ipo.plugins.code.biz.ICodeManage;
import com.zero2ipo.plugins.code.bo.CodeInfo;
import com.zero2ipo.plugins.code.bo.CodeSort;
import com.zero2ipo.plugins.template.bizc.ITemplate;
import com.zero2ipo.plugins.weixin.utils.GetAllWeixinUserUtil;
import com.zero2ipo.plugins.weixin.utils.WeiXinUserBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title： 标准代码管理维护控制类
 * @description: 针对标准代码分类管理、标准代码项的一个维护管理控制类。
 * @author： zhengYunFei
 * @date： 2013-07-03 10:10
 */
@Controller
@RequestMapping("/wxMenuManage")
public class WxMenuManageCtrl extends BaseCtrl{

	//自动注入codeManage标准代码管理业务操作接口
	@Autowired @Qualifier("codeManage")
	private ICodeManage codeManage;
	@Autowired @Qualifier("template")
	private ITemplate template;
	private static String MenuCodeId="117";
	/**
	 * @title: 初始化代码管理页面
	 * @description: 初始化标准代码管理页面
	 * @param : void
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView /code/codeManage_init.jsp
	 */
	@RequestMapping("forInit.shtml")
	public ModelAndView initCodeManage(){
		ModelAndView mv = new  ModelAndView("/s9/weixin/menu/index.jsp");
		try {
			if(!StringUtil.isNullOrEmpty(MenuCodeId)){
				CodeSort bo = codeManage.findCodeSort(CodeCommon.CREATE_MENU);
				mv.addObject("codeSortObj", bo);
				mv.addObject("codeSortId", MenuCodeId);
			}
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "初始化代码信息列表页面 出错 initCodeSortAndItem! ", e);
		}

		return mv;
	}

	/**
	 * @title: 初始化代码管理树
	 * @description: 初始化标准代码管理页面_代码管理树展现初始化
	 * @param : codeSortName  代码分类名称
	 * @param : codeSortType  代码类型
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: map
	 */
	@RequestMapping("initCodeSortTree.shtml")
	@ResponseBody
	public Map<String,Object> initCodeSortTree(String codeSortName, String codeSortType){
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		try{
			CodeSort bo = new CodeSort();
			if(!StringUtil.isNullOrEmpty(codeSortName)){
				bo.setCodeSortName(codeSortName);
			}
			if(!StringUtil.isNullOrEmpty(codeSortType)){
				bo.setCodeSortType(codeSortType);
			}
			List<CodeSort> list = codeManage.findCodeSortTree(bo);
			jsonMap.put("dataList", list);
		}catch (BaseException e) {
			BaseLog.e(this.getClass(), "初始化代码管理树，出错 initCodeSortTree! ", e);
		}
		return jsonMap;
	}

	/**
	 * @title: 初始化代码项信息列表页面
	 * @description: 初始化代码信息列表页面 _代码分类信息初始化
	 * @param : codeSortId  代码分类ID
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView  /s9/code/codeSortAndItem_init.jsp
	 */
	@RequestMapping("initCodeSortAndItem.shtml")
	public ModelAndView initCodeSortAndItem(String codeSortId){
		ModelAndView mv = new ModelAndView("/s9/weixin/menu/codeSortAndItem_init.jsp");
		try {
			if(!StringUtil.isNullOrEmpty(codeSortId)){
				CodeSort bo = codeManage.findCodeSort(codeSortId);
				mv.addObject("codeSortObj", bo);
				mv.addObject("codeSortId", codeSortId);
			}
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "初始化代码信息列表页面 出错 initCodeSortAndItem! ", e);
		}
		return mv;
	}

	/**
	 * @title: 查找代码信息列表
	 * @description:查找代码信息列表 _代码项数据列表查找
	 * @param : codeSortId  代码分类ID
	 * @param : curNo  分页-页码
	 * @param : curSize  分页-页面加载记录条数
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: map
	 */
	@RequestMapping("findCodeInfoList.shtml")
	@ResponseBody
	public Map<String,Object>  findCodeInfoList(String codeSortId,String curNo, String curSize){
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
 			/* 查询条件*/
			CodeInfo bo = new CodeInfo();
			List<CodeInfo> list=null ;
			int allSize=0;
			if (!StringUtil.isNullOrEmpty(codeSortId)){
				bo.setCodeSortId(codeSortId);
				bo.setPcode(GlobalConstant.TREE_ROOT);
				list = codeManage.findCodeInfoList(bo,(skip - 1) * max,
						max);
            	 /* 总记录数 */
				allSize= codeManage.findCodeInfoCount(bo);

				setChildren(list);

			}
			jsonMap.put("Rows", list);
			jsonMap.put("Total", allSize);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "查找代码信息列表出错! findCodeInfoList! ", e);
		}
		return jsonMap;
	}

	/**
	 * 递归下级
	 */
	public void setChildren(List<CodeInfo> list){
		CodeInfo obj =null;
		List<CodeInfo> childrenList=null ;
		for(CodeInfo bo:list){
			obj = new CodeInfo();
			obj.setPcode(bo.getCodeValue());
			childrenList = codeManage.findCodeInfoList(obj);
			if(!childrenList.isEmpty()){
				bo.setChildren(childrenList);
				setChildren(childrenList);
			}
		}
	}

	/**
	 * @title:  初始化代码分类信息添加页面
	 * @description: 初始化代码分类信息添加页面
	 * @param : void
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView /s9/code/s9/code/Sort_add.jsp
	 */
	@RequestMapping("forInitSaveCodeSort.shtml")
	public ModelAndView forInitSaveCodeSort(){
		ModelAndView mv = new ModelAndView("/s9/code/codeSort_add.jsp");
		String date=DateUtil.getCurrentDate("yyyy-MM-dd");
		mv.addObject("effectDate",date);
		return mv;
	}

	/**
	 * @title: 查询分类名字是否已经存在
	 * @description: 查询分类名字是否已经存在
	 * @param : codeSortName 分类名称
	 * @author: zhengYunFei
	 * @date: 2014-09-16 10:20
	 */
	@RequestMapping("findCodeSortNameIsExist.shtml")
	@ResponseBody
	public int findCodeSortNameIsExist(String codeName){
		int count=0;
		count=codeManage.findCodeSortNameIsExist(codeName);
		return count;
	}
	/**
	 * @title:  保存代码分类信息
	 * @description: 保存代码分类信息
	 * @param : bo 代码分类信息bean
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView/s9/code/e/codeSort_add.jsp
	 */
	@RequestMapping("addCodeSort.shtml")
	public ModelAndView addCodeSort(CodeSort bo){
		ModelAndView mv = new ModelAndView();
		try {
			if(!StringUtil.isNullOrEmpty(bo)){
				String codeSortId =	codeManage.addCodeSort(bo);
				mv.addObject("msg", "sucess");
				bo.setCodeSortId(codeSortId);
				mv.addObject("codeSortObj", bo);
			}
		} catch (BaseException e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "添加代码分类信息出错! addCodeSort! ", e);
		}
		mv.setViewName("/s9/code/codeSort_add.jsp");
		return mv;
	}

	/**
	 * @title:  初始化代码分类信息修改页面
	 * @description: 初始化代码分类信息修改页面
	 * @param : codeSortId 代码分类信息ID
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView  /s9/code/codeSort_upd.jsp
	 */
	@RequestMapping("forInitUpdateCodeSort.shtml")
	public ModelAndView forInitUpdateCodeSort(String codeSortId){
		ModelAndView mv = new ModelAndView("/s9/code/codeSort_upd.jsp");
		try {
			CodeSort bo = null;
			if(!StringUtil.isNullOrEmpty(codeSortId)){
				bo = codeManage.findCodeSort(codeSortId);
			}
			mv.addObject("codeSortObj", bo);
			//Map<String,String> typeMap = GlobalConstant.getProTypeMap();//系统类型
			//mv.addObject("typeMap", typeMap);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "初始化代码分类信息修改页面出错! forInit.shtmlUpdateCodeSort! ", e);
		}
		return mv;
	}

	/**
	 * @title:  修改代码分类信息
	 * @description: 修改代码分类信息
	 * @param : bo 代码分类信息bean
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView  /s9/code/codeSort_upd.jsp
	 */
	@RequestMapping("updCodeSort.shtml")
	public ModelAndView updCodeSort(CodeSort bo){
		ModelAndView mv = new ModelAndView("/s9/code/codeSort_upd.jsp");
		try {
			if(!StringUtil.isNullOrEmpty(bo)){
				codeManage.updCodeSort(bo);
				mv.addObject("codeSortObj", bo);
				mv.addObject("msg", "sucess");
			}
		} catch (BaseException e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "修改代码分类信息出错! updCodeSort! ", e);
		}
		return mv;
	}

	/**
	 * @title:  删除代码分类信息
	 * @description: 删除代码分类信息_代码项信息
	 * @param : codeSortId 代码分类信息ID
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: map
	 */
	@RequestMapping("delCodeSort.shtml")
	@ResponseBody
	public Map<String,Object> delCodeSort(String codeSortId){
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		try {
			if(!StringUtil.isNullOrEmpty(codeSortId)){
				codeManage.delCodeSort(codeSortId);
				jsonMap.put("msg", "sucess");
			}
		} catch (BaseException e) {
			jsonMap.put("msg", "error");
			BaseLog.e(this.getClass(), "删除代码分类信息出错! delCodeSort! ", e);
		}
		return jsonMap;
	}

	/**
	 * @title:  初始化代码项信息添加页面
	 * @description:初始化代码项信息添加页面
	 * @param : codeSortId 代码分类信息ID
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView /s9/code/codeItem_add.jsp
	 */
	@RequestMapping("forInitSaveCodeInfo.shtml")
	public ModelAndView forInitSaveCodeInfo(String codeSortId,String codeType ,String pcode,String codeId){
		ModelAndView mv = new ModelAndView("/s9/weixin/menu/wxmenu_add.jsp");
		try {
			mv.addObject("codeSortId", codeSortId);
			mv.addObject("codeType", codeType);
			mv.addObject("codeId", codeId);
			if(null==pcode || "".equals(pcode))
				pcode = GlobalConstant.TREE_ROOT;
			mv.addObject("pcode", pcode);
			//查询模版下拉框
			//Map<String,Object> queryMap=new HashMap<String, Object>();
			//List<TemplateBo> listSelect=template.findAllList(queryMap);
			//mv.addObject("listSelect",listSelect);
		} catch (BaseException e) {
			e.printStackTrace();
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "初始化代码信息添加页面出错! forInit.shtmlSaveCodeInfo! ", e);
		}
		return mv;
	}

	/**
	 * @title:  保存代码项信息添加页面
	 * @description:保存代码项信息添加页面
	 * @param : bo  代码项信息BEAN
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView /s9/code/codeItem_add.jsp
	 */
	@RequestMapping("addCodeInfo.shtml")
	public ModelAndView addCodeInfo(CodeInfo bo){
		ModelAndView mv = new ModelAndView("/s9/weixin/menu/wxmenu_add.jsp");
		try {
			if(!StringUtil.isNullOrEmpty(bo)){
				String param=bo.getPcode();
				if(bo.getPcode().equals(GlobalConstant.TREE_ROOT)){
					param=bo.getCodeSortId();
				}
				String codeValue=codeManage.generateCodeValue(param);
				bo.setCodeValue(codeValue);
				//bo.setCodeSortId(GlobalConstant.SORT_PRO);
				codeManage.addCodeInfo(bo);
				mv.addObject("msg", "sucess");
			}
		} catch (BaseException e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "添加代码分类信息出错! addCodeInfo! ", e);
		}
		return mv;
	}

	/**
	 * @title:  初始化代码项信息修改页面
	 * @description:初始化代码项信息添加页面
	 * @param : codeId  代码项ID
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView /s9/code/codeItem_upd.jsp
	 */
	@RequestMapping("forInitUpdateCodeInfo.shtml")
	public ModelAndView forInitUpdateCodeInfo(String codeId){
		ModelAndView mv = new ModelAndView("/s9/weixin/menu/wxmenu_upd.jsp");
		try {
			CodeInfo bo = null;
			if(!StringUtil.isNullOrEmpty(codeId)){
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("codeId", codeId);
				bo = codeManage.findCodeInfo(map);
			}
			//查询模版下拉框
			/*Map<String,Object> queryMap=new HashMap<String, Object>();
			List<TemplateBo> listSelect=template.findAllList(queryMap);
			mv.addObject("listSelect",listSelect);*/
			mv.addObject("codeObj", bo);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "初始化代码分类信息修改页面出错! forInit.shtmlUpdateCodeInfo! ", e);
		}
		return mv;
	}
	/**
	 * @title:  修改代码项信息修改页面
	 * @description:修改代码项信息添加页面
	 * @param : bo  代码项信息bean
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: ModelAndView /s9/code/codeItem_upd.jsp
	 */
	@RequestMapping("updCodeInfo.shtml")
	public ModelAndView updCodeInfo(CodeInfo bo){
		ModelAndView mv = new ModelAndView("/s9/weixin/menu/wxmenu_upd.jsp");
		try {
			if(!StringUtil.isNullOrEmpty(bo)){
				codeManage.updCodeInfo(bo);
				mv.addObject("msg", "sucess");
			}
		} catch (BaseException e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "修改代码分类信息出错! updCodeInfo! ", e);
		}
		return mv;
	}

	/**
	 * @title: 删除代码项信息修改页面
	 * @description:删除代码项信息添加页面
	 * @param : codeIds  {代码项ID，代码项ID……}
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 * @return: map
	 */
	@RequestMapping("delCodeInfo.shtml")
	@ResponseBody
	public Map<String,Object> delCodeInfo(String codeIds){
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		try {
			if(!StringUtil.isNullOrEmpty(codeIds)){
				codeManage.delCodeInfo(codeIds);
				jsonMap.put("msg", "sucess");
			}
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "delCodeInfo 删除代码项信息出错! ", e);
		}
		return jsonMap;
	}
	/**
	 * @title: 根据代码id或者代码名称查询字典
	 * @description:根据代码id或者代码名称查询字典
	 * @param : codeId  代码标志
	 * @param :codeName 代码名称
	 * @author: zhengYunFei
	 * @date: 2014-09-15 10:20
	 * @return: map
	 */
	@RequestMapping("getCodeValue.shtml")
	public Map<String,Object> getCodeValue(String codeId,String codeName){
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(!StringUtil.isNullOrEmpty(codeId)){
				map.put("codeId",codeId);
			}
			if(!StringUtil.isNullOrEmpty(codeName)){
				map.put("codeName",codeName);
			}
			jsonMap=codeManage.getCodeValue(map);
		} catch (BaseException e) {
			BaseLog.e(this.getClass(), "根据代码id或者代码名称查询字典出错! ", e);
		}
		return jsonMap;
	}
	/**
	 * @title: 根据代码id或者代码名称查询字典
	 * @description:根据代码id或者代码名称查询字典
	 * @param : codeId  代码标志
	 * @param :codeName 代码名称
	 * @author: zhengYunFei
	 * @date: 2014-09-15 10:20
	 * @return: map
	 */
	@RequestMapping("getCodeValueAjax.shtml")
	@ResponseBody
	public List<CodeInfo> getCodeValueAjax(String codeId,String codeName){
		/* 查询条件*/
		CodeInfo bo = new CodeInfo();
		List<CodeInfo> list=null ;
		if (!StringUtil.isNullOrEmpty(codeId)){
			bo.setCodeSortId(codeId);
		}if(!StringUtil.isNullOrEmpty(codeName)){
			bo.setCodeName(codeName);
		}
		bo.setPcode(GlobalConstant.TREE_ROOT);
		list = codeManage.findCodeInfoList(bo);
		setChildren(list);
		return list;
	}

}
