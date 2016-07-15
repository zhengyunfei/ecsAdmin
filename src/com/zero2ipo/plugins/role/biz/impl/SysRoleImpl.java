/**
 * @(#)ICodeManage.java 07/23/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-23
 */
package com.zero2ipo.plugins.role.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.plugins.role.biz.ISysRole;
import com.zero2ipo.plugins.role.bo.SysRole;
import com.zero2ipo.plugins.role.bo.User;
import com.zero2ipo.plugins.role.bo.UserRoleRela;

/**
 * @title :角色管理业务处理接口实现类
 * @description :对角色进行增删改查等操作
 * @author: zhengYunFei
 * @date: 2013-7-23
 */
@Service("sysRole")
public class SysRoleImpl implements ISysRole{

    //数据库公共操作接口注入
    @Autowired @Qualifier("baseDao")
    private IBaseDao baseDao;

    /**
     * @title:添加角色
     * @description: 添加一条新的角色
     * @author:zhengYunFei
     * @date:2013-7-23
     * @param role：新添加的角色
     */
    public void addRole(SysRole role) {
        try {
            long roleId = baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT);
            role.setRoleId(String.valueOf(roleId));
            role.setUserNo("test");
            role.setCreateTime(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            baseDao.addObject("addSysRole", role);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "addRole添加角色信息失败", e);
            throw new BaseException("addRole添加角色信息失败");
        }
    }

    /**
     * @title:删除角色
     * @description: 根据角色标识删除角色
     * @author:zhengYunFei
     * @date:2013-7-23
     * @param roleId：角色标识
     */
    public void delRole(String roleId) {
        try {
            baseDao.delObject("delSysRole", roleId);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "delRole删除角色信息失败", e);
            throw new BaseException("delRole删除角色信息失败");
        }
    }

    /**
     * @title:修改角色
     * @description: 修改原有角色的数据
     * @author:zhengYunFei
     * @date:2013-7-23
     * @param role：需要修改的角色
     */
    public void updRole(SysRole role) {
        try {
            baseDao.updObject("updSysRole", role);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "updRole修改角色失败", e);
            throw new BaseException("updRole修改角色失败");
        }
    }

    /**
     * @title:查询角色
     * @description: 查询所有角色的数据
     * @author:zhengYunFei
     * @date:2013-7-23
     * @return：List<SysRole>角色列表
     */
    public List<SysRole> findRoleList(Map<String, Object> map) {
        List<SysRole> roleList = null;
        try {
            roleList = baseDao.findForList("findSysRole",map);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findRoleList查找角色列表失败", e);
            throw new BaseException("findRoleList查询所有角色数据失败");
        }
        return roleList;
    }
    public List<UserRoleRela> findUserRoleListByRoleId(Map<String, Object> queryMap){
        List<UserRoleRela> roleList = null;
        try {
            roleList = baseDao.findForList("findUserRoleList",queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findRoleList查找角色列表失败", e);
            throw new BaseException("findRoleList查询所有角色数据失败");
        }
        return roleList;
    }
    /**
     * @title:查询角色基本信息
     * @description: 根据角色id查询角色基本信息
     * @author:zhengYunFei
     * @date:2013-7-23
     * @param roleId 角色id
     * @return：角色对象
     */
    public SysRole findRoleById(String roleId){
        SysRole role = null;
        try {
            role = (SysRole)baseDao.findForObject("findRoleById", roleId);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findRoleById根据角色id查询角色基本信息失败", e);
        }
        return role;
    }

    /**
     * @title:查询角色用户
     * @description: 根据角色id分页查询数据该角色的用户
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param roleId 角色标识
     * @param curNo 当前页数
     * @param curSize 每页显示条数
     * @return：用户列表
     */
    public List<User> findUserByRoleId(String roleId,int curNo,int curSize){
        List<User> userList = null;
        try {
            userList = (List<User>)baseDao.findForList("findUserByRoleId", roleId, curNo, curSize);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUserByRoleId根据角色id分页查找用户失败", e);
        }
        return userList;
    }

    /**
     * @title:获得该角色下的用户总数
     * @description: 根据角色id查询该角色下的用户总数
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param roleId 角色标识
     * @return：用户总数
     */
    public int findUserCountByRoleId(String roleId){
        int count = 0;
        try {
            count = (Integer)baseDao.findForObject("findUserCountByRoleId", roleId);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUserCountByRoleId根据角色id查找用户总数失败", e);
        }
        return count;
    }

    /**
     * @title:查询用户列表
     * @description: 根据输入的真实姓名，用户名称模糊查询用户列表
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param realName 真实姓名
     * @param userName 用户名称
     * @param curNo
     * @param curSize
     * @return：用户列表
     */
    public List<User> findUsers(Map<String,String> map, int curNo,int curSize){
        List<User> users = null;
        try {
            users = baseDao.findForList("findUsers", map, curNo, curSize);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUsers查询用户列表失败", e);
        }
        return users;
    }

    /**
     * @title:查询用户个数
     * @description: 根据输入的真实姓名，用户名称模糊查询用户个数
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param map 查询条件（包括真实姓名和用户名称）
     * @return：用户个数
     */
    public int findUserCount(Map<String,String> map){
        int count=0;
        try {
            count = (Integer)baseDao.findForObject("findUserCount", map);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUserCount查询用户个数", e);
        }
        return count;
    }

    /**
     * @title:将选中那个的用户插入角色中
     * @description: 将用户和角色的关系插入关系表中S9_SYS_USER_ROLE_RELA
     * @author:zhengYunFei
     * @date:2013-8-1
     * @param userIds 用户标识数组
     * @param roleId：角色标识
     */
    public void addUserToRole(String[] userIds,String roleId){
        try {
            for(int i = 0; i<userIds.length; i++){
                UserRoleRela rela = new UserRoleRela();
                long relaId = baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT);
                rela.setRelaId(String.valueOf(relaId));
                rela.setRoleId(roleId);
                rela.setUserId(userIds[i]);
                rela.setAddUser("test");
               // rela.setAddTime(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                baseDao.addObject("addUserToRole", rela);
            }
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "addUserToRole将用户放入角色中失败", e);
            throw new BaseException("addUserToRole将用户放入角色中失败");
        }
    }

    /**
     * @title:从角色中删除关联的用户
     * @description: 将S9_SYS_USER_ROLE_RELA与roleId这个角色关联的用户关系数据删掉
     * @author:zhengYunFei
     * @date:2013-8-1
     * @param userIds 用户标识数组
     * @param roleId：角色标识
     */
    public void delUserFromRole(String[] userIds, String roleId){
        try {
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("userIds", userIds);
            map.put("roleId", roleId);
            baseDao.delObject("delUserFromRole", map);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "delUserFromRole从角色中删除用户失败", e);
        }
    }

}
