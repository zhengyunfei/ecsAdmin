/**
 * @(#)ICodeManage.java 07/23/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-23
 */
package com.zero2ipo.plugins.role.biz;

import com.zero2ipo.plugins.role.bo.SysRole;
import com.zero2ipo.plugins.role.bo.User;
import com.zero2ipo.plugins.role.bo.UserRoleRela;

import java.util.List;
import java.util.Map;

/**
 *
 * @title :角色管理业务处理接口类
 * @description :对角色进行增删改查以及将系统用户从个角色中添加删除等操作
 * @author: zhengYunFei
 * @date: 2013-7-23
 */
public interface ISysRole {
    /**
     *
     * @title:添加角色
     * @description: 添加一条新的角色
     * @author:zhengYunFei
     * @date:2013-7-23
     * @param role：新添加的角色
     */
    public void addRole(SysRole role);
    /**
     *
     * @title:删除角色
     * @description: 根据角色标识删除角色
     * @author:zhengYunFei
     * @date:2013-7-23
     * @param roleId：角色标识
     */
    public void delRole(String roleId);
    /**
     *
     * @title:修改角色
     * @description: 修改原有角色的数据
     * @author:zhengYunFei
     * @date:2013-7-23
     * @param role：需要修改的角色
     */
    public void updRole(SysRole role);
    /**
     *
     * @title:查询角色
     * @description: 查询所有角色的数据
     * @author:zhengYunFei
     * @date:2013-7-23
     * @return：List<SysRole>角色列表
     */
    public List<SysRole> findRoleList(Map<String, Object> map);
    /**
     *
     * @title:查询角色基本信息
     * @description: 根据角色id查询角色基本信息
     * @author:zhengYunFei
     * @date:2013-7-23
     * @param roleId 角色id
     * @return：角色对象
     */
    public SysRole findRoleById(String roleId);
    /**
     *
     * @title:查询角色用户
     * @description: 根据角色id分页查询数据该角色的用户
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param roleId 角色标识
     * @param curNo 当前页数
     * @param curSize 每页显示条数
     * @return：用户列表
     */
    public List<User> findUserByRoleId(String roleId, int curNo, int curSize);
    /**
     *
     * @title:获得该角色下的用户总数
     * @description: 根据角色id查询该角色下的用户总数
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param roleId 角色标识
     * @return：用户总数
     */
    public int findUserCountByRoleId(String roleId);
    /**
     *
     * @title:查询用户列表
     * @description: 根据输入的真实姓名，用户名称模糊查询用户列表
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param map 查询条件（包括真实姓名和用户名称）
     * @param curNo
     * @param curSize
     * @return：用户列表
     */
    public List<User> findUsers(Map<String, String> map, int curNo, int curSize);
    /**
     *
     * @title:查询用户个数
     * @description: 根据输入的真实姓名，用户名称模糊查询用户个数
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param map 查询条件（包括真实姓名和用户名称）
     * @return：用户个数
     */
    public int findUserCount(Map<String, String> map);
    /**
     *
     * @title:将选中那个的用户插入角色中
     * @description: 将用户和角色的关系插入关系表中S9_SYS_USER_ROLE_RELA
     * @author:zhengYunFei
     * @date:2013-8-1
     * @param userIds 用户标识数组
     * @param roleId：角色标识
     */
    public void addUserToRole(String[] userIds, String roleId);
    /**
     *
     * @title:从角色中删除关联的用户
     * @description: 将S9_SYS_USER_ROLE_RELA与roleId这个角色关联的用户关系数据删掉
     * @author:zhengYunFei
     * @date:2013-8-1
     * @param userIds 用户标识数组
     * @param roleId：角色标识
     */
    public void delUserFromRole(String[] userIds, String roleId);

    public List<UserRoleRela> findUserRoleListByRoleId(Map<String, Object> queryMap);
}
