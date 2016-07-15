/**
 * @(#)ICodeManage.java 07/23/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-23 
 */
package com.zero2ipo.plugins.role.bo;

import java.io.Serializable;

/**
 * @title :系统角色实体类
 * @description :对应数据库表S9_SYS_ROLE的实体类
 * @author: zhengYunFei
 * @date: 2013-7-23
 */
public class SysRole implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public String roleId;     //角色标识
    public String roleNo;     //角色编码
    public String roleName;   //角色名称
    public String userNo;     //创建人员
    public String createTime; //创建时间
    public String isActive;   //可用标识
    public String roleDesc;   //角色描述
    
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getRoleNo() {
        return roleNo;
    }
    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getUserNo() {
        return userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getIsActive() {
        return isActive;
    }
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    public String getRoleDesc() {
        return roleDesc;
    }
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
    
}
