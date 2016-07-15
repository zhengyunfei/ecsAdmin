/**
 * @(#)IuserManage.java	10:10 07/08/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-08 
 */
package com.zero2ipo.plugins.user.biz;

import java.util.List;
import java.util.Map;

import com.zero2ipo.plugins.user.bo.User;

/**
 * @title: 系统用户信息管理业务处理接口定义
 * @description: 针对系统用户信息管理业务处理统一接口的定义类
 * @author： zhengYunFei
 * @date： 2013-07-08 10:10
 */
public interface IuserManage {

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
	public List<User> findUserInfoList(Map<String, Object> map, int curNo, int curSize);
	
	/**
	 * @title： 用户信息列表    查询
	 * @description: 根据用户实体对象获取系统用户列表信息
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:20
	 * @return： list<user> 用户信息(user)列表
	 */
	public List<User> findUserInfoList(User user);
	
	/**
	 * @title： 用户信息列表    总条数查询
	 * @description: 根据不同查询条件 获取系统用户列表总记录条数
	 * @param: map 	用户信息查询条件
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:20
	 * @return： int 不同条件查询的总记录条数
	 */
	public int findUserInfoListCount(Map<String, Object> map);
	
	/**
	 * @title： 用户信息    新增
	 * @description: 存储系统用户的信息,不同管理人员对用户信息的添加操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： String 新增用户成功、失败信息
	 */
	public String addUser(User user);

	/**
	 * @title： 用户信息修改页面初始化
	 * @description: 对不同系统用户信息的查询修改数据初始化
	 * @param: userId   用户标识
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： user   系统用户信息实体类
	 */
	public User updUserInit(String userId);

	/**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： String 修改用户信息的成功、失败信息
	 */
	public String updUser(User user);

	/**
	 * @title： 用户信息	删除
	 * @description: 删除指定用户标识的系统用户信息,对多个系统用户信息的删除操作
	 * @param: userId   用户标识（多个用户时用,号隔开）
	 * @author: zhengYunFei
     * @date: 2013-07-08 16:40
	 * @return： String 删除用户信息的成功、失败信息
	 */
	public String delUserById(String userIds);
	
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
	public String updUserStatus(String userIds, String status);
	
	/**
	 * @title :根据用户ID查询用户角色
	 * @param : String userId
	 * @return :List<String>
	 * */
	public List<String> findUserRole(String userId);
	
	/**
	 * @title : 删除用户角色
	 * @param : String userId
	 * @return void
	 * */
	public void delUserRole(String userId);
	
	/**
	 * @title查询用户
	 * @description 查询所有用户
	 * @author wangli
	 * @return List<user> 用户列表
	 */
    public List<User> findUserList() ;
}
