package com.zero2ipo.cfj.invitationCode.bizc.impl;
import com.zero2ipo.cfj.invitationCode.bizc.IInvitationCode;
import com.zero2ipo.cfj.invitationCode.bo.InvitationCodeBo;
import com.zero2ipo.cfj.invitationCode.bo.InvitationCodeUserBo;
import com.zero2ipo.cfj.message.bizc.ITelMessage;
import com.zero2ipo.cfj.newstation.bizc.INewsTation;
import com.zero2ipo.cfj.user.bizc.IVipManage;
import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.module.entity.invitationCode.InvitationCodeEntity;
import com.zero2ipo.module.entity.invitationCode.InvitationCodeUserEntity;
import com.zero2ipo.plugins.code.biz.ICodeManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
   /**
    * cfjInvitationCode 实体类
    * Tue Dec 30 15:11:39 GMT+08:00 2014 zhengyunfei
    */ 
	@Service("invitationCode")
	public class InvitationCodeImpl implements IInvitationCode{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	@Autowired @Qualifier("telMessage")
	private ITelMessage telMessage;
	@Autowired @Qualifier("newsTation")
	private INewsTation newsTation;
	//自动注入codeManage标准代码管理业务操作接口
	@Autowired @Qualifier("codeManage")
	private ICodeManage codeManage;
	@Autowired @Qualifier("vipManage")
	private IVipManage vipManage;//会员分类业务处理类
	/**
	*新增
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:39 GMT+08:00 2014
	*/
	public String add(InvitationCodeBo bo){
	String backInfo="保存成功";
	try{
		if(!StringUtil.isNullOrEmpty(bo)){
			String ids[]=bo.getUserId().split(",");
			String value=GlobalConstant.MESSAGE_INFO;
	    	String codeValue=value+GlobalConstant.MESSAGE_INVINATION_CODE;
	    	//根据状态status判断发送短信的内容
	    	String content=codeManage.findCodeNameByValue(codeValue);
			for(int i=0;i<ids.length;i++){
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				String id=String.valueOf(baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT));
				bo.setId(id);
				bo.setUserId(ids[i]);
				InvitationCodeEntity entity=InvitationCodeBo.boToEntity(bo);
				entity.setValue(StringUtil.ShortText(ids[i],id));//邀请码
				baseDao.addObject("addInvitationCode", entity);
				/*//发送站内信 
				if("on".equals(bo.getMessageInfo())){
					NewsTationBo newsTationBo=new NewsTationBo();
					newsTationBo.setStatus("0");
					newsTationBo.setTitle("邀请码下发通知");
					newsTationBo.setContent(content.replace("******",entity.getValue()));
					newsTationBo.setUserId(bo.getUserId());
					newsTationBo.setSendTime(StringUtil.getDateNow());
					newsTationBo.setCreateUser("admin");
					newsTation.add(newsTationBo);
				}
				//发送手机短信
				if("on".equals((bo.getTelMessage()))){
					String result = sendMessage.mdgxsend(bo.getMobile().split(",")[i], content.replace("******",entity.getValue()), "", "", "", "");
					TelMessageBo telBo=new TelMessageBo();
					telBo.setContent(content.replace("******",entity.getValue()));
					telBo.setTel(bo.getMobile().split(",")[i]);
					int typyValue=Integer.parseInt(GlobalConstant.MESSAGE_INVINATION_CODE+1);
					telBo.setTypy(typyValue+"");
					telBo.setReturnCode(result.split(",")[0]);
		    		telMessage.add(telBo);
				}*/
			}
			//接受返回结果
		}	
		}catch(Exception e){
		backInfo="保存失败";
		 BaseLog.e(this.getClass(), "add 添加失败", e);
		 throw new BaseException("添加！",e);
	}
	return backInfo;
}
	
	/**
	*新增
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:39 GMT+08:00 2014
	*/
	public String autoAdd(InvitationCodeBo bo){
	String backInfo="保存成功";
	try{
		if(!StringUtil.isNullOrEmpty(bo)){
			String ids[]=bo.getUserId().split(",");
	    	//根据状态status判断发送短信的内容
			for(int i=0;i<ids.length;i++){
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				String id=String.valueOf(baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT));
				bo.setId(id);
				bo.setUserId(ids[i]);
				String code=StringUtil.ShortText(id, ids[i]);
				bo.setValue(code);
				InvitationCodeEntity entity=InvitationCodeBo.boToEntity(bo);
				baseDao.addObject("addInvitationCode", entity);
			}
			//接受返回结果
		}	
		}catch(Exception e){
		backInfo="保存失败";
		 BaseLog.e(this.getClass(), "add 添加失败", e);
		 throw new BaseException("添加！",e);
	}
	return backInfo;
}
	/**
	*修改
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:39 GMT+08:00 2014
	*/
	public String update(InvitationCodeBo bo){
	String backInfo="修改成功";
	String ids[];
	try{
		if(!StringUtil.isNullOrEmpty(bo)){
			ids=bo.getId().split(",");
			for(int i=0;i<ids.length;i++){
				bo.setId(ids[i]);
				InvitationCodeEntity entity=InvitationCodeBo.boToEntity(bo);
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				baseDao.updObject("updInvitationCode", entity);
			}
		}
		
		}catch(Exception e){
		backInfo="保存失败";
		 BaseLog.e(this.getClass(), "update更新失败", e);
		 throw new BaseException("更新！",e);
	}
	return backInfo;
}
	/**
	*删除
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:39 GMT+08:00 2014
	*/
	public String delete(String id){
	String backInfo="删除成功";
	try{
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		Map<String,Object> map=new HashMap<String, Object>();
		 map.put("id",id.split(","));
		 baseDao.delObject("deleteInvitationCode", map);
		}catch(Exception e){
		backInfo="保存失败";
		 BaseLog.e(this.getClass(), "add 添加失败", e);
		 throw new BaseException("添加！",e);
	}
	return backInfo;
}
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:39 GMT+08:00 2014
	*/
	public InvitationCodeBo findById(String id){
		InvitationCodeEntity entity=null;
		InvitationCodeBo bo=null;
	try{
		    baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		    Map<String,Object> map=new HashMap<String, Object>();
		    map.put("id",id);
		    entity=(InvitationCodeEntity)baseDao.findForObject("findInvitationCodeBo", id);
		  bo=InvitationCodeBo.entityToBo(entity);
		}catch(Exception e){
		BaseLog.e(this.getClass(), "findById 查询失败", e);
		 throw new BaseException("查询！",e);
	}
	return bo;
}
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Tue Dec 30 15:11:39 GMT+08:00 2014
	*/
	public List<InvitationCodeUserBo> findAllList(Map<String,Object> map,int curNo, int curSize){
		List<InvitationCodeUserBo> boList=new ArrayList<InvitationCodeUserBo>();
		List<InvitationCodeUserEntity> entityList=new ArrayList<InvitationCodeUserEntity>();
	try{
	    baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
	    entityList=baseDao.findForList("findInvitationCodeUserList", map);

		}catch(Exception e){
		BaseLog.e(this.getClass(), "findById 查询失败", e);
		 throw new BaseException("查询！",e);
	}
	return boList;
}
}

