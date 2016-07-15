package com.zero2ipo.plugins.comment.webc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.DateUtil;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.comment.biz.ICommentManage;
import com.zero2ipo.plugins.comment.bo.Comment;
import com.zero2ipo.plugins.user.bo.User;

/**
 * @title： 系统评论信息管理控制类
 * @description: 针对系统会员信息统一管理的控制类。
 * @author： wangli
 * @date： 2015-10-18
 */
@Controller
@RequestMapping("/commentManage")
public class CommentManageCtrl extends BaseCtrl {
	@Autowired
	@Qualifier("commentManage")
	private ICommentManage commentManage;// 分类业务处理类

	/**
	 * @title: 初始评论页面
	 * @description: 初始评论页面
	 * @author: wangli
	 * @return：/s9/comment/commentManage_init.jsp
	 */
	@RequestMapping("forInit.shtml")
	public ModelAndView forInit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/s9/comment/commentManage_init.jsp");
		return mv;
	}

	/**
	 * @title: 查找评论分类信息列表
	 * @description:查找评论信息列表
	 * @param : curNo 分页-页码
	 * @param : curSize 分页-页面加载记录条数
	 * @author: wangli
	 * @return:jsonMap
	 */
	@RequestMapping("commentInfoList.shtml")
	@ResponseBody
	public Map<String, Object> commentInfoList(String curNo, String curSize,
			String curPgn, String status, String commentLevel) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
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
			/************ 分页处理结束 ***********/
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", status); // 有效标识
			map.put("commentLevel", commentLevel); // 评论等级
			List<Comment> commentInfo = commentManage.findCommentInfoList(map,
					(skip - 1) * max, max);
			int total = commentManage.findCommentInfoListCount(map);
			jsonMap.put("Rows", commentInfo);
			jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "forLinkcommentInfoList.shtml:评论信息初始化有误", e);
		}
		return jsonMap;
	}

	/**
	 * @title: 初始评论页面
	 * @description: 初始评论页面
	 * @author: wangli
	 * @return：/s9/comment/commentManage_add.jsp
	 */
	@RequestMapping("commentManageAddInit.shtml")
	public ModelAndView forAddInit() {
		ModelAndView mv = new ModelAndView("/s9/comment/commentManage_add.jsp");
		@SuppressWarnings("unused")
		User oper = (User) session.getAttribute(GlobalConstant.SESSION_USER);
		// 设置添加该用户信息的操作人员
		Comment comment = new Comment();
		// vip.setAddUser(oper.getAddUser());
		// vip.setAddDate(oper.getAddDate());
		mv.addObject("comment", comment);
		return mv;
	}

	/**
	 * @title： 评论信息 新增
	 * @description: 存储系统评论的信息,不同管理人员对评论的添加操作
	 * @param: comment 系统评论信息实体类
	 * @author: wangli
	 * @return： ModelAndView 新增评论成功、失败信息
	 */
	@RequestMapping("addComment.shtml")
	public ModelAndView addComment(Comment comment) {
		ModelAndView mv = new ModelAndView("/s9/comment/commentManage_add.jsp");
		String backInfo = null;
		try {
			comment.setCommentDate(DateUtil.format(new Date()));
			backInfo = commentManage.addComment(comment);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "addComment 添加评论", e);
		}
		mv.addObject("backInfo", backInfo);
		mv.addObject("comment", comment);
		return mv;
	}

	/**
	 * @title： 评论信息 修改初始化
	 * @description: 修改系统评论的信息,对系统评论信息的修改操作
	 * @param: commentId 系统评论标识
	 * @author: wangli
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
	@RequestMapping("updCommentInit.shtml")
	public ModelAndView updCommentInit(String commentId) {
		ModelAndView mv = new ModelAndView("/s9/comment/commentManage_upd.jsp");
		try {
			// 用户信息修改初始化
			Comment comment =commentManage.updCommentInit(commentId);
			mv.addObject("comment", comment);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "updCommentInit 修改评论前查询", e);
		}
		return mv;
	}

	/**
	 * @title： 评论信息 修改
	 * @description: 修改系统评论的信息,对系统评论信息的修改操作
	 * @param: comment 系统评论信息实体类
	 * @author: wangli
	 * @return： ModelAndView 修改评论信息的成功、失败信息
	 */
	@RequestMapping("updComment.shtml")
	public ModelAndView updComment(Comment comment) {
		ModelAndView mv = new ModelAndView("/s9/comment/commentManage_upd.jsp");
		String backInfo = null;
		try {
			backInfo = commentManage.updComment(comment);
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "updComment修改评论信息失败", e);
		}
		mv.addObject("backInfo", backInfo);
		mv.addObject("comment", comment);
		return mv;
	}

	/**
	 * @title： 评论信息 删除
	 * @description: 删除指定评论标识的系统用户信息,对多个系统评论信息的删除操作
	 * @param: commentIds 评论标识（多个评论时用,号隔开）
	 * @author: wangli
	 * @return： Map 删除评论信息的成功、失败信息
	 */
	@RequestMapping("delComment.shtml")
	@ResponseBody
	public Map<String, Object> delComment(String commentIds) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String backInfo = "";
		backInfo = commentManage.delCommentById(commentIds);
		jsonMap.put("message", backInfo);
		return jsonMap;
	}

}