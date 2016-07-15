package com.zero2ipo.cfj.invitationCode.bizc;
import java.util.List;
import java.util.Map;

import com.zero2ipo.cfj.invitationCode.bo.InvitationCodeBo;
import com.zero2ipo.cfj.invitationCode.bo.InvitationCodeUserBo;
   /**
    * cfjInvitationCode 实体类
    * Tue Dec 30 15:11:23 GMT+08:00 2014 zhengyunfei
    */ 


public interface IInvitationCode{
	/**
	*新增
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:23 GMT+08:00 2014
	*/
	public String add(InvitationCodeBo bo);
	/**
	*新增
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:39 GMT+08:00 2014
	*/
	public String autoAdd(InvitationCodeBo bo);
	/**
	*修改
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:23 GMT+08:00 2014
	*/
	public String update(InvitationCodeBo bo);
	/**
	*删除
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:23 GMT+08:00 2014
	*/
	public String delete(String id);
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:23 GMT+08:00 2014
	*/
	public InvitationCodeBo findById(String id);
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:23 GMT+08:00 2014
	*/
	public List<InvitationCodeUserBo> findAllList(Map<String, Object> map, int i, int max);
}

