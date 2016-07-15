package com.zero2ipo.cfj.user.bizc;

import java.util.List;
import java.util.Map;

import com.zero2ipo.cfj.user.bo.OrganizationUserInfoBo;
import com.zero2ipo.cfj.user.bo.UserBo;
import com.zero2ipo.cfj.user.bo.UserInfoBo;
import com.zero2ipo.cfj.user.bo.Users;

/**
 * @description :友会员管理接口
 * @author: zhengYunFei
 * @date: 2013-10-15 16:53
 */
public interface IVipManage {
	/**
	 * @description: 查询会员列表
	 * @param typeName：
	 * @param  skip：页码
	 * @param  max ：最大值
	 * @return：会员列表
	 */
	public List<Users> queryVipInfoList(Map<String, Object> map, int skip, int max);
	/**
	 * @title： 用户信息    新增
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： String 新增用户成功、失败信息
	 */
	public String addUser(UserBo user);
	/**
	 * @title： 用户信息修改页面初始化
	 * @description: 对不同系统用户信息的查询修改数据初始化
	 * @param: userId   用户标识
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： user   系统用户信息实体类
	 */
	public Users updUserInit(String userId);
	/**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： String 修改用户信息的成功、失败信息
	 */
	public String updUser(Users bo) ;
    /**
     * @title: 会员管理密码重置
     * @description: 会员管理密码重置
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：Boolean
     */
	public Boolean updResetPwd(UserBo vipInfo);
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
	 * @title：批量操作
	 * @param: userId   用户标识（多个用户时用,号隔开）
	 * @author: zhengYunFei
     * @date: 2013-07-08 16:40
	 */
	public String updSelectAllUser(Map<String, Object> queryMap);
	public List<UserBo> queryVipInfoList(Map<String, Object> map);
	public int queryVipInfoListCount(Map<String, Object> map);
	/**
	 * 机构信息管理
	 * @param map
	 * @return
	 */
	public List<UserBo> queryOrganInfoList(Map<String, Object> map, int skip, int max);
	public String updUserInfo(UserInfoBo user);
	public String updOrganInfo(OrganizationUserInfoBo organ);
	public UserInfoBo findUserInfoDetail(String userId);
	public OrganizationUserInfoBo findOrganInfoDetail(String userId);
}