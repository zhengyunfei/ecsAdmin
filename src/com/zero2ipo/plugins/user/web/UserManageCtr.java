/**
 * @(#)UserManageCtr.java	10:10 07/08/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-08
 */
package com.zero2ipo.plugins.user.web;

import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.plugins.user.biz.IuserManage;
import com.zero2ipo.plugins.user.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title： 系统用户信息管理控制类
 * @description: 针对系统用户信息统一管理的控制类。
 * @author： zhengYunFei
 * @date： 2013-07-09 10:10
 */
@Controller
@RequestMapping("userManage")
public class UserManageCtr extends BaseCtrl {

	//自动注入用户信息管理业务处理操作接口
	@Autowired @Qualifier("userManage")
	private IuserManage userManage ;
	/**
	 * 页面初始化
	 *@title
	 *@date 2014-10-11
	 *@author ZhengYunfei
	 * @return
	 */
	 @RequestMapping("forInit.shtml")
	public ModelAndView forInit(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/s9/user/userManage_init.jsp");
		return mv;
	}
	/**
	 * @title： 用户信息列表   异步数据查询
	 * @description: 根据页面不同查询条件 获取系统用户列表信息
	 * @param: 	map 	用户信息查询条件
	 * @param: 	curNo 	列表查询当前页码
	 * @param: 	curSize  最大记录位置
	 * @author: zhengYunFei
     * @date: 	2013-07-08 10:20
	 * @return： Map<user> 用户信息(user)列表
	 */
    @RequestMapping("findUserInfoListAjax.shtml")
	@ResponseBody
    public Map<String,Object> findUserInfoListAjax(String curNo,String curSize,String curPgn,
    		String userNo,String userName){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
    	try {
			/************* 分页处理开始 ************/
			int skip;
			int max;
			if (curNo == null || "".equals(curNo))
				curNo = "0";
			if (curSize == null || "".equals(curSize))
				curSize = "20";
			if (curPgn == null || "".equals(curPgn))
				curPgn = "1";
			skip = Integer.parseInt(curNo);
			max = Integer.parseInt(curSize);
			request.setAttribute("curNo", curNo);
			request.setAttribute("curSize", curSize);
			/************* 分页处理结束 ************/
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userNo", userNo);		//用户编号
			map.put("userName", userName);	//用户名称
		    List<User> list = userManage.findUserInfoList(map,(skip-1)*max,max);//分页查询数据
		    int countList=userManage.findUserInfoListCount(map);//查询完整数据
		    User sessionUser = (User) session.getAttribute(GlobalConstant.SESSION_USER);
		    if (sessionUser != null){//将登陆id传到页面，不允许删除自身
		    	request.setAttribute("userId", sessionUser.getUserId());
			}
		    jsonMap.put("Rows", list);
		    jsonMap.put("Total", countList);
		} catch (Exception e) {
		    BaseLog.e(this.getClass(),"queryOperationPersonList 查询运维人员列表", e);
		}
		return jsonMap;
    }

	/**
	 * @title： 用户信息    新增初始化
	 * @description: 存储系统用户的信息,不同管理人员对用户信息的添加操作
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： ModelAndView(user) 新增用户信息初始化
	 */
    @RequestMapping("addUserInit.shtml")
    public ModelAndView addUserInit() {
    	ModelAndView mv=new ModelAndView("/s9/user/user_add.jsp");
		User oper = (User) session.getAttribute(GlobalConstant.SESSION_USER);
		//设置添加该用户信息的操作人员
		String expHintDate = "9999-01-01";
		User user = new User() ;
		user.setCurStatusCode("01") ;
		user.setExpHintDate(expHintDate) ;
//		user.setAddUser(oper.getAddUser()) ;
		mv.addObject("user", user) ;
		return mv ;
    }

	/**
	 * @title： 用户信息    新增
	 * @description: 存储系统用户的信息,不同管理人员对用户信息的添加操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： ModelAndView 新增用户成功、失败信息
	 */
    @RequestMapping("addUser.shtml")
    public ModelAndView addUser(User user) {
    	ModelAndView mv=new ModelAndView("/s9/user/user_add.jsp");
    	String backInfo = null;
    	try{
    		user.setAddDate(DateUtil.formatDate(new Date()));
			backInfo = userManage.addUser(user);
    	}catch (Exception e) {
    		BaseLog.e(this.getClass(), "addSysOper 添加运维人员", e);
		}
    	mv.addObject("backInfo", backInfo);
    	mv.addObject("user", user);
		return mv;
    }

	/**
	 * @title： 用户信息	修改初始化
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: userId   系统用户标识
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
    @RequestMapping("updUserInit.shtml")
    public ModelAndView updUserInit(String userId) {
    	ModelAndView mv = new ModelAndView("/s9/user/user_upd.jsp");
		try {
			//用户信息修改初始化
		    User user = userManage.updUserInit(userId);
		    mv.addObject("user", user);
		} catch (Exception e) {
		    BaseLog.e(this.getClass(), "upSysOperInit 修改运维人员前查询",e);
		}
		return mv;
    }

	/**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
    @RequestMapping("updUser.shtml")
    public ModelAndView updUser(User user) {
    	ModelAndView mv=new ModelAndView("/s9/user/user_upd.jsp");
    	String backInfo = null;
    	try{
			backInfo = userManage.updUser(user);

    	}catch (Exception e) {
			e.printStackTrace();
    		BaseLog.e(this.getClass(), "updUser修改用户信息失败", e);
		}
		mv.addObject("backInfo", backInfo);
    	mv.addObject("user", user);
		return mv;
    }

    /**
     * @title : 获得用户角色
     * @param : String userId
     * */
    @RequestMapping("findUserRole.shtml")
    @ResponseBody
    public Map<String, Object> findUserRole(String userId){
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	try {
			List<String> list = userManage.findUserRole(userId);
			jsonMap.put("message", "success");
			jsonMap.put("list", list);
		} catch (Exception e) {
			jsonMap.put("message", "error");
		}
		return jsonMap;
    }

	/**
	 * @title： 用户信息	删除
	 * @description: 删除指定用户标识的系统用户信息,对多个系统用户信息的删除操作
	 * @param: userIds   用户标识（多个用户时用,号隔开）
	 * @author: zhengYunFei
     * @date: 2013-07-08 16:40
	 * @return： Map 删除用户信息的成功、失败信息
	 */
    @RequestMapping("delUser.shtml")
    @ResponseBody
    public Map<String, Object> delUser(String userIds) {
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		String backInfo = "";
		//删除前检验是不是有超级管理员
		backInfo = checkAdmin(userIds);
		if ("".equals(backInfo)) {
		    backInfo = userManage.delUserById(userIds);
		    jsonMap.put("message", backInfo);
		}
    	return jsonMap;
    }

    /**
     * @title : 管理员相关操作验证
     * @description: 删除、启用、停用用户前检验是不是有超级管理员
	 * @param: userIds   用户标识（多个用户时用,号隔开）
     * @return ：String
     */
    public String checkAdmin(String userIds) {
		String backInfo = "";
		String[] ids = userIds.split(",");
		for (String id : ids) {
		    if (GlobalConstant.SUPER_OPER_ID.equals(id)) {
				backInfo = "超级管理员用户不能进行删除、启用、停用操作";
				return backInfo;
		    }
		}
		return backInfo;
    }

    /**
	 * @title： 用户信息	启用和停用操作
	 * @description: 启用和停用指定用户标识的系统用户信息。
	 * 					   启用操作是将用户从锁定状态修改为正常状态
	 *                   停用操作是将用户从正常状态修改为锁定状态
	 * @param: userIds   用户标识（多个用户时用,号隔开）
	 * @param: status    操作状态（1：启用   0：停用）
	 * @author: zhengYunFei
     * @date: 2013-07-10 16:40
	 * @return： Map 用户状态信息的更新成功、失败信息
	 */
    @RequestMapping("updUserStatus.shtml")
    @ResponseBody
    public Map<String, Object> updUserStatus(String userIds , String status) {
    	Map<String,Object> jsonMap = new HashMap<String, Object>();
		String backInfo = "";
		//启用和停用前检验是不是有超级管理员
		backInfo = checkAdmin(userIds);
		if ("".equals(backInfo)) {
		    backInfo = userManage.updUserStatus(userIds, status) ;
		    jsonMap.put("message", backInfo) ;
		}
    	return jsonMap;
    }
}
