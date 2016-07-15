package com.zero2ipo.plugins.login.web;

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
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.plugins.login.biz.Ilogin;
import com.zero2ipo.plugins.menu.biz.IsysMenu;
import com.zero2ipo.plugins.menu.bo.SysMenu;
import com.zero2ipo.plugins.user.biz.IuserManage;
import com.zero2ipo.plugins.user.bo.User;
/**
 * @title : 系统用户登录
 * @date: 2013-08-05
 * @author zhengYunFei
 * */
@Controller
@RequestMapping("/login")
public class LoginCtrl extends BaseCtrl {

	@Autowired @Qualifier("login")
	private Ilogin login;
	@Autowired @Qualifier("userManage")
	private IuserManage userManage;
	@Autowired @Qualifier("menu")
	private IsysMenu sysMenu;
	
	/**
	 * @title : 用户登录
	 * @param ：String userName
	 * @param : String pwd
	 * */
	@RequestMapping("userLogin.shtml")
	@ResponseBody
	public Map<String, Object> userLogin(String userName,String pwd){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			User user = login.login(userName);
			if(user !=null && !"".equals(user)){
				if(pwd.equals(user.getUserPwd())){
					//用户状态
					String status = user.getCurStatusCode();
					if(status.equals("02")){
						//当前用户已被禁用!
						jsonMap.put("loginMessage", "06");
					}else if(status.equals("04")){
						//当前用户已被锁定!
						jsonMap.put("loginMessage", "05");
					}else{
						//正常 登录成功!
						session.setAttribute("user", user);
						jsonMap.put("loginMessage", "01");
					}
				}else{
					//密码输入有误!
					jsonMap.put("loginMessage", "04");
				}
			}else{
				//用户名不存在!
				jsonMap.put("loginMessage", "03");
			}
			jsonMap.put("message", "success");
		} catch (Exception e) {
			jsonMap.put("message", "error");
			BaseLog.e(this.getClass(), "userLogin 用户登录有误!", e);
		}
		return jsonMap;
		
	}
	/**
	 * @title : 系统主页面
	 * */
	@RequestMapping("initApplication.shtml")
	public ModelAndView initApplication(String flag){
		ModelAndView mv = new ModelAndView();
		Object user = session.getAttribute("user");
		if(user!=null){
			mv.addObject("user", user);
			mv.addObject("flag",flag);
			mv.setViewName("/s9/application.jsp");
		}else{
			mv.setViewName("redirect:/index.html");
		}
		return mv;
	}
	
	/**
     * @title : 用户登录成功，主页面异步加载用户菜单权限
     * */
	@RequestMapping("findUserMenu.shtml")
	@ResponseBody
	public Map<String, Object> findUserMenu(){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			//1.获得用户
			User user = (User)session.getAttribute("user");
			if(user!=null){
				//2.获得用户对应角色
				List<String> roleList = userManage.findUserRole(user.getUserId());
				//3.获得角色对应菜单权限
				List<String> roleMenuList = sysMenu.findRoleMenu(roleList);
				//4.获得系统菜单
				List<SysMenu> menuList = sysMenu.listSysMenu(false);
				
				//5.用户菜单分类（顶级节点，二级节点）
				List<SysMenu> topMenu = new ArrayList<SysMenu>();
				List<SysMenu> twoMenu = new ArrayList<SysMenu>();
				for(SysMenu menu:menuList){
					for(String menuId:roleMenuList){
						if(menuId.equals(menu.getMenuId())){
							if(GlobalConstant.TOP_SYSMENU_CODE.equals(menu.getPmenuCode())){
								//获得顶级菜单
								topMenu.add(menu);
							}else{
								//获得二级菜单
								twoMenu.add(menu);
							}
						}
					}
				}
				//5.js比较用户权限
				jsonMap.put("message", "success");
				jsonMap.put("topMenu",topMenu);
				jsonMap.put("twoMenu",twoMenu);
			}else{
				jsonMap.put("message", "error");
			}
		} catch (Exception e) {
			jsonMap.put("message", "error");
			BaseLog.e(this.getClass(), "findUserMenu:获得用户菜单权限有误!", e);
		}
		return jsonMap;
	}
	/**
	 * @title : 用户退出
	 * @date ：2013-08-06
	 * @author zhengYunFei
	 * */
	@RequestMapping("userLogout.shtml")
	public ModelAndView userLogout(){
		ModelAndView mv = new ModelAndView();
		session.setAttribute("user", "");
		mv.setViewName("redirect:/index.html");
		return mv;
	}
}
