/**
 * @(#)UserManageImpl.java	10:10 07/08/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-08
 */
package com.zero2ipo.plugins.user.biz.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.user.biz.IuserManage;
import com.zero2ipo.plugins.user.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: 系统用户信息管理业务处理接口实现类
 * @description: 针对系统用户信息管理业务处理管理接口的实现
 * @author： zhengYunFei
 * @date： 2013-07-08 10:10
 */
@Service("userManage")
public class UserManageImpl implements IuserManage {

	//自动注入数据库公共操作接口
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao ;

	/**
	 * @title： 用户信息列表   查询
	 * @description: 根据页面不同查询条件 获取系统用户列表信息
	 * @param: 	map 	用户信息查询条件
	 * @param: 	curNo 	列表查询当前页码
	 * @param: 	curSize  最大记录位置
	 * @author: zhengYunFei
     * @date: 	2013-07-08 10:20
	 * @return： list<user> 用户信息(user)列表
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserInfoList(Map<String, Object> map , int curNo , int curSize) {
		List<User> list = null;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("findUserInfoList", map, curNo, curSize);
		}catch(Exception e){
			e.printStackTrace();
		    BaseLog.e(this.getClass(), "findUserInfoList 系统用户列表信息查询", e);
		    throw new BaseException("根据页面查询条件查询系统用户列表信息出错！",e);
		}
		return list;
	}

	/**
	 * @title： 用户信息列表    查询
	 * @description: 根据用户实体对象获取系统用户列表信息
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:20
	 * @return： list<user> 用户信息(user)列表
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserInfoList(User user) {
		List list = null;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("findUserInfoList", user);
		}catch(Exception e){
			e.printStackTrace();
		    BaseLog.e(this.getClass(), "queryOperationPersonList 查询运维人员列表", e);
		    throw new BaseException("查询运维人员列表出错！",e);
		}
		return list;
	}

	/**
	 * @title： 用户信息列表    总条数查询
	 * @description: 根据不同查询条件 获取系统用户列表总记录条数
	 * @param: map 	用户信息查询条件
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:20
	 * @return： int 不同条件查询的总记录条数
	 */
	public int findUserInfoListCount(Map<String, Object> map) {
		int count=0;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			count = (Integer) baseDao.findForObject("findUserInfoListCount", map);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "queryNewsList ", e);
			throw new BaseException("初始化查询运维人员列表出错！");
		}
		return count;
	}

	/**
	 * @title： 用户信息    新增
	 * @description: 存储系统用户的信息,不同管理人员对用户信息的添加操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： String 新增用户成功、失败信息
	 */
	public String addUser(User user) {
		SqlMapClient client =  baseDao.getSqlMapClient();
		String backInfo= "1";
		try{
			client.startTransaction();//开启事物
			client.startBatch();//开启批处理模式
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			//序列获取用户标识
			user.setUserId(String.valueOf(baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT)));
			//添加用户
			client.insert("addUser", user);

			//添加用户对应角色关系
			String roleId = user.getRoleId();
			if(!StringUtil.isNullOrEmpty(roleId)){
				String[] id = (roleId.substring(0,roleId.length()-1)).split(",");
				for(int i=0;i<id.length;i++){
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("userRoleId", baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT));
					map.put("userId", user.getUserId());
					map.put("roleId", id[i]);
					client.insert("addUserRole", map);
				}
			}
			backInfo= "1";
			client.executeBatch();//执行批处理模式
		}catch(Exception e){
			try {
				client.getCurrentConnection().rollback();
			} catch (SQLException e1) {
				backInfo= "0";
				e.printStackTrace();
			    BaseLog.e(this.getClass(), "事物回滚", e);
			    throw new BaseException("事物回滚！",e);
			}

		}finally{
            try {
                client.commitTransaction();//提交事物
                client.endTransaction();//结束事物
            } catch (Exception e) {
                BaseLog.e(this.getClass(), "addRoleMenu:提交&结束事物异常!", e);
            }
        }
		return backInfo;
	}

	/**
	 * @title： 用户信息修改页面初始化
	 * @description: 对不同系统用户信息的查询修改数据初始化
	 * @param: userId   用户标识
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： user   系统用户信息实体类
	 */
	public User updUserInit(String userId) {
		User user = null;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			user = (User)baseDao.findForObject("findUserById", userId);
		}catch(Exception e){
		    BaseLog.e(this.getClass(), "upSysOperInit 修改运维人员前查询", e);
		    throw new BaseException("修改运维人员前查询出错！",e);
		}
		return user;
	}

	/**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： String 修改用户信息的成功、失败信息
	 */
	public String updUser(User user) {
		SqlMapClient client =  baseDao.getSqlMapClient();
		String backInfo = "0";
		try{
			client.startTransaction();//开启事物
			client.startBatch();//开启批处理模式
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			client.delete("delUserRole", user.getUserId());
			client.update("upSysOper", user);
			//添加用户对应角色关系
			String roleId = user.getRoleId();
			String[] id = (roleId.substring(0,roleId.length()-1)).split(",");
			for(int i=0;i<id.length;i++){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("userRoleId", baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT));
				map.put("userId", user.getUserId());
				map.put("roleId", id[i]);
				client.insert("addUserRole", map);
			}
			client.executeBatch();//执行批处理模式
			backInfo = "1";
		}catch(Exception e){
			e.printStackTrace();
		    BaseLog.e(this.getClass(), "upSysOper 修改运维人员", e);
		    throw new BaseException("修改运维人员出错！",e);
		}finally{
            try {
                client.commitTransaction();//提交事物
                client.endTransaction();//结束事物
            } catch (Exception e) {
				e.printStackTrace();
                BaseLog.e(this.getClass(), "addRoleMenu:提交&结束事物异常!", e);
            }
        }
		return backInfo;
	}

	/**
	 * @title： 用户信息	删除
	 * @description: 删除指定用户标识的系统用户信息,对多个系统用户信息的删除操作
	 * @param: userId   用户标识（多个用户时用,号隔开）
	 * @author: zhengYunFei
     * @date: 2013-07-08 16:40
	 * @return： String 删除用户信息的成功、失败信息
	 */
	@SuppressWarnings("unchecked")
	public String delUserById(String userIds) {
		String backInfo = "0";
		try{
			Map map = new HashMap();
			map.put("userId",userIds.split(","));
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);

			baseDao.delObject("delSysOper", map);
			//删除成功
			backInfo = "1";
		}catch(Exception e){
			backInfo = "0";	//删除失败
		    BaseLog.e(this.getClass(), "delSysOper 删除运维人员", e);
		    throw new BaseException("删除运维人员出错！",e);
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
	 * @return： String 用户状态信息的更新成功、失败信息
	 */
	@SuppressWarnings("unchecked")
	public String updUserStatus(String userIds , String status) {
		String backInfo = "0";
		try{
			Map map = new HashMap();
			map.put("userId",userIds.split(","));
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			if(GlobalConstant.USER_OPER_ENABLE.equals(status)) {
				//用户启用操作
				baseDao.updObject("updUserEnStatus", map);
			}else if(GlobalConstant.USER_OPER_DISABLE.equals(status)) {
				//用户停用操作
				baseDao.updObject("updUserDisStatus", map);
			}
			//操作成功
			backInfo = "1";
		}catch(Exception e){
			backInfo = "0";	//操作失败
		    BaseLog.e(this.getClass(), "updUserStatus 用户信息出错", e);
		    throw new BaseException("启用、停用用户信息出错！",e);
		}
		return backInfo;
	}
	/**
	 * @title :根据用户ID查询用户角色
	 * @param : String userId
	 * @return :List<String>
	 * */
	public List<String> findUserRole(String userId){
		List<String> list = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("returnUserRole", userId);
		} catch (Exception e) {
			 BaseLog.e(this.getClass(), "returnUserRole 根据用户ID查询用户角色出错", e);
			 throw new BaseException("根据用户ID查询用户角色出错！",e);
		}
		return list;
	}

	/**
	 * @title : 删除用户角色
	 * @param : String userId
	 * @return void
	 * */
	public void delUserRole(String userId){
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.delObject("delUserRole", userId);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "delUserRole 删除用户角色出错", e);
			 throw new BaseException("删除用户角色出错！",e);
		}
	}
	/**
	 * @title查询用户
	 * @description 查询所有用户
	 * @author wangli
	 * @return List<user> 用户列表
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserList() {
	      List<User> userList = null;
	        try {
	            userList = baseDao.findForList("findUserList",null);
	        } catch (Exception e) {
	            BaseLog.e(this.getClass(), "findUserList查用户列表失败", e);
	            throw new BaseException("findUserList查询所有用户数据失败");
	        }
	        return userList;
	}
}
