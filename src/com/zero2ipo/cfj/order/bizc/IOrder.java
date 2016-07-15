package com.zero2ipo.cfj.order.bizc;
import java.util.List;
import java.util.Map;

import com.zero2ipo.cfj.order.bo.Order;

public interface IOrder {
	/**
	 * @title： 订单信息列表   查询
	 * @description: 根据页面不同查询条件 获取系统用户列表信息
	 * @param: 	map 	订单信息查询条件
	 * @param: 	curNo 	列表查询当前页码
	 * @param: 	curSize  最大记录位置
	 * @author: yangxn
     * @date: 	
	 * @return： list<Inatitutions> 订单信息(Inatitutions)列表
	 */
	public List<Order> queryOrderInfoList(Map<String, Object> map, int i, int max);
	
	/**
	 * @title： 订单信息    新增
	 * @description: 存储订单信息
	 * @param: user   系统用户信息实体类
	 * @author: yangxn
     * @date: 
	 * @return： String 新增订单信息成功、失败信息
	 */
	public String addOrder(Order order);
	
	/**
	 * @title： 订单信息	删除
	 * @description: 删除指定订单信息
	 * @param: userId   订单标识（多个用户时用,号隔开）
	 * @author: yangxn
     * @date: 
	 * @return： String 删除订单信息的成功、失败信息
	 */
	public String delOrderById(String orderIds);
	/**
	 * @title： 订单信息修改页面初始化
	 * @description: 对不同订单信息的查询修改数据初始化
	 * @param: inatitutionsId   订单标识
	 * @author: yangxn
     * @date: 2014-09-15 10:25
	 * @return： InatitutionsEntity   系统用户信息实体类
	 */
	public Order updOrderInit(Map<String, Object> map);
	
	/**
	 * @title： 订单信息修改
	 * @description: 修改订单信息,对订单信息的修改操作
	 * @param: Inatitutions   订单信息实体类
	 * @author: yangxn
     * @date: 2014-09-15 10:25
	 * @return： String 修改订单信息的成功、失败信息
	 */
	public String updOrder(Order order);
	/**
	 * @title： 订单信息  查询
	 * @description: 根据页面不同查询条件 获取系统用户列表信息
	 * @param: 	map 	订单信息查询条件
	 * @param: 	curNo 	列表查询当前页码
	 * @param: 	curSize  最大记录位置
	 * @author: yangxn
     * @date: 	
	 * @return： list<Order> 订单信息(Order)列表
	 */
	public List<Order> getAllOrders();
	
	public int queryOrderListCount(Map<String, Object> map);
}
