/**
 * @(#)IuserManage.java	10:10 07/08/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-08 
 */
package com.zero2ipo.plugins.comment.biz;

import java.util.List;
import java.util.Map;

import com.zero2ipo.plugins.comment.bo.Comment;

/**
 * @title: 系统评论信息管理业务处理接口定义
 * @description: 针对系统评论信息管理业务处理统一接口的定义类
 * @author： wangli
 * @date：2015-10-18
 */
public interface ICommentManage {

	/**
	 * @title： 评论信息列表   查询
	 * @description: 根据页面不同查询条件 获取系统评论列表信息
	 * @param: 	map 	评论信息查询条件
	 * @param: 	curNo 	列表查询当前页码
	 * @param: 	curSize  最大记录位置
	 * @author: wangli
	 * @return： list<comment> 评论信息(comment)列表
	 */
	public List<Comment> findCommentInfoList(Map<String, Object> map, int curNo, int curSize);
	
	/**
	 * @title：评论信息列表    查询
	 * @description: 根据评论实体对象获取系统评论列表信息
	 * @param: comment   系统评论信息实体类
	 * @author: wangli
	 * @return： list<comment> 评论信息(comment)列表
	 */
	public List<Comment> findCommentInfoList(Comment comment);
	
	/**
	 * @title： 评论信息列表    总条数查询
	 * @description: 根据不同查询条件 获取系统评论列表总记录条数
	 * @param: map 	评论信息查询条件
	 * @author: wangli
	 * @return： int 不同条件查询的总记录条数
	 */
	public int findCommentInfoListCount(Map<String, Object> map);
	
	/**
	 * @title： 评论信息    新增
	 * @description: 存储系统评论的信息,评论对评论信息的添加操作
	 * @param: comment  系统评论信息实体类
	 * @author: wangli
	 */
	public String addComment(Comment comment);

	/**
	 * @title： 评论信息修改页面初始化
	 * @description: 对不同系统评论信息的查询修改数据初始化
	 * @param: commentId   评论标识
	 * @author: wangli
	 * @return： comment   系统评论信息实体类
	 */
	public Comment updCommentInit(String commentId);

	/**
	 * @title： 评论信息	修改
	 * @description: 修改系统评论的信息,对系统评论信息的修改操作
	 * @param: comment   系统评论信息实体类
	 * @author: wangli
	 * @return： String 修改评论信息的成功、失败信息
	 */
	public String updComment(Comment comment);

	/**
	 * @title： 评论信息	删除
	 * @description: 删除指定评论标识的系统评论信息,对多个系统评论信息的删除操作
	 * @param: commentId   评论标识（多个评论时用,号隔开）
	 * @author: wangli
	 * @return： String 删除评论信息的成功、失败信息
	 */
	public String delCommentById(String commentIds);
	
}
