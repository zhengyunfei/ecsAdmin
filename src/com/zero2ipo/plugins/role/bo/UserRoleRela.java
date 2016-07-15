/**
 * @(#)ICodeManage.java 08/01/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-08-01 
 */
package com.zero2ipo.plugins.role.bo;

import java.io.Serializable;

/**
 * @title :用户角色关系实体类
 * @description :
 * @author: zhengYunFei
 * @date: 2013-8-1
 */
public class UserRoleRela implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public String relaId; //关系标识
    public String roleId; //角色标识
    public String userId; //操作员标识
    public String addUser;//操作人员
    public String addTime;//创建操作时间
    public String getRelaId() {
        return relaId;
    }
    public void setRelaId(String relaId) {
        this.relaId = relaId;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAddUser() {
        return addUser;
    }
    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }
    public String getAddTime() {
        return addTime;
    }
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
    
    
}
