package com.zero2ipo.cfj.order.webc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.cfj.order.bizc.IOrder;
import com.zero2ipo.cfj.order.bo.Order;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.user.bo.User;

@Controller
@RequestMapping("/order")
public class OrderCtrl extends BaseCtrl{
	@Autowired @Qualifier("order")
	private IOrder orderManage;//管理人
	
	/**
     * @title: 初始管理人页面
     * @author: yangxiaoning
     * @date: 2013-10-15 16:53
     * @return：/s9/vip/vipManage_init.jsp
     */
    @RequestMapping("forInit.shtml")
    public ModelAndView forInit(){
        return new ModelAndView("/s9/order/order_init.jsp");
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
    @RequestMapping("orderInfoList.shtml")
    @ResponseBody
    public Map<String,Object>  orderInfoList(String curNo, String curSize, String productContent){
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
			map.put("productContent", productContent);	//管理人名称 
			int total=0;
        	total=orderManage.queryOrderListCount(map);
        	List<Order> inatitutionsInfo= null;
        	if(total>0){
        		inatitutionsInfo = orderManage.queryOrderInfoList(map, (skip-1)*max, max);
        	}
        	jsonMap.put("Rows", inatitutionsInfo);
    		jsonMap.put("Total", total);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "forLinkTypeinitAjax.shtml:管理人分类信息初始化有误", e);
		}
		return jsonMap;
    }
    
    /**
	 * @title： 增加管理人
	 * @description: 存储管理人信息
	 * @author: yangxiaoning
	 */
    @RequestMapping("addOrder.shtml")
    public ModelAndView addOrder() {
    	ModelAndView mv=new ModelAndView("/s9/order/order_add.jsp");
    	try{
    		//List<Product> list=productManage.queryAll();
    		//mv.addObject("productList",list);
    	}catch (Exception e) {
			BaseLog.e(this.getClass(), "addOrder", e);
			throw new BaseException("addOrder 异常！");
		}
		return mv;
    }
    @RequestMapping("saveOrder.shtml")
    public ModelAndView saveOrder(
			Order order) {
    	ModelAndView mv=new ModelAndView("/s9/order/order_add.jsp");
    	String backInfo = null;
    	try {
			backInfo = orderManage.addOrder(order);
			mv.addObject("backInfo", backInfo);
	    	mv.addObject("order", order);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "saveOrder", e);
			throw new BaseException("saveOrder 异常！");
		}
    	return mv;
    }
    /**
   	 * @title：管理人	删除
   	 * @description: 删除指定管理人标识的系统机构信息,对多个系统管理人信息的删除操作
   	 * @param: userIds   管理人标识（多个用户时用,号隔开）
   	 * @author: yangxn
        * @date: 
   	 * @return： Map 删除机构信息的成功、失败信息
   	 */
       @RequestMapping("delOrder.shtml")
       @ResponseBody
       public Map<String, Object> delOrder(String orderIds) {
   		Map<String,Object> jsonMap = new HashMap<String, Object>();
   		String backInfo = "";
   		//删除前检验是不是有超级管理员
   	    backInfo = orderManage.delOrderById(orderIds);
   	    jsonMap.put("message", backInfo);
       	return jsonMap;
       }
       
       /**
   	 * @title： 管理人信息	修改初始化
   	 * @description: 修改管理人信息,对管理人信息的修改操作
   	 * @param: userId   管理人标识
   	 * @return： ModelAndView 修改机构信息的成功、失败信息
   	 */
       @RequestMapping("updOrderInit.shtml")
       public ModelAndView updOrderInit(String orderId) {
       	ModelAndView mv = new ModelAndView("/s9/order/order_upd.jsp");
   		try {
//   			List<Product> list=productManage.queryProductInfoList(null,0,0);
//    		mv.addObject("productList",list);
   			//用户信息修改初始化
   			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
    		Order order = orderManage.updOrderInit(map);
   		    mv.addObject("order", order);
   		} catch (Exception e) {
   		    BaseLog.e(this.getClass(), "updOrderInit 修改订单查询",e);
   		}
   		return mv;
       }

   	/**
   	 * @title： 管理人信息	修改
   	 * @description: 修改管理人的信息,对管理人信息的修改操作
   	 * @param: Inatitutions   管理人实体类
   	 * @author: yangxn
        * @date: 2014-09-15 10:30
   	 * @return： ModelAndView 修改用户信息的成功、失败信息
   	 */
       @RequestMapping("updOrder.shtml")
       public ModelAndView updOrder(Order order) {
       	ModelAndView mv=new ModelAndView("/s9/order/order_init.jsp");
       	String backInfo = null;
       	try{
       		User user=(User)session.getAttribute("user");
   			backInfo = orderManage.updOrder(order);
       	}catch (Exception e) {
       		BaseLog.e(this.getClass(), "updUser修改管理人信息失败", e);
   		}
   		mv.addObject("backInfo", backInfo);
       	mv.addObject("order", order);
   		return mv;
       }
}
