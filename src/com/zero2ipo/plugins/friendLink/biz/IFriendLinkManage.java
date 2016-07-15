package com.zero2ipo.plugins.friendLink.biz;

import java.util.List;

import com.zero2ipo.plugins.friendLink.bo.FriendLink;
import com.zero2ipo.plugins.friendLink.bo.FriendLinkInfo;


/**
 * @description :友情链接管理接口
 * @author: zhengYunFei
 * @date: 2013-10-15 16:53
 */
public interface IFriendLinkManage {
	/**
	 * @description: 查询友情链接列表
	 * @param typeName：
	 * @param  skip：页码
	 * @param  max ：最大值
	 * @return：友情链接列表
	 */
	public List<FriendLink> queryFriendLinkGroupList(String typeName, int skip, int max);
	/**
	 * @description:根据链接分类名称和链接分类id查询友情链接分组个数
	 * @param map：该参数包括友情链接类型的typeid和友情类型的name值
	 */
	public Integer queryFriendLinkGroupCount(String typeName);
	/**
	 * @description: 添加友情链接分组信息
	 * @param friendLink
	 * @return：flag
	 */
	public Boolean addFriendLinkGroup(FriendLink friendLink);
	/**
	 * @description: 更新友情链接分组信息
	 * @param friendLink
	 * @return：flag
	 */
	public Boolean updFriendLinkGroup(FriendLink friendLink);
	/**
	 * @description: 删除友情链接分组信息
	 * @param typeId
	 * @return：flag
	 */
	public Boolean delFriendLinkGroup(String typeId);
	/**
	 * @description: 查询友情链接分组列表
	 * @param friendLinkType：
	 * @param  skip：页码
	 * @param  max ：最大值
	 * @return：友情链接列表
	 */
	public List<FriendLinkInfo> queryFriendLinkInfoList(String typeId, int skip, int max);
	/**
	 * @description:根据链接分类名称和链接分类id查询友情链接个数
	 * @param map：该参数包括友情链接类型的typeid和友情类型的name值
	 */
	public Integer queryFriendLinkInfoCount(String typeId);
	/**
	 * @description: 查询友情链接信息
	 * @param typeId
	 * @return：friendLink
	 */
	public FriendLinkInfo queryFriendLinkInfo(String id);
	/**
	 * @description: 添加友情链接信息
	 * @param friendLinkInfo
	 * @return：flag
	 */
	public Boolean addFriendLinkInfo(FriendLinkInfo freinFriendLinkInfo);
	/**
	 * @description: 更新友情链接信息
	 * @param friendLinkInfo
	 * @return：flag
	 */
	public Boolean updFriendLinkInfo(FriendLinkInfo friendLinkInfo);
	/**
	 * @description: 删除友情链接信息
	 * @param id
	 * @return：flag
	 */
	public Boolean delFriendLinkInfo(String id);
}