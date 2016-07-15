package com.zero2ipo.plugins.login.biz;

import com.zero2ipo.plugins.user.bo.User;


/**
 * @title : 登录接口
 * */
public interface Ilogin {

	/**
	 * @title : 用户登录
	 * @param : String userName
	 * @return : User user
	 * */
	public User login(String userName);
	
}
