package com.zero2ipo.plugins.friendLink.web;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.friendLink.biz.IFriendLinkManage;
import com.zero2ipo.plugins.friendLink.bo.FriendLink;
import com.zero2ipo.plugins.friendLink.bo.FriendLinkInfo;
import com.zero2ipo.plugins.user.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/friendLinkManage")
public class FriendLinkManageCtrl  extends BaseCtrl{

	@Autowired @Qualifier("baseDao")
    private IBaseDao baseDao;
	@Autowired @Qualifier("friendLinkManage")
	private IFriendLinkManage friendLinkManage;//友情链接分类业务处理类
    /**
     * @title: 初始友情链接页面
     * @description: 初始友情链接页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLink_init.jsp
     */
    @RequestMapping("/forInit.shtml")
    public ModelAndView forInit(){
        return new ModelAndView("/s9/friendlink/friendLink_init.jsp");
    }
    
    /**
     * @title: 查找友情链接分类信息列表
     * @description:查找友情链接信息列表
     * @param : curNo  分页-页码
     * @param : curSize  分页-页面加载记录条数
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return:jsonMap
     */
    @RequestMapping("/findFriendLinkList")
    @ResponseBody
    public Map<String,Object>  findFriendLinkList(String curNo, String curSize,String typeName){
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
//    		int allSize = friendLinkManage.queryFriendLinkGroupCount(typeName);
        	List<FriendLink> friendLinkList = friendLinkManage.queryFriendLinkGroupList(typeName, (skip-1)*max, max);
        	jsonMap.put("Rows", friendLinkList);
    		jsonMap.put("Total", friendLinkList.size());
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "forLinkTypeinitAjax.shtml:友情链接分类信息初始化有误", e);
		}
		return jsonMap;
    }
    /**
     * @title: 初始友情链接添加分组页面
     * @description: 初始友情链接添加分组页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLink_add.jsp
     */
    @RequestMapping("/initAddFriendLinkGroup")
    public ModelAndView initAddFriendLinkGroup(){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLink_add.jsp");
    	return mv;
    }
    /**
     * @title: 添加友情链接分类信息
     * @description: 添加友情链接分类信息
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLinkItem_add.jsp
     */
    @RequestMapping("/saveAddFriendLinkGroup")
    public ModelAndView saveAddFriendLinkGroup(FriendLink friendLink){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLink_add.jsp");
    	try {
    		if(friendLinkManage.addFriendLinkGroup(friendLink)){
    			mv.addObject("msg", "sucess");
    		}
		} catch (Exception e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "saveFriendLinkInfo:添加友情链接信息有误", e);
		}
    	return mv;
    }
    /**
     * @title: 初始友情链接添加分组页面
     * @description: 初始友情链接添加分组页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLink_add.jsp
     */
    @RequestMapping("/initUpdFriendLinkGroup")
    public ModelAndView initUpdFriendLinkGroup(String typeId,String typeName,String typeDesc){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLink_upd.jsp");
    	mv.addObject("typeId", typeId);
    	mv.addObject("typeName", typeName);
    	mv.addObject("typeDesc", typeDesc);
    	return mv;
    }
    /**
     * @title: 更新友情链接分类信息
     * @description: 更新友情链接分类信息
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLinkItem_add.jsp
     */
    @RequestMapping("/saveUpdFriendLinkGroup")
    public ModelAndView saveUpdFriendLinkGroup(FriendLink friendLink){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLink_upd.jsp");
    	try {
//    		friendLinkInfo.setUserId("2012000000013043");
    		if(friendLinkManage.updFriendLinkGroup(friendLink)){
    			mv.addObject("msg", "sucess");
    		}
		} catch (Exception e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "saveFriendLinkInfo:添加友情链接信息有误", e);
		}
    	return mv;
    }
    /**
     * @title: 删除友情链接分类信息
     * @description: 删除友情链接分类信息
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     */
    @RequestMapping("/delFriendLinkGroup")
    @ResponseBody
    public Map<String,Object> delFriendLinkGroup(String typeIds){
    	Map<String,Object> jsonMap = new HashMap<String, Object>();
    	try {
    		if(friendLinkManage.delFriendLinkGroup(typeIds)){
    			jsonMap.put("msg", "sucess");
    		}
		} catch (Exception e) {
			jsonMap.put("msg", "error");
			BaseLog.e(this.getClass(), "saveFriendLinkInfo:添加友情链接信息有误", e);
		}
    	return jsonMap;
    }
    /**
     * @title: 初始友情链接页面
     * @description: 初始友情链接页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLinkItem_init.jsp
     */
    @RequestMapping("/initFriendLinkInfoList")
    public ModelAndView initFriendLinkInfoList(String typeId){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLinkItem_init.jsp");
    	mv.addObject("typeId", typeId);
    	return mv;
    }
    /**
     * @title: 查找友情链接信息列表
     * @description:查找友情链接信息列表
     * @param : curNo  分页-页码
     * @param : curSize  分页-页面加载记录条数
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return:jsonMap
     */
    @RequestMapping("/findFriendLinkInfoList")
    @ResponseBody
    public Map<String,Object>  findFriendLinkInfoList(String curNo, String curSize,String typeId){
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
//    		int allSize = friendLinkManage.queryFriendLinkInfoCount(typeId);
        	List<FriendLinkInfo> friendLinkInfoList = friendLinkManage.queryFriendLinkInfoList(typeId, (skip-1)*max, max);
        	jsonMap.put("Rows", friendLinkInfoList);
    		jsonMap.put("Total", friendLinkInfoList.size());
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "forLinkTypeinitAjax.shtml:友情链接分类信息初始化有误", e);
		}
		return jsonMap;
    }
    /**
     * @title: 初始友情链接添加页面
     * @description: 初始友情链接添加页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLinkItem_add.jsp
     */
    @RequestMapping("/initSaveFriendLinkInfo")
    public ModelAndView initSaveFriendLinkInfo(String typeId){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLinkItem_add.jsp");
    	mv.addObject("typeId", typeId);
    	return mv;
    }
    /**
     * @title: 添加友情链接信息
     * @description: 添加友情链接信息
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLinkItem_add.jsp
     */
    @RequestMapping("/saveAddFriendLinkInfo")
    public ModelAndView saveAddFriendLinkInfo(FriendLinkInfo friendLinkInfo){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLinkItem_add.jsp");
    	try {
		    User sessionUser = (User) session.getAttribute("user");
    		friendLinkInfo.setUserId(sessionUser.getUserId());
    		if(friendLinkManage.addFriendLinkInfo(friendLinkInfo)){
    			mv.addObject("msg", "sucess");
    		}
		} catch (Exception e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "saveFriendLinkInfo:添加友情链接信息有误", e);
		}
    	mv.addObject("typeId", friendLinkInfo.getTypeId());
    	return mv;
    }
    /**
     * @title: 初始友情链接更新页面
     * @description: 初始友情链接更新页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLinkItem_add.jsp
     */
    @RequestMapping("/initUpdFriendLinkInfo")
    public ModelAndView initUpdFriendLinkInfo(String typeId, String id){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLinkItem_upd.jsp");
    	FriendLinkInfo friendLinkInfo = new FriendLinkInfo();
    	friendLinkInfo = friendLinkManage.queryFriendLinkInfo(id);
    	mv.addObject("friendLinkInfo", friendLinkInfo);
    	mv.addObject("typeId", typeId);
    	return mv;
    }
    /**
     * @title: 更新友情链接信息
     * @description: 更新友情链接信息
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/friendlink/friendLinkItem_add.jsp
     */
    @RequestMapping("/saveUpdFriendLinkInfo")
    public ModelAndView saveUpdFriendLinkInfo(FriendLinkInfo friendLinkInfo){
    	ModelAndView mv = new ModelAndView("/s9/friendlink/friendLinkItem_upd.jsp");
    	try {
    		if(friendLinkManage.updFriendLinkInfo(friendLinkInfo)){
    			mv.addObject("msg", "sucess");
    		}
		} catch (Exception e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "saveFriendLinkInfo:添加友情链接信息有误", e);
		}
    	mv.addObject("typeId", friendLinkInfo.getTypeId());
    	return mv;
    }
    /**
     * @title: 删除友情链接信息
     * @description: 删除友情链接信息
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：jsonMap
     */
    @RequestMapping("/delFriendLinkInfo")
    @ResponseBody
    public Map<String,Object> delFriendLinkInfo(String ids){
    	Map<String,Object> jsonMap = new HashMap<String, Object>();
    	try {
    		if(friendLinkManage.delFriendLinkInfo(ids)){
    			jsonMap.put("msg", "sucess");
    		}
		} catch (Exception e) {
			jsonMap.put("msg", "error");
			BaseLog.e(this.getClass(), "saveFriendLinkInfo:添加友情链接信息有误", e);
		}
    	return jsonMap;
    }
}