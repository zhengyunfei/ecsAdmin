package com.zero2ipo.cfj.order.bizc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.cfj.order.bizc.IOrder;
import com.zero2ipo.cfj.order.bo.Order;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.module.entity.order.OrderEntity;
@Service("order")
public class OrderManager implements IOrder {
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	@Override
	public List<Order> queryOrderInfoList(Map<String, Object> map, int skip, int max) {
		List<Order> orderInfoList = new ArrayList<Order>();
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		orderInfoList = (List<Order>)baseDao.findForList("findOrderInfoList", map,skip,max);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "queryOrderInfoList 查询列表失败",e);
		}
		return orderInfoList;
	}
	/**
	 * 查询所有联系人
	 * 
	 */
	@Override
	public List<Order> getAllOrders(){
		List<Order> orderInfoList = new ArrayList<Order>();
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
    		List<OrderEntity> entityList = (List<OrderEntity>)baseDao.findForList("findOrderInfoList", null);
    		for (OrderEntity entity:entityList){
    			Order bo = Order.entityToBo(entity);
    			orderInfoList.add(bo);
    		}
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "queryVipInfoList 查询会员列表失败",e);
		}
		return orderInfoList;
	}
	@Override
	public String addOrder(Order order) {
		SqlMapClient client =  baseDao.getSqlMapClient();
		String backInfo= "1";
		try{
			
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			//序列获取联系人标识
			UUID str = UUID.randomUUID();
			order.setOrderId(str.toString());
			//order.setStatus(GlobalConstant.NORMAL);
			OrderEntity entity=Order.boToEntity(order);
			client.insert("addOrder", entity);
			backInfo= "1";
			client.executeBatch();//执行批处理模式
		}catch(Exception e){
			e.printStackTrace();
			backInfo= "0";
		    BaseLog.e(this.getClass(), "addOrder 添加联系人", e);
		    throw new BaseException("添加联系人出错！",e);
		}
		return backInfo;
	}

	@SuppressWarnings("unchecked")
	public String delOrderById(String orderIds) {
		String backInfo = "0";
		try{
			Map map = new HashMap();
			map.put("orderId",orderIds.split(","));
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.delObject("delOrder", map);
			//删除成功
			backInfo = "1";
		}catch(Exception e){
			backInfo = "0";	//删除失败
		    BaseLog.e(this.getClass(), "delOrderById 删除联系人", e);
		    throw new BaseException("删除联系人出错！",e);
		}
		return backInfo;
	}

	@Override
	public Order updOrderInit(Map<String, Object> map) {
		Order order = null;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			OrderEntity entity = (OrderEntity)baseDao.findForObject("findOrderById", map);
			order = Order.entityToBo(entity);
		}catch(Exception e){
		    BaseLog.e(this.getClass(), "updInatitutionsInit 修改联系人前查询", e);
		    throw new BaseException("修改联系人前查询出错！",e);
		}
		return order;
	}

	@Override
	public String updOrder(Order order) {
		SqlMapClient client =  baseDao.getSqlMapClient();
		String backInfo = "0";
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			OrderEntity entity = Order.boToEntity(order);
			client.update("upOrder", entity);
			backInfo = "1";
		}catch(Exception e){
			e.getStackTrace();
		    BaseLog.e(this.getClass(), "updOrder 修改联系人", e);
		    throw new BaseException("修改联系人出错！",e);
		}
		return backInfo;
	}
	@Override
	public int queryOrderListCount(Map<String, Object> map) {
		int count=0;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			count = (Integer)baseDao.findForObject("queryOrderListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

}
