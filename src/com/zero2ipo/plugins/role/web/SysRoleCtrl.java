/**
 * @(#)ICodeManage.java 07/23/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-23 
 */
package com.zero2ipo.plugins.role.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.role.biz.ISysRole;
import com.zero2ipo.plugins.role.bo.SysRole;
import com.zero2ipo.plugins.role.bo.User;

/**
 * @title :系统角色管理
 * @description :该控制类提供了角色的增删改查功能
 * @author: zhengYunFei
 * @date: 2013-7-23
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleCtrl {
    @Autowired @Qualifier("sysRole")
    public ISysRole sysRole;
    
    /**
     * 
     * @title:初始角色管理页面
     * @description: 跳转至角色管理页面
     * @param: void
     * @author:zhengYunFei
     * @date:2013-7-23
     * @return：ModelAndView /s9/role/roleManage_init.jsp
     */
    @RequestMapping("initSysRole.shtml")
    public ModelAndView initSysRole(){
        return new ModelAndView("/s9/role/roleManage_init.jsp");
    }
    
    /**
     * 
     * @title:初始系统角色列表
     * @description: 将所有的角色信息取出来
     * @param:void
     * @author:zhengYunFei
     * @date:2013-7-23
     * @return：Map
     */
    @RequestMapping("initRoleTree.shtml")
    @ResponseBody
    public Map<String,Object> initRoleTree(String roleId,String roleName){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
        	  Map<String, Object> params = new HashMap<String, Object>();
        	  params.put("roleName", roleId);
        	  params.put("roleName", roleName);
            List<SysRole> roleList = sysRole.findRoleList(params);
            map.put("msg", "success");
            map.put("roleList", roleList);
        } catch (Exception e) {
            map.put("msg", "error");
            BaseLog.e(this.getClass(), "initRoleTree初始化角色信息失败", e);
        }
        return map;
    }
    /**
     * 
     * @title:获取角色基本信息
     * @description: 根据角色id获得角色基本信息
     * @author:zhengYunFei
     * @date:2013-7-24
     * @param roleId 角色标识
     * @return：ModelAndView /s9/role/roleAndUser_init.jsp
     */
    @RequestMapping("initUserOfRole.shtml")
    public ModelAndView initUserOfRole(String roleId){
        ModelAndView mv = new ModelAndView("/s9/role/roleAndUser_init.jsp");
        try {
            SysRole role = sysRole.findRoleById(roleId);
            mv.addObject("role", role);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "initUserOfRole根据角色id获取角色基本信息失败", e);
        }
        return mv;
    }
    /**
     * 
     * @title:添加角色初始化
     * @description: 弹出添加角色的表单
     * @author:zhengYunFei
     * @date:2013-7-25
     * @return：ModelAndView /s9/role/roleManage_add.jsp
     */
    @RequestMapping("forInitAddRole.shtml")
    public ModelAndView forInitAddRole(){
        return new ModelAndView("/s9/role/roleManage_add.jsp");
    }
    /**
     * 
     * @title:添加角色表单提交
     * @description: 将角色表单数据插入数据库
     * @author:zhengYunFei
     * @date:2013-7-25
     * @param role 角色实体
     * @return：ModelAndView /s9/role/roleManage_add.jsp
     */
    @RequestMapping("addRole.shtml")
    public ModelAndView addRole(SysRole role){
        ModelAndView mv = new ModelAndView("/s9/role/roleManage_add.jsp");
        try {
            sysRole.addRole(role);
            mv.addObject("msg", "success");
            mv.addObject("role", role);
        } catch (Exception e) {
            mv.addObject("msg", "error");
            BaseLog.e(this.getClass(), "addRole添加角色失败", e);
        }
        return mv;
    }
    /**
     * 
     * @title:修改角色初始化
     * @description: 跳转至修改角色页面
     * @author:zhengYunFei
     * @date:2013-7-25
     * @param roleId
     * @return：ModelAndView /s9/role/roleManage_upd.jsp
     */
    @RequestMapping("forInitUpdRole.shtml")
    public ModelAndView forInitUpdRole(String roleId){
        ModelAndView mv = new ModelAndView("/s9/role/roleManage_upd.jsp");
        try {
            SysRole role = sysRole.findRoleById(roleId);
            mv.addObject("role", role);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "forInit.shtmlUpdRole修改角色初始化失败", e);
        }
        return mv;
    }
    /**
     * 
     * @title:修改角色
     * @description: 将数据库中对应的角色修改
     * @author:zhengYunFei
     * @date:2013-7-25
     * @param role 角色实体
     * @return：ModelAndView /s9/role/roleManage_upd.jsp
     */
    @RequestMapping("updRole.shtml")
    public ModelAndView updRole(SysRole role){
        ModelAndView mv = new ModelAndView("/s9/role/roleManage_upd.jsp");
        try {
            sysRole.updRole(role);
            mv.addObject("role",role);
            mv.addObject("msg", "success");
        } catch (Exception e) {
            mv.addObject("msg", "error");
            BaseLog.e(this.getClass(), "updRole修改角色信息失败", e);
        }
        return mv;
    }
    
    /**
     * 
     * @title:删除角色信息
     * @description: 将数据库的该条角色信息删除
     * @author:zhengYunFei
     * @date:2013-7-25
     * @param roleId 角色标识
     * @return：Map 执行成功标志
     */
    @RequestMapping("delRole.shtml")
    @ResponseBody
    public Map<String,Object> delRole(String roleId){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            sysRole.delRole(roleId);
            map.put("msg", "success");
        } catch (Exception e) {
            map.put("msg", "error");
            BaseLog.e(this.getClass(), "delRole删除角色信息失败", e);
        }
        return map;
    }
    
    /**
     * 
     * @title:初始化角色用户
     * @description: 根据选中的角色id查找该角色下所有的用户
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param roleId 角色标识
     * @param curNo
     * @param curSize
     * @return：Map<String,Object> 用户总数和当前页用户列表
     */
    @RequestMapping("initUserRole.shtml")
    @ResponseBody
    public Map<String,Object> initUserRole(String roleId, String curNo, String curSize ){
        Map<String,Object> map = new HashMap<String,Object>();
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
            /* 查询条件*/
             List<User> list=null ;
             int allSize=0;
             if (!StringUtil.isNullOrEmpty(roleId)){
                 list = sysRole.findUserByRoleId(roleId,(skip - 1) * max, max);
                 /* 总记录数 */
                 allSize= sysRole.findUserCountByRoleId(roleId); 
             }
             map.put("Rows", list);
             map.put("Total", allSize);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "initUserRole根据角色id初始属于这个角色的所有用户失败", e);
        }
        return map;
    }
    /**
     * 
     * @title:初始化用户列表
     * @description: 将用户添加到角色初始化，查询所有用户
     * @author:zhengYunFei
     * @date:2013-7-29
     * @return：ModelAndView /s9/role/roleAndUser_add.jsp
     */
    @RequestMapping("forInitAddUser")
    public ModelAndView forInitAddUser(String roleId){
        ModelAndView mv = new ModelAndView("/s9/role/roleAndUser_add.jsp");
        mv.addObject("roleId", roleId);
        return mv;
    }
    
    /**
     * @title:查询用户列表
     * @description: 将所有用户查询出来供操作人选择添加到角色中
     * @author:zhengYunFei
     * @date:2013-7-29
     * @param roleId：角色Id
     * @param userNo： 用户编号
     * @param realName： 用户真实姓名
     * @param curNo
     * @param curSize
     * @return：returnMap
     */
    @RequestMapping("findUserList.shtml")
    @ResponseBody
    public Map<String,Object> findUserList(String roleId ,String realName, String userName, String curNo,String curSize){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            /************* 分页处理 ************/
            int skip;
            int max;
            if (StringUtil.isNullOrEmpty(curNo))
                curNo = "0";
            if (StringUtil.isNullOrEmpty(curSize))
                curSize = "10";
            skip = Integer.parseInt(curNo);
            max = Integer.parseInt(curSize);
            /************  分页处理结束 ***********/
            /* 查询条件*/
            Map<String, String> map = new HashMap<String, String>();
            map.put("roleId", roleId) ;
            map.put("userName", userName);
            map.put("userRealName", realName);
            List<User> list=null ;
            int allSize=0;
            list = sysRole.findUsers(map,(skip - 1) * max,max);
            /* 总记录数 */
            allSize= sysRole.findUserCount(map); 
            returnMap.put("Rows", list);
            returnMap.put("Total", allSize);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUserList查询用户列表失败", e);
        }
        return returnMap;
    }
    /**
     * 
     * @title:添加用户与角色的关联数据
     * @description: 将选择的用户与角色信息关系数据存入关系表中
     * @author:zhengYunFei
     * @date:2013-8-1
     * @param roleId 角色标识
     * @param userIds 用户标识
     * @return：map成功标志
     */
    @RequestMapping("addUserToRole.shtml")
    @ResponseBody
    public Map<String,Object> addUserToRole(String roleId, String userIds){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            userIds = userIds.substring(0, userIds.length()-1);
            String[] s=userIds.split(",");
            sysRole.addUserToRole(s,roleId);
            map.put("msg", "success");
        } catch (Exception e) {
            map.put("msg", "error");
            BaseLog.e(this.getClass(), "addUserToRole将用户添加到角色中失败", e);
        }
        return map;
    }
    /**
     * 
     * @title:删除角色关联的用户
     * @description: 将与角色关联的用户关系数据删除
     * @author:zhengYunFei
     * @date:2013-8-1
     * @param roleId 角色标识
     * @param userIds 用户标识
     * @return：map 成功标志
     */
    @RequestMapping("sysRoledel/UserFromRole.shtml")
    @ResponseBody
    public Map<String,Object> delUserFromRole(String roleId, String userIds){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            userIds = userIds.substring(0, userIds.length()-1);
            sysRole.delUserFromRole(userIds.split(","),roleId);
            map.put("msg", "success");
        } catch (Exception e) {
            map.put("msg", "error");
            BaseLog.e(this.getClass(), "delUserFromRole从角色中删除用户失败", e);
        }
        return map;
    }
}
