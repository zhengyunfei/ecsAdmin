package com.zero2ipo.plugins.comment.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.plugins.comment.biz.ICommentManage;
import com.zero2ipo.plugins.comment.bo.Comment;

/**
 * @title: 系统评论信息管理业务处理接口定义
 * @description: 针对系统评论信息管理业务处理统一接口的定义类
 * @author： wangli
 * @date：2015-10-18
 */
@Service("commentManage")
public class CommentManageImpl implements ICommentManage {
	
	// 自动注入数据库公共操作接口
	@Autowired
	@Qualifier("baseDao")
	private IBaseDao baseDao;
	
	/**
	 * @title： 评论信息    新增
	 * @description: 存储系统评论的信息,会员对评论信息的添加操作
	 * @param: comment  系统评论信息实体类
	 * @author: wangli
	 */
	@Override
	public String addComment(Comment comment) {
		String backInfo = "1";
		try {
				// 序列获取用户标识
			long commentId = baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT);
			comment.setCommentId(String.valueOf(commentId));
			// 添加用户
			baseDao.addObject("addComment", comment);
		} catch (Exception e) {
			backInfo = "0";
			BaseLog.e(this.getClass(), "addComment添加评论信息失败", e);
			throw new BaseException("addComment添加评论信息失败");
		}
		return backInfo;
	}

	/**
	 * @title： 评论信息	删除
	 * @description: 删除指定评论标识的系统评论信息,对多个系统评论信息的删除操作
	 * @param: commentId   评论标识（多个评论时用,号隔开）
	 * @author: wangli
	 * @return： String 删除评论信息的成功、失败信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String delCommentById(String commentIds) {
		String backInfo = "0";
		try {
			Map map = new HashMap();
			map.put("commentId", commentIds.split(","));
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.delObject("delComment", map);
			// 删除成功
			backInfo = "1";
		} catch (Exception e) {
			backInfo = "0"; // 删除失败
			BaseLog.e(this.getClass(), "delComment 删除评论", e);
			throw new BaseException("删除评论出错！", e);
		}
		return backInfo;
	}
	/**
	 * @title： 评论信息列表   查询
	 * @description: 根据页面不同查询条件 获取系统评论列表信息
	 * @param: 	map 	评论信息查询条件
	 * @param: 	curNo 	列表查询当前页码
	 * @param: 	curSize  最大记录位置
	 * @author: wangli
	 * @return： list<comment> 评论信息(comment)列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findCommentInfoList(Map<String, Object> map,
			int curNo, int curSize) {
		List<Comment> list = null;
		try {
			list = baseDao.findForList("findCommentInfoList", map, curNo, curSize);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findCommentInfoList系统评论列表信息查询失败", e);
		}
		return list;
	}
	/**
	 * @title：评论信息列表    查询
	 * @description: 根据评论实体对象获取系统评论列表信息
	 * @param: comment   系统评论信息实体类
	 * @author: wangli
	 * @return： list<comment> 评论信息(comment)列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findCommentInfoList(Comment comment) {
		List list = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			list = baseDao.findForList("findCommentInfoList", comment);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "findCommentInfoList查询评论列表", e);
			throw new BaseException("查询评论列表出错！", e);
		}
		return list;
	}
	
	/**
	 * @title： 评论信息列表    总条数查询
	 * @description: 根据不同查询条件 获取系统评论列表总记录条数
	 * @param: map 	评论信息查询条件
	 * @author: wangli
	 * @return： int 不同条件查询的总记录条数
	 */
	@Override
	public int findCommentInfoListCount(Map<String, Object> map) {
		int count = 0;
		try {
			count = (Integer) baseDao
					.findForObject("findCommentInfoListCount", map);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findCommentInfoListCount查询评论个数", e);
		}
		return count;
	}

	/**
	 * @title： 评论信息	修改
	 * @description: 修改系统评论的信息,对系统评论信息的修改操作
	 * @param: comment   系统评论信息实体类
	 * @author: wangli
	 * @return： String 修改评论信息的成功、失败信息
	 */
	@Override
	public String updComment(Comment comment) {
		SqlMapClient client = baseDao.getSqlMapClient();
		String backInfo = "0";
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			client.update("updComment", comment);
			client.executeBatch();// 执行批处理模式
			backInfo = "1";
		} catch (Exception e) {
			 backInfo = "0";
			BaseLog.e(this.getClass(), "updComment 修改评论信息", e);
			throw new BaseException("修改评论信息出错！", e);
		}
		return backInfo;
	}

	/**
	 * @title： 评论信息修改页面初始化
	 * @description: 对不同系统评论信息的查询修改数据初始化
	 * @param: commentId   评论标识
	 * @author: wangli
	 * @return： comment   系统评论信息实体类
	 */
	@Override
	public Comment updCommentInit(String commentId) {
		Comment comment = null;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			comment = (Comment) baseDao.findForObject("findCommentById", commentId);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "findCommentById 修改评论前查询", e);
			throw new BaseException("修改评论前查询出错！", e);
		}
		return comment;
	}

}
