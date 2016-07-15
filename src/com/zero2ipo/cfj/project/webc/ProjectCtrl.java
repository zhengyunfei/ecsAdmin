package com.zero2ipo.cfj.project.webc;

import com.zero2ipo.cfj.project.bizc.IServiceProject;
import com.zero2ipo.cfj.project.bo.ServiceProject;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
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
@RequestMapping("/serviceProject")
public class ProjectCtrl extends BaseCtrl{
	@Autowired @Qualifier("serviceProject")
	private IServiceProject serviceProject;
	
	/**
     * @title: 初始管理人页面
     * @author: yangxiaoning
     * @date: 2013-10-15 16:53
     * @return：/s9/vip/vipManage_init.jsp
     */
    @RequestMapping("forInit.shtml")
    public ModelAndView forInit(){
        return new ModelAndView("/s9/project/project_init.jsp");
    }
    
    /**
     * @title: 查找管理人分类信息列表
     * @description:查找管理人信息列表
     * @param : curNo  分页-页码
     * @param : curSize  分页-页面加载记录条数
     * @author: yangxiaoning
     * @date: 2013-10-15 16:53
     * @return:jsonMap
     */
    @RequestMapping("serviceProjectList.shtml")
    @ResponseBody
    public Map<String,Object>  orderInfoList(String curNo, String curSize){
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
			
			int total=0;
        	total=serviceProject.findAllListCount(map);
        	List<ServiceProject> list= null;
        	if(total>0){
        		list = serviceProject.findAllList(map, (skip-1)*max, max);
        	}
        	jsonMap.put("Rows", list);
    		jsonMap.put("Total", total);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "forLinkTypeinitAjax.shtml:管理人分类信息初始化有误", e);
		}
		return jsonMap;
    }
    
    /**
	 * @title： 增加管理人
	 * @description: 存储管理人信息
	 */
    @RequestMapping("addProjectForInitPage.shtml")
    public ModelAndView addServiceProject() {
    	ModelAndView mv=new ModelAndView("/s9/project/project_add.jsp");
		return mv;
    }
    /**
     * 添加
     * @param project
     * @return
     */
    @RequestMapping("forAddAjax.shtml")
    @ResponseBody
    public Map<String,Object> saveServiceProject(ServiceProject project) {
    	Map<String,Object> result=new HashMap<String,Object>();
    	boolean flg=false;
    	try {
			 serviceProject.add(project);
			flg=true;
		} catch (Exception e) {
			 flg=false;
			e.printStackTrace();
			BaseLog.e(this.getClass(), "saveOrder", e);
			throw new BaseException("saveOrder 异常！");
		}
		result.put("success", flg);
    	return result;
    }
    /**
   	 * @title：管理人	删除
   	 * @description: 删除指定管理人标识的系统机构信息,对多个系统管理人信息的删除操作
   	 * @param: userIds   管理人标识（多个用户时用,号隔开）
   	 * @author: yangxn
        * @date: 
   	 * @return： Map 删除机构信息的成功、失败信息
   	 */
       @RequestMapping("delProject.shtml")
       @ResponseBody
       public Map<String, Object> delOrder(String ids) {
   		Map<String,Object> jsonMap = new HashMap<String, Object>();
   		String backInfo = "";
   		boolean flg=false;
   		try{
   			backInfo = serviceProject.delete(ids);
   			flg=true;
   		}catch(Exception e){
   			flg=false;
   			e.printStackTrace();
   		}
   	   
   	    jsonMap.put("message", backInfo);
   	    jsonMap.put("success",flg);
       	return jsonMap;
       }
       
       /**
   	 * @title： 管理人信息	修改初始化
   	 * @description: 修改管理人信息,对管理人信息的修改操作
   	 * @param: userId   管理人标识
   	 * @return： ModelAndView 修改机构信息的成功、失败信息
   	 */
       @RequestMapping("updOrderInit.shtml")
       public ModelAndView updOrderInit(String id) {
       	ModelAndView mv = new ModelAndView("/s9/project/project_upd.jsp");
   		try {
//   			List<Product> list=productManage.queryProductInfoList(null,0,0);
//    		mv.addObject("productList",list);
   			//用户信息修改初始化
   			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
    		ServiceProject bo = serviceProject.findById(id);
   		    mv.addObject("bo", bo);
   		} catch (Exception e) {
   		    BaseLog.e(this.getClass(), "updOrderInit 修改订单查询",e);
   		}
   		return mv;
       }

   	/**
   	 * @title： 管理人信息	修改
   	 * @description: 修改管理人的信息,对管理人信息的修改操作
   	 * @param: Inatitutions   管理人实体类
   	 * @return： ModelAndView 修改用户信息的成功、失败信息
   	 */
       @RequestMapping("updProject.shtml")
       @ResponseBody
       public Map<String,Object> updProject(ServiceProject bo) {
    	Map<String,Object> resutlMap=new HashMap<String,Object>();
       	String backInfo = null;
       	boolean flg=false;
       	try{
       		User user=(User)session.getAttribute("user");
   			backInfo = serviceProject.update(bo);
   			flg=true;
       	}catch (Exception e) {
       		e.printStackTrace();
       		flg=false;
       		BaseLog.e(this.getClass(), "updUser修改管理人信息失败", e);
   		}
       	resutlMap.put("success", flg);
   		return resutlMap;
       }
}
