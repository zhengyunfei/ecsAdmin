package com.zero2ipo.plugins.login.biz.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.plugins.login.biz.Ilogin;
import com.zero2ipo.plugins.user.bo.User;

/**
 * @title : 用户登录实现类
 * @author ： zhengYunFei
 * */
@Service("login")
public class LoginImpl implements Ilogin {

	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	/**
	 * @title : 用户登录
	 * @param : String userName
	 * @return : User user
	 * */
	public User login(String userName){
		User user = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			user = (User)baseDao.findForObject("login", userName);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "LoginImpl_login 用户登录有误", e);
			throw new BaseException("用户登录有误!",e);
		}
		return user;
	}

}
